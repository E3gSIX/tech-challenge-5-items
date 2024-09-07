package com.e3gsix.fiap.tech_challenge_5_items.service;

import com.e3gsix.fiap.tech_challenge_5_items.model.dto.request.ItemCreateRequest;
import com.e3gsix.fiap.tech_challenge_5_items.model.dto.response.ItemResponse;

public interface ItemsService {
    Long create(ItemCreateRequest request);

    ItemResponse findById(Long id);

    ItemResponse updateQuantity(Long id, Integer quantity);
}
