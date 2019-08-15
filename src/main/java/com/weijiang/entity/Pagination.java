package com.weijiang.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Pagination {
    private List<QuestionDTo> questions;
    private boolean showPrevious;
    private boolean showFirstPage;
    private boolean showNext;
    private boolean showEndPage;
    private Integer currentPage;
    private List<Integer> pages = new ArrayList<>();
    private Integer totalPage;

    public void setPagination(Integer totalCount, Integer page, Integer size) {
        //计算页面总数

        if (totalCount % size == 0){
            totalPage = totalCount / size;
        }else {
            totalPage = totalCount / size + 1;
        }

        if (page < 1){
            page = 1;
        }

        if(page > totalPage){
            page = totalPage;
        }

        pages.add(page);
        for (int i = 1 ; i <= 3 ; i ++){
            if (page - i > 0){
                pages.add(0 , page - i);
            }
            if (page + i <= totalPage){
                pages.add(page + i);
            }
        }
        currentPage = page;

        //如果为第一页则没有前面的标号
        if (page == 1){
            showPrevious = false;
        }else {
            showPrevious = true;
        }

        //如果为最后一页则没有后面的标号
        if (page == totalPage){
            showNext = false;
        }else {
            showNext = true;
        }

        //是否展示第一页
        if (pages.contains(1)){
            showFirstPage = false;
        }else {
            showFirstPage = true;
        }

        //是否展示最后一页
        if (pages.contains(totalPage)){
            showEndPage = false;
        }else {
            showEndPage = true;
        }
    }
}
