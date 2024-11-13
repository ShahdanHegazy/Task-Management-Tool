package com.Academy.Task_Tool.Repository;

import com.Academy.Task_Tool.Entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CardRepository extends JpaRepository<Card,Integer>{
}
