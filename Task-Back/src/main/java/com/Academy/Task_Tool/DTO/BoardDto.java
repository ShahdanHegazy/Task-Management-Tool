package com.Academy.Task_Tool.DTO;

import lombok.Data;

import java.util.List;

@Data
public class BoardDto {
    private int id;
    private String name;
    private List<CardBoardDto> cardList;

}
