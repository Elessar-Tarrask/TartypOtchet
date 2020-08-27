package com.tartyp.otchet.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Entity
@Table(name = "actions", schema = "synergy")
public class ActionsEntity implements Comparable {
    private String actionId;
    private Timestamp startDate;
    private Timestamp finishDate;
    private Timestamp finished;
    private String resUserId;
    private int stateId;
    private Timestamp deleted;

    @Id
    @Column(name = "actionID")
    public String getActionId() {
        return actionId;
    }

    public void setActionId(String actionId) {
        this.actionId = actionId;
    }

    @Basic
    @Column(name = "start_date")
    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    @Basic
    @Column(name = "finish_date")
    public Timestamp getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Timestamp finishDate) {
        this.finishDate = finishDate;
    }

    @Basic
    @Column(name = "finished")
    public Timestamp getFinished() {
        return finished;
    }

    public void setFinished(Timestamp finished) {
        this.finished = finished;
    }

    @Basic
    @Column(name = "resUserID")
    public String getResUserId() {
        return resUserId;
    }

    public void setResUserId(String resUserId) {
        this.resUserId = resUserId;
    }

    @Basic
    @Column(name = "stateID")
    public Integer getStateId() {
        return stateId;
    }

    public void setStateId(int stateId) {
        this.stateId = stateId;
    }

    @Basic
    @Column(name = "deleted")
    public Timestamp getDeleted() {
        return deleted;
    }

    public void setDeleted(Timestamp deleted) {
        this.deleted = deleted;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ActionsEntity that = (ActionsEntity) o;

        if (stateId != that.stateId) return false;
        if (actionId != null ? !actionId.equals(that.actionId) : that.actionId != null) return false;
        if (startDate != null ? !startDate.equals(that.startDate) : that.startDate != null) return false;
        if (finishDate != null ? !finishDate.equals(that.finishDate) : that.finishDate != null) return false;
        if (finished != null ? !finished.equals(that.finished) : that.finished != null) return false;
        if (resUserId != null ? !resUserId.equals(that.resUserId) : that.resUserId != null) return false;
        if (deleted != null ? !deleted.equals(that.deleted) : that.deleted != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = actionId != null ? actionId.hashCode() : 0;
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (finishDate != null ? finishDate.hashCode() : 0);
        result = 31 * result + (finished != null ? finished.hashCode() : 0);
        result = 31 * result + (resUserId != null ? resUserId.hashCode() : 0);
        result = 31 * result + (int) stateId;
        result = 31 * result + (deleted != null ? deleted.hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(Object o) {
        return (int) (ChronoUnit.DAYS.between(new Date().toInstant(), new Date(this.finishDate.getTime()).toInstant()) - ChronoUnit.DAYS.between(new Date().toInstant(), new Date(((ActionsEntity)o).getFinishDate().getTime()).toInstant()));
    }

}
