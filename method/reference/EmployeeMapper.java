package com.method.reference;

public class EmployeeMapper {

    public static EmployeeDo convert(Employee employee){
        EmployeeDo empDo = new EmployeeDo();
        empDo.setId(employee.getId());
        empDo.setName(employee.getNema());
        empDo.setSalary(employee.getSalary());
        return empDo;
    }

    public  EmployeeDo convertInstance(Employee employee){
        EmployeeDo empDo = new EmployeeDo();
        empDo.setId(employee.getId());
        empDo.setName(employee.getNema());
        empDo.setSalary(employee.getSalary());
        return empDo;
    }
}
