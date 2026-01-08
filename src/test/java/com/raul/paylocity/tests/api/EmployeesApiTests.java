package com.raul.paylocity.tests.api;

import com.raul.paylocity.api.client.EmployeesClient;
import com.raul.paylocity.api.models.Employee;
import com.raul.paylocity.config.Config;
import com.raul.paylocity.utils.DataFactory;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class EmployeesApiTests {

    private final EmployeesClient client =
            new EmployeesClient(Config.get("api.baseUrl"), Config.get("api.auth"));

    @Test
    public void getEmployees_shouldReturn200() {
        Response res = client.getAll();
        Assert.assertEquals(res.statusCode(), 200);
        Assert.assertTrue(res.asString() != null && !res.asString().isBlank());
    }

    @Test
    public void createUpdateDeleteEmployee_happyPath() {
        // Create
        Employee e = DataFactory.newEmployee(2);
        Response create = client.create(e);
        Assert.assertEquals(create.statusCode(), 200, create.asString());

        Employee created = create.as(Employee.class);
        Assert.assertNotNull(created.id, "Expected created.id not null");
        Assert.assertEquals(created.firstName, e.firstName);
        Assert.assertEquals(created.lastName, e.lastName);

        // Update dependants
        created.dependants = 3;
        Response update = client.update(created);
        Assert.assertEquals(update.statusCode(), 200, update.asString());

        // Verify by id
        Response getById = client.getById(created.id);
        Assert.assertEquals(getById.statusCode(), 200, getById.asString());

        // Delete
        Response del = client.delete(created.id);
        Assert.assertEquals(del.statusCode(), 200, del.asString());
    }

    @Test
    public void createEmployee_withNegativeDependants_shouldFail_orIsBug() {
        Employee e = DataFactory.newEmployee(-1);

        Response res = client.create(e);


        Assert.assertTrue(
                res.statusCode() >= 400,
                "Expected 4xx for negative dependants. Actual: " + res.statusCode() + " body=" + res.asString()
        );
    }
}
