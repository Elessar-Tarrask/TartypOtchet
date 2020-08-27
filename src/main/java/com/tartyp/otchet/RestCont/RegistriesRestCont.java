package com.tartyp.otchet.RestCont;

import com.tartyp.otchet.entity.RegistriesEntity;
import com.tartyp.otchet.repo.RegistriesRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = {"*"}, allowedHeaders = {"*"})
@RequestMapping({"/api"})
@RequiredArgsConstructor
public class RegistriesRestCont {

    private final RegistriesRepo registriesRepo;

    @GetMapping({"/getRegistry"})
    public List<RegistriesEntity> getByIdAll() throws Exception {
        return registriesRepo.findAll();
        //return registriesRepo.findByNameContaining("Акт выполненных работ");
        //return registriesRepo.findDataByName("Акт выполненных работ");
    }
}
