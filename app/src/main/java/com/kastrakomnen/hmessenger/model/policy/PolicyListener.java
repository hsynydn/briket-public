package com.kastrakomnen.hmessenger.model.policy;

public interface PolicyListener<T, K> {
    void onListen(T t, K k);

    final class Ignore<T, K> implements  PolicyListener<T, K>{
        @Override
        public void onListen(T t, K k) {}
    }
}
