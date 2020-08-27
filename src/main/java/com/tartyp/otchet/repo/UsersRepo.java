package com.tartyp.otchet.repo;

import com.tartyp.otchet.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface UsersRepo extends JpaRepository<UsersEntity, Serializable> {

}
