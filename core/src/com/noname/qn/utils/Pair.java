package com.noname.qn.utils;

import java.util.Objects;

public class Pair<E1, E2> {
    E1 element1;
    E2 element2;

    public Pair(E1 element1, E2 element2) {
        this.element1 = element1;
        this.element2 = element2;
    }

    public E1 getElement1() {
        return element1;
    }

    public E2 getElement2() {
        return element2;
    }

    @Override
    public String toString() {
        return element1.toString() + " - " + element2.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pair)) return false;
        Pair<?, ?> pair = (Pair<?, ?>) o;
        return Objects.equals(element1, pair.element1) &&
                Objects.equals(element2, pair.element2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(element1, element2);
    }
}
