package com.stackery.web;

import java.lang.reflect.Method;

//此页面为练习反射的测试页面
public class Test {
    public void login(String name){
        System.out.println("login方法调用了");
        System.out.println(name);
    }

    public void register(){
        System.out.println("register方法调用了");
    }

    public void updateUser(){
        System.out.println("updateUser方法调用了");
    }

    public void updateUserPassword(){
        System.out.println("updateUserPassword方法调用了");
    }

    public static void main(String[] args) {
        String action = "login";
        try {
            Method method = Test.class.getDeclaredMethod(action, String.class);
//            System.out.println(method);
            method.invoke(new Test(), "wao");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
