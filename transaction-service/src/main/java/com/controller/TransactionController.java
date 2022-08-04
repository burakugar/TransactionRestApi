package com.controller;

import com.model.dto.TransactionDto;
import com.model.request.TransactionRequest;
import com.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping
    public TransactionDto create(@RequestBody @Valid TransactionRequest transactionRequest) {
        return transactionService.save(transactionRequest);
    }

    @GetMapping("/{id}")
    public TransactionDto getById(@PathVariable Integer id) {
        return transactionService.getById(id);
    }

    @GetMapping("/type")
    public List<TransactionDto> getByType(@Valid String type) {
        return transactionService.getByType(type);
    }

    @GetMapping("/actor")
    public List<TransactionDto> getByActor(@Valid String actor) {
        return transactionService.getByActor(actor);
    }

    @GetMapping
    public List<TransactionDto> getAll() {
        return transactionService.getAll();
    }

    @PatchMapping("/{id}")
    public TransactionDto update(@PathVariable Integer id, @RequestBody @Valid TransactionRequest request) {
        return transactionService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public TransactionDto delete(@PathVariable Integer id) {
        return transactionService.delete(id);
    }


}
