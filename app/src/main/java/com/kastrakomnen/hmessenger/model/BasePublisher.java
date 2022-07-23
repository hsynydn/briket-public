package com.kastrakomnen.hmessenger.model;

import java.util.ArrayList;

public class BasePublisher implements Publisher{

    private final ArrayList<Subscriber> subscribers;

    public BasePublisher(){
        subscribers = new ArrayList<>();
    }

    @Override
    public void register(Subscriber subscriber) {
        subscribers.add(subscriber);
    }

    @Override
    public void publish() {
        for (Subscriber s : subscribers) {
            s.onNotify();
        }
    }
}
