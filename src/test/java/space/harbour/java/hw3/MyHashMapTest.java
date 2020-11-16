package space.harbour.java.hw3;



import java.util.AbstractMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.junit.Assert;
import org.junit.Test;

public class MyHashMapTest {

    @Test
    public void testSize()  {
        MyHashMap<Integer, Integer>  testHm = new MyHashMap<>();
        Assert.assertEquals(testHm.size(), 0);

        testHm.put(3, 0);
        Assert.assertEquals(testHm.size(), 1);

    }

    @Test
    public void testIsEmpty()  {
        MyHashMap<Integer, Integer>  testHm = new MyHashMap<>();
        testHm.put(3, 0);
        Assert.assertFalse(testHm.isEmpty());

        testHm.remove(3);
        Assert.assertTrue(testHm.isEmpty());

    }

    @Test
    public void testContainsKey() {
        MyHashMap<Integer, Integer>  testHm = new MyHashMap<>();
        testHm.put(3, 0);
        Assert.assertTrue(testHm.containsKey(3));


        testHm.remove(3);
        Assert.assertFalse(testHm.containsKey(3));

    }

    @Test
    public void testContainsValue() {
        MyHashMap<Integer, Integer>  testHm = new MyHashMap<>();
        testHm.put(3, 0);
        Assert.assertFalse(testHm.containsValue(3));
        Assert.assertTrue(testHm.containsValue(0));

        testHm.remove(3);
        Assert.assertFalse(testHm.containsValue(0));
    }

    @Test
    public void testGet() {
        MyHashMap<Integer, Integer>  testHm = new MyHashMap<>();
        testHm.put(3, 0);

        Assert.assertEquals((int) testHm.get(3), 0);
        Assert.assertNull(testHm.get(4));
    }

    @Test
    public void testGetNull() {
        MyHashMap<Integer, Integer>  testHm = new MyHashMap<>();
        Assert.assertNull(testHm.get(null));
    }

    @Test
    public void testPut() {
        MyHashMap<Integer, Integer>  testHm = new MyHashMap<>();

        testHm.put(3, 0);
        Assert.assertEquals((int) testHm.get(3), 0);
        Assert.assertEquals(1, testHm.size());

        //overide values
        testHm.put(3, 0);
        Assert.assertEquals(1, testHm.size());

    }

    @Test
    public void testRemove() {
        MyHashMap<Integer, Integer>  testHm = new MyHashMap<>();
        testHm.put(3, 0);
        Assert.assertEquals(1, testHm.size());

        testHm.remove(3);
        Assert.assertEquals(0, testHm.size());
        Assert.assertFalse(testHm.containsKey(3));

    }

    @Test
    public void testRemoveNonExistant() {
        MyHashMap<Integer, Integer>  testHm = new MyHashMap<>();

        testHm.remove(3);
        Assert.assertEquals(0, testHm.size());
        Assert.assertFalse(testHm.containsKey(3));
    }

    @Test
    public void testRemoveNull() {
        MyHashMap<Integer, Integer>  testHm = new MyHashMap<>();

        testHm.remove(null);
        Assert.assertEquals(0, testHm.size());
        Assert.assertFalse(testHm.containsKey(null));
    }

    @Test
    public void testPutAll() {
        HashMap<Integer, Integer> full = new HashMap<>();
        full.put(0, 122);
        full.put(4, 142);
        full.put(6, 15);
        full.put(120, 14);

        MyHashMap<Integer, Integer>  testHm = new MyHashMap<>();
        testHm.putAll(full);
        Assert.assertEquals(4, testHm.size());

        Assert.assertEquals(122, (int) testHm.get(0));
        Assert.assertEquals(4, testHm.size());

    }

    @Test
    public void testPutAllEmpty() {
        MyHashMap<Integer, Integer>  testHm = new MyHashMap<>();

        HashMap<Integer, Integer> empty = new HashMap<>();
        testHm.putAll(empty);
        Assert.assertEquals(0, testHm.size());
    }

    @Test
    public void testClear() {
        MyHashMap<Integer, Integer>  testHm = new MyHashMap<>();
        testHm.put(3, 0);
        testHm.put(7, 4);
        testHm.put(6, 5);
        testHm.put(4, 6);

        Assert.assertEquals(4, testHm.size());

        testHm.clear();
        Assert.assertEquals(0, testHm.size());

        testHm.clear();
        Assert.assertFalse(testHm.containsKey(3));
    }

    @Test
    public void testKeySet() {
        MyHashMap<Integer, Integer>  testHm = new MyHashMap<>();
        testHm.put(3, 0);
        testHm.put(7, 4);
        testHm.put(6, 5);
        testHm.put(4, 6);
        testHm.put(18, 6);

        Object[] keys = {18, 3, 4, 6, 7};
        Assert.assertArrayEquals(keys, testHm.keySet().toArray());

    }

    @Test
    public void testValues() {
        MyHashMap<Integer, Integer>  testHm = new MyHashMap<>();
        testHm.put(3, 0);
        testHm.put(7, 4);
        testHm.put(6, 5);
        testHm.put(4, 6);
        testHm.put(18, 6);

        Object[] keys = {0, 4, 5, 6};
        Assert.assertArrayEquals(keys, testHm.values().toArray());
    }

    @Test
    public void testPutNullKey() {
        MyHashMap<Integer, Integer>  testHm = new MyHashMap<>();
        testHm.put(null, null);
        Assert.assertEquals(0, testHm.size());

    }

    @Test
    public void testPutNullValue() {
        MyHashMap<Integer, Integer>  testHm = new MyHashMap<>();
        testHm.put(12122, null);
        Assert.assertNull(testHm.get(12122));
    }

    @Test
    public void testContainsNullValue() {
        MyHashMap<Integer, Integer>  testHm = new MyHashMap<>();
        testHm.put(12122, null);
        Assert.assertTrue(testHm.containsValue(null));
    }

    @Test
    public void testContainsNullKey() {
        MyHashMap<Integer, Integer> testHm = new MyHashMap<>();
        testHm.put(12122, null);
        Assert.assertFalse(testHm.containsKey(null));
    }

    @Test
    public void testEntrySet() {
        MyHashMap<Integer, Integer>  testHm = new MyHashMap<>();
        testHm.put(3, 0);
        testHm.put(7, 4);
        testHm.put(6, 5);
        testHm.put(4, 6);
        testHm.put(18, 6);

        Set<Map.Entry<Integer, Integer>> testEs = new HashSet<>();
        testEs.add(new AbstractMap.SimpleEntry<>(3, 0));
        testEs.add(new AbstractMap.SimpleEntry<>(7, 4));
        testEs.add(new AbstractMap.SimpleEntry<>(6, 5));
        testEs.add(new AbstractMap.SimpleEntry<>(4, 6));
        testEs.add(new AbstractMap.SimpleEntry<>(18, 6));

        Set<Map.Entry<Integer, Integer>> resultEs = testHm.entrySet();

        Assert.assertEquals(testEs.size(), resultEs.size());
        Assert.assertTrue(testEs.containsAll(resultEs));
    }

}