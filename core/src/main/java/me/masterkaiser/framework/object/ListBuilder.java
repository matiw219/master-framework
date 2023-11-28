package me.masterkaiser.framework.object;

import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@NoArgsConstructor
public class ListBuilder<V> {
    private final List<V> list = new ArrayList<>();

    public List<V> build() {
        return list;
    }

    public ListBuilder<V> add(V value) {
        list.add(value);
        return this;
    }

    public ListBuilder(@NotNull List<V> list) {
        list.addAll(this.list);
    }

    public ListBuilder(V value) {
        list.add(value);
    }
    public ListBuilder(V value, V value2) {
        addSome(value, value2);
    }

    public ListBuilder(V value, V value2, V value3) {
        addSome(value, value2, value3);
    }

    public ListBuilder(V value, V value2, V value3, V value4) {
        addSome(value, value2, value3, value4);
    }

    public ListBuilder(V value, V value2, V value3, V value4, V value5) {
        addSome(value, value2, value3, value4, value5);
    }

    public ListBuilder(V value, V value2, V value3, V value4, V value5, V value6) {
        addSome(value, value2, value3, value4, value5, value6);
    }

    public ListBuilder(V value, V value2, V value3, V value4, V value5, V value6, V value7) {
        addSome(value, value2, value3, value4, value5, value6, value7);
    }

    public ListBuilder(V value, V value2, V value3, V value4, V value5, V value6, V value7, V value8) {
        addSome(value, value2, value3, value4, value5, value6, value7, value8);
    }

    public ListBuilder(V value, V value2, V value3, V value4, V value5, V value6, V value7, V value8, V value9) {
        addSome(value, value2, value3, value4, value5, value6, value7, value8, value9);
    }

    @SafeVarargs
    public final void addSome(V... values) {
        list.addAll(Arrays.asList(values));
    }

    public static <V> ListBuilder<V> of(@NotNull List<V> list) {
        return new ListBuilder<>(list);
    }
}
