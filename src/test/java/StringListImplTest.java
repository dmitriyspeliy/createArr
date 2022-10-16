import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class IntegerListImplTest {

    private final IntegerListImpl stringList = new IntegerListImpl();

    @BeforeEach
    public void init() {
        stringList.add(1);
        stringList.add(2);
        stringList.add(3);
        stringList.add(4);
        stringList.add(5);
    }

    @Test
    void add() {
        assertTrue(stringList.contains(1));
        assertFalse(stringList.contains(null));
        assertEquals(stringList.indexOf(1), 0);
        assertEquals(stringList.size(), 5);
        assertEquals(stringList.add(6), 6);
        assertThrows(NullPointerException.class, () -> stringList.add(null));
    }


    @Test
    void testAdd() {
        stringList.add(1, 121);
        assertEquals(stringList.indexOf(121), 1);
        assertEquals(stringList.indexOf(2), 2);
        assertFalse(stringList.contains(null));
        assertThrows(NullPointerException.class, () -> stringList.add(null));
        assertThrows(RuntimeException.class, () -> stringList.add(1203, 34));
        assertThrows(RuntimeException.class, () -> stringList.add(-12, 34));

    }

    @Test
    void set() {
        stringList.set(0, 14);
        assertFalse(stringList.contains(1));
        assertTrue(stringList.contains(14));
        assertFalse(stringList.contains(null));
        assertEquals(stringList.indexOf(14), 0);
        assertThrows(RuntimeException.class, () -> stringList.set(1203, 3123));
        assertThrows(RuntimeException.class, () -> stringList.set(-12, 3213));
        assertThrows(NullPointerException.class, () -> stringList.set(1, null));
        stringList.set(2, 44);
        assertEquals(2, stringList.indexOf(44));
        assertEquals(stringList.add(55), 55);
    }

    @Test
    void remove() {
        assertEquals(2, stringList.remove(1));
        assertFalse(stringList.contains(2));
        assertFalse(stringList.contains(null));
        assertThrows(NullPointerException.class, () -> stringList.remove(null));
        assertThrows(NullPointerException.class, () -> stringList.remove(1231));
    }

    @Test
    void testRemove() {
        assertThrows(NullPointerException.class, () -> stringList.remove(1000));
        assertThrows(NullPointerException.class, () -> stringList.remove(-1));
        assertEquals(1, stringList.remove(0));
        assertFalse(stringList.contains(null));
        assertFalse(stringList.contains(1));
    }

    @Test
    void contains() {
        assertFalse(stringList.contains(1433));
        assertTrue(stringList.contains(1));
    }

    @Test
    void indexOf() {
        assertEquals(-1, stringList.indexOf(1433));
        assertEquals(0, stringList.indexOf(1));
        assertThrows(NullPointerException.class, () -> stringList.indexOf(null));
    }

    @Test
    void lastIndexOf() {
        assertEquals(-1, stringList.lastIndexOf(1433));
        assertEquals(4, stringList.lastIndexOf(1));
        assertThrows(NullPointerException.class, () -> stringList.lastIndexOf(null));
    }

    @Test
    void get() {
        assertEquals(1, stringList.get(0));
        assertThrows(NullPointerException.class, () -> stringList.get(1000));
        assertThrows(NullPointerException.class, () -> stringList.get(-1));
    }

    @Test
    void testEquals() {
        IntegerListImpl test1 = new IntegerListImpl(1,2,3,4,5);
        assertTrue(Arrays.equals(stringList.toArray(), test1.getArr()));
        IntegerListImpl test2 = new IntegerListImpl(1,12,2,3,4,5);
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

    @Test
    void BinaryResearch() {
        assertFalse(stringList.BinarySearchContains(1433));
        assertTrue(stringList.BinarySearchContains(1));
    }

}