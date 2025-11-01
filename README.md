# ☕ Java 17 Collections Showcase

A hands-on project demonstrating **Java 17 features** and **core Collections Framework** concepts — including `List`, `Set`, and `Map` implementations, concurrency utilities, streams, and modern patterns like `record`, `sealed` types, and pattern matching for `instanceof`.

---

## 🚀 Tech Stack

| Category | Tools / Frameworks |
|-----------|--------------------|
| Language | **Java 17** |
| Build | **Maven** (Spotless for formatting) |
| Testing | **JUnit 5** |
| IDE | IntelliJ IDEA Ultimate |
| Architecture | Micro-example structure (clean packages per concept) |

---


---

## ⚙️ Build & Run (Maven)

### 1️⃣ Verify environment
- Java 17+ installed
- Maven 3.9+
- IntelliJ IDEA (Ultimate) with Maven integration enabled

### 2️⃣ Run tests
```bash
mvn clean test
```
3️⃣ Run specific demo
```bash
mvn compile exec:java -Dexec.mainClass="MapExamples"
```
4️⃣ Apply Spotless formatting
```bash
mvn spotless:apply
```
🧠 IntelliJ Tip:
Go to Settings → Tools → Actions on Save → Run Maven Goal → add spotless:apply
→ now your code auto-formats on save.

| Concept              | Highlights                                                                                                                                     |
| -------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------- |
| **Collections**      | `List`, `Set`, `Map` + key implementations                                                                                                     |
| **Maps**             | `HashMap`, `LinkedHashMap`, `TreeMap`, `WeakHashMap`, `IdentityHashMap`, `EnumMap`, `ConcurrentHashMap`, `ConcurrentSkipListMap`, `Properties` |
| **Streams**          | `groupingBy`, `mapping`, `reducing`, `flatMap`, `toList()`                                                                                     |
| **Concurrency**      | `ConcurrentHashMap`, `CopyOnWriteArrayList`, thread pools                                                                                      |
| **Java 17 features** | `record`, `sealed` interfaces, pattern matching, text blocks                                                                                   |
| **Testing**          | JUnit 5 tests for each example                                                                                                                 |


| Map                     | Ordered? | Sorted? | Thread-Safe? | Nulls    | Best for                   |
| ----------------------- | -------- | ------- | ------------ | -------- | -------------------------- |
| `HashMap`               | ❌        | ❌       | ❌            | ✅        | General use                |
| `LinkedHashMap`         | ✅        | ❌       | ❌            | ✅        | Deterministic order, LRU   |
| `TreeMap`               | ✅        | ✅       | ❌            | ❌        | Sorted data, range queries |
| `IdentityHashMap`       | ❌        | ❌       | ❌            | ✅        | Identity-based lookup      |
| `WeakHashMap`           | ❌        | ❌       | ❌            | ✅        | GC-sensitive caches        |
| `EnumMap`               | ✅        | ✅       | ❌            | ❌ (keys) | Enum keys                  |
| `Properties`            | ✅        | ❌       | ✅            | ❌        | Config I/O                 |
| `ConcurrentHashMap`     | ❌        | ❌       | ✅            | ❌        | Parallel access            |
| `ConcurrentSkipListMap` | ✅        | ✅       | ✅            | ❌        | Concurrent sorted maps     |

🤝 Contributing

Fork this repo

Create a branch: feature/my-example

Add demo/test

Run mvn spotless:apply && mvn test

Submit a PR


📬 Author

👨‍💻 Gagan Ubbey
Java Architect | Spring Boot | AWS | Microservices
📍 Washington, USA
🔗 https://www.linkedin.com/in/gaganubbey/
