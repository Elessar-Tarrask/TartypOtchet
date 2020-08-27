package com.tartyp.otchet.RestCont;

import com.tartyp.otchet.DTO.DepartmentsDTO;
import com.tartyp.otchet.entity.DepartmentsEntity;
import com.tartyp.otchet.services.DepartmentsService;
import lombok.RequiredArgsConstructor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = {"*"}, allowedHeaders = {"*"})
@RequestMapping({"/api"})
@RequiredArgsConstructor
public class DepartmentsRestCont {

    private final DepartmentsService departmentService;

    @GetMapping({"/getDepartments"})
    public List<DepartmentsDTO> getById(
            @RequestParam(value = "startDate", required = true) String startDate,
            @RequestParam(value = "finishDate", required = true) String finishDate) throws Exception {
        Date start;
        Date finish;
        try {
            start = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse(startDate);
            finish = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse(finishDate);
        } catch (Exception err) {
            start = new Date();
            finish = new Date();
        }
        return departmentService.getAll(start, finish);
    }

    @GetMapping({"/getDepartmentsAllData"})
    public List<DepartmentsEntity> getByIdAll() throws Exception {
        return departmentService.getAll(true);
    }

    @GetMapping({"/template"})
    public ResponseEntity<ByteArrayResource> downloadTemplate(@RequestParam(value = "startDate", required = true) String startDate,
                                                              @RequestParam(value = "finishDate", required = true) String finishDate,
                                                              @RequestParam(value = "showLevelNames", required = false, defaultValue = "false") Boolean showLevelNames,
                                                              @RequestParam(value = "departmentId", required = true) String departmentId,
                                                              @RequestParam(value = "showEmptyDepartments", required = false, defaultValue = "false") Boolean showEmptyDepartments,
                                                              @RequestParam(value = "showPositionLevels", required = false, defaultValue = "false") Boolean  showPositionLevels) throws Exception {
        try {
            Date start;
            Date finish;
            try {
                start = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse(startDate);
                finish = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse(finishDate);
            } catch (Exception err) {
                start = new Date();
                finish = new Date();
            }
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            System.out.println(showEmptyDepartments);
            XSSFWorkbook workbook = departmentService.getXlSXList(start, finish, showLevelNames, departmentId, showEmptyDepartments,  showPositionLevels);
            HttpHeaders header = new HttpHeaders();
            header.setContentType(new MediaType("application", "force-download"));
            header.set("Content-Disposition", "attachment; filename=otchetTartyp.xlsx");
            workbook.write(stream);
            workbook.close();
            return new ResponseEntity<>(new ByteArrayResource(stream.toByteArray()),
                    header, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
