package com.tartyp.otchet.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "positions", schema = "synergy")
public class PositionsEntity implements Comparable {
    private String positionId;
    private String departmentId;
    private Byte objTypeId;
    private String nameru;
    private String namekz;
    private String nameen;
    private Integer needed;
    private String code;
    private Date created;
    private Date deleted;
    private String pointerCode;
    private int number;
    private long translationId;

    @Id
    @Column(name = "positionID")
    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    @Basic
    @Column(name = "departmentID")
    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    @Basic
    @Column(name = "obj_typeID")
    public Byte getObjTypeId() {
        return objTypeId;
    }

    public void setObjTypeId(Byte objTypeId) {
        this.objTypeId = objTypeId;
    }

    @Basic
    @Column(name = "nameru")
    public String getNameru() {
        return nameru;
    }

    public void setNameru(String nameru) {
        this.nameru = nameru;
    }

    @Basic
    @Column(name = "namekz")
    public String getNamekz() {
        return namekz;
    }

    public void setNamekz(String namekz) {
        this.namekz = namekz;
    }

    @Basic
    @Column(name = "nameen")
    public String getNameen() {
        return nameen;
    }

    public void setNameen(String nameen) {
        this.nameen = nameen;
    }

    @Basic
    @Column(name = "needed")
    public Integer getNeeded() {
        return needed;
    }

    public void setNeeded(Integer needed) {
        this.needed = needed;
    }

    @Basic
    @Column(name = "code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Basic
    @Column(name = "created")
    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @Basic
    @Column(name = "deleted")
    public Date getDeleted() {
        return deleted;
    }

    public void setDeleted(Date deleted) {
        this.deleted = deleted;
    }

    @Basic
    @Column(name = "pointer_code")
    public String getPointerCode() {
        return pointerCode;
    }

    public void setPointerCode(String pointerCode) {
        this.pointerCode = pointerCode;
    }

    @Basic
    @Column(name = "number")
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Basic
    @Column(name = "translationID")
    public long getTranslationId() {
        return translationId;
    }

    public void setTranslationId(long translationId) {
        this.translationId = translationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PositionsEntity that = (PositionsEntity) o;
        return number == that.number &&
                translationId == that.translationId &&
                Objects.equals(positionId, that.positionId) &&
                Objects.equals(departmentId, that.departmentId) &&
                Objects.equals(objTypeId, that.objTypeId) &&
                Objects.equals(nameru, that.nameru) &&
                Objects.equals(namekz, that.namekz) &&
                Objects.equals(nameen, that.nameen) &&
                Objects.equals(needed, that.needed) &&
                Objects.equals(code, that.code) &&
                Objects.equals(created, that.created) &&
                Objects.equals(deleted, that.deleted) &&
                Objects.equals(pointerCode, that.pointerCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(positionId, departmentId, objTypeId, nameru, namekz, nameen, needed, code, created, deleted, pointerCode, number, translationId);
    }

    @Override
    public int compareTo(Object d) {
        return this.getNumber() - ((PositionsEntity) d).getNumber();
    }
}
