package com.example.string.manuplation;

public class Method1 {

  public static void main(String[] args) {
    for (int i = 0; i < Integer.MAX_VALUE; i++) {
      String str = replaceChars("");
    }
  }

  private static String replaceChars(String token) {
    if(null != token && (token.length() > 0  && token.length() > 4)){
      //mask everything after the first 4
      StringBuilder builder = new StringBuilder();
      builder.append(token.substring(0, 4));
      for (int i = 5; i < token.length(); i++) {
        builder.append("*");
      }
      return builder.toString();
    }else if(token.length() <= 4){
      //nothing to mask
      return token;
    }
    //empty token
    return "";
  }
}
