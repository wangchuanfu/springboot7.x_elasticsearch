package com.j1.stream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by wangchuanfu on 20/10/9.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person implements Comparable<Person> {



    private Integer id;
    private String name;
    private String sex;
    private Integer age;
    @Override
    public int compareTo(Person o) {
        return this.age - o.getAge();
    }


    public static void main(String[] args) {
        List<Person> list = new ArrayList<>();
        Person p1 = new Person(1,"xiaoming","man",23);
        Person p2 = new Person(2,"xiaohong","woman",27);
        Person p3 = new Person(1,"xiaolei","man",24);
        Person p4 = new Person(1,"xiaoli","man",26);
        list.add(p1);
        list.add(p2);
        list.add(p3);
        list.add(p4);

        List<Person> collect1 = list.stream().sorted().collect(Collectors.toList());


      //  System.out.print(collect1);
        List<Person> collect = list.stream()
                .sorted((Comparator.comparingInt(Person::getAge)).reversed())
                .collect(Collectors.toList());
       // System.out.println(collect);
        Set<Person> collect2= list.stream().limit(4).collect(Collectors.toSet());
        Map<Integer, Person> map = list.stream().collect(Collectors.toMap(Person::getId, Function.identity()));
        Map<Integer, String> collect3 = list.stream().collect(Collectors.toMap(Person::getId, Person::getName));


        List<Person> data = new ArrayList<>();
        Person p6 = new Person(1,"xiaoming","man",24);
        Person p5 = new Person(5,"xiaomeng","man",23);
        data.add(p1);
        data.add(p2);
        data.add(p3);
        data.add(p4);
        data.add(p5);
        data.add(p6);
        Map<Integer, Person> collect6 = data.stream().collect(Collectors.toMap(Person::getId, Function.identity(), BinaryOperator.maxBy(Comparator.comparing(Person::getAge))));

        System.out.print(collect6);
    }
}
