package com.e3gsix.fiap.tech_challenge_5_items.controller;

import com.e3gsix.fiap.tech_challenge_5_items.model.dto.request.ItemCreateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;

public interface ItemController {

    ResponseEntity create(
            @RequestBody ItemCreateRequest request,
            UriComponentsBuilder uriComponentsBuilder
    );
}
