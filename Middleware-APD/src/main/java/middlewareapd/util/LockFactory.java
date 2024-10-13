// working LockFactory
package middlewareapd.util;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.*;


/**
 * The {@code LockFactory} class is responsible for creating and accessing locks across
 * multiple threads. It allows locks to be identified by a unique name, which is 
 * represented as a {@code String}. The class internally maintains a mapping 
 * from lock names to their corresponding lock objects.
 * 
 * This class supports two types of locks:
 * <ul>
 *   <li>{@link ReentrantLock} - A simple reentrant lock.</li>
 *   <li>{@link ReentrantReadWriteLock} - A reentrant read-write lock, allowing 
 *   multiple readers or a single writer.</li>
 * </ul>
 * 
 * <p>Concurrent access to the {@code LockFactory} is managed using a 
 * {@link ConcurrentHashMap}, ensuring thread-safe operations for adding 
 * and acquiring locks.</p>
 */
public class LockFactory {

  /**
   * A concurrent hash map for storing {@code ReentrantLock} instances. 
   * This map allows for thread-safe access and manipulation of locks.
   */
  private static final ConcurrentHashMap<String, ReentrantLock> LOCK_MAP = 
      new ConcurrentHashMap<>();

  /**
   * Retrieves a {@code ReentrantLock} identified by the specified lock name. 
   * If a lock with the given name does not exist, a new one is created and 
   * added to the map.
   *
   * @param lockName the name of the lock to retrieve or create
   * @return the {@code ReentrantLock} associated with the specified lock name
   */
  public static Lock getLock(String lockName) {
    Lock lock = LOCK_MAP.putIfAbsent(lockName, new ReentrantLock());
    if (lock == null) {
      lock = LOCK_MAP.get(lockName);
    }
    return lock;
  }

  // ------------------------------------------------------------------------------------------
  
  /**
   * A concurrent hash map for storing {@code ReentrantReadWriteLock} instances. 
   * This map allows for thread-safe access and manipulation of read-write locks.
   */
  private static final ConcurrentHashMap<String, ReentrantReadWriteLock> RWLOCK_MAP = 
      new ConcurrentHashMap<>();

  /**
   * Retrieves a {@code ReentrantReadWriteLock} identified by the specified lock name. 
   * If a read-write lock with the given name does not exist, a new one is created 
   * and added to the map.
   *
   * @param lockName the name of the read-write lock to retrieve or create
   * @return the {@code ReentrantReadWriteLock} associated with the specified lock name
   */
  public static ReadWriteLock getRWLock(String lockName) {
    ReadWriteLock lock = RWLOCK_MAP.putIfAbsent(lockName, new ReentrantReadWriteLock());
    if (lock == null) {
      lock = RWLOCK_MAP.get(lockName);
    }
    return lock;
  }

  /**
   * A simple main method for testing the functionality of the {@code LockFactory}. 
   * It attempts to retrieve both read-write locks and reentrant locks, asserting 
   * that they are not null.
   *
   * @param args command line arguments (not used)
   */
  public static void main(String[] args) {
    // Test the Factory
    Lock lock = LockFactory.getRWLock("RWL a").readLock();
    assert (lock != null);

    lock = LockFactory.getRWLock("RWL b").writeLock();
    assert (lock != null);

    lock = LockFactory.getLock("RL a");
    assert (lock != null);
  }
}