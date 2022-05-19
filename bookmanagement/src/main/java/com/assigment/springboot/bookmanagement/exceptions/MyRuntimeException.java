package com.assigment.springboot.bookmanagement.exceptions;

public class MyRuntimeException extends Throwable {
    public MyRuntimeException(String s) {
        System.out.println(s+" not found");
    }
}
