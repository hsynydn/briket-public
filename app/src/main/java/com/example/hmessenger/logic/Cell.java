package com.example.hmessenger.logic;

public class Cell {

    private Object value;
    private String colorValue = "";
    private int color = 0x000000;

    public Cell(){
        this.setValue(null);
    }

    public void set(){
        setValue(Variables.VALUE);
    }

    public void reset(){
        setValue(null);
    }

    public void setColor(int color){
        this.color = color;
    }

    public int getColor(){
        return this.color;
    }

    private void setValue(Object value) {
        this.value = value;
    }

    public boolean isSet(){
        if( value == Variables.VALUE ){
            return true;
        }

        return false;
    }
}

