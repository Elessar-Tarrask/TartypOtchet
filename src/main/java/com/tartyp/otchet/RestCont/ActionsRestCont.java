package com.tartyp.otchet.RestCont;

import com.tartyp.otchet.entity.ActionsEntity;
import com.tartyp.otchet.services.ActionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = {"*"}, allowedHeaders = {"*"})
@RequestMapping({"/api"})
@RequiredArgsConstructor
public class ActionsRestCont {

    private final ActionsService ActionsService;

    @GetMapping({"/getActions"})
    public List<ActionsEntity> getAll(
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
        return ActionsService.getAll(start, finish);
    }

    @GetMapping({"/getActionsMax"})
    public List<ActionsEntity> getMax(
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
        List<ActionsEntity> a = ActionsService.getNotFinishedForYear("16d82522-c3ac-4389-8d85-d0a592656662", finish);
        Collections.sort(a);
        a.forEach(element -> {
            System.out.println((int) ChronoUnit.DAYS.between(new Date(element.getFinishDate().getTime()).toInstant(), new Date().toInstant()));
        });
        System.out.println("____________________________");
        for (ActionsEntity element : a) {
            System.out.println((int) ChronoUnit.DAYS.between(new Date(element.getFinishDate().getTime()).toInstant(), new Date().toInstant()));
        }
        System.out.println("____________________________");
        System.out.println(ChronoUnit.DAYS.between(new Date(a.get(0).getFinishDate().getTime()).toInstant(), new Date().toInstant()));
        System.out.println(ChronoUnit.DAYS.between(new Date().toInstant(), new Date(a.get(a.size() - 1).getFinishDate().getTime()).toInstant()));
        return a;
    }

    @GetMapping({"/getDifference"})
    public String getMax() throws ParseException {
        Date start = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse("2020-06-05 11:48:35");
        System.out.println(ChronoUnit.DAYS.between(new Date().toInstant(), start.toInstant()));

        return "test";
    }

}
