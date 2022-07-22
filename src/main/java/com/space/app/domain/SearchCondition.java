package com.space.app.domain;

import org.springframework.web.util.UriComponentsBuilder;

public class SearchCondition {
    private Integer page = 1; // 첫 페이지
    private Integer pageSize = 10; // 한 페이지의 개시물 갯수
    private String keyword = ""; // 검색어
    private String option = ""; // 제목,내용,작성자 조건 검색

    public SearchCondition(){}
    public SearchCondition(Integer page, Integer pageSize, String keyword, String option) {
        this.page = page;
        this.pageSize = pageSize;
        this.keyword = keyword;
        this.option = option;
    }

    public String getQueryString(){
        return getQueryString(page);
    }
    public String getQueryString(Integer page){
        return UriComponentsBuilder.newInstance()
                .queryParam("page", page)
                .queryParam("pageSize", pageSize)
                .queryParam("option", option)
                .queryParam("keyword", keyword)
                .build().toString();
    }

    // offset = sql문에서 limit을 사용하기 위해서 작성
    public int getOffset() {
        return (page-1) * pageSize;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    @Override
    public String toString() {
        return "SearchCondition{" +
                "page=" + page +
                ", pageSize=" + pageSize +
                ", offset=" + getOffset() +
                ", keyword='" + keyword + '\'' +
                ", option='" + option + '\'' +
                '}';
    }
}
