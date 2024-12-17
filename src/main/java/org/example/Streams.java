package org.example;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Streams {
    public static void main(String[] args) {
        List<String> l1 = new ArrayList<>(List.of("sreeja", "mani", "hari", "yes", "siri", "madav"));
        //Example 1: Create a Stream from a List
        l1.stream().forEach(System.out::println);
        //2: Filter Elements in a Stream
        System.out.println(l1.stream().filter(s -> s.length() > 4).toList());
        //Filtering Strings: From a list of strings, print all strings containing the letter 'a'.
        l1.stream().filter(s -> s.contains("a")).forEach(System.out::println);
        //Sum of Even Numbers: Find the sum of all even numbers from an integer list.
        System.out.println(IntStream.rangeClosed(1, 5).filter(i -> i % 2 == 0).sum());
        //Unique Characters Count: Calculate the count of unique characters in a string.
        String s = "sreeja";
        //find the count of each char then check if charcount ==1
        Map<Integer, Long> count = s.chars().boxed().collect(Collectors.groupingBy(i -> i, Collectors.counting()));
        System.out.println(count.entrySet().stream().filter(e -> e.getValue() == 1).count());
        count.entrySet().stream().filter(e -> e.getValue() == 1).map(e -> (char) e.getKey().intValue()).forEach(System.out::println);
        //Average of List of Doubles: Compute the average of a list of doubles.
        //collectors - grouping(classifier , downstream)
        //collectors - summing averaging counting partitioning reducing
        List<Double> l2 = List.of(3.14, 1.618);
        System.out.println(l2.stream().collect(Collectors.averagingDouble(d -> d)).doubleValue());
        //Grouping by Length: Group a list of strings by their lengths.
        l1.stream().collect(Collectors.groupingBy(String::length)).entrySet().forEach(System.out::println);
        //Counting Words Starting with 'S': From a list of words, count words that start with the letter 'S'.
        l1.stream().collect(Collectors.groupingBy(p -> p.startsWith("s"), Collectors.counting())).entrySet().forEach(System.out::println);
        //Sorting Strings by Length: Sort a list of strings by their lengths.
        //
        //Max and Min of List: Find the maximum and minimum values from a list of integers.

        l1.stream().sorted(Comparator.comparingInt(String::length)).forEach(System.out::println);
        List<Integer> l3 = new ArrayList<>(List.of(3, 4, 66, 7, 3, 3, 4));
        l3.stream().mapToInt(i -> i).min();

        //Counting Elements: Count occurrences of each element in a list.
        l3.stream().collect(Collectors.groupingBy(i -> i, Collectors.counting())).entrySet().forEach(System.out::println);

        //Group a list of strings by their starting letters.
        l1.stream().collect(Collectors.groupingBy(s1 -> s1.charAt(0))).entrySet().forEach(System.out::println);

//        Check for Palindromes: Determine if a list of strings contains palindromes.
        List<String> l4 = new ArrayList<>(List.of("ara", "poi"));
        l4.stream().filter(w -> w.contentEquals(new StringBuffer(w).reverse())).forEach(System.out::println);

        //Finding Average Length of Strings: Calculate the average length of strings from a list.
        System.out.println(l1.stream().map(String::length).mapToInt(i -> i).average());

        //emove Elements Based on Condition: Remove elements from a list that satisfy a given condition.
        System.out.println(l1.stream().filter(u -> u.length() > 4).toList());

        //Find Most Common Element: Find the most common element in a list.
        Map<Integer, Long> l = l3.stream().collect(Collectors.groupingBy(i -> i, Collectors.counting()));
        Long r = l.values().stream().max(Comparator.comparingLong(u -> u)).get();
        l.entrySet().stream().filter(w -> Objects.equals(w.getValue(), r)).forEach(j -> System.out.println(j.getKey()));

        //for map to compare the keys and values we have comparaters like comparing by value , comparing by keys
        l3.stream()
                .collect(Collectors.groupingBy(i -> i, Collectors.counting())) // Group by element and count occurrences
                .entrySet().stream().max(Map.Entry.comparingByValue()).ifPresent(entry -> System.out.println(entry.getKey()));

        System.out.println("chatgpt");
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        System.out.println((Integer) numbers.stream().mapToInt(i -> i).sum());

        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
        System.out.println(names.stream().collect(Collectors.joining(",")));

        List<Integer> numbers2 = Arrays.asList(5, 10, 15, 20, 25);
        System.out.println(numbers2.stream().collect(Collectors.partitioningBy(i -> i > 10)));

        List<String> words = Arrays.asList("apple", "banana", "apple", "orange", "banana", "apple");
        words.stream().collect(Collectors.groupingBy(i -> i, Collectors.counting())).entrySet().forEach(System.out::println);

        System.out.println(EmployeeData.getEmployees());

        //1. Group by Department and Count Employees
        List<Employee> employees = EmployeeData.getEmployees();
        employees.stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting())).entrySet().forEach(System.out::println);

        //2. Find the Employee with the Highest Salary
        employees.stream().map(Employee::getSalary).max(Comparator.comparingDouble(i -> i)).ifPresent(System.out::println);
        employees.stream().max(Comparator.comparingDouble(Employee::getSalary)).ifPresent(i -> System.out.println(i.getName()));

        // Average Salary by Department
        employees.stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.averagingDouble(Employee::getSalary))).entrySet().forEach(System.out::println);

        //List Employees Sorted by Age
        employees.stream().sorted(Comparator.comparingInt(Employee::getAge)).forEach(e -> System.out.println(e.getName()));

        //Sum of Salaries of All Employees
        System.out.println(employees.stream().mapToDouble(Employee::getSalary).sum());

        //List Employee Names in Uppercase, Sorted by Name
        employees.stream().map(Employee::getName).map(String::toUpperCase).sorted().forEach(System.out::println);

        //1. Find the Second Highest Paid Employee
        employees.stream().sorted(Comparator.comparingDouble(Employee::getSalary).reversed()).skip(1).findFirst().ifPresent(i-> System.out.println(i.getName()));

        // Group Employees by Department and Find Highest Salary in Each Department
        employees.stream().collect(Collectors.groupingBy(Employee::getDepartment,Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary)))).entrySet().forEach(System.out::println);

        // Total Salary by Department for Employees Aged 30 or Above
        employees.stream().filter(employee -> employee.getAge()>=30).collect(Collectors.groupingBy(Employee::getDepartment,Collectors.summingDouble(Employee::getSalary))).entrySet().forEach(System.out::println);



        int[] arr = {1,3,4,5,6};
        int target = 5;
        for (int i = 0; i < arr.length; i++) {
           if(arr[i]==target)
           {
               System.out.println(i);
           }
        }
         // return -1;


    }

    public int binarysearch(int[] arr , int target)
    {
        int left = 0;
        int right = arr.length-1;
       while(left<=right)
       {
           int mid = left + (right-left)/2;
           if(target == mid)
           {
               return arr[mid];
           } else if (target>mid) {
               left = mid+1;
           }
               right = mid-1;
       }
       return -1;
    }
}
