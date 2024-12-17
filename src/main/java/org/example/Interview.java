package org.example;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Interview {
    public record Employee(int id, String name, List<String> skills) {}
    public record Customer(int id, String name, List<Order> orders) {}
   // public record Order(int orderId, String product, double amount) {}
    public record Order(int customerId, List<String> products) {}
     public record Project(int id, String name, List<Task> tasks) {}
    public record Task(String description, LocalDate dueDate, boolean completed) {}
    public record Project2(int projectId, List<Employee2> employees) {}
    public record Employee2(int id, String name) {}
    public record Movie(int id, List<Reviews> reviewList){}
    public record Reviews(int rating, int comment){}

    void moviewsWithRating(List<Movie> movieList,int value)
    {
        movieList.stream().filter(movie->movie.reviewList.stream().anyMatch(reviews -> reviews.rating>3)).toList();
        movieList.stream().flatMap(movie -> movie.reviewList.stream()).filter(reviews -> reviews.rating>5).toList();
    }
    public static void main(String[] args) {

    }
    public void employee(List<Employee> employees)
    {
        employees.stream().filter(employee -> employee.skills().stream().count() >=2).toList();
    }
    public void customer(List<Customer> customers, double value)
    {
      //  customers.stream().filter(customer -> customer.orders.stream().mapToDouble(Order::amount).sum() >value).toList();
    }
    public void mostPopularProduct(List<Order> orders)
    {
        orders.stream().collect(Collectors.groupingBy(Order::products,Collectors.counting())).entrySet().stream().max(Map.Entry.comparingByValue()).ifPresent(System.out::println);
        orders.stream().flatMap(order -> order.products().stream()).collect(Collectors.groupingBy(product->product,Collectors.counting())).entrySet().stream().max(Map.Entry.comparingByValue()).ifPresent(System.out::println);
    }
    public  void projectWithOverDueTask(List<Project> projects)
    {
     projects.stream().filter(project -> project.tasks.stream().anyMatch(task -> task.dueDate.isBefore(LocalDate.now()) && !task.completed)).forEach(System.out::println);
    }
    public void employeesWorkingOnMoreThanOneProject(List<Project2> project2s)
    {
        project2s.stream().flatMap(pr->pr.employees().stream()).collect(Collectors.groupingBy(employee2 -> employee2.name,Collectors.counting())).entrySet().stream().filter(e->e.getValue()>1).forEach(System.out::println);
    }
    private void filterBooksWithCoverAreaOverTheThreshold(List<Book> books, int threshold) {
        books.stream().filter(book -> book.covers().stream().anyMatch(b->b.getArea()>threshold)).toList();

    //Write a method that filters books where all the covers have an area greater than the threshold.
        books.stream().filter(book -> book.covers().stream().allMatch(b->b.getArea()>threshold)).toList();

        System.out.println(books.stream().map(book -> book.covers().stream().collect(Collectors.summingInt(Cover::getArea))).sorted());

   List<Integer> i = List.of(1,3,4,5,6,7);
   i.stream().mapToInt(i1->i1).sum();
   i.stream().collect(Collectors.summingInt(i2->i2));
  // List<String> l2 =




    }
}

 record Book(int id, List<Cover> covers) {
}

 record Cover(int id, int width, int height) implements Comparable<Cover> {
    public int getArea() {
        return width * height;
    }

    @Override
    public int compareTo(Cover cover) {
        if (this.getArea() == cover.getArea()) {
            return 0;
        }
        return this.getArea() > cover.getArea() ? 1 : -1;
    }
}


