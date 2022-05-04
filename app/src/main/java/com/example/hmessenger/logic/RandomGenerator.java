package com.example.hmessenger.logic;

public  class RandomGenerator {

    private MovableObject movableObject;

    public  MovableObject generate(){
        // TODO Temporary Returned Value
        int randomChoose = (int) (Math.random()*4 +1);

        switch(randomChoose){
            case 1 :	return (movableObject = new TPattern());
            case 2 :	return (movableObject = new BoxPattern());
            case 3 :	return (movableObject = new LinePattern());
            case 4 :	return (movableObject = new LPattern());
        }

        return null;
    }

    public MovableObject getLastPattern(){
        return movableObject;
    }
}

