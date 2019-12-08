package com.exception;

public class IllegalTypeException extends Exception{
    String illegalType;
    public IllegalTypeException(String illegalType){
        this.illegalType=illegalType;
    }
    public void showException(){
        System.out.println("非法类型："+this.illegalType);
    }
}
