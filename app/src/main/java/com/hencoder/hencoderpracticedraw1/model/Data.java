package com.hencoder.hencoderpracticedraw1.model;

/**
 * Created by yanxiaolong on 2017/11/29.
 */

public class Data {

    private String name;
    private float num;
    private int color;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getNum() {
        return num;
    }

    public void setNum(float num) {
        this.num = num;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public Data(String name, float num, int color) {
        this.name = name;
        this.num = num;
        this.color = color;
    }
}
