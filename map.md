# Java 17 Map Implementations – Quick Guide

A practical cheat‑sheet with when‑to‑use guidance, null/thread‑safety notes, Big‑O, and minimal runnable snippets.

---

## Quick chooser

| Need | Pick |
|---|---|
| Fast lookups, order doesn’t matter | **HashMap** |
| Stable insertion order / LRU cache | **LinkedHashMap** (access‑order) |
| Sorted keys & range queries | **TreeMap** |
| Keys are enum constants | **EnumMap** |
| Identity (==) key semantics | **IdentityHashMap** |
| Entries should vanish when key is GC’d | **WeakHashMap** |
| Legacy key/value strings with .properties I/O | **Properties** |
| High‑throughput concurrent unsorted | **ConcurrentHashMap** |
| Concurrent + sorted + range ops | **ConcurrentSkipListMap** |

---

## HashMap<K,V>
**What:** General‑purpose hash table.  
**Nulls:** 1 null key; many null values.  
**Thread‑safe:** No.  
**Complexity:** avg O(1) get/put, worst O(n).

```java
Map<String,Integer> m = new HashMap<>();
m.put("a",1);
m.merge("a",1,Integer::sum);       // a -> 2
m.getOrDefault("x", 0);            // 0
```

**Use when:** You need speed and don’t care about iteration order.

---

## LinkedHashMap<K,V>
**What:** HashMap + predictable iteration order (insertion or access).  
**Nulls:** Same as HashMap.  
**Thread‑safe:** No.  
**Complexity:** ~HashMap.

```java
// Insertion order preserved
Map<String,Integer> ins = new LinkedHashMap<>();
ins.put("x",1); ins.put("y",2); // iterates x,y

// LRU cache (access‑order)
Map<String,String> lru = new LinkedHashMap<>(16,0.75f,true){
  protected boolean removeEldestEntry(Map.Entry<String,String> e){ return size()>2; }
};
lru.put("a","1"); lru.put("b","2"); lru.put("c","3");
lru.get("a"); lru.put("d","4"); // evicts least‑recent ("b")
```

**Use when:** You need deterministic order or a tiny LRU cache.

---

## TreeMap<K,V>
**What:** Red‑black tree; keys kept **sorted**; `NavigableMap` operations.  
**Nulls:** No null keys (values may be null).  
**Thread‑safe:** No.  
**Complexity:** O(log n) get/put.

```java
NavigableMap<Integer,String> tm = new TreeMap<>();
tm.put(10,"A"); tm.put(3,"B"); tm.put(7,"C");
tm.subMap(3,true,10,false);   // keys [3,10)
tm.ceilingEntry(8);            // -> 10=A
```

**Use when:** You need sorted keys or range queries.

---

## IdentityHashMap<K,V>
**What:** Keys compared by **reference (==)**, not `equals`.  
**Nulls:** Allows one null key.  
**Thread‑safe:** No.  
**Complexity:** ~HashMap.

```java
Map<String,Integer> id = new IdentityHashMap<>();
id.put(new String("A"), 1);
id.put(new String("A"), 2); // size==2 (different objects)
```

**Use when:** Implementing object‑identity tables, graph algorithms, interning. Not a general cache.

---

## WeakHashMap<K,V>
**What:** Keys are **weakly referenced**; entries disappear after GC when key has no strong refs.  
**Nulls:** Allows null key/value.  
**Thread‑safe:** No.  
**Complexity:** ~HashMap (cleanup is GC‑driven).

```java
Map<Object,String> weak = new WeakHashMap<>();
Object key = new Object();
weak.put(key, "temp");
key = null; System.gc();
// entry eventually removed; timing is non‑deterministic
```

**Use when:** You want GC‑sensitive caches/metadata keyed by other objects.

---

## EnumMap<K extends Enum<K>,V>
**What:** Array‑backed, ultra‑fast map for a **single enum type**.  
**Nulls:** Key cannot be null; values may be null.  
**Thread‑safe:** No.  
**Complexity:** O(1) get/put.

```java
enum State { NEW, RUNNING, DONE }
EnumMap<State,Integer> em = new EnumMap<>(State.class);
em.merge(State.NEW,1,Integer::sum);
em.merge(State.DONE,1,Integer::sum);
```

**Use when:** Keys are an enum; need speed and predictable enum order.

---

## Properties (extends Hashtable<String,String>)
**What:** String‑only map with `.properties` file I/O and defaults chain.  
**Nulls:** No nulls.  
**Thread‑safe:** Yes (via Hashtable’s legacy synchronization).  
**Complexity:** ~Hashtable.

```java
Properties p = new Properties();
p.setProperty("host","localhost");
try (var out = Files.newOutputStream(Path.of("app.properties"))) { p.store(out, "cfg"); }
```

**Use when:** Reading/writing Java `.properties` config files or legacy settings.

---

## ConcurrentHashMap<K,V>
**What:** Scalable concurrent hash map (lock striping + non‑blocking reads).  
**Nulls:** No null keys/values.  
**Thread‑safe:** Yes.  
**Complexity:** amortized O(1) get/put under contention.

```java
ConcurrentMap<String,Long> chm = new ConcurrentHashMap<>();
chm.merge("hits", 1L, Long::sum);
chm.computeIfAbsent("users", k -> 0L);
```

**Use when:** Many threads update/read without needing ordering.

---

## ConcurrentSkipListMap<K,V>
**What:** Concurrent, **sorted** map based on skip‑lists; `ConcurrentNavigableMap`.  
**Nulls:** No null keys/values.  
**Thread‑safe:** Yes.  
**Complexity:** O(log n) get/put with predictable concurrency.

```java
ConcurrentNavigableMap<Integer,String> cslm = new ConcurrentSkipListMap<>();
cslm.put(2,"B"); cslm.put(1,"A"); cslm.put(3,"C");
cslm.headMap(2,true);   // <= 2
```

**Use when:** You need both concurrency and sorted/range operations.

---

## Wrappers & factories (for completeness)
- `Collections.unmodifiableMap(m)` — read‑only **view** of `m` (backed; UOE on writes).
- `Collections.synchronizedMap(m)` — coarse‑grained synchronized wrapper (legacy).
- `Map.of(...)`, `Map.copyOf(...)` — truly **immutable** maps (no backing link).

```java
Map<String,Integer> base = new HashMap<>();
Map<String,Integer> ro = Collections.unmodifiableMap(base); // view over base
Map<String,Integer> imm = Map.of("a",1,"b",2);            // immutable
```

