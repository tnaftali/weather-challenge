package com.example.weatherchallenge.mappers;

import com.example.weatherchallenge.model.Board;
import com.example.weatherchallenge.model.dto.BoardDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class EntityMapper<T> {

    public List<BoardDto> boardListToBoardDtoList (List<Board> list) {
        List<BoardDto> dtoList = new ArrayList<>();
        for (Iterator<Board> i = list.iterator(); i.hasNext();) {
            Board item = i.next();
            dtoList.add(item.toDto());
        }

        return dtoList;
    }
}
