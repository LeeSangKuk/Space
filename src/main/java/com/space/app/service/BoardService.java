package com.space.app.service;

import com.space.app.domain.BoardDTO;
import com.space.app.domain.SearchCondition;

import java.util.List;

public interface BoardService {
    int getCount();
    List<BoardDTO> getList();
    int write(BoardDTO boardDTO);
    BoardDTO read(Integer bno);
    int modify(BoardDTO boardDTO);
    int remove(Integer bno, String writer);
//  ========================================
    int getSearchResultCnt(SearchCondition sc); // getCount + sc
    List<BoardDTO> getSearchResultPage(SearchCondition sc); // getList + sc
}
