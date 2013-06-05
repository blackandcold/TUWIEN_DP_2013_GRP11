package model;

/**
 * As java offers no implementation besides AbstractMap.SimpleEntry<,> this class
 * can be used to store two related values.
 * @author Stefan Weghofer
 *
 * @param <K> the type of the key
 * @param <V> the type of the value
 */
public class KeyValue<K, V> {

	private K key;
	private V value;
	
	public KeyValue() {} //empty constructor for jax-ws
	
	public KeyValue(K key, V value) {
		this.key = key;
		this.value = value;
	}
	
	/**
	 * @return the key
	 */
	public K getKey() {
		return key;
	}
	
	/**
	 * @param key the key to set
	 */
	public void setKey(K key) {
		this.key = key;
	}
	
	/**
	 * @return the value
	 */
	public V getValue() {
		return value;
	}
	
	/**
	 * @param value the value to set
	 */
	public void setValue(V value) {
		this.value = value;
	}
	
}
