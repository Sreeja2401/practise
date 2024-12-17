package org.example;

 class staticOuterClass {
    static class staticInnerClass{
       void m1(){
           System.out.println("static");
       }
    }
}
class outerclass{
     class innerclass{
         void m1()
         {
             System.out.println("member");
         }
     }
     void m2()
     {
         class innerclass{
             void m1()
             {
                 System.out.println("local");
             }
         }
         innerclass in = new innerclass();
         in.m1();
     }
}
interface annonymous{
     void m1();
}
class m1{
    static void n1()
    {
        System.out.println("m1 m1");
    }
}
class m2 extends m1{
    static void n1(){
        System.out.println("m2 m1");
    }
}
class Parent {
    static void display() {
        System.out.println("Static method in Parent");
    }
}

class Child extends Parent {
    static void display() { // This method hides the Parent's display method
        System.out.println("Static method in Child");
    }
}
public class Innerclass {
    public static void main(String[] args) {
        staticOuterClass.staticInnerClass snc = new staticOuterClass.staticInnerClass();
        snc.m1();
        outerclass oc = new outerclass();
        outerclass.innerclass mic =  oc.new innerclass();
        mic.m1();
        oc.m2();
        annonymous an = new annonymous() {
            @Override
            public void m1() {
                System.out.println("annony");
            }
        };
        an.m1();

        Parent parent = new Parent();
        Parent childAsParent = new Child();
        Child child = new Child();

        parent.display();        // Output: Static method in Parent
        childAsParent.display(); // Output: Static method in Parent
        child.display();
    }
}
