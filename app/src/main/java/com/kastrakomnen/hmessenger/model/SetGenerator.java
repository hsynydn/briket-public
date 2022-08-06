package com.kastrakomnen.hmessenger.model;

import com.kastrakomnen.hmessenger.model.stat.Distribution;

public class SetGenerator {

    private final Distribution<FormationType> distribution;

    public SetGenerator(Distribution<FormationType> distribution){
        this.distribution = distribution;
    }

    public Set generate(){
        FormationType formationType = distribution.next();
        return SetBuilder.build(formationType);
    }
}
