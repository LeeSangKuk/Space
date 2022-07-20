package com.space.app.dao;

import com.space.app.domain.BoardDTO;

import java.util.List;

public interface BoardDAO {
    List<BoardDTO> selectAll();
    int insert(BoardDTO boardDTO);
}
