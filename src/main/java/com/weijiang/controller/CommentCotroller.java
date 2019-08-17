package com.weijiang.controller;

import com.weijiang.entity.CommentDTo;
import com.weijiang.entity.ResultDTO;
import com.weijiang.exception.CustomErrorCode;
import com.weijiang.model.Comment;
import com.weijiang.model.User;
import com.weijiang.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CommentCotroller {

    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "/comment" , method = RequestMethod.POST)
    public Object post(@RequestBody CommentDTo commentDTo , HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        if(user == null){
           return ResultDTO.errorOf(CustomErrorCode.NO_LOGIN);
        }
        Comment comment = new Comment();
        comment.setParentId(commentDTo.getParentId());
        comment.setContent(commentDTo.getContent());
        comment.setType(commentDTo.getType());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setCommentor(1);
        commentService.insert(comment);
        return null;
    }
}
