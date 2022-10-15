import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class StringListImplTest {

    private final StringListImpl stringList = new StringListImpl();

    @BeforeEach
    public void init() {
        stringList.add("1");
        stringList.add("2");
        stringList.add("3");
        stringList.add("4");
        stringList.add("5");
    }

    @Test
    void add() {
        assertTrue(stringList.contains("1"));
        assertFalse(stringList.contains(null));
        assertEquals(stringList.indexOf("1"), 0);
        assertEquals(stringList.size(), 5);
        assertEquals(stringList.add("6"), "6");
        assertThrows(NullPointerException.class, () -> stringList.add(null));
    }


    @Test
    void testAdd() {
        stringList.add(1, "test");
        assertEquals(stringList.indexOf("test"), 1);
        assertEquals(stringList.indexOf("2"), 2);
        assertFalse(stringList.contains(null));
        assertThrows(NullPointerException.class, () -> stringList.add(null));
        assertThrows(RuntimeException.class, () -> stringList.add(1203, "test3"));
        assertThrows(RuntimeException.class, () -> stringList.add(-12, "test3"));

    }

    @Test
    void set() {
        stringList.set(0, "test");
        assertFalse(stringList.contains("1"));
        assertTrue(stringList.contains("test"));
        assertFalse(stringList.contains(null));
        assertEquals(stringList.indexOf("test"), 0);
        assertThrows(RuntimeException.class, () -> stringList.set(1203, "test3"));
        assertThrows(RuntimeException.class, () -> stringList.set(-12, "test3"));
        assertThrows(NullPointerException.class, () -> stringList.set(1, null));
        stringList.set(2, "check");
        assertEquals(2, stringList.indexOf("check"));
        assertEquals(stringList.add("test11"), "test11");
    }

    @Test
    void remove() {
        assertEquals("1", stringList.remove("1"));
        assertFalse(stringList.contains("1"));
        assertFalse(stringList.contains(null));
        assertThrows(NullPointerException.class, () -> stringList.remove(null));
        assertThrows(NullPointerException.class, () -> stringList.remove("check"));
    }

    @Test
    void testRemove() {
        assertThrows(NullPointerException.class, () -> stringList.remove(1000));
        assertThrows(NullPointerException.class, () -> stringList.remove(-1));
        assertEquals("1", stringList.remove(0));
        assertFalse(stringList.contains(null));
        assertFalse(stringList.contains("1"));
    }

    @Test
    void contains() {
        assertFalse(stringList.contains("hi"));
        assertTrue(stringList.contains("1"));
    }

    @Test
    void indexOf() {
        assertEquals(-1, stringList.indexOf("hi"));
        assertEquals(0, stringList.indexOf("1"));
        assertThrows(NullPointerException.class, () -> stringList.indexOf(null));
    }

    @Test
    void lastIndexOf() {
        assertEquals(-1, stringList.lastIndexOf("hi"));
        assertEquals(4, stringList.lastIndexOf("1"));
        assertThrows(NullPointerException.class, () -> stringList.lastIndexOf(null));
    }

    @Test
    void get() {
        assertEquals("1", stringList.get(0));
        assertThrows(NullPointerException.class, () -> stringList.get(1000));
        assertThrows(NullPointerException.class, () -> stringList.get(-1));
    }

    @Test
    void testEquals() {
        StringListImpl test1 = new StringListImpl("1", "2", "3", "4", "5");
        assertTrue(Arrays.equals(stringList.toArray(), test1.getArr()));
        StringListImpl test2 = new StringListImpl("1", "check", "3", "4", "5");
        assertFalse(Arrays.equals(stringList.toArray(), test2.getArr()));
    }

    @Test
    void size() {
        assertEquals(5, stringList.size());
    }

    @Test
    void isEmpty() {
        stringList.clear();
        assertTrue(stringList.isEmpty());
    }

    @Test
    void clear() {
        stringList.clear();
        assertEquals(0, stringList.size());
    }

}