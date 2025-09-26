package com.mattpapp.bytetree;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.charset.StandardCharsets;
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
}
