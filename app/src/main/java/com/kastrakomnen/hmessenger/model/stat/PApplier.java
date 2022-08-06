package com.kastrakomnen.hmessenger.model.stat;

public class PApplier<Agent, Product> {

    Distribution<Agent> distribution;
    PApplierFunction<Agent, Product> pApplierFunction;

    public PApplier(
            Distribution<Agent> distribution,
            PApplierFunction<Agent, Product> pApplierFunction
    ){
        this.distribution = distribution;
        this.pApplierFunction = pApplierFunction;
    }

    public Product apply(){
        return pApplierFunction.apply(distribution.next());
    }

    public Product apply(Product product){
        return pApplierFunction.apply(distribution.next(), product);
    }
}
