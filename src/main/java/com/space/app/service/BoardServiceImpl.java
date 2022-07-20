package com.space.app.service;

import com.space.app.dao.BoardDAO;
import com.space.app.domain.BoardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {
    @Autowired
    BoardDAO boardDAO;

    @Override
    public List<BoardDTO> getList() {
        return boardDAO.selectAll();
    }

    @Override
    public int write(BoardDTO boardDTO) { return boardDAO.insert(boardDTO); }
}
