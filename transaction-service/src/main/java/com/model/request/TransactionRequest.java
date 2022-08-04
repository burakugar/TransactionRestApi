package com.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Map;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TransactionRequest {
    private String actor;
    private String type;
    Map<String, String> transactionDataEntityMap;
}
