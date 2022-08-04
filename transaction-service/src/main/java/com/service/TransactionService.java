package com.service;

import com.constant.CacheNames;
import com.exception.TransactionNotFoundException;
import com.model.converter.TransactionConverter;
import com.model.dto.TransactionDto;
import com.model.entity.TransactionEntity;
import com.model.request.TransactionRequest;
import com.repository.TransactionDataRepository;
import com.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final TransactionDataRepository transactionDataRepository;

    @CacheEvict(value = CacheNames.transactionName, key = "#id", beforeInvocation = true, allEntries = true)
    public TransactionDto save(TransactionRequest transactionRequest) {
        TransactionEntity transactionEntity = TransactionConverter.toTransactionEntity(transactionRequest);
        transactionEntity.getTransactionDataEntityList()
                .stream()
                .map(
                        transactionDataEntity ->
                                transactionDataRepository.save(transactionDataEntity)
                );
        return TransactionConverter.toTransactionDto(transactionRepository.save(transactionEntity));
    }

    @Cacheable(cacheNames = CacheNames.transactionName, sync = true, key = "#id")
    public TransactionDto getById(Integer id) {
        return transactionRepository.findById(id)
                .map(TransactionConverter::toTransactionDto)
                .orElseThrow(TransactionNotFoundException::new);
    }

    @Cacheable(cacheNames = CacheNames.transactionTypeListName, sync = true, key = "#type")
    public List<TransactionDto> getByType(String type) {
        List<TransactionDto> transactionDtoList = transactionRepository.findByType(type)
                .stream()
                .map(TransactionConverter::toTransactionDto)
                .collect(Collectors.toList());
        if (transactionDtoList.isEmpty()) {
            throw new TransactionNotFoundException();
        }
        return transactionDtoList;
    }

    @Cacheable(cacheNames = CacheNames.transactionActorListName, sync = true, key = "#actor")
    public List<TransactionDto> getByActor(String actor) {
        List<TransactionDto> transactionDtoList = transactionRepository.findByActor(actor)
                .stream()
                .map(TransactionConverter::toTransactionDto)
                .collect(Collectors.toList());
        if (transactionDtoList.isEmpty()) {
            throw new TransactionNotFoundException();
        }
        return transactionDtoList;
    }

    public List<TransactionDto> getAll() {
        List<TransactionDto> transactionDtoList = transactionRepository.findAll()
                .stream()
                .map(TransactionConverter::toTransactionDto)
                .collect(Collectors.toList());
        if (transactionDtoList.isEmpty()) {
            throw new TransactionNotFoundException();
        }
        return transactionDtoList;
    }

    @Transactional
    @Caching(evict = {
            @CacheEvict(value = CacheNames.transactionName, key = "#id", beforeInvocation = true, allEntries = true),
            @CacheEvict(value = CacheNames.transactionActorListName, beforeInvocation = true, allEntries = true),
            @CacheEvict(value = CacheNames.transactionTypeListName, beforeInvocation = true, allEntries = true),
    })
    public TransactionDto update(Integer id, TransactionRequest transactionRequest) {
        Optional<TransactionEntity> entity = transactionRepository.findById(id);
        return entity.map(e -> {
            transactionRepository.deleteById(id);
            return save(transactionRequest);
        }).orElseThrow(TransactionNotFoundException::new);
    }

    @Transactional
    @Caching(evict = {
            @CacheEvict(value = CacheNames.transactionName, key = "#id", beforeInvocation = true, allEntries = true),
            @CacheEvict(value = CacheNames.transactionActorListName, beforeInvocation = true, allEntries = true),
            @CacheEvict(value = CacheNames.transactionTypeListName, beforeInvocation = true, allEntries = true),
    })
    public TransactionDto delete(Integer id) {
        Optional<TransactionEntity> entity = transactionRepository.findById(id);
        return entity.map(e -> {
            transactionRepository.deleteById(id);
            return TransactionConverter.toTransactionDto(e);
        }).orElseThrow(TransactionNotFoundException::new);
    }
}
