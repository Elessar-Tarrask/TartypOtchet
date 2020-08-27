package com.tartyp.otchet.repo;

import com.tartyp.otchet.entity.PositionsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public interface PositionsRepo extends JpaRepository<PositionsEntity, Serializable> {
    List<PositionsEntity> findByDepartmentIdAndDeletedIsNullAndPointerCodeIsNotNull(String departmentId);
    List<PositionsEntity> findByDepartmentIdAndDeletedIsNull(String departmentId);
}
