package edu.hawaii.ics.csdl.simplejcs;

/*import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Ignore;
import org.junit.Test;
*/
/**
 * Tests the SimpleJcs class.
 * 
 * @author Philip Johnson
 * 
 */
public class TestSimpleJcs {
  
  /** Put the cache files in the build dir. **/
  private static final String testDir = System.getProperty("user.dir") + "/build/testsimplejcs";

  /**
   * Test simple cache put and get.
   */
  /*@Test
  public void testSimpleCache() {
    // Create a cache
    String key = "key";
    String value = "value";
    SimpleJcs<String, String> cache = new SimpleJcs<String, String>("TestSimpleCache", testDir);
    cache.clear();
    cache.put(key, value);
    assertEquals("Checking simple get", value, cache.get(key));
    assertTrue("Checking keys", cache.getKeys().contains(key));
    cache.remove(key);
    assertNull("Checking non-existant get", cache.get(key));
    assertEquals("Checking size", 0, cache.size());
  }*/
  
  /**
   * Tests addition and deletion of hierarchical cache entries. 
   * The idea is that if you use keys with the ":" to separate parts, you can delete
   * collections of cache elements with one call by specifying the colon delimited hierarchy.
   * See http://jakarta.apache.org/jcs/faq.html#hierarchical-removal for details. 
   * Unfortunately this does not work.
   */
  /*@Ignore
  @Test
  public void testHierarchicalKeyCacheRemoval() {
    // Create a cache. 
    SimpleJcs<String, String> cache = new SimpleJcs<String, String>("HierarchicalCache", testDir);
    cache.clear();
    Logger.getLogger("org.apache.jcs").setLevel(Level.ALL);
    // Add three elements.
    cache.put("foo:bar:baz", "one");
    cache.put("foo:bar:qux", "two");
    cache.put("bar:quxx", "three");
    cache.remove("foo:bar:");
    assertNull("Checking foo:bar:baz is gone", cache.get("foo:bar:baz"));
    assertNull("Checking foo:bar:qux is gone", cache.get("foo:bar:qux"));
    assertEquals("Checking bar:qux is still there", "three", cache.get("bar:quxx"));
  }*/
  
  /**
   * Tests addition and deletion of grouped cache entries. 
   */
  /*@Test
  public void testGroupedElementsCache() {
    // Create a cache. 
    SimpleJcs<String, String> cache = new SimpleJcs<String, String>("GroupedKeyCache", testDir);
    cache.clear();
    String group1 = "group1";
    String group2 = "group2";
    String one = "one";
    String two = "two";
    String three = "three";
    cache.putInGroup(one, group1, "1");
    cache.putInGroup(two, group1, "2");
    cache.putInGroup(three, "group2", "3");
    assertEquals("Test simple group retrieval1", "1", cache.getFromGroup(one, group1));
    assertEquals("Test simple group retrieval2", "2", cache.getFromGroup(two, group1));
    assertEquals("Test simple group retrieval3", "3", cache.getFromGroup(three, "group2"));
    assertNull("Test non-group retrieval won't get the element", cache.get(one));
    assertEquals("Test group1 keyset", 2, cache.getGroupKeys(group1).size());
    assertEquals("Test group2 keyset", 1, cache.getGroupKeys(group2).size());
    cache.removeFromGroup(one, group1);
    assertEquals("Test new group1 keyset", 1, cache.getGroupKeys(group1).size());
    assertTrue("Test group1 keyset element", cache.getGroupKeys(group1).contains(two));
    cache.clearGroup(group1);
    assertEquals("Test clearGroup", 0, cache.getGroupKeys(group1).size());
    assertNull("Test clearGroup 2", cache.getFromGroup(one, group1));
  }*/
  
 
  /**
   * Test simple cache put and get.
   */
  /*@Test
  public void testDisposeCache() {
    // Create a cache
    String key = "key";
    String value = "value";
    String cacheName = "TestDisposeCache";
    SimpleJcs<String, String> cache = new SimpleJcs<String, String>(cacheName, testDir);
    cache.clear();
    cache.put(key, value);
    assertEquals("Checking simple get", value, cache.get(key));
    // now dispose and try again.
    SimpleJcs.dispose(cacheName);
    cache = new SimpleJcs<String, String>(cacheName, testDir);
    cache.put(key, value);
    assertEquals("Checking simple get 2", value, cache.get(key));
  }*/

  /**
   * Test use of disk cache and the capacity value.
   */
  /*@Test
  public void testDiskCache() {
    // Create a cache
    Long maxLifeSeconds = 100L;
    Long capacity = 100L;
    SimpleJcs<Integer, Integer> cache = 
      new SimpleJcs<Integer, Integer>("TestDiskCache", testDir, maxLifeSeconds, capacity, null);
    cache.clear();
    // Now do a loop and put 200 items in it.
    for (Integer i = 0; i < 200; i++) {
      cache.put(i, i);
    }
    // Now check to see that we can retrieve the last 100 items
    for (Integer i = 100; i < 200; i++) {
      assertEquals("Checking retrieval", i, cache.get(i));
    }
  }*/
  
  /**
   * Test that we can expire elements from the cache. 
   * @throws Exception If problems occur.
   */
  /*@Test
  public void testElementExpiration() throws Exception {
    // Create a cache with maxLife of 1 second and a maximum of 100 elements.
    Long maxLifeSeconds = 1L;
    Long capacity = 100L;
    SimpleJcs<Integer, Integer> cache = 
      new SimpleJcs<Integer, Integer>("TestExpiration", testDir, maxLifeSeconds, capacity, null);
    cache.clear();

    // Put 200 elements in the cache (that can only hold 100 elements)
    for (Integer i = 0; i < 200; i++) {
      cache.put(i, i);
    }
    // Now wait for two seconds.
    Thread.sleep(2000);
    // Now check to see that all of the items are actually gone.
    for (Integer i = 0; i < 200; i++) {
      assertNull("Checking retrieval", cache.get(i));
    }
    // Add an element that expires in 1 second.
    cache.put(300, 300, 2.77e-4D);
    // Check to make sure it's there.
    assertEquals("Check non-expired element", Integer.valueOf(300), cache.get(300));
    // Now wait one more second, enough time for our custom maxLife time to be exceeded.
    Thread.sleep(1001);
    // Now see that our element with the custom maxLife time is now gone.
    assertNull("Check expired element", cache.get(300));
  }*/
}
