package com.mattpapp.bytetree;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

import org.junit.jupiter.api.Test;

class SkipListSortedByteTreeTest {

  @Test
  void canStoreAndRetrieveValues() {
    SortedByteTree tree = new SkipListSortedByteTree();
    byte[] key = "hello".getBytes(StandardCharsets.UTF_8);
    byte[] value = "world".getBytes(StandardCharsets.UTF_8);

    tree.put(key, value);

    assertArrayEquals(value, tree.get(key));
  }

  @Test
  void returnsNullForMissingKeys() {
    SortedByteTree tree = new SkipListSortedByteTree();
    byte[] missingKey = "nonexistent".getBytes(StandardCharsets.UTF_8);

    assertNull(tree.get(missingKey));
  }

  @Test
  void protectsAgainstExternalMutation() {
    SortedByteTree tree = new SkipListSortedByteTree();
    byte[] key = {1, 2, 3};
    byte[] value = {10, 20, 30};
    
    tree.put(key, value);
    
    key[0] = 99;
    value[0] = 99;
    
    byte[] retrieved = tree.get(new byte[]{1, 2, 3});
    assertArrayEquals(new byte[]{10, 20, 30}, retrieved);
  }

  @Test
  void protectsAgainstReturnedValueMutation() {
    SortedByteTree tree = new SkipListSortedByteTree();
    byte[] key = "test".getBytes(StandardCharsets.UTF_8);
    byte[] value = {5, 10, 15};
    
    tree.put(key, value);
    
    byte[] retrieved = tree.get(key);
    Objects.requireNonNull(retrieved)[0] = 99;
    
    byte[] retrievedAgain = tree.get(key);
    assertArrayEquals(new byte[]{5, 10, 15}, retrievedAgain);
  }
}
