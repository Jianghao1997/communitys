package com.hoodee.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Package: com.hoodee.community.dto
 * Description：分页对象
 * Author: jianghao
 * Date:  2019.12.22 0:12
 * Modified By:
 */
@Data
public class PaginationDTO<T>{
    private List<T> data;
    private boolean showPrevious;
    private boolean showFirstPage;
    private boolean showNext;
    private boolean showEndPage;
    private Integer page;
    private Integer totalPage;
    private List<Integer> pages = new ArrayList<>();

    public void setPagination(Integer totalPage, Integer page) {
        this.totalPage = totalPage;
        this.page = page;

        pages.add(page);
        for (int i=1 ;i<=3 ;i++){
            if(page-i>0){
                pages.add(0,page-i);
            }
            if(page + i <= totalPage){
                pages.add(page + i);
            }
        }
        // 是否展示上一页
        if (page == 1){
            showPrevious = false;
        }else{
            showPrevious = true;
        }
        // 是否展示下一页
        if (page == totalPage){
            showNext = false;
        }else {
            showNext = true;
        }

        // 是否展示第一页
        if (pages.contains(1)){
            showFirstPage = false;
        }else {
            showFirstPage = true;
        }
        // 是否展示最后一页
        if (pages.contains(totalPage)){
            showEndPage = false;
        }else {
            showEndPage = true;
        }
    }
}
