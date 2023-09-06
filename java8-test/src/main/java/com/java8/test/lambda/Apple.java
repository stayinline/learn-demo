package com.java8.test.lambda;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Apple {

    private String color;
    private Integer weight;
    private Integer country;


    public static void main(String[] args) {
        Apple apple = new Apple("green", 10, 100);
        Apple apple1 = new Apple("red", 13, 120);
        Apple apple2 = new Apple("red", 14, 12);
        Apple apple3 = new Apple("red", 15, 130);
        List<Apple> list = new ArrayList<>();
        list.add(apple);
        list.add(apple1);
        list.add(apple2);
        list.add(apple3);
        System.out.println(list);
        List<Apple> sortList = list.stream()
                .sorted(Comparator.comparing(Apple::getWeight)
                        .reversed()
                        .thenComparing(Apple::getCountry))
                .collect(Collectors.toList());

        System.out.println(sortList);


    }
}
