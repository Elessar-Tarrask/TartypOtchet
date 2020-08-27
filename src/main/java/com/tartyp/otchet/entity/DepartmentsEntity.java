package com.tartyp.otchet.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "departments", schema = "synergy")
public class DepartmentsEntity implements Comparable {
    private String departmentId;
    private String parentDepartmentId;
    private String nameru;
    private String namekz;
    private String nameen;
    private Byte isBranch;
    private String uri;
    private Date created;
    private Date deleted;
    private String branchId;
    private Integer typeId;
    private String structNumber;
    private Integer level;
    private String pointerCode;
    private Integer number;
    private long translationId;

    @Id
    @Column(name = "departmentID")
    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    @Basic
    @Column(name = "parentDepartmentID")
    public String getParentDepartmentId() {
        return parentDepartmentId;
    }

    public void setParentDepartmentId(String parentDepartmentId) {
        this.parentDepartmentId = parentDepartmentId;
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
    @Column(name = "isBranch")
    public Byte getIsBranch() {
        return isBranch;
    }

    public void setIsBranch(Byte isBranch) {
        this.isBranch = isBranch;
    }

    @Basic
    @Column(name = "uri")
    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
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
    @Column(name = "branchID")
    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    @Basic
    @Column(name = "typeID")
    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    @Basic
    @Column(name = "struct_number")
    public String getStructNumber() {
        return structNumber;
    }

    public void setStructNumber(String structNumber) {
        this.structNumber = structNumber;
    }

    @Basic
    @Column(name = "level")
    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
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
    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
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
        DepartmentsEntity that = (DepartmentsEntity) o;
        return translationId == that.translationId &&
                Objects.equals(departmentId, that.departmentId) &&
                Objects.equals(parentDepartmentId, that.parentDepartmentId) &&
                Objects.equals(nameru, that.nameru) &&
                Objects.equals(namekz, that.namekz) &&
                Objects.equals(nameen, that.nameen) &&
                Objects.equals(isBranch, that.isBranch) &&
                Objects.equals(uri, that.uri) &&
                Objects.equals(created, that.created) &&
                Objects.equals(deleted, that.deleted) &&
                Objects.equals(branchId, that.branchId) &&
                Objects.equals(typeId, that.typeId) &&
                Objects.equals(structNumber, that.structNumber) &&
                Objects.equals(level, that.level) &&
                Objects.equals(pointerCode, that.pointerCode) &&
                Objects.equals(number, that.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(departmentId, parentDepartmentId, nameru, namekz, nameen, isBranch, uri, created, deleted, branchId, typeId, structNumber, level, pointerCode, number, translationId);
    }

    @Override
    public int compareTo(Object d) {
        int level = this.getLevel() - ((DepartmentsEntity) d).getLevel();
        if (level == 0) {
            level = this.getNumber() - ((DepartmentsEntity) d).getNumber();
            if (level == 0)
                level = this.getStructNumber().compareTo(((DepartmentsEntity) d).getStructNumber());
        }
        return level;
    }
}
