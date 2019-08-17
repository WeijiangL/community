package com.weijiang.advice;

import com.weijiang.entity.ResultDTO;
import com.weijiang.exception.CustomErrorCode;
import com.weijiang.exception.CustomException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(Exception.class)
    Object handleControllerException(Throwable ex, Model model, HttpServletRequest request) {
        String contentType = request.getContentType();
        if ("application/json".equals(contentType)) {
            if (ex instanceof CustomException) {
                return ResultDTO.errorOf((CustomException) ex);
            } else {
                return ResultDTO.errorOf(CustomErrorCode.SYS_ERROR);
            }
        } else {
            if (ex instanceof CustomException) {
                model.addAttribute("messages", ex.getMessage());
            } else {
                model.addAttribute("messages", "服务器繁忙请稍后再试");
            }
            return new ModelAndView("error");
        }
    }
}
