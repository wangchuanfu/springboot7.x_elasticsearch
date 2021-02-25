package com.j1.stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeTest {

    List<Employee> employees = Arrays.asList(

            new Employee("张三", 12, 6666),
            new Employee("李四", 22, 4444),
            new Employee("王五", 18, 8888),
            new Employee("王五", 18, 8888),
            new Employee("王五", 18, 8888),

            new Employee("赵六", 38, 9999));

    @Test
    public void test1() {
        //employees.stream().map(Employee::getAge).forEach(System.out::println);
        //  employees.stream().forEach(employee -> employee.getAge());
        List<Integer> ageList = employees.stream()
                .map(employee -> employee.getAge() + 100)
                .collect(Collectors.toList());
        ageList.stream().forEach(System.out::println);
        System.out.println("================================================");
        employees.stream().forEach(employee -> {
            employee.setAge(99);
            employee.setName("小张");
        });
        employees.stream().forEach(System.out::println);
    }


}
