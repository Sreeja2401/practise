
class Parent {
     int i;
     void display() {
        System.out.println("Static method in Parent");
    }
}

class Child extends Parent {
     int i;
      void display() { // This method hides the Parent's display method
        System.out.println("Static method in Child");
    }
}
interface nithya{
    void r();
    static void s(){
        System.out.println("s");
    }
}
public class sreeja {
    public static void main(String[] args) {
        Parent parent = new Parent();
        Parent childAsParent = new Child();
        Child child = new Child();

        parent.display();        // Output: Static method in Parent
        System.out.println(child.i); // Output: Static method in Parent
        child.display();
        // Output: Static method in Child
        nithya n = () -> System.out.println("n");
    }
}
