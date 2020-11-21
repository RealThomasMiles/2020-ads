package ru.mail.polis.ads.hash;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class HashTableClass<Key, Value> implements HashTable<Key, Value> {
    private static class Element<Key, Value> {
        private final Key key;
        private Value value;
        private Element<Key, Value> next;

        public Element(Key key, Value value, Element<Key, Value> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    private int size;
    private int capacity;
    private float loadFactor;
    private int currentPrime;
    private Object[] elements;
    private final int[] prime = new int[] {17, 37, 67, 131, 263, 541, 1087, 2179, 4363, 8731, 17467, 34939, 69899, 139801, 279607, 559217, 1118419};

    private int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % capacity;
    }

    private void resize() {
        currentPrime++;
        capacity = prime[currentPrime];
        Object[] temp = elements;
        elements = new Object[capacity];
        for (Object o : temp) {
            @SuppressWarnings("unchecked")
            Element<Key, Value> tempE = (Element<Key, Value>) o;
            put(tempE.key, tempE.value);
        }
    }

    public HashTableClass() {
        size = 0;
        currentPrime = 0;
        capacity = prime[currentPrime];
        loadFactor = 0;
        elements = new Element[capacity];
    }

    @Override
    public @Nullable Value get(@NotNull Key key) {
        int hash = hash(key);
        @SuppressWarnings("unchecked")
        Element<Key, Value> root = (Element<Key, Value>) elements[hash];

        while (root != null) {
            if (root.key.equals(key)) {
                return root.value;
            }
            root = root.next;
        }

        return null;
    }

    @Override
    public boolean containsKey(@NotNull Key key) {
        int hash = hash(key);
        @SuppressWarnings("unchecked")
        Element<Key, Value> root = (Element<Key, Value>) elements[hash];

        while (root != null) {
            if (root.key.equals(key)) {
                return true;
            }
            root = root.next;
        }

        return false;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void put(@NotNull Key key, @NotNull Value value) {
        int hash = hash(key);

        Element<Key, Value> root = (Element<Key, Value>) elements[hash];

        while (root != null) {
            if (root.key.equals(key)) {
                root.value = value;
                return;
            }
            root = root.next;
        }

        size++;
        loadFactor = size / (float) capacity;
        root = (Element<Key, Value>) elements[hash];
        Element<Key, Value> newElement = new Element<>(key, value, root);
        elements[hash] = newElement;

        if (loadFactor > 0.75) resize();
    }

    @Override
    public @Nullable Value remove(@NotNull Key key) {
        if (!containsKey(key)) return null;
        int hash = hash(key);
        @SuppressWarnings("unchecked")
        Element<Key, Value> root = (Element<Key, Value>) elements[hash];

        Element<Key, Value> prev = null;
        while (root != null) {
            if (root.key.equals(key)) {
                break;
            }
            prev = root;
            root = root.next;
        }

        size--;
        assert root != null;
        if (prev != null) {
            prev.next = root.next;
        } else {
            elements[hash] = root.next;
        }

        return root.value;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
