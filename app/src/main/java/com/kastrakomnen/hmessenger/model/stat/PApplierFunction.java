package com.kastrakomnen.hmessenger.model.stat;

public interface PApplierFunction<Agent, Product> {
    Product apply(Agent agent);
    Product apply(Agent agent, Product product);
}
