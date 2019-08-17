package com.weijiang.service;

import com.weijiang.entity.Pagination;
import com.weijiang.entity.QuestionDTo;
import com.weijiang.exception.CustomErrorCode;
import com.weijiang.exception.CustomException;
import com.weijiang.mapper.QuestionExtMapper;
import com.weijiang.mapper.QuestionMapper;
import com.weijiang.mapper.UserMapper;
import com.weijiang.model.Question;
import com.weijiang.model.QuestionExample;
import com.weijiang.model.User;
import org.apache.ibatis.session.RowBounds;
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

    @Autowired
    private QuestionExtMapper questionExtMapper;

    public Pagination list(Integer page, Integer size) {
        Pagination pagination = new Pagination();
        //问题总数
        Integer totalCount = (int)questionMapper.countByExample(new QuestionExample());
        pagination.setPagination(totalCount , page , size);
        //判断页码超载的逻辑
        if (page < 1){
            page = 1;
        }

        if(page > pagination.getTotalPage()){
            page = pagination.getTotalPage();
        }

        Integer offset = size * (page - 1);
        List<Question> list = questionMapper.selectByExampleWithRowbounds(new QuestionExample(),new RowBounds(offset,size));
        List<QuestionDTo> questionDTolist = new ArrayList<QuestionDTo>();

        for (Question question : list) {
            User user = userMapper.selectByPrimaryKey(question.getCreator());
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

        QuestionExample example = new QuestionExample();
        example.createCriteria().andCreatorEqualTo(userId);
        Integer totalCount = (int)questionMapper.countByExample(example);
        pagination.setPagination(totalCount , page , size);
        //判断页码超载的逻辑
        if (page < 1){
            page = 1;
        }

        if(page > pagination.getTotalPage()){
            page = pagination.getTotalPage();
        }

        Integer offset = size * (page - 1);
        QuestionExample example1 = new QuestionExample();
        example1.createCriteria().andCreatorEqualTo(userId);
        List<Question> list = questionMapper.selectByExampleWithRowbounds(example1,new RowBounds(offset,size));
        List<QuestionDTo> questionDTolist = new ArrayList<QuestionDTo>();

        for (Question question : list) {
            User user = userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTo questionDTo = new QuestionDTo();
            BeanUtils.copyProperties(question , questionDTo);
            questionDTo.setUser(user);
            questionDTolist.add(questionDTo);
        }
        //将问题的详细内容展现在页面上
        pagination.setQuestions(questionDTolist);

        return pagination;
    }

    public QuestionDTo getById(Long id) {
        Question question = questionMapper.selectByPrimaryKey(id);
        if (question == null){
            throw new CustomException(CustomErrorCode.QUESTION_NOT_FOUND);
        }

        QuestionDTo questionDTo = new QuestionDTo();
        BeanUtils.copyProperties(question , questionDTo);
        User user = userMapper.selectByPrimaryKey(question.getCreator());
        questionDTo.setUser(user);
        return questionDTo;
    }

    public void createOrUpdate(Question question) {
        if (question.getId() == null){
            //插入
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            questionMapper.insert(question);
        }else {
            //修改
            Question dbQuestion = questionMapper.selectByPrimaryKey(question.getId());
            if (dbQuestion == null) {
                throw new CustomException(CustomErrorCode.QUESTION_NOT_FOUND);
            }

            Question question1 = new Question();
            question1.setGmtModified(System.currentTimeMillis());
            question1.setTitle(question.getTitle());
            question1.setDescription(question.getDescription());
            question1.setTag(question.getTag());

            QuestionExample example = new QuestionExample();
            example.createCriteria().andIdEqualTo(question.getId());

            int i = questionMapper.updateByExampleSelective(question1, example);
            if(i != 1){
                throw new CustomException(CustomErrorCode.QUESTION_NOT_FOUND);
            }
        }
    }

    public void incViewCount(Long id) {
        Question question = new Question();
        question.setId(id);
        question.setViewCount(1);
        questionExtMapper.incView(question);
    }
}
