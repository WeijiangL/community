package com.weijiang.service;

import com.weijiang.exception.CustomErrorCode;
import com.weijiang.exception.CustomException;
import com.weijiang.model.Comment;

public class CommentService {

    public void insert(Comment comment) {
        if (comment.getParentId() == null || comment.getParentId() == 0){
            throw new CustomException(CustomErrorCode.TARGET_PARAM_NOT_FOUND);
        }
    }
}
