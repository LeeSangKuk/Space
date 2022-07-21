package com.space.app.dao;

import com.space.app.domain.BoardDTO;

import java.util.List;

public interface BoardDAO {
    List<BoardDTO> selectAll();
    int insert(BoardDTO boardDTO);
    BoardDTO select(Integer bno);
    int update(BoardDTO boardDTO);
    int delete(Integer bno, String writer);
    int increaseViewCnt(Integer bno);
}
