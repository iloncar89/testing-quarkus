package com.iloncar.exception;

public class InternalServerErrorException extends Exception{
  private static final long serialVersionUID = 1L;
  public InternalServerErrorException(String message){
    super(message);
  }
}
