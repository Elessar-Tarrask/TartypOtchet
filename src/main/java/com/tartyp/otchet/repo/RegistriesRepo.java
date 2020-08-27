package com.tartyp.otchet.repo;

import com.tartyp.otchet.entity.RegistriesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public interface RegistriesRepo extends JpaRepository<RegistriesEntity, Serializable> {

    List<RegistriesEntity> findByNameContaining(String name);

    //@Query("select r from RegistriesEntity r where r.name like ")
    //List<RegistriesEntity> findDataByName();
}
