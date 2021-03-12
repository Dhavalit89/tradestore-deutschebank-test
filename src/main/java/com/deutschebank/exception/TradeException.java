package com.deutschebank.exception;

public class TradeException extends Exception{
    String message;
    public TradeException(String str) {
        message = str;
    }
    public String toString() {
        return ("Trade Exception Occurred : " + message);
    }
}