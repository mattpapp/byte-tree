package com.mattpapp.bytetree;

public interface SortedByteTree {
  void put(byte[] key, byte[] value);

  byte[] get(byte[] key);
}
