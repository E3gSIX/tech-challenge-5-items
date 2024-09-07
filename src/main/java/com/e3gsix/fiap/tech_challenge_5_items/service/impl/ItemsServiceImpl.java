package com.e3gsix.fiap.tech_challenge_5_items.service.impl;

import com.e3gsix.fiap.tech_challenge_5_items.controller.exception.NotFoundException;
import com.e3gsix.fiap.tech_challenge_5_items.model.dto.request.ItemCreateRequest;
import com.e3gsix.fiap.tech_challenge_5_items.model.dto.response.ItemResponse;
import com.e3gsix.fiap.tech_challenge_5_items.model.entity.Item;
import com.e3gsix.fiap.tech_challenge_5_items.repository.ItemRepository;
import com.e3gsix.fiap.tech_challenge_5_items.service.ItemsService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;

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
        Item item = getItem(id);

        return toItemResponse(item);
    }

    @Override
    public ItemResponse update(Long id, Integer quantity, BigDecimal price) {
        if(Objects.isNull(quantity) && Objects.isNull(price)) {
            throw new UnsupportedOperationException("Preço e/ou quantidade devem ser informados.");
        }

        Item itemToUpdate = getItem(id);

        if (Objects.nonNull(quantity)) itemToUpdate.setQuantity(quantity);
        if (Objects.nonNull(price)) itemToUpdate.setPrice(price);

        Item savedItem = this.itemRepository.save(itemToUpdate);

        return toItemResponse(savedItem);
    }

    private Item getItem(Long id) {
        return this.itemRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Item de id '" + id + "' não foi encontrado."));
    }

    private static ItemResponse toItemResponse(Item item) {
        return new ItemResponse(item.getName(), item.getDescription(), item.getPrice(), item.getQuantity());
    }
}
