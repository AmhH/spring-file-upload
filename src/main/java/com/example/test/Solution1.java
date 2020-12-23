package com.example.test;


public class Solution1 {

  public static void main(String[] args) {
    System.out.println(solution(-123));
    System.out.println(solution(123));
  }

  public static  int solution(int N){
    int negative = N < 0 ? -1 : 1;
    StringBuilder builder = new StringBuilder(Integer.toString(Math.abs(N)));
    for (int i = 0; i < builder.length(); i++) {
      if((negative * Character.getNumericValue(builder.charAt(i))) < negative * 5){
        builder.insert(i, 5);
        break;
      }
      if(i == builder.length() - 1){
        builder.append(5);
        break;
      }
    }

    return negative * Integer.parseInt(builder.toString());
  }
}
