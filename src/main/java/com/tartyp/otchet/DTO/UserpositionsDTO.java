package com.tartyp.otchet.DTO;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class UserpositionsDTO {
    private String userId;
    private Timestamp startdate;
    private String name;
    private Integer totalActions;      // все

    private Integer totalFinished;     // выполненые
    private Integer finishedOnTime;    // выполнены во время
    private Integer finishedLate;      // выполнены с опозданием

    private Integer notFinished;       // в работе всего за месяц
    private Integer notFinishedInYear; // в работе всего за год

    private Long maxDelay;
    private Long minDelay;
}
