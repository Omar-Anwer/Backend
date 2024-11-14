package com.udacity.jwdnd.course1.cloudstorage.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyErrorController implements ErrorController  {

    @Value("${server.error.path}")
    private String errorpath;

    @RequestMapping(value = "${server.error.path}")
    public String handleError(HttpServletRequest request) {
        
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());
        
            if(statusCode == HttpStatus.NOT_FOUND.value()) {
                // return "error-404";
                return "error";
            }
            else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                // return "error-500";
                return "error";
            }
        }
        return "error";
    }

    @Override
    public String getErrorPath() {
        return errorpath;
    }
}