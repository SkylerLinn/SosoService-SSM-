package com.pojo.exception;

public class IllegalTypeException extends Exception{
    String illegalType;
    public IllegalTypeException(String illegalType){
        this.illegalType=illegalType;
    }
    public String showException(){
        return ("非法类型："+this.illegalType);
    }
}
