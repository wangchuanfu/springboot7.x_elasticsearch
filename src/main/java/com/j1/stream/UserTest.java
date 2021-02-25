package com.j1.stream;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@AllArgsConstructor
public class UserTest {

    @Test
    public void test1() {
        ArrayList<UserTest1> list = new ArrayList<>();

        list.add(new UserTest1("liubei", "111", 40));
        list.add(new UserTest1("zhangfei", "222", 30));
        list.add(new UserTest1("guanyu", "333", 35));

        System.out.println("list: " + list.toString());

        System.out.println("test1:");
        list.stream().map(n -> n)
                .forEach(n -> System.out.println(n));

        System.out.println("test2--age:");
        list.stream().map(n -> n.getAge())
                .forEach(n -> System.out.println(n));

        System.out.println("test3--age:");
        list.stream().map(n -> n.getAge() + 2)
                .map(n -> n).forEach(n -> System.out.println(n));

        System.out.println("test2--username:");
        list.stream().map(n -> n.getUsername())
                .forEach(n -> System.out.println(n));

        System.out.println("test3--username:");
        list.stream().map(n -> n.getUsername())
                .map(n -> n).forEach(n -> System.out.println(n));
    }

    @Test
    public void test2() {
        System.out.println("========================");
        List<String> stringList = Stream.of(new String[]{"Hello", "World"}).flatMap(str -> Arrays.stream(str.split(""))
        ).distinct().collect(Collectors.toList());
        stringList.forEach(System.out::println);
    }

    @Test
    public void test3() {
        List<String> teamIndia = Arrays.asList("Virat", "Dhoni", "Jadeja");
        List<String> teamAustralia = Arrays.asList("Warner", "Watson", "Smith");
        List<String> teamEngland = Arrays.asList("Alex", "Bell", "Broad");
        List<String> teamNewZeland = Arrays.asList("Kane", "Nathan", "Vettori");
        List<String> teamSouthAfrica = Arrays.asList("AB", "Amla", "Faf");
        List<String> teamWestIndies = Arrays.asList("Sammy", "Gayle", "Narine");
        List<String> teamSriLanka = Arrays.asList("Mahela", "Sanga", "Dilshan");
        List<String> teamPakistan = Arrays.asList("Misbah", "Afridi", "Shehzad");
        List<List<String>> playersInWorldCup2016 = new ArrayList<>();
        playersInWorldCup2016.add(teamIndia);
        playersInWorldCup2016.add(teamAustralia);
        playersInWorldCup2016.add(teamEngland);
        playersInWorldCup2016.add(teamNewZeland);
        playersInWorldCup2016.add(teamSouthAfrica);
        playersInWorldCup2016.add(teamWestIndies);
        playersInWorldCup2016.add(teamSriLanka);
        playersInWorldCup2016.add(teamPakistan);

        List<String> listOfAllPlayers = new ArrayList<>();

        for (List<String> team : playersInWorldCup2016) {
            for (String name : team) {
                listOfAllPlayers.add(name);
            }
        }

        System.out.println("Players playing in world cup 2016");
        System.out.print(listOfAllPlayers);
        System.out.println("=============");
        List<String> flatMapList = playersInWorldCup2016.stream()
                .flatMap(pList -> pList.stream())
                .collect(Collectors.toList());
        System.out.println(flatMapList);


    }

    @Test
    public void test29() {
        List<String> list = Arrays.asList("aaaa", "vvv", "ccc");
        list.stream().map(str -> str.toUpperCase()).forEach(System.out::println);
        list.stream().forEach(System.out::println);
        System.out.println("=============");
        Stream<Stream<Character>> streamStream = list.stream().map(UserTest::filterChararcter);
        streamStream.forEach(sm -> {
            sm.forEach(System.out::println);
        });
        System.out.println("=============");
        list.stream().flatMap(UserTest::filterChararcter).forEach(System.out::println);
    }


    public static Stream<Character> filterChararcter(String str) {
        List<Character> list = new ArrayList<>();
        for (Character ch : str.toCharArray()) {
            list.add(ch);
        }
        return list.stream();
    }

    @Test
    public void test30() {
        List<String> list1 = Arrays.asList("aaaa", "vvv", "ccc");
        List list2 = new ArrayList();
        list2.add("fff");
        list2.add(list1);//[[aaa,bbb,ccc],fff]]
        list2.addAll(list1);//[aaa,bbb,ccc,fff]]
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
        List<Item> items1 = Arrays.asList(
                new Item("2020-01-01", "xiaowang", "22", "1"),
                new Item("2020-02-01", "xiaoli", "18", "1"),
                new Item("2020-03-01", "xiaozhang", "32", "1")
        );
        List<Item> newList = itemList.stream().map(item -> {
            Item it = new Item();
            BeanUtils.copyProperties(item, it);//it是目标对象,items是源对象
            return it;
        }).collect(Collectors.toList());

        newList.forEach(System.out::println);
        List<Item> collect = itemList.stream().map(item -> {
                    Item it = new Item();
                    BeanUtils.copyProperties(item, it);
                    return it;
                }
        ).collect(Collectors.toList());
        collect.forEach(System.out::println);
        System.out.println("-------------------------old list-------------------------");
        itemList.forEach(System.out::println);
        System.out.println("----------------------------------------------------------");
        System.out.println("-------------------------new list-------------------------");
        newList.forEach(System.out::println);
    }

    @Test
    public void test8() {

    }
}
