package com.ecommerce.ecombackend.Exception;


public class APIException extends  RuntimeException{

    private  static  final long serialVersionUID=1L;

    public APIException(String message){
        super(message);
    }

}
