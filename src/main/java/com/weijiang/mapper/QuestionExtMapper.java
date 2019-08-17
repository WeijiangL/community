package com.weijiang.mapper;

import com.weijiang.model.Question;

public interface QuestionExtMapper {
    //阅读数功能
    int incView(Question record);
}