package com.example.test;

import java.util.Arrays;
import java.util.function.Predicate;

public class PrintNthElement {

  public static void main(String[] args) {
    int[] arr = {10,13,14,16,19,12};
    Arrays.stream(arr)
        .skip(3)
        .limit(1)
        .forEach(System.out::println);
    //C a = new A();
    Character.isAlphabetic('3');
    Character.isLetter('3');
    Predicate<String> predicate = (String s) -> s.length() > 0;
  }

  static {
    System.out.println("I am Raya");
  }

  class C{
    C(){ System.out.println("class C");}
  }

  class B extends C{
    B(){ System.out.println("class B");}
  }

  class A extends B{
    A(){ System.out.println("class A");}
  }

  class MyPredicate implements Predicate<String>{

    @Override
    public boolean test(String s) {
      return s.length() > 0;
    }
  }
}
