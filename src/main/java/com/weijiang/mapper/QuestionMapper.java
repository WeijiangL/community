package com.weijiang.mapper;

import com.weijiang.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface QuestionMapper {
    @Insert("insert into question (title,description,gmt_create,gmt_modified,creator,tag) values (#{title},#{description},#{gmt_create},#{gmt_modified},#{creator},#{tag})")
    void insert(Question question);

    @Select("select * from question limit #{offset} , #{size}")
    List<Question> list(@Param(value = "offset") Integer offset, @Param(value = "size") Integer size);

    //获取问题总数
    @Select("select count(1) from question")
    Integer count();

    @Select("select * from question where creator = #{id} limit #{offset} , #{size}")
    List<Question> listById(@Param(value = "id")Integer userId, @Param(value = "offset") Integer offset, @Param(value = "size") Integer size);
}
