package org.example;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        List<String> l1 = List.of("sree","sumi","sree");
      //  System.out.println(l1.stream().collect(Collectors.collectingAndThen(Collectors.toList(), List::size)));
        l1.stream().collect(Collectors.groupingBy(i->i,Collectors.counting())).entrySet().stream().filter(entry->entry.getValue()==1).forEach(entry-> System.out.println(entry.getKey()));

        System.out.println(IntStream.rangeClosed(1,15).boxed().collect(Collectors.partitioningBy(i->i%2==0,Collectors.summarizingInt(i->i))));

        //grouping by multiple criteria
        List<User> userList = new ArrayList<>();
        userList.add(new User(23,"hyderabad","sreeja",List.of(new Courses(1,"java"))));
        userList.add(new User(28,"tpt","sumi",List.of(new Courses(1,"java"),new Courses(2,"python"))));
        userList.add(new User(50,"hyderabad","srk",List.of(new Courses(2,"python"))));
        userList.add(new User(52,"tpt","salman",List.of(new Courses(2,"python"),new Courses(3,".net"))));
        System.out.println(userList.stream().collect(Collectors.groupingBy(User::city,Collectors.partitioningBy(user -> user.age>30))));
        System.out.println(userList.stream().flatMap(user -> user.courses.stream()).collect(Collectors.groupingBy(Courses::name)));
        System.out.println(userList.stream().collect(Collectors.groupingBy(User::city,Collectors.maxBy(Comparator.comparing(user -> user.age)))));
        // Group users by course name
        Map<String, List<User>> usersGroupedByCourse = userList.stream()
                .flatMap(user -> user.courses().stream() // Flatten the list of courses for each user
                        .map(course -> new AbstractMap.SimpleEntry<>(course.name(), user))) // Create an entry of courseName and user
                .collect(Collectors.groupingBy(Map.Entry::getKey, // Group by course name
                        Collectors.mapping(Map.Entry::getValue, Collectors.toList()))); // Map to user list
        System.out.println("--------------");
        System.out.println(userList.stream().max(Comparator.comparing(user -> user.courses().size())));
       // System.out.println(userList.stream().flatMap(user -> user.courses().stream()).collect(Collectors.groupingBy()));
        System.out.println(userList.stream().collect(Collectors.groupingBy(User::city,Collectors.averagingInt(User::age))));
    //    System.out.println(userList.stream().flatMap(user -> user.courses.stream().map()));
//        // Output the results
//        usersGroupedByCourse.forEach((courseName, users) -> {
//            System.out.println("Course: " + courseName);
//            users.forEach(user -> System.out.println("  User: " + user.name));
//        });
        record Skills(String name, int rating){}
        record Employee(String name , List<Skills> skills){}
        System.out.println("-------------------------------------------skill map-------------------------");
        List<Employee> empList = new ArrayList<>();
        empList.add(new Employee("Sreeja",List.of(new Skills("java",2),new Skills("c",2))));
        empList.add(new Employee("mani",List.of(new Skills("java",1),new Skills("c",2))));
        empList.add(new Employee("vamsi",List.of(new Skills("c",3),new Skills("java",3))));
       Map<String,Double> skillmap = empList.stream().flatMap(employee -> employee.skills().stream()).collect(Collectors.groupingBy(Skills::name,Collectors.averagingInt(Skills::rating)));
        System.out.println(skillmap);


        class userException extends RuntimeException{

            public userException(String sreeja) {
                super(sreeja);
            }
        }
        class sreeja{

            void m1 () {
                try {
                    System.out.println("88888888888888888888");
                    throw new userException("sreeja");
                }
                catch (userException e)
                {
                    System.out.println(e.getMessage());
                }
            }
        }

        sreeja s = new sreeja();
        s.m1();
    }
    record User(int age, String city, String name ,  List<Courses> courses){}
    record Courses(int id, String name){}


















}