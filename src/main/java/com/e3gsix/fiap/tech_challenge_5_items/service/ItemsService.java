package com.e3gsix.fiap.tech_challenge_5_items.service;

import com.e3gsix.fiap.tech_challenge_5_items.model.dto.request.ItemCreateRequest;

import java.util.UUID;

public interface ItemsService {
    UUID create(ItemCreateRequest request);
}
