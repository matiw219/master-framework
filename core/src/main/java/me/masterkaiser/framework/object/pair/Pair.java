package me.masterkaiser.framework.object.pair;

import org.jetbrains.annotations.Nullable;

public class Pair<L, R> {
    private L left;
    private R right;

    public Pair(@Nullable L left, @Nullable R right) {
        this.left = left;
        this.right = right;
    }

    public @Nullable L getLeft() {
        return left;
    }

    public @Nullable R getRight() {
        return right;
    }

    public Pair<L, R> setLeft(@Nullable L left) {
        this.left = left;
        return this;
    }

    public Pair<L, R> setRight(@Nullable R right) {
        this.right = right;
        return this;
    }

    public boolean isNullOne() {
        return (right == null || left == null);
    }
}
