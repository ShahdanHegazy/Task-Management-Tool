package com.Academy.Task_Tool.Repository;
import com.Academy.Task_Tool.Entity.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ListRepository extends JpaRepository<List,Integer> {

    Optional<List> findByListIdAndIsDeletedFalse(Integer listId);


    @Query("SELECT l FROM List l WHERE l.project.project_id = :projectId AND l.isDeleted = false")
    java.util.List<List> findAllByProjectIdAndIsDeletedFalse(@Param("projectId") int projectId);


}
