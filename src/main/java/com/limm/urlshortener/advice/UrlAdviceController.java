package com.limm.urlshortener.advice;

import com.limm.urlshortener.dto.Alert;
import com.limm.urlshortener.exception.InvalidShortUrlException;
import com.limm.urlshortener.exception.InvalidUrlException;
import com.limm.urlshortener.exception.UrlNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class UrlAdviceController {

    @ExceptionHandler(InvalidUrlException.class)
    protected ModelAndView InvalidUrlExceptionHandler(final InvalidUrlException exception) {
        ModelAndView mv = new ModelAndView();

        Alert alert = new Alert(exception.getMessage());
        mv.addObject("data", alert);
        mv.setViewName("message");

        return mv;
    }

    @ExceptionHandler(InvalidShortUrlException.class)
    protected ModelAndView InvalidShortUrlExceptionHandler(InvalidShortUrlException exception) {
        ModelAndView mv = new ModelAndView();

        Alert alert = new Alert(exception.getMessage());
        mv.addObject("data", alert);
        mv.setViewName("message");

        return mv;
    }

    @ExceptionHandler(UrlNotFoundException.class)
    protected ModelAndView InvalidShortUrlExceptionHandler(UrlNotFoundException exception) {
        ModelAndView mv = new ModelAndView();

        Alert alert = new Alert(exception.getMessage());
        mv.addObject("data", alert);
        mv.setViewName("message");

        return mv;
    }
}
