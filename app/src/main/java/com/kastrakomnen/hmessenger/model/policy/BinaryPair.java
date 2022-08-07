package com.kastrakomnen.hmessenger.model.policy;

public interface BinaryPair<T, K> {
    boolean getBinaryEvent(T t, K k);
}
