package com.tartyp.otchet.repo;

import com.tartyp.otchet.entity.DepartmentsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public interface DepartmentsRepo extends JpaRepository<DepartmentsEntity, Serializable> {
    List<DepartmentsEntity> findAllByLevelGreaterThanAndDeletedIsNullAndNumberIsLessThan(Integer level, Integer number);
    List<DepartmentsEntity> findAllByLevelGreaterThanAndDeletedIsNullAndNumberIsLessThanAndDepartmentId(Integer level, Integer number, String departmentId);
    List<DepartmentsEntity> findAllByLevelGreaterThanAndDeletedIsNullAndNumberIsLessThanAndParentDepartmentId(Integer level, Integer number, String departmentId);
}
