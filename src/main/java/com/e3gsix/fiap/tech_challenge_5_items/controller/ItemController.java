package com.e3gsix.fiap.tech_challenge_5_items.controller;

import com.e3gsix.fiap.tech_challenge_5_items.model.dto.request.ItemCreateRequest;
import com.e3gsix.fiap.tech_challenge_5_items.model.dto.response.ItemResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

public interface ItemController {

    ResponseEntity create(
            @RequestBody ItemCreateRequest request,
            UriComponentsBuilder uriComponentsBuilder
    );

    ResponseEntity<ItemResponse> findById(Long id);
}
