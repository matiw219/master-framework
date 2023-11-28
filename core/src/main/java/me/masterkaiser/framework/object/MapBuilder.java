package me.masterkaiser.framework.object;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@NoArgsConstructor
public class MapBuilder<K, V> {
    private Map<K, V> map = new HashMap<>();

    public void setLinked(boolean linked) {
        if (linked) {
            map = new LinkedHashMap<>(map);
        } else {
            map = new HashMap<>(map);
        }
    }

    public MapBuilder(K key, V value) {
        map.put(key, value);
    }

    public MapBuilder(@NotNull Map<K, V> map) {
        this.map.putAll(map);
    }

    public MapBuilder<K, V> put(K key, V value) {
        map.put(key, value);
        return this;
    }

    public MapBuilder<K, V> putSome(K[] keys, V value) {
        Arrays.stream(keys).forEach(key -> map.put(key, value));
        return this;
    }

    public Map<K, V> build() {
        return map;
    }

    public MapBuilder(K key, V value, K key1, V value1) {
        map.put(key, value);
        map.put(key1, value1);
    }

    public MapBuilder(K key, V value, K key1, V value1, K key2, V value2) {
        map.put(key, value);
        map.put(key1, value1);
        map.put(key2, value2);
    }

    public MapBuilder(K key, V value, K key1, V value1, K key2, V value2, K key3, V value3) {
        map.put(key, value);
        map.put(key1, value1);
        map.put(key2, value2);
        map.put(key3, value3);
    }

    public MapBuilder(K key, V value, K key1, V value1, K key2, V value2, K key3, V value3,
                      K key4, V value4) {
        map.put(key, value);
        map.put(key1, value1);
        map.put(key2, value2);
        map.put(key3, value3);
        map.put(key4, value4);
    }
    public MapBuilder(K key, V value, K key1, V value1, K key2, V value2, K key3, V value3,
                      K key4, V value4, K key5, V value5) {
        map.put(key, value);
        map.put(key1, value1);
        map.put(key2, value2);
        map.put(key3, value3);
        map.put(key4, value4);
        map.put(key5, value5);
    }

    public MapBuilder(K key, V value, K key1, V value1, K key2, V value2, K key3, V value3,
                      K key4, V value4, K key5, V value5, K key6, V value6) {
        map.put(key, value);
        map.put(key1, value1);
        map.put(key2, value2);
        map.put(key3, value3);
        map.put(key4, value4);
        map.put(key5, value5);
        map.put(key6, value6);
    }

    public MapBuilder(K key, V value, K key1, V value1, K key2, V value2, K key3, V value3,
                      K key4, V value4, K key5, V value5, K key6, V value6, K key7, V value7) {
        map.put(key, value);
        map.put(key1, value1);
        map.put(key2, value2);
        map.put(key3, value3);
        map.put(key4, value4);
        map.put(key5, value5);
        map.put(key6, value6);
        map.put(key7, value7);
    }

    public MapBuilder(K key, V value, K key1, V value1, K key2, V value2, K key3, V value3,
                      K key4, V value4, K key5, V value5, K key6, V value6, K key7, V value7,
                      K key8, V value8) {
        map.put(key, value);
        map.put(key1, value1);
        map.put(key2, value2);
        map.put(key3, value3);
        map.put(key4, value4);
        map.put(key5, value5);
        map.put(key6, value6);
        map.put(key7, value7);
        map.put(key8, value8);
    }

    public MapBuilder(K key, V value, K key1, V value1, K key2, V value2, K key3, V value3,
                      K key4, V value4, K key5, V value5, K key6, V value6, K key7, V value7,
                      K key8, V value8, K key9, V value9) {
        map.put(key, value);
        map.put(key1, value1);
        map.put(key2, value2);
        map.put(key3, value3);
        map.put(key4, value4);
        map.put(key5, value5);
        map.put(key6, value6);
        map.put(key7, value7);
        map.put(key8, value8);
        map.put(key9, value9);
    }

    public MapBuilder(K key, V value, K key1, V value1, K key2, V value2, K key3, V value3,
                      K key4, V value4, K key5, V value5, K key6, V value6, K key7, V value7,
                      K key8, V value8, K key9, V value9, K key10, V value10) {
        map.put(key, value);
        map.put(key1, value1);
        map.put(key2, value2);
        map.put(key3, value3);
        map.put(key4, value4);
        map.put(key5, value5);
        map.put(key6, value6);
        map.put(key7, value7);
        map.put(key8, value8);
        map.put(key9, value9);
        map.put(key10, value10);
    }

    public MapBuilder(K key, V value, K key1, V value1, K key2, V value2, K key3, V value3,
                      K key4, V value4, K key5, V value5, K key6, V value6, K key7, V value7,
                      K key8, V value8, K key9, V value9, K key10, V value10, K key11, V value11) {
        map.put(key, value);
        map.put(key1, value1);
        map.put(key2, value2);
        map.put(key3, value3);
        map.put(key4, value4);
        map.put(key5, value5);
        map.put(key6, value6);
        map.put(key7, value7);
        map.put(key8, value8);
        map.put(key9, value9);
        map.put(key10, value10);
        map.put(key11, value11);
    }

    public MapBuilder(K key, V value, K key1, V value1, K key2, V value2, K key3, V value3,
                      K key4, V value4, K key5, V value5, K key6, V value6, K key7, V value7,
                      K key8, V value8, K key9, V value9, K key10, V value10, K key11, V value11,
                      K key12, V value12) {
        map.put(key, value);
        map.put(key1, value1);
        map.put(key2, value2);
        map.put(key3, value3);
        map.put(key4, value4);
        map.put(key5, value5);
        map.put(key6, value6);
        map.put(key7, value7);
        map.put(key8, value8);
        map.put(key9, value9);
        map.put(key10, value10);
        map.put(key11, value11);
        map.put(key12, value12);
    }

    public MapBuilder(K key, V value, K key1, V value1, K key2, V value2, K key3, V value3,
                      K key4, V value4, K key5, V value5, K key6, V value6, K key7, V value7,
                      K key8, V value8, K key9, V value9, K key10, V value10, K key11, V value11,
                      K key12, V value12, K key13, V value13) {
        map.put(key, value);
        map.put(key1, value1);
        map.put(key2, value2);
        map.put(key3, value3);
        map.put(key4, value4);
        map.put(key5, value5);
        map.put(key6, value6);
        map.put(key7, value7);
        map.put(key8, value8);
        map.put(key9, value9);
        map.put(key10, value10);
        map.put(key11, value11);
        map.put(key12, value12);
        map.put(key13, value13);
    }

    public MapBuilder(K key, V value, K key1, V value1, K key2, V value2, K key3, V value3,
                      K key4, V value4, K key5, V value5, K key6, V value6, K key7, V value7,
                      K key8, V value8, K key9, V value9, K key10, V value10, K key11, V value11,
                      K key12, V value12, K key13, V value13, K key14, V value14) {
        map.put(key, value);
        map.put(key1, value1);
        map.put(key2, value2);
        map.put(key3, value3);
        map.put(key4, value4);
        map.put(key5, value5);
        map.put(key6, value6);
        map.put(key7, value7);
        map.put(key8, value8);
        map.put(key9, value9);
        map.put(key10, value10);
        map.put(key11, value11);
        map.put(key12, value12);
        map.put(key13, value13);
        map.put(key14, value14);
    }

    public MapBuilder(K key, V value, K key1, V value1, K key2, V value2, K key3, V value3,
                      K key4, V value4, K key5, V value5, K key6, V value6, K key7, V value7,
                      K key8, V value8, K key9, V value9, K key10, V value10, K key11, V value11,
                      K key12, V value12, K key13, V value13, K key14, V value14, K key15, V value15) {
        map.put(key, value);
        map.put(key1, value1);
        map.put(key2, value2);
        map.put(key3, value3);
        map.put(key4, value4);
        map.put(key5, value5);
        map.put(key6, value6);
        map.put(key7, value7);
        map.put(key8, value8);
        map.put(key9, value9);
        map.put(key10, value10);
        map.put(key11, value11);
        map.put(key12, value12);
        map.put(key13, value13);
        map.put(key14, value14);
        map.put(key15, value15);
    }

    public MapBuilder(K key, V value, K key1, V value1, K key2, V value2, K key3, V value3,
                      K key4, V value4, K key5, V value5, K key6, V value6, K key7, V value7,
                      K key8, V value8, K key9, V value9, K key10, V value10, K key11, V value11,
                      K key12, V value12, K key13, V value13, K key14, V value14, K key15, V value15,
                      K key16, V value16) {
        map.put(key, value);
        map.put(key1, value1);
        map.put(key2, value2);
        map.put(key3, value3);
        map.put(key4, value4);
        map.put(key5, value5);
        map.put(key6, value6);
        map.put(key7, value7);
        map.put(key8, value8);
        map.put(key9, value9);
        map.put(key10, value10);
        map.put(key11, value11);
        map.put(key12, value12);
        map.put(key13, value13);
        map.put(key14, value14);
        map.put(key15, value15);
        map.put(key16, value16);
    }

    public MapBuilder(K key, V value, K key1, V value1, K key2, V value2, K key3, V value3,
                      K key4, V value4, K key5, V value5, K key6, V value6, K key7, V value7,
                      K key8, V value8, K key9, V value9, K key10, V value10, K key11, V value11,
                      K key12, V value12, K key13, V value13, K key14, V value14, K key15, V value15,
                      K key16, V value16, K key17, V value17) {
        map.put(key, value);
        map.put(key1, value1);
        map.put(key2, value2);
        map.put(key3, value3);
        map.put(key4, value4);
        map.put(key5, value5);
        map.put(key6, value6);
        map.put(key7, value7);
        map.put(key8, value8);
        map.put(key9, value9);
        map.put(key10, value10);
        map.put(key11, value11);
        map.put(key12, value12);
        map.put(key13, value13);
        map.put(key14, value14);
        map.put(key15, value15);
        map.put(key16, value16);
        map.put(key17, value17);
    }
    
    public MapBuilder(K key, V value, K key1, V value1, K key2, V value2, K key3, V value3,
                      K key4, V value4, K key5, V value5, K key6, V value6, K key7, V value7,
                      K key8, V value8, K key9, V value9, K key10, V value10, K key11, V value11,
                      K key12, V value12, K key13, V value13, K key14, V value14, K key15, V value15,
                      K key16, V value16, K key17, V value17, K key18, V value18) {
        map.put(key, value);
        map.put(key1, value1);
        map.put(key2, value2);
        map.put(key3, value3);
        map.put(key4, value4);
        map.put(key5, value5);
        map.put(key6, value6);
        map.put(key7, value7);
        map.put(key8, value8);
        map.put(key9, value9);
        map.put(key10, value10);
        map.put(key11, value11);
        map.put(key12, value12);
        map.put(key13, value13);
        map.put(key14, value14);
        map.put(key15, value15);
        map.put(key16, value16);
        map.put(key17, value17);
        map.put(key18, value18);
    }

    public MapBuilder(K key, V value, K key1, V value1, K key2, V value2, K key3, V value3,
                      K key4, V value4, K key5, V value5, K key6, V value6, K key7, V value7,
                      K key8, V value8, K key9, V value9, K key10, V value10, K key11, V value11,
                      K key12, V value12, K key13, V value13, K key14, V value14, K key15, V value15,
                      K key16, V value16, K key17, V value17, K key18, V value18, K key19, V value19) {
        map.put(key, value);
        map.put(key1, value1);
        map.put(key2, value2);
        map.put(key3, value3);
        map.put(key4, value4);
        map.put(key5, value5);
        map.put(key6, value6);
        map.put(key7, value7);
        map.put(key8, value8);
        map.put(key9, value9);
        map.put(key10, value10);
        map.put(key11, value11);
        map.put(key12, value12);
        map.put(key13, value13);
        map.put(key14, value14);
        map.put(key15, value15);
        map.put(key16, value16);
        map.put(key17, value17);
        map.put(key18, value18);
        map.put(key19, value19);
    }

    public static <K, V> MapBuilder<K, V> of(@NotNull Map<K, V> map) {
        return new MapBuilder<>(map);
    }
}
