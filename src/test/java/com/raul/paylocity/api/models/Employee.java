package com.raul.paylocity.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Employee {
    public String id;
    public String username;
    public String firstName;
    public String lastName;
    public Integer dependants;

    public Float gross;
    public Float benefitsCost;
    public Float net;

    public Employee() {}

    public Employee(String username, String firstName, String lastName, int dependants) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dependants = dependants;
    }
}
