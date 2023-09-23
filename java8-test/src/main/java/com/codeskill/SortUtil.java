package com.codeskill;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

public class SortUtil {
    public static <T> List<T> sortByField(List<T> list,
                                          final String firstSortField, final boolean isFirstFieldAsc,
                                          final String secondSortField, final boolean isSecondFieldAsc) {
        return list.stream().sorted((o1, o2) -> {
            try {
                int result = compareField(o1, o2, firstSortField);
                if (result == 0) {
                    result = compareField(o1, o2, secondSortField);
                    return isSecondFieldAsc ? result : -result;
                } else {
                    return isFirstFieldAsc ? result : -result;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return 0;
            }
        }).collect(Collectors.toList());
    }

    private static <T> int compareField(T o1, T o2, String sortField) throws NoSuchFieldException, IllegalAccessException {
        Field field1 = o1.getClass().getDeclaredField(sortField);
        field1.setAccessible(true);
        Comparable value1 = (Comparable) field1.get(o1);

        Field field2 = o2.getClass().getDeclaredField(sortField);
        field2.setAccessible(true);
        Comparable value2 = (Comparable) field2.get(o2);

        return value1.compareTo(value2);
    }

    public static <T> void sortByField(List<T> list, Class<T> c, String... fieldNames) {
        try {
            Comparator<T> compoundComparator = null;
            for (String field : fieldNames) {
                Field declaredField = c.getDeclaredField(field);
                declaredField.setAccessible(true);
                Comparator<T> fieldComparator = Comparator.comparing(o -> {
                    try {
                        return (Comparable) declaredField.get(o);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                });

                if (compoundComparator == null) {
                    compoundComparator = fieldComparator;
                } else {
                    compoundComparator = compoundComparator.thenComparing(fieldComparator);
                }
            }

            list.sort(compoundComparator);
        } catch (NoSuchFieldException e) {
            throw new IllegalArgumentException("Field not found", e);
        }
    }


    public static void main(String[] args) throws Exception {

        List<Person> people = new ArrayList<>();
        people.add(new Person("Bob", "Hill", 30));
        people.add(new Person("Brad", "Woods", 25));
        people.add(new Person("Cathy", "Jenkins", 35));
        people.add(new Person("David", "Lambert", 30));
        people.add(new Person("Ally", "Hill", 30));
        System.out.println(people);

//        SortUtil.sortByField(people, "age", true, "firstName", false);
        SortUtil.sortByField(people, Person.class, "age", "firstName");

        System.out.println(people);
        /*
         * [SortUtil.Person(firstName=Cathy, lastName=Jenkins, age=35),
         * SortUtil.Person(firstName=Ally, lastName=Hill, age=30),
         * SortUtil.Person(firstName=David, lastName=Lambert, age=30),
         * SortUtil.Person(firstName=Brad, lastName=Woods, age=25)]
         */

    }


    @AllArgsConstructor
    @Data
    public static class Person {
        private String firstName;
        private String lastName;
        private Integer age;

    }
}
