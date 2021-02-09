package com.example.string.manuplation;

import java.util.Arrays;

public class Method3 {

  public static void main(String[] args) {
    for (int i = 0; i < Integer.MAX_VALUE; i++) {
      String str = replaceChars("899fe51c-f07e-36fa-aed6-8080305772cf");
    }
  }

  private static String replaceChars(String token) {
    if(null != token && (token.length() > 0  && token.length() > 4)){
      //mask everything after the first 4
      char[] chars = token.toCharArray();
      token.getChars(0, 4, chars, 0);
      Arrays.fill(chars, 4, chars.length, '*');
      return new String(chars);
    }else if(token.length() <= 4){
      //nothing to mask
      return token;
    }
    //empty token
    return "";
  }
}
