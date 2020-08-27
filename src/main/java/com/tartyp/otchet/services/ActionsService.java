package com.tartyp.otchet.services;

import com.tartyp.otchet.entity.ActionsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.joda.time.DateTime;

import java.util.Date;
import java.util.List;

@Service
public class ActionsService {

    @Autowired
    com.tartyp.otchet.repo.ActionsRepo ActionsRepo;

    public List<ActionsEntity> getAll(Date start, Date finish) {
        return ActionsRepo.findAllByFinishDateLessThanEqualAndStartDateGreaterThanEqualAndDeletedIsNull(finish, start);
    }

    public Long getAll(String resUserId, Date start, Date finish) {
        return ActionsRepo.countAllByResUserIdAndStartDateGreaterThanEqualAndFinishDateLessThanEqualAndDeletedIsNullAndStateId(resUserId, start, finish, 1);
    }

    public List<ActionsEntity> getAll(String resUserId, Date start, Date finish, Integer type) {
        return ActionsRepo.findAllByResUserIdAndStartDateGreaterThanEqualAndFinishDateLessThanEqualAndDeletedIsNullAndStateId(resUserId, start, finish, 1);
    }

    public Long getFinishedOnTime(String resUserId, Date start, Date finish) {
        return ActionsRepo.findAllByResUserIdAndStartDateGreaterThanEqualAndFinishDateLessThanEqualAndDeletedIsNullAndStateIdAndFinished(resUserId, start, finish, 1);
    }

    public Long getFinishedLate(String resUserId, Date start, Date finish) {
        return ActionsRepo.findAllFinishedLate(resUserId, start, finish, 1);
    }

    public List<ActionsEntity> getNotFinished(String resUserId, Date start, Date finish) {
        return ActionsRepo.findAllByResUserIdAndStartDateGreaterThanEqualAndFinishDateLessThanEqualAndDeletedIsNullAndStateId(resUserId, start, finish, 0);
    }

    public List<ActionsEntity> getNotFinishedForYear(String resUserId, Date finish) {
        return ActionsRepo.findAllByResUserIdAndStartDateGreaterThanEqualAndFinishDateLessThanEqualAndDeletedIsNullAndStateId(resUserId,new DateTime(finish).minusDays(1095).toDate(), finish, 0);
    }

    public List<ActionsEntity> getNotMax(String resUserId, Date start) {
        return ActionsRepo.findMaxDate(resUserId,new DateTime(start).minusDays(365).toDate(), start, 0);
    }
}
