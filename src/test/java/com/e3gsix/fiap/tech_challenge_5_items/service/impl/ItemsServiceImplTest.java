package com.e3gsix.fiap.tech_challenge_5_items.service.impl;

import com.e3gsix.fiap.tech_challenge_5_items.controller.exception.NotFoundException;
import com.e3gsix.fiap.tech_challenge_5_items.model.dto.request.ItemCreateRequest;
import com.e3gsix.fiap.tech_challenge_5_items.model.dto.response.ItemResponse;
import com.e3gsix.fiap.tech_challenge_5_items.model.entity.Item;
import com.e3gsix.fiap.tech_challenge_5_items.repository.ItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ItemsServiceImplTest {

    @Mock
    private ItemRepository itemRepository;

    @InjectMocks
    private ItemsServiceImpl itemsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void create_validData_shouldSaveSuccessfully() {
        ItemCreateRequest request = new ItemCreateRequest("Item1", "Description1", new BigDecimal("10.00"), 5);
        Item item = new Item("Item1", "Description1", new BigDecimal("10.00"), 5);
        Item savedItem = new Item(
                1L,
                item.getName(),
                item.getDescription(),
                item.getPrice(),
                item.getQuantity());

        when(itemRepository.save(any(Item.class))).thenReturn(savedItem);

        Long id = itemsService.create(request);

        assertEquals(1L, id);
        verify(itemRepository, times(1)).save(any(Item.class));
    }

    @Test
    void findById_existingItem_shouldReturnItemSuccessfully() {
        Long expectedId = 1L;
        Item item = new Item(expectedId, "Item1", "Description1", new BigDecimal("10.00"), 5);

        when(itemRepository.findById(expectedId)).thenReturn(Optional.of(item));

        ItemResponse response = itemsService.findById(expectedId);

        assertNotNull(response);
        assertEquals(expectedId, response.id());
        assertEquals(item.getName(), response.name());
        assertEquals(item.getDescription(), response.description());
        assertEquals(item.getPrice(), response.price());
        assertEquals(item.getQuantity(), response.quantity());
        verify(itemRepository, times(1)).findById(1L);
    }

    @Test
    void findById_notExistingItem_shouldThrowNotFoundException() {
        assertThrows(NotFoundException.class, () -> itemsService.findById(1L));
    }

    @Test
    void update_validData_shouldUpdateSuccessfully() {
        Integer expectedQuantity = 10;
        BigDecimal expectedPrice = new BigDecimal("20.00");
        Item item = new Item(1L, "Item1", "Description1", new BigDecimal("10.00"), 5);

        when(itemRepository.findById(1L)).thenReturn(Optional.of(item));
        when(itemRepository.save(any(Item.class))).thenReturn(item);

        ItemResponse response = itemsService.update(1L, 10, expectedPrice);

        assertNotNull(response);
        assertEquals(expectedQuantity, response.quantity());
        assertEquals(expectedPrice, response.price());
        verify(itemRepository, times(1)).findById(1L);
        verify(itemRepository, times(1)).save(any(Item.class));
    }

    @Test
    void update_justQuantity_shouldUpdateOnlyQuantityAttribute() {
        Integer expectedQuantity = 10;

        Item item = new Item(1L, "Item1", "Description1", new BigDecimal("10.00"), 5);

        when(itemRepository.findById(1L)).thenReturn(Optional.of(item));
        when(itemRepository.save(any(Item.class))).thenReturn(item);

        ItemResponse response = itemsService.update(1L, 10, null);

        assertNotNull(response);
        assertEquals(expectedQuantity, response.quantity());
        assertEquals(item.getPrice(), response.price());
        verify(itemRepository, times(1)).findById(1L);
        verify(itemRepository, times(1)).save(any(Item.class));
    }

    @Test
    void update_justPrice_shouldUpdateOnlyPriceAttribute() {
        BigDecimal expectedPrice = new BigDecimal("20.00");

        Item item = new Item(1L, "Item1", "Description1", new BigDecimal("10.00"), 5);

        when(itemRepository.findById(1L)).thenReturn(Optional.of(item));
        when(itemRepository.save(any(Item.class))).thenReturn(item);

        ItemResponse response = itemsService.update(1L, null, expectedPrice);

        assertNotNull(response);
        assertEquals(item.getQuantity(), response.quantity());
        assertEquals(expectedPrice, response.price());
        verify(itemRepository, times(1)).findById(1L);
        verify(itemRepository, times(1)).save(any(Item.class));
    }

    @Test
    void update_withNoQuantityNeitherPrice_shouldThrowUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> itemsService.update(1L, null, null));
    }

    @Test
    void deleteById_existingItem_shouldDeleteSuccessfully() {
        Item item = new Item(1L, "Item1", "Description1", new BigDecimal("10.00"), 5);

        when(itemRepository.findById(1L)).thenReturn(Optional.of(item));

        itemsService.deleteById(1L);

        verify(itemRepository, times(1)).findById(1L);
        verify(itemRepository, times(1)).deleteById(1L);
    }

    @Test
    void deleteById_existingItem_shouldThrowNotFoundException() {
        assertThrows(NotFoundException.class, () -> itemsService.deleteById(1L));
    }
}
