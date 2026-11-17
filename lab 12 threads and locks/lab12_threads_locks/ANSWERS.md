# Lab 12 – Answers

Run:
- Fixed pool: `mvn clean package exec:java`
- Cached pool: `mvn clean package exec:java -Dexec.mainClass=edu.psu.se411.lab12_threads.App2`

## 1. Fixed thread pool vs cached thread pool

Measured on a 24-core machine (results vary per run/machine):

| Case | Fixed pool (24 threads) | Cached pool |
|---|---|---|
| 1000 accounts, 1,000,000 transactions | ~250–400 ms | ~1400 ms |
| 10 accounts, 1,000,000 transactions | ~200–450 ms | ~1900 ms |

**Observation:**
- The fixed thread pool is faster in both cases. Each transaction is a very short task, so a small
  number of reused threads (one per core) is enough. The cached pool creates a new thread whenever
  no idle thread is available, so under a flood of tasks it creates many threads; the cost of
  creating them, context switching and contention outweighs any benefit.
- With only 10 accounts, contention is much higher (many threads compete for the same 10 locks).
  This hurts the cached pool the most because it has many more threads blocked waiting on locks.
  With 1000 accounts, two transactions rarely touch the same account, so there is less waiting.
- In both cases the total money stays constant, so the synchronization is correct.

## 2. Replacing `synchronized` with locks – what could go wrong?

If each transaction simply does `sender.lock(); receiver.lock();`, a **deadlock** can occur:
- Transaction T1 (A → B) locks A, then waits for B.
- Transaction T2 (B → A) locks B, then waits for A.
- Each thread holds the lock the other one needs, so both wait forever and the program never ends.

(Also, if an exception such as `InsufficientBalanceException` is thrown after `lock()` without a
`try/finally`, the lock is never released and every later transaction on that account blocks.)

## 3. Solution: resource ordering

`CryptoAccount` implements `Comparable` using its integer id (a total order). In
`Transaction.execute()` the account with the smaller id is always locked first, then the larger
one, and both are released in `finally` blocks. Since every thread acquires locks in the same
global order, a circular wait is impossible, so deadlock cannot happen.
