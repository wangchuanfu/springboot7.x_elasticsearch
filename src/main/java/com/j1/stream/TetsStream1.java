package com.j1.stream;


import com.google.common.collect.Lists;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class TetsStream1 {


    List<Employee> employees = Arrays.asList(

            new Employee("张三", 12, 6666),
            new Employee("李四", 22, 4444),
            new Employee("王五", 18, 8888),
            new Employee("王五", 18, 8888),
            new Employee("王五", 18, 8888),

            new Employee("赵六", 38, 9999));

    @Test
    public void test1() {
        List<String> proNames = Arrays.asList(new String[]{"Ni", "Hao", "Lambda"});
        // 当lambda表达式只包含一条语句时，可以省略大括号、return和语句结尾的分号
        List<String> list1 = proNames.stream().map(name -> name.toLowerCase()).collect(Collectors.toList());
        List<String> list2 = proNames.stream().map(
                name -> {
                    return name.toLowerCase();
                }
        ).collect(Collectors.toList());
        List<String> list3 = proNames.stream().map(String::toLowerCase).collect(Collectors.toList());
        list1.stream().forEach(System.out::println);
        System.out.println("=================");
        list2.stream().forEach(System.out::println);
        System.out.println("=================");
        list3.stream().forEach(System.out::println);


        String waibu = "lambda :";
        List<String> proStrs = Arrays.asList(new String[]{"Ni", "Hao", "Lambda"});
        List<String> execStrs = proStrs.stream().map(chuandi -> {
            Long zidingyi = System.currentTimeMillis();
            return waibu + chuandi + " -----:" + zidingyi;
        }).collect(Collectors.toList());
        execStrs.forEach(System.out::println);
    }

    public void test2() {
        List<String> list = new ArrayList<>();
        list.add("张无忌");
        list.add("周芷若");
        list.add("赵敏");
        list.add("张强");
        list.add("张三丰");
        list.stream().filter(s -> s.startsWith("周1"))
                .filter(s -> s.length() == 3)
                .forEach(System.out::print);

        Stream<String> original = Stream.of("10", "12", "18");
        Stream<Integer> result = original.map(str -> Integer.parseInt(str));
        // System.out.print(result);

        Stream<String> streamA = Stream.of("张无忌");
        Stream<String> streamB = Stream.of("张翠山");
        Stream<String> resultC = Stream.concat(streamA, streamB);

        Student stu1 = new Student("1", "xiaoming", "man", "20");
        Student stu2 = new Student("2", "wangwu", "woman", "22");
        Student stu3 = new Student("3", "lisi", "man", "24");
        Student stu4 = new Student("4", "xiyangyang", "man", "26");
        Student stu5 = new Student("5", "zhangsan", "man", "27");
        Student stu6 = new Student("6", "xiaohong", "woman", "27");

        List<Student> data = new ArrayList<>();
        data.add(stu1);
        data.add(stu2);
        data.add(stu3);
        data.add(stu4);
        data.add(stu5);
        data.add(stu6);
        List<String> list2 = new ArrayList<>();
        list2.add("apple");
        list2.add("banana");
        list2.add("orange");
        list2.add("pear");
        list2.add("dog");
        list2.add("cat");
        list2.add("apple");
        list2.add("apple");
        list2.add("apple");

        List<Student> collect1 = data.stream().filter(student -> "woman".equals(student.getSex())).collect(Collectors.toList());
        List<Student> collect2 = data.stream().filter(student -> "man".equals(student.getSex()) && "24" == student.getAge()).collect(Collectors.toList());

        // System.out.print(collect2);
        //取出所有用户的名字:
        List<String> collect3 = data.stream().map(student -> student.getName()).collect(Collectors.toList());

        collect3.stream().forEach(System.out::println);

        List<String> listName2 = data.stream().map(Student::getName).collect(Collectors.toList());
        listName2.stream().forEach(System.out::println);
        List<String> listAge = data.stream().map(Student::getAge).collect(Collectors.toList());


        //  System.out.print(listAge);

        //去重
        List<String> collect = list2.stream().distinct().collect(Collectors.toList());
        //  System.out.print(collect);

        Student student1 = data.stream().filter(student -> student.getAge() == "24").findFirst().get();
        //System.out.println(student1);

        List<Integer> list3 = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);


        //匹配

        boolean cat1 = list2.stream().anyMatch(s -> s.equals("cat"));
        boolean cat2 = list2.stream().allMatch(s -> s.equals("cat"));
        boolean cat3 = list2.stream().noneMatch(s -> s.equals("cat"));

        System.out.print(cat1);
        //求最值:


        Student student = data.stream().max(Comparator.comparing(Student::getAge)).get();
        // System.out.print(student);
        //转数组
        Student[] students = data.stream().toArray(Student[]::new);
        Student[] students1 = data.toArray(new Student[0]);

        List<String> listS = new ArrayList<>();
        listS.add("123,http://wwww.baidu.com");
        listS.stream().forEach(
                e -> {
                    String[] split = e.split(",");
                    String goodId = split[0];
                    String imgUrl = split[1];
                    System.out.print(goodId);
                    System.out.print(imgUrl);

                }
        );
    }

    @Test
    public void test3() {
        //遍历map
        Map<String, String> map = new HashMap<String, String>(2);
        map.put("name", "张三");
        map.put("age", "18");
        map.put("sex", "男");
        map.forEach((key, value) -> {
                    System.out.println("Key: " + key + ", Value: " + value);
                }
        );

    }

    @Test
    public void test4() {
        List<Integer> extendsGoodsNumberIds = Arrays.asList(
                10, 66, 99, 88, 55, 44, 66
        );
        Map<Integer, Integer> extendsGoodsMap = extendsGoodsNumberIds.stream().collect(Collectors.toMap(x -> x, y -> 1, (x, y) -> x));
        extendsGoodsMap.forEach((key, value) -> {
                    System.out.println("Key: " + key + ", Value: " + value);
                }
        );
    }

    @Test
    public void tes5() {
        List<String> list = new ArrayList<>();
        list.add("张无忌");
        list.add("周芷若");
        list.add("赵敏");
        list.add("张强");
        list.add("张三丰");
        list.stream().filter(s -> s.startsWith("周1"))
                .filter(s -> s.length() == 3)
                .forEach(System.out::print);

        Stream<String> original = Stream.of("10", "12", "18");
        Stream<Integer> result = original.map(str -> Integer.parseInt(str));
        // System.out.print(result);

        Stream<String> streamA = Stream.of("张无忌");
        Stream<String> streamB = Stream.of("张翠山");
        Stream<String> resultC = Stream.concat(streamA, streamB);

        Student stu1 = new Student("1", "xiaoming", "man", "20");
        Student stu2 = new Student("2", "wangwu", "woman", "22");
        Student stu3 = new Student("3", "lisi", "man", "24");
        Student stu4 = new Student("4", "xiyangyang", "man", "26");
        Student stu5 = new Student("5", "zhangsan", "man", "27");
        Student stu6 = new Student("6", "xiaohong", "woman", "27");

        List<Student> data = new ArrayList<>();
        data.add(stu1);
        data.add(stu2);
        data.add(stu3);
        data.add(stu4);
        data.add(stu5);
        data.add(stu6);
        List<String> list2 = new ArrayList<>();
        list2.add("apple");
        list2.add("banana");
        list2.add("orange");
        list2.add("pear");
        list2.add("dog");
        list2.add("cat");
        list2.add("apple");
        list2.add("apple");
        list2.add("apple");

        List<Student> collect1 = data.stream().filter(student -> "woman".equals(student.getSex())).collect(Collectors.toList());
        List<Student> collect2 = data.stream().filter(student -> "man".equals(student.getSex()) && "24" == student.getAge()).collect(Collectors.toList());

        // System.out.print(collect2);
        //取出所有用户的名字:
        List<String> collect3 = data.stream().map(student -> student.getName()).collect(Collectors.toList());

        collect3.stream().forEach(System.out::println);

        List<String> listName2 = data.stream().map(Student::getName).collect(Collectors.toList());
        listName2.stream().forEach(System.out::println);
        List<String> listAge = data.stream().map(Student::getAge).collect(Collectors.toList());


        //  System.out.print(listAge);

        //去重
        List<String> collect = list2.stream().distinct().collect(Collectors.toList());
        //  System.out.print(collect);

        Student student1 = data.stream().filter(student -> student.getAge() == "24").findFirst().get();
        //System.out.println(student1);

        List<Integer> list3 = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);


        //匹配

        boolean cat1 = list2.stream().anyMatch(s -> s.equals("cat"));
        boolean cat2 = list2.stream().allMatch(s -> s.equals("cat"));
        boolean cat3 = list2.stream().noneMatch(s -> s.equals("cat"));

        System.out.print(cat1);
        //求最值:


        Student student = data.stream().max(Comparator.comparing(Student::getAge)).get();
        // System.out.print(student);
        //转数组
        Student[] students = data.stream().toArray(Student[]::new);
        Student[] students1 = data.toArray(new Student[0]);

        List<String> listS = new ArrayList<>();
        listS.add("123,http://wwww.baidu.com");
        listS.stream().forEach(
                e -> {
                    String[] split = e.split(",");
                    String goodId = split[0];
                    String imgUrl = split[1];
                    System.out.print(goodId);
                    System.out.print(imgUrl);

                }
        );
    }

    @Test
    public void test6() {

        List<Person> list = new ArrayList<>();
        Person p1 = new Person(1, "xiaoming", "man", 23);
        Person p2 = new Person(2, "xiaohong", "woman", 27);
        Person p3 = new Person(1, "xiaolei", "man", 24);
        Person p4 = new Person(1, "xiaoli", "man", 26);
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
        Set<Person> collect2 = list.stream().limit(4).collect(Collectors.toSet());
        Map<Integer, Person> map = list.stream().collect(Collectors.toMap(Person::getId, Function.identity()));
        Map<Integer, String> collect3 = list.stream().collect(Collectors.toMap(Person::getId, Person::getName));


        List<Person> data = new ArrayList<>();
        Person p6 = new Person(1, "xiaoming", "man", 24);
        Person p5 = new Person(5, "xiaomeng", "man", 23);
        data.add(p1);
        data.add(p2);
        data.add(p3);
        data.add(p4);
        data.add(p5);
        data.add(p6);
        Map<Integer, Person> collect6 = data.stream().collect(Collectors.toMap(Person::getId, Function.identity(), BinaryOperator.maxBy(Comparator.comparing(Person::getAge))));

        System.out.print(collect6);

    }

    @Test
    public void test7() {

        Item item1 = new Item("2020-01-01", "xiaowang", "2", "1");
        Item item2 = new Item("2020-01-01", "xiaowang", "3", "2");
        Item item3 = new Item("2020-01-01", "xiaowang", "4", "3");
        List<Item> itemList = new ArrayList<>();
        itemList.add(item1);
        itemList.add(item2);
        itemList.add(item3);
        List<Item> newList = itemList.stream().map(items -> {
            Item it = new Item();
            BeanUtils.copyProperties(items, it);//it是目标对象,items是源对象
            return it;
        }).collect(Collectors.toList());
        List<Item> collect = itemList.stream().map(item -> {
                    Item it = new Item();
                    BeanUtils.copyProperties(item, it);
                    return it;
                }
        ).collect(Collectors.toList());
        collect.forEach(System.out::print);
        System.out.println("-------------------------old list-------------------------");
        itemList.forEach(System.out::println);
        System.out.println("----------------------------------------------------------");
        System.out.println("-------------------------new list-------------------------");
        newList.forEach(System.out::println);
    }


    @Test
    public void test11() {
        employees.stream().filter(e -> e.getSalary() > 5000).forEach(System.out::println);
    }


    @Test
    public void test12() {
        Stream<Employee> stream = employees.stream().filter(e -> {
            System.out.print("执行操作");
            return e.getSalary() > 5000;
        });
        stream.forEach(System.out::println);

    }


    @Test
    public void test13() {
        employees.stream().filter(e -> e.getSalary() > 5000).distinct().forEach(System.out::println);
    }

    @Test
    public void test14() {

        List<String> list = Arrays.asList("aaa", "bbb", "ccc");
        list.stream().map(str -> str.toUpperCase()).forEach(System.out::print);
        Stream<String> stringStream = employees.stream().map(Employee::getName);
        employees.stream().map(Employee::getName).forEach(System.out::print);
    }

    @Test
    public void test15() {
        StringBuilder newStr = new StringBuilder();

        List<String> list = Arrays.asList("aaa", "bbb", "ccc", "eg");
        List<String> list2 = Arrays.asList("eee", "ddd", "ffff", "eg");


        for (int i = 0; i < list.size(); i++) {
            String s = list.get(i);
            if (list2.contains(s)) {
                System.out.print(newStr.append("hehe"));
            }
        }

    }


    @Test
    public void test21() {
        List<String> collect = employees.stream().map(Employee::getName).distinct().collect(Collectors.toList());
        collect.stream().forEach(System.out::println);
    }

    @Test
    public void test22() {
        List<Employee> employees1 = employees.stream().filter(employee -> employee.getName().equals("王五")).collect(Collectors.toList());
    }

    @Test
    public void test23() {
        List list2 = new ArrayList<>();
        list2.add("apple");
        list2.add("banana");
        list2.add("orange");
        list2.add("pear");
        list2.add("dog");
        list2.add("cat");
        list2.add("apple");
        list2.add("apple");
        list2.add("apple");
        boolean b = list2.stream().anyMatch(s -> s.equals("cat"));
        System.out.print(b);
    }

    @Test
    public void test24() {
        Stream<List<Integer>> inputStream = Stream.of(
                Arrays.asList(1),
                Arrays.asList(2, 3),
                Arrays.asList(4, 5, 6)
        );
        Stream<Integer> outputStream = inputStream.
                flatMap((childList) -> childList.stream());
        outputStream.forEach(System.out::println);
    }

    @Test
    public void test25() {
        List<Integer> nums = Lists.newArrayList(1, 1, null, 2, 3, 4, null, 5, 6, 7, 8, 9, 10);
        System.out.println("sum is:" + nums.stream().filter(num -> num != null).distinct().mapToInt(num -> num * 2).peek(System.out::println).skip(2).limit(4).sum());
    }

    @Test
    public void test26() {
        List<Integer> nums = Lists.newArrayList(1, null, 3, 4, null, 6);
        long count = nums.stream().filter(num -> num != null).count();
        System.out.println(count);

    }

    @Test
    public void test27() {

        List<Integer> nums = Lists.newArrayList(1, 1, null, 2, 3, 4, null, 5, 6, 7, 8, 9, 10);
        List<Integer> numsWithoutNull = nums.stream().filter(num -> num != null).
                collect(() -> new ArrayList<Integer>(),
                        (list, item) -> list.add(item),
                        (list1, list2) -> list1.addAll(list2));
        numsWithoutNull.stream().forEach(System.out::println);
        System.out.println("=======================================");
        List<Integer> numsWithoutNul2l = nums.stream().filter(num -> num != null).
                collect(Collectors.toList());
        numsWithoutNul2l.stream().forEach(System.out::println);
    }

}
