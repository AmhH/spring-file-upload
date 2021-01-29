package com.example.test;

import java.util.Comparator;
import java.util.List;

public class Test {

  public static void main(String[] args) {
    Number[] nums = new Long[3];
    nums[0] = 1L;
    nums[1] = 2L;
    nums[2] = 3L;
    Object[] objs = nums;
    objs[2] = "ArrayStoreException happens here";
  }
  interface SortingService {
    <T> void sort(List<T> list, Comparator<T> comparator);
  }

  interface SortingService2 {
    <T> void sort(List<T> list, Comparator<? super T> comparator);
  }
  /**
   *  PECS (producer-extends, consumer-super).
   *  It means that a producer should be of type ? extends T while consumer of ? super T.
   */
}
