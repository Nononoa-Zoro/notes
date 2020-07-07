package com.study.annotation;

public class Apple {

    @FruitName(value="富士")
    private  String name;

    @FruitColor(fruitColor = FruitColor.Color.RED)
    private String color;

    @FruitProvider(id = 1,name = "红富士批发部1",address = "四川省成都市")
    private String appleProvider;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getAppleProvider() {
        return appleProvider;
    }

    public void setAppleProvider(String appleProvider) {
        this.appleProvider = appleProvider;
    }
}
