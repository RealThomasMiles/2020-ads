package ru.mail.polis.ads.hash;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Basic tests on {@link HashTable}.
 */
class HashTableBaseTest {

    HashTable<String, String> newTable() {
        // Use implementation
        return new HashTableClass<>();
    }

    @Test
    void emptyTable() {
        HashTable<String, String> table = newTable();
        assertNull(table.get(""));
        assertNull(table.get("some key"));
        assertEquals(0, table.size());
    }


    @Test
    void put() {
        HashTable<String, String> table = newTable();

        int size = 0;
        assertEquals(table.size(), size);
        assertNull(table.get("testStringKey1"));

        table.put("testStringKey1", "testStringValue1");

        assertEquals(table.size(), ++size);
        assertEquals(table.get("testStringKey1"), "testStringValue1");

        table.put("testStringKey2", "testStringValue2");

        assertEquals(table.size(), ++size);
        assertEquals(table.get("testStringKey2"), "testStringValue2");

        table.put("testStringKey2", "case with same value");

        assertEquals(table.size(), size);
        assertEquals(table.get("testStringKey2"), "case with same value");

        table.put("testStringKey3", "testStringValue3");

        assertEquals(table.size(), ++size);
        assertEquals(table.get("testStringKey3"), "testStringValue3");

        table.put("testStringKey", "testStringValue");

        assertEquals(table.size(), ++size);
        assertEquals(table.get("testStringKey"), "testStringValue");
    }

    @Test
    void remove() {
        HashTable<String, String> table = newTable();
        assertNull(table.remove("case when HashTable is empty"));
        assertTrue(table.isEmpty());

        table.put("testStringKey3", "testStringValue3");
        table.put("testStringKey4", "testStringValue4");
        table.put("testStringKey2", "testStringValue2");
        table.put("testStringKey5", "testStringValue5");
        table.put("testStringKey1", "testStringValue1");
        table.put("testStringKey0", "testStringValue0");

        assertFalse(table.isEmpty());
        int size = table.size();

        assertEquals(table.remove("testStringKey4"), "testStringValue4");
        assertEquals(table.size(), --size);
        assertFalse(table.containsKey("testStringKey4"));

        assertEquals(table.remove("testStringKey1"), "testStringValue1");
        assertEquals(table.size(), --size);
        assertFalse(table.containsKey("testStringKey1"));

        assertNull(table.remove("testStringKey1"), "testStringValue1");
        assertEquals(table.size(), size);
        assertFalse(table.isEmpty());
        assertFalse(table.containsKey("testStringKey1"));

        assertEquals(table.remove("testStringKey3"), "testStringValue3");
        assertEquals(table.size(), --size);
        assertFalse(table.containsKey("testStringKey3"));

        assertEquals(table.remove("testStringKey0"), "testStringValue0");
        assertEquals(table.size(), --size);
        assertFalse(table.containsKey("testStringKey0"));

        assertEquals(table.remove("testStringKey2"), "testStringValue2");
        assertEquals(table.size(), --size);
        assertFalse(table.containsKey("testStringKey2"));

        assertEquals(table.remove("testStringKey5"), "testStringValue5");
        assertEquals(table.size(), --size);
        assertFalse(table.containsKey("testStringKey5"));

        assertTrue(table.isEmpty());
    }

    @Test
    void contains() {
        HashTable<String, String> table = newTable();

        assertFalse(table.containsKey("testStringKey"));
        assertFalse(table.containsKey("testStringKey1"));

        table.put("testStringKey", "testStringValue");
        assertTrue(table.containsKey("testStringKey"));
        assertFalse(table.containsKey("testStringKey1"));

        table.put("testStringKey1", "testStringValue1");
        assertTrue(table.containsKey("testStringKey1"));
        assertTrue(table.containsKey("testStringKey"));

        table.remove("testStringKey");
        assertTrue(table.containsKey("testStringKey1"));
        assertFalse(table.containsKey("testStringKey"));

        table.remove("testStringKey1");
        assertFalse(table.containsKey("testStringKey"));
        assertFalse(table.containsKey("testStringKey1"));
    }

    @Test
    void empty() {
        HashTable<String, String> table = newTable();

        assertTrue(table.isEmpty());

        table.put("testStringKey", "testStringValue");
        assertFalse(table.isEmpty());

        table.put("testStringKey1", "testStringValue1");
        assertFalse(table.isEmpty());

        table.remove("testStringKey");
        assertFalse(table.isEmpty());

        table.remove("testStringKey1");
        assertTrue(table.isEmpty());
    }

    @Test
    void replace() {
        HashTable<String, String> table = newTable();

        assertNull(table.get("1"));

        table.put("1", "testStringValue3");
        assertEquals(table.get("1"), "testStringValue3");

        table.put("1", "testStringValue4");
        assertEquals(table.get("1"), "testStringValue4");

        table.put("1", "testStringValue2");
        assertEquals(table.get("1"), "testStringValue2");

        table.put("7", "testStringValue5");
        assertEquals(table.get("7"), "testStringValue5");
        assertEquals(table.get("1"), "testStringValue2");
    }
}
