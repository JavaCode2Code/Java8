package com.method.reference;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MethodReferenceDemo {
    public static void main(String[] args) {

        EmployeeService service = new EmployeeService();


      //annoymouse
        EmployeeManager empManager = () -> new Employee();
       // empManager.getEmployee().getEmployeeInfo();
        //constructor reference



        EmployeeManager empManager2 = Employee::new;
       // empManager2.getEmployee().getEmployeeInfo();




      service.loadEmpFromDb().stream().map(Employee::getId).map(ArrayList::new).forEach(System.out::println);


        //annonymous approach
      /*  service.loadEmpFromDb().forEach(new Consumer<Employee>() {
            @Override
            public void accept(Employee employee) {
                //     System.out.println(employee);
            }
        });*/
        //Lambda Expression approach
   /*     service.loadEmpFromDb().forEach((employee) -> {
            System.out.println(employee);
        });*/

        //method reference with custom type
        //service.loadEmpFromDb().forEach((MethodReferenceDemo::print));

        //method reference predefine method

        //  service.loadEmpFromDb().forEach((System.out::println));
        List<EmployeeDo> empDOs = service.
                loadEmpFromDb().stream().map(new Function<Employee, EmployeeDo>() {

                    @Override
                    public EmployeeDo apply(Employee employee) {
                        EmployeeDo empDos = new EmployeeDo();
                        empDos.setId(employee.getId());
                        empDos.setName(employee.getNema());
                        empDos.setSalary(employee.getSalary());
                        return empDos;
                    }
                }).collect(Collectors.toList());

        // System.out.println(empDOs);


//lambda Expression
        List<EmployeeDo> empDO2 = service.
                loadEmpFromDb().stream().map((employee) -> {
                            EmployeeDo empDos = new EmployeeDo();
                            empDos.setId(employee.getId());
                            empDos.setName(employee.getNema());
                            empDos.setSalary(employee.getSalary());
                            return empDos;
                        }
                ).collect(Collectors.toList());

        // System.out.println(empDO2);

        //Method Reference static method
        List<EmployeeDo> empDO3 = service.
                loadEmpFromDb().stream().map(EmployeeMapper::convert

                ).collect(Collectors.toList());

        //  System.out.println(empDO3);


        //Method Reference instance method
        EmployeeMapper mapper = new EmployeeMapper();
        List<EmployeeDo> empDO4 = service.
                loadEmpFromDb().stream().map(mapper::convertInstance

                ).collect(Collectors.toList());

        //System.out.println(empDO4);


        //here getName method is not static but we can able call as static

        //because the method does not contain any argument it is behave like static method
        List<String> nameList = service.loadEmpFromDb().stream().map(Employee::getNema).collect(Collectors.toList());
        // System.out.println(nameList);

        List<String> nameList2 = service.loadEmpFromDb().stream().map(Employee::test).collect(Collectors.toList());

        // System.out.println(nameList2);


    }


    public static void print(Employee employee) {
        System.out.println(employee);
//Constructor Reference
        //  EmployeeDo::new
    }
}

