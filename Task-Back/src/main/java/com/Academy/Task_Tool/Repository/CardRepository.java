package com.Academy.Task_Tool.Repository;

import com.Academy.Task_Tool.DTO.CardDto;
import com.Academy.Task_Tool.Entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CardRepository extends JpaRepository<Card,Integer>{
//    List<CardDto> findByProject_Id(Integer project_Id);
    Optional<Card> findByCardId(Integer cardId);


//     Optional<Card> findByCardIdAndIsDeletedFalse(Integer cardId);


   Optional<Card> findByCardIdAndIsDeletedFalse(Integer cardId);


    @Query("SELECT c FROM Card c WHERE c.list.listId = :listId AND c.isDeleted = false")
    List<Card> findAllByListIdAndIsDeletedFalse(@Param("listId") int listId);


}
