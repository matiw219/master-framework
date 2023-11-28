package me.masterkaiser.framework.object.pair;

import org.jetbrains.annotations.Nullable;

public class Triple<L, M, R> {
    private L left;
    private M middle;
    private R right;

    public Triple(@Nullable L left, @Nullable M middle, @Nullable R right) {
        this.left = left;
        this.middle = middle;
        this.right = right;
    }

    public @Nullable L getLeft() {
        return left;
    }

    public @Nullable M getMiddle() {
        return middle;
    }

    public @Nullable R getRight() {
        return right;
    }

    public Triple<L, M, R> setLeft(@Nullable L left) {
        this.left = left;
        return this;
    }

    public Triple<L, M, R> setMiddle(@Nullable M middle) {
        this.middle = middle;
        return this;
    }

    public Triple<L, M, R> setRight(@Nullable R right) {
        this.right = right;
        return this;
    }

    public boolean isNullOne() {
        return (right == null || middle == null || left == null);
    }
}
