package com.tartyp.otchet.DTO;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ActionsDTO {
    private String actionId;
    private Timestamp startDate;
    private Timestamp finishDate;
    private Timestamp finished;
    private String resUserId;
    private int stateId;
}
