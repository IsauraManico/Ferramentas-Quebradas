package com.ferramentas.ferramentasbackend.exceptions;

import org.springframework.http.HttpStatus;

public class ErrorMessage {
    public static final String WRONG_TOKEN = "{ \"error\": \"Wrong token!\" }";
    public static final String OUT_OF_BOUNDS = "{ \"error\": \"The number of a row or a column is out of bounds!\" }";
    public static final String ALREADY_PURCHASED = "{ \"error\": \"The ticket has been already purchased!\" }";
    public static final String WRONG_PASSWORD = "{ \"error\": \"The password is wrong!\" }";
    public static final String  ENTITY_NOT_FOUND = "{ \"error\": \"The Entity requested does not exist\" }";
}
