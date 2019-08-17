package com.weijiang.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/error")
//@RequestMapping({"${server.error.path:${error.path:/error}}"})
//"${server.error.path:${error.path:/error}}"表示：如果application.properties中设置了server.error.path，就映射该值，否则，如果error.path有值，就映射该值，
//否则映射/error
public class CustomExceptionController implements ErrorController {

    @Override
    public String getErrorPath() {
        return "error";
    }

    @RequestMapping(
            produces = {"text/html"}
    ) //它的作用是指定返回值类型，不但可以设置返回值类型还可以设定返回值的字符编码；还有一个属性与其对应，就是consumes： 指定处理请求的提交内容类型（Content-Type），例如application/json, text/html;
    public ModelAndView errorHtml(HttpServletRequest request, Model model) {
        HttpStatus status = this.getStatus(request);
        if (status.is4xxClientError()){
            model.addAttribute("messages","您的请求方式有问题，要不换个姿势？");
        }
        if(status.is5xxServerError()){
            model.addAttribute("messages","我嗝屁了？");
        }
        return  new ModelAndView("error");
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer)request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        } else {
            try {
                return HttpStatus.valueOf(statusCode);
            } catch (Exception var4) {
                return HttpStatus.INTERNAL_SERVER_ERROR;
            }
        }
    }
}
