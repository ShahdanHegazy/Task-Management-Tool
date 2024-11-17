package com.Academy.Task_Tool.Repository;

import com.Academy.Task_Tool.DTO.CardDto;
import com.Academy.Task_Tool.Entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CardRepository extends JpaRepository<Card,Integer>{
//    List<CardDto> findByProjectIdAndIsDeletedFalse(Integer projectId);
    Optional<Card> findByCardId(Integer cardId);
}
