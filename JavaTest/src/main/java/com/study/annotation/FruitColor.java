package com.study.annotation;


import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FruitColor {
    public enum Color{
        BLUE,RED,GREEN
    }

    Color fruitColor() default Color.BLUE;
}
