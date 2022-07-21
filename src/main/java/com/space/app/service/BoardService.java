package com.space.app.service;

import com.space.app.domain.BoardDTO;

import java.util.List;

public interface BoardService {
    List<BoardDTO> getList();
    int write(BoardDTO boardDTO);
    BoardDTO read(Integer bno);
    int modify(BoardDTO boardDTO);
    int remove(Integer bno, String writer);
}
