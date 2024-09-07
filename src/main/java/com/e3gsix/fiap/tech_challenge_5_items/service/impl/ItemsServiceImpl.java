package com.e3gsix.fiap.tech_challenge_5_items.service.impl;

import com.e3gsix.fiap.tech_challenge_5_items.controller.exception.NotFoundException;
import com.e3gsix.fiap.tech_challenge_5_items.model.dto.request.ItemCreateRequest;
import com.e3gsix.fiap.tech_challenge_5_items.model.dto.response.ItemResponse;
import com.e3gsix.fiap.tech_challenge_5_items.model.entity.Item;
import com.e3gsix.fiap.tech_challenge_5_items.repository.ItemRepository;
import com.e3gsix.fiap.tech_challenge_5_items.service.ItemsService;
import org.springframework.stereotype.Service;

@Service
public class ItemsServiceImpl implements ItemsService {

    private final ItemRepository itemRepository;

    public ItemsServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public Long create(ItemCreateRequest request) {
        Item itemToSave = new Item(
                request.name(),
                request.description(),
                request.price(),
                request.quantity()
        );
        Item savedItem = this.itemRepository.save(itemToSave);

        return savedItem.getId();
    }

    @Override
    public ItemResponse findById(Long id) {
        Item item = this.itemRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Item de id '" + id + "' não foi encontrado."));

        return new ItemResponse(item.getName(), item.getDescription(), item.getPrice(), item.getQuantity());
    }
}
