package com.raul.paylocity.utils;

import com.raul.paylocity.api.models.Employee;

import java.util.UUID;

public final class DataFactory {
    private DataFactory() {}

    public static Employee newEmployee(int dependants) {
        String suffix = UUID.randomUUID().toString().substring(0, 8);
        String username = "raul_" + suffix;
        String first = "Raul" + suffix;
        String last = "DeLeon" + suffix;
        return new Employee(username, first, last, dependants);
    }
}
