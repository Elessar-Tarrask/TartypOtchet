package com.tartyp.otchet.DTO;

import lombok.Data;

import java.util.List;

@Data
public class DepartmentsDTO implements Comparable {
    private String departmentId;

    public DepartmentsDTO(String departmentId, String parentDepartmentId, String name, String structNumber, Integer level, Long translationId, Integer typeId, Integer number) {
        this.departmentId = departmentId;
        this.parentDepartmentId = parentDepartmentId;
        this.name = name;
        this.structNumber = structNumber;
        this.level = level;
        this.translationId = translationId;
        this.typeId = typeId;
        this.number = number;
    }

    private Integer typeId;
    private String parentDepartmentId;
    private String name;
    private String structNumber;
    private Integer level;
    private long translationId;
    private Integer number;
    private List<DepDTOPositions> positions;
    //private List<PositionsEntity> fullPosition;

    @Override
    public int compareTo(Object d) {
        int level = this.getLevel() - ((DepartmentsDTO) d).getLevel();
        if (level == 0) {
            level = this.getNumber() - ((DepartmentsDTO) d).getNumber();
            if (level == 0)
                level = this.getStructNumber().compareTo(((DepartmentsDTO) d).getStructNumber());
        }
        return level;
    }
}
