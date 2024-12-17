package org.example;


class parent {
    int a = 2;
    public parent()
    {
        System.out.println("pc");
    }
    public parent(String s)
    {
        System.out.println(s);
    }
     public void m1()
     {
         System.out.println("m1");
     }
}
class child extends parent{
    int a = 3;
    public child()
    {
        super("s");
        System.out.println("cc");
    }
    public void m1()
    {
        System.out.println("m2");
    }


}

interface I1{
    void m1();
    static void dm1(){
        System.out.println("d from I1");
    }
    default void dm2(){}
}
interface I2{
    void m1();
    default void dm1(){
        System.out.println("d from I2");
    }
}
class c implements I1,I2{

    @Override
    public void m1() {
        System.out.println("m1 from both interfaces");
    }

    @Override
    public void dm1() {
        I1.super.dm1();
        I2.super.dm1();
    }
}

public class Inheritence {
    public static void main(String[] args) {
//       parent P = new parent();
//       P.m1();
//       parent Q = new child();
//       Q.m1();
//        System.out.println(Q.a);
//       child C = new child();
//       C.m1();
//        System.out.println(C.a);

        I1 i = () -> {

        };

        m1 n1 = new m1();
        m1 n2 = new m2();
        m2 n3 = new m2();

    }
}
