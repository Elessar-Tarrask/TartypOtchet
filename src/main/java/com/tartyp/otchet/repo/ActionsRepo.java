package com.tartyp.otchet.repo;

import com.tartyp.otchet.entity.ActionsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Repository
public interface ActionsRepo extends JpaRepository<ActionsEntity, Serializable> {

    List<ActionsEntity> findAllByResUserIdAndStartDateGreaterThanEqualAndFinishDateLessThanEqualAndDeletedIsNullAndStateId(String resUserId, Date startDate, Date finishDate, int stateId);  // total

    Long countAllByResUserIdAndStartDateGreaterThanEqualAndFinishDateLessThanEqualAndDeletedIsNullAndStateId(String resUserId, Date startDate, Date finishDate, int stateId);  // total

    @Query("select count(a) from ActionsEntity a where a.resUserId = ?1 and a.startDate >= ?2 and a.finishDate <= ?3 and a.deleted = null and a.stateId = ?4 and a.finished <= a.finishDate")
    Long findAllByResUserIdAndStartDateGreaterThanEqualAndFinishDateLessThanEqualAndDeletedIsNullAndStateIdAndFinished(String resUserId, Date startDate, Date finishDate, int stateId);  // finished_on_time

    @Query("select count(a) from ActionsEntity a where a.resUserId = ?1 and a.startDate >= ?2 and a.finishDate <= ?3 and a.deleted = null and a.stateId = ?4 and a.finished > a.finishDate")
    Long findAllFinishedLate(String resUserId, Date startDate, Date finishDate, int stateId);  // finished_on_time

    List<ActionsEntity> findAllByFinishDateLessThanEqualAndStartDateGreaterThanEqualAndDeletedIsNull(Date finishDate, Date startDate);

    @Query(value="SELECT 1 *, days(date1) - days(date2) AS DDiff FROM Actions a WHERE a.resUserId = ?1 AND a.startDate >= ?2 AND a.finishDate <= ?3 AND a.deleted = null AND a.stateId = ?4 BY DDiff DESC LIMIT 1", nativeQuery = true)
    List<ActionsEntity> findMaxDate(String resUserId, Date startDate, Date finishDate, int stateId);

}
