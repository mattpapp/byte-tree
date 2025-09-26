package com.mattpapp.bytetree;

import java.util.Arrays;
import java.util.Objects;

public final class ByteSequence implements Comparable<ByteSequence> {
  private final byte[] data;

  public ByteSequence(byte[] src) {
    Objects.requireNonNull(src, "key");
    this.data = Arrays.copyOf(src, src.length);
  }

  @Override
  public int compareTo(ByteSequence o) {
    byte[] a = this.data;
    byte[] b = o.data;
    int len = Math.min(a.length, b.length);
    for (int i = 0; i < len; i++) {
      int ai = a[i] & 0xFF;
      int bi = b[i] & 0xFF;
      if (ai != bi) return ai - bi;
    }
    return Integer.compare(a.length, b.length);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (!(obj instanceof ByteSequence other)) return false;
    return Arrays.equals(this.data, other.data);
  }

  @Override
  public int hashCode() {
    return Arrays.hashCode(data);
  }

  @Override
  public String toString() {
    return "ByteSequence[len=" + data.length + "]";
  }
}
