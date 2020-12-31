package com.example.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CharacterTest {

  @Test
  public void getTypeTest(){
    assertEquals(Character.UPPERCASE_LETTER, Character.getType('U'));
    assertEquals(Character.LOWERCASE_LETTER, Character.getType('u'));
    assertEquals(Character.TITLECASE_LETTER, Character.getType('\u01f2'));
    assertEquals(Character.MODIFIER_LETTER, Character.getType('\u02b0'));
    assertEquals(Character.OTHER_LETTER, Character.getType('\u05d0'));
    assertEquals(Character.LETTER_NUMBER, Character.getType('\u2164'));
    System.out.println('\u01f2');
    System.out.println('\u02b0');
    System.out.println('\u05d0');
    System.out.println('\u2164');
  }

  @Test
  public void isAlphabeticTest(){
    assertTrue(Character.isAlphabetic('A'));
    assertTrue(Character.isAlphabetic('\u01f2'));
    System.out.println('\u01f2');
  }

  @Test
  public void isLetterTest(){
    assertTrue(Character.isLetter('a'));
    assertTrue(Character.isLetter('\u02b0'));
    System.out.println('\u02b0');
    System.out.println('\u2164');
    assertFalse(Character.isLetter('\u2164'));
    assertTrue(Character.isAlphabetic('\u2164'));
  }

}
