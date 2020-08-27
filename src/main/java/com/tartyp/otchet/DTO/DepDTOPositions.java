package com.tartyp.otchet.DTO;

import lombok.Data;

import java.util.List;

@Data
public class DepDTOPositions {
    private String positionId;
    private String name;
    private List<UserpositionsDTO> FullUsers;
}
