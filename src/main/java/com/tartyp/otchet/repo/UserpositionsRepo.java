package com.tartyp.otchet.repo;

import com.tartyp.otchet.entity.UserpositionsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public interface UserpositionsRepo extends JpaRepository<UserpositionsEntity, Serializable> {
    List<UserpositionsEntity> findByPositionIdAndFinishdateIsNull(String positionId);
}
