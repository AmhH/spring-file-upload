package com.example.test;

public class EnglishAlphabetLetters {

  public static void main(String[] args) {
    System.out.println(checkStringForAllTheLetters("Farmer jack realized that big yellow quilts were expensive."));
    System.out.println(checkStringForAllLetterUsingRegex("Farmer jack realized that big yellow quilts were expensive."));
    System.out.println(checkStringForAllLetterUsingStream("Farmer jack realized that big yellow quilts were expensive."));
  }

  public static boolean checkStringForAllTheLetters(String input) {
    int index = 0;
    boolean[] visited = new boolean[26];

    for (int id = 0; id < input.length(); id++) {
      if ('a' <= input.charAt(id) && input.charAt(id) <= 'z') {
        index = input.charAt(id) - 'a';
      } else if ('A' <= input.charAt(id) && input.charAt(id) <= 'Z') {
        index = input.charAt(id) - 'A';
      }
      visited[index] = true;
    }

    for (int id = 0; id < 26; id++) {
      if (!visited[id]) {
        return false;
      }
    }
    return true;
  }

  public static boolean checkStringForAllLetterUsingRegex(String input) {
    return input.toLowerCase()
        .replaceAll("[^a-z]", "")
        .replaceAll("(.)(?=.*\\1)", "")
        .length() == 26;
  }

  public static boolean checkStringForAllLetterUsingStream(String input) {
    long c = input.toLowerCase().chars()
        .filter(ch -> ch >= 'a' && ch <= 'z')
        .distinct()
        .count();
    return c == 26;
  }

}
