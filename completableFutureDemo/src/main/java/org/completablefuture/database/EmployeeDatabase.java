package org.completablefuture.database;

import org.completablefuture.dto.Employee;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class EmployeeDatabase {
    public static List<Employee> fetchEmployees(){
        ObjectMapper objectMapper=new ObjectMapper();
        return objectMapper.readValue(new File("completableFutureDemo\\employees.json"),new TypeReference<List<Employee>>(){

        });
    }
}
