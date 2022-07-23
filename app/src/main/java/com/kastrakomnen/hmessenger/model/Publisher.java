package com.kastrakomnen.hmessenger.model;

public interface Publisher {
    void register(Subscriber subscriber);
    void publish();
}
