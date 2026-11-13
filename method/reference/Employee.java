package com.method.reference;

public class Employee {
    private int id;
    private String name;
    private int salary;

    public Employee() {
    }

    public void getEmployeeInfo() {
        System.out.println("Method In Employee Class");
    }

    public Employee(int id, String name, int salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNema() {
        return name;
    }

    public void setNema(String name) {
        this.name = name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String test() {
        return "test";
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }
}
