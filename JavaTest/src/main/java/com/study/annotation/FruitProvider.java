package com.study.annotation;


import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FruitProvider {
    /**
     *水果供应商id
     */
    public int id() default -1;
    /**
     *水果供应商name
     */
    public String name() default "";
    /**
     *水果供应商address
     */
    public String address() default "";
}
