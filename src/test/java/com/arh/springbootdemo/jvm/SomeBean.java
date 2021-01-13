package com.arh.springbootdemo.jvm;

public class SomeBean {

    public SomeBean(String a, String b) {
        System.out.println(this.a);
        System.out.println(this.b);
        this.a = a;
        this.b = b;
    }


    private String a = getStr();

    private String b = "b";

    static {
        System.out.println("static");
    }

    public String getStr(){
        System.out.println("getStr");
        return "a";
    }

}
