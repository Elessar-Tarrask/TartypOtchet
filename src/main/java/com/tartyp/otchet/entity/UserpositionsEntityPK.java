package com.tartyp.otchet.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserpositionsEntityPK implements Serializable {
    private String userId;
    private String positionId;
}
