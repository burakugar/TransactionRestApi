package com.model.converter;

import com.model.dto.TransactionDto;
import com.model.entity.TransactionDataEntity;
import com.model.entity.TransactionEntity;
import com.model.request.TransactionRequest;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public final class TransactionConverter {

    private TransactionConverter() {
        throw new IllegalStateException("Converter Class");
    }

    public static TransactionEntity toTransactionEntity(TransactionRequest transactionRequest) {
        Map<String, String> requestMap = transactionRequest.getTransactionDataEntityMap();
        List<TransactionDataEntity> transactionDataEntityList = requestMap.entrySet()
                .stream()
                .map(e ->
                        TransactionDataEntity
                                .builder()
                                .name(e.getKey())
                                .value(e.getValue())
                                .build()
                )
                .collect(Collectors.toList());

        return TransactionEntity.builder()
                .actor(transactionRequest.getActor())
                .type(transactionRequest.getType())
                .transactionDataEntityList(transactionDataEntityList)
                .build();
    }

    public static TransactionDto toTransactionDto(TransactionEntity transactionEntity) {
        return TransactionDto.builder()
                .id(transactionEntity.getId())
                .actor(transactionEntity.getActor())
                .type(transactionEntity.getType())
                .timestamp(transactionEntity.getTimestamp())
                .transactionDataEntityList(transactionEntity.getTransactionDataEntityList())
                .build();
    }

    public static TransactionEntity toUpdatedTransactionEntity(TransactionRequest request, TransactionEntity updatedEntity) {
        Map<String, String> requestMap = request.getTransactionDataEntityMap();
        List<TransactionDataEntity> transactionDataEntityList = requestMap.entrySet()
                .stream()
                .map(e ->
                        TransactionDataEntity
                                .builder()
                                .name(e.getKey())
                                .value(e.getValue())
                                .build()
                )
                .collect(Collectors.toList());

        return TransactionEntity.builder()
                .actor(updatedEntity.getActor())
                .type(updatedEntity.getType())
                .transactionDataEntityList(transactionDataEntityList)
                .build();
    }

}
