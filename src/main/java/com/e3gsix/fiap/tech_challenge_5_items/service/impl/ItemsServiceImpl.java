package com.e3gsix.fiap.tech_challenge_5_items.service.impl;

import com.e3gsix.fiap.tech_challenge_5_items.model.dto.request.ItemCreateRequest;
import com.e3gsix.fiap.tech_challenge_5_items.model.entity.Item;
import com.e3gsix.fiap.tech_challenge_5_items.repository.ItemRepository;
import com.e3gsix.fiap.tech_challenge_5_items.service.ItemsService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ItemsServiceImpl implements ItemsService {

    private final ItemRepository itemRepository;

    public ItemsServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public UUID create(ItemCreateRequest request) {
        this.itemRepository.save(new Item(
                request.name(),
                request.description(),
                request.price(),
                request.quantity()
        ));
        return UUID.randomUUID();
    }
}
