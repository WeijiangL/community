package com.weijiang.service;

import com.weijiang.entity.Pagination;
import com.weijiang.entity.QuestionDTo;
import com.weijiang.mapper.QuestionMapper;
import com.weijiang.mapper.UserMapper;
import com.weijiang.model.Question;
import com.weijiang.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class QuestionService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionMapper questionMapper;


    public Pagination list(Integer page, Integer size) {
        Pagination pagination = new Pagination();
        //问题总数
        Integer totalCount = questionMapper.count();
        pagination.setPagination(totalCount , page , size);
        //判断页码超载的逻辑
        if (page < 1){
            page = 1;
        }

        if(page > pagination.getTotalPage()){
            page = pagination.getTotalPage();
        }

        Integer offset = size * (page - 1);
        List<Question> list = questionMapper.list(offset , size);
        List<QuestionDTo> questionDTolist = new ArrayList<QuestionDTo>();

        for (Question question : list) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTo questionDTo = new QuestionDTo();
            BeanUtils.copyProperties(question , questionDTo);
            questionDTo.setUser(user);
            questionDTolist.add(questionDTo);
        }
        //将问题的详细内容展现在页面上
        pagination.setQuestions(questionDTolist);

        return pagination;
    }

    public Pagination list(Integer userId, Integer page, Integer size) {
        Pagination pagination = new Pagination();
        //问题总数
        Integer totalCount = questionMapper.count();
        pagination.setPagination(totalCount , page , size);
        //判断页码超载的逻辑
        if (page < 1){
            page = 1;
        }

        if(page > pagination.getTotalPage()){
            page = pagination.getTotalPage();
        }

        Integer offset = size * (page - 1);
        List<Question> list = questionMapper.listById(userId , offset , size);
        List<QuestionDTo> questionDTolist = new ArrayList<QuestionDTo>();

        for (Question question : list) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTo questionDTo = new QuestionDTo();
            BeanUtils.copyProperties(question , questionDTo);
            questionDTo.setUser(user);
            questionDTolist.add(questionDTo);
        }
        //将问题的详细内容展现在页面上
        pagination.setQuestions(questionDTolist);

        return pagination;
    }
}
