package com.demo.anno;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)//指定什么时候有效
@Target(ElementType.METHOD)//指定在那个地方有效
public @interface Log {
}
