package com.mattpapp.bytetree;

import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.ConcurrentSkipListMap;

public final class SkipListSortedByteTree implements SortedByteTree {

  private final ConcurrentSkipListMap<ByteSequence, byte[]> map = new ConcurrentSkipListMap<>();

  @Override
  public void put(byte[] key, byte[] value) {
    Objects.requireNonNull(key, "key");
    Objects.requireNonNull(value, "value");
    map.put(new ByteSequence(key), Arrays.copyOf(value, value.length));
  }

  @Override
  public byte[] get(byte[] key) {
    Objects.requireNonNull(key, "key");
    byte[] v = map.get(new ByteSequence(key));
    return (v == null) ? null : Arrays.copyOf(v, v.length);
  }
}
