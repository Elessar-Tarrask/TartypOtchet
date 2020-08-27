package com.tartyp.otchet.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "userpositions", schema = "synergy")
@IdClass(UserpositionsEntityPK.class)
public class UserpositionsEntity{
    private String userId;
    private String positionId;
    private Timestamp startdate;
    private Timestamp finishdate;
    private String periodId;

    @Basic
    @Id
    @Column(name = "userID")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @Id
    @Column(name = "positionID")
    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    @Basic
    @Column(name = "startdate")
    public Timestamp getStartdate() {
        return startdate;
    }

    public void setStartdate(Timestamp startdate) {
        this.startdate = startdate;
    }

    @Basic
    @Column(name = "finishdate")
    public Timestamp getFinishdate() {
        return finishdate;
    }

    public void setFinishdate(Timestamp finishdate) {
        this.finishdate = finishdate;
    }

    @Basic
    @Column(name = "periodID")
    public String getPeriodId() {
        return periodId;
    }

    public void setPeriodId(String periodId) {
        this.periodId = periodId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserpositionsEntity that = (UserpositionsEntity) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(positionId, that.positionId) &&
                Objects.equals(startdate, that.startdate) &&
                Objects.equals(finishdate, that.finishdate) &&
                Objects.equals(periodId, that.periodId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, positionId, startdate, finishdate, periodId);
    }
}
