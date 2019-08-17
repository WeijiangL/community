package com.weijiang.entity;

import com.weijiang.exception.CustomErrorCode;
import com.weijiang.exception.CustomException;
import lombok.Data;

//返回的错误码以及信息
@Data
public class ResultDTO {
    private Integer code;
    private String message;

    public static ResultDTO errorOf(Integer code , String message){
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(code);
        resultDTO.setMessage(message);
        return resultDTO;
    }

    public static ResultDTO errorOf(CustomErrorCode customErrorCode) {
        return errorOf(customErrorCode.getCode() , customErrorCode.getMessage());
    }

    public static ResultDTO errorOf(CustomException ex) {
        return errorOf(ex.getCode() , ex.getMessage());
    }
}
