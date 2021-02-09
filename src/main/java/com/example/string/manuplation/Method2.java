package com.example.string.manuplation;

import org.apache.commons.lang3.StringUtils;

public class Method2 {

  public static void main(String[] args) {
    for (int i = 0; i < Integer.MAX_VALUE; i++) {
      String str = replaceChars("");
    }
  }

  private static String replaceChars(String token) {
    if(null != token && (token.length() > 0  && token.length() > 4)){
      //mask everything after the first 4
      String masking = StringUtils.repeat('*', 36);
      return StringUtils.overlay(token, masking, 4, 36);
    }else if(token.length() <= 4){
      //nothing to mask
      return token;
    }
    //empty token
    return "";
  }
}
