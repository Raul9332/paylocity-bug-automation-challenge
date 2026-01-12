package com.raul.paylocity.tests.api;

import com.raul.paylocity.api.client.EmployeesClient;
import com.raul.paylocity.api.models.Employee;
import com.raul.paylocity.config.Config;
import com.raul.paylocity.utils.DataFactory;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.UUID;

public class EmployeesApiTests {

    private final EmployeesClient client =
            new EmployeesClient(Config.get("api.baseUrl"), Config.get("api.auth"));

    @Test
    public void getEmployees_shouldReturn200() {
        Response res = client.getAll();

        Assert.assertEquals(res.statusCode(), 200, res.asString());
        Assert.assertTrue(res.asString() != null && !res.asString().isBlank(), "Response body is empty");

        // Optional: valida que sea JSON
        Assert.assertTrue(res.contentType().contains(ContentType.JSON.toString()), "Expected JSON response");
    }

    @Test
    public void createUpdateDeleteEmployee_happyPath() {
        // CREATE
        Employee e = DataFactory.newEmployee(2);

        // Asegura required fields (por si DataFactory falla)
        Assert.assertNotNull(e.username, "username is required");
        Assert.assertNotNull(e.firstName, "firstName is required");
        Assert.assertNotNull(e.lastName, "lastName is required");

        Response create = client.create(e);

        if (create.statusCode() >= 400) {
            // Debug pro: imprime todo si falla
            create.then().log().all();
        }

        Assert.assertEquals(create.statusCode(), 200, create.asString());

        Employee created = create.as(Employee.class);

        // El OpenAPI dice que id es UUID (format uuid)
        Assert.assertNotNull(created.id, "Expected created.id not null");
        assertIsUuid(created.id);

        Assert.assertEquals(created.firstName, e.firstName, "firstName mismatch");
        Assert.assertEquals(created.lastName, e.lastName, "lastName mismatch");
        Assert.assertEquals(created.username, e.username, "username mismatch");

        // UPDATE (PUT /api/Employees según el contrato)
        created.dependants = 3;

        Response update = client.update(created);

        if (update.statusCode() >= 400) {
            update.then().log().all();
        }

        Assert.assertEquals(update.statusCode(), 200, update.asString());

        // VERIFY by ID
        Response getById = client.getById(created.id);

        if (getById.statusCode() >= 400) {
            getById.then().log().all();
        }

        Assert.assertEquals(getById.statusCode(), 200, getById.asString());

        Employee fetched = getById.as(Employee.class);
        Assert.assertEquals(fetched.dependants, created.dependants, "dependants not updated");

        // DELETE
        Response del = client.delete(created.id);

        if (del.statusCode() >= 400) {
            del.then().log().all();
        }

        Assert.assertEquals(del.statusCode(), 200, del.asString());
    }

    @Test
    public void createEmployee_withNegativeDependants_shouldReturn4xx() {
        Employee e = DataFactory.newEmployee(-1);

        Response res = client.create(e);

        // Si el backend está buggy y permite negativos, este test lo detecta
        if (res.statusCode() < 400) {
            res.then().log().all();
        }

        Assert.assertTrue(
                res.statusCode() >= 400,
                "Expected 4xx for negative dependants. Actual: " + res.statusCode() + " body=" + res.asString()
        );
    }

    private static void assertIsUuid(String value) {
        try {
            UUID.fromString(value);
        } catch (Exception ex) {
            Assert.fail("Expected UUID but got: " + value);
        }
    }
}
