package Library;

import java.util.Collection;

/**
 * Created by kevin on 3/19/2017.
 */
public interface DB<K,V> {
    /**
     * Add a value entry to the database.
     * The database will determine the key based on the value type.
     * @param value - the value to add
     * @return the previous value associated with the key
     */
    V addValue(V value);

    /**
     * Get the value for a given key.
     * @param key - the key
     * @return the value that is associated with the key, return null if not present
     */
    V getValue(K key);

    /**
     * Indicates whether a key is in the database or not, in constant time.
     * @param key - the key to search for
     * @return whether the given key is present.
     */
    boolean hasKey(K key);

    /**
     * Get all the values in the database in linear time.
     * @return all the values
     */
    Collection<V> getAllValues();

}
