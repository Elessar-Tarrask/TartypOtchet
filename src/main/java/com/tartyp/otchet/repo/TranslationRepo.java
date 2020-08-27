package com.tartyp.otchet.repo;

import com.tartyp.otchet.entity.TranslationsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface TranslationRepo extends JpaRepository<TranslationsEntity, Serializable> {
}
