# Byte Tree

A thread-safe sorted in-memory tree for `byte[]` keys and values.

## Overview

This implementation provides exactly two operations:
- `put(byte[] key, byte[] value)` - Store a key-value pair
- `get(byte[] key)` - Retrieve a value by key (returns `null` if not found)

## Data Structure Choice: ConcurrentSkipListMap

**Why ConcurrentSkipListMap?**

### Thread Safety
- **Lock-free operations**: No blocking between readers and writers
- **Linearizable**: All operations appear to happen atomically
- **Scalable**: Performance won't worsen under high concurrency

### Sorted Order
- **Automatic sorting**: Keeps keys in sorted order via comparator
- **O(log n) operations**: Efficient get/put performance
- **No rebalancing overhead**: Unlike for exmaple red-black trees, skip lists don't need complex rebalancing

## Key Design Decisions

### ByteSequence Wrapper
Keys are wrapped in an immutable `ByteSequence` class that:
- **Prevents mutation**: Defensive copying ensures thread safety here
- **Unsigned lexicographic ordering**: Treats bytes as unsigned (0-255)
- **Shorter-first rule**: eg [1,2]` comes before `[1,2,3]`

### Defensive Copying
Both keys and values are copied on:
- **Input**: `put()` operations copy arrays to prevent external mutation
- **Output**: `get()` operations return copies to prevent internal state corruption

## Flow

```
SortedByteTree (interface)
    ↓
SkipListSortedByteTree (implementation)
    ↓
ConcurrentSkipListMap<ByteSequence, byte[]>
```

## Testing

Run tests with:
```bash
mvn test
```

## Other options

| Alternative | Why Not?                                                      |
|-------------|---------------------------------------------------------------|
| `TreeMap` + locks | Not very good concurrency, blocking operations                |
| `ConcurrentHashMap` | No sorted order guarantee                                     |
| Custom B-Tree | Complex implementation, I think out of the scope of this task |
| Custom Red-Black Tree | Rebalancing complexity, no concurrency                        |

**ConcurrentSkipListMap** gives the perfect balance of thread safety, sorted order, and performance for this task's scope.
