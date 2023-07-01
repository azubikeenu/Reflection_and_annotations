package com.azubike.ellipsis.reflections.methods;

public class Entity {
    private int val;
    public String type;

    public Entity(final int val, final String type) {
        this.val = val;
        this.type = type;
    }
    public void test(String a , int b){

    }


    public int getVal() {
        return val;
    }

    public void setVal(final int val) {
        this.val = val;
    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Entity{" +
                "val=" + val +
                ", type='" + type + '\'' +
                '}';
    }
}
