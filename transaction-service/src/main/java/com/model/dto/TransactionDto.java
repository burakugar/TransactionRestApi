package com.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.model.entity.TransactionDataEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionDto implements Serializable {
    private Integer id;
    private String type;
    private String actor;
    private Timestamp timestamp;
    private List<TransactionDataEntity> transactionDataEntityList;
}
