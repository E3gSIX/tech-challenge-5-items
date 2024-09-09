package com.e3gsix.fiap.tech_challenge_5_items.service;

import com.e3gsix.fiap.tech_challenge_5_items.model.dto.request.ItemCreateRequest;
import com.e3gsix.fiap.tech_challenge_5_items.model.dto.response.ItemResponse;
import com.e3gsix.fiap.tech_challenge_5_items.model.entity.Item;
import com.e3gsix.fiap.tech_challenge_5_items.repository.ItemRepository;
import com.e3gsix.fiap.tech_challenge_5_items.service.impl.ItemsServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Optional;

class ItemServiceImplTest {

    private ItemsService itemsService;

    @Mock
    private ItemRepository itemRepository;

    AutoCloseable openMocks;

    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
        itemsService = new ItemsServiceImpl(itemRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    void testCreate_withValue_successful() {
        ItemCreateRequest item = new ItemCreateRequest("Produto A", "Descrição do produto", BigDecimal.valueOf(100.0), 5);
        Item savedItem = new Item("Produto A", "Descrição do produto", BigDecimal.valueOf(100.0), 5);
        Mockito.when(itemRepository.save(Mockito.any(Item.class))).thenReturn(savedItem);

        Long itemId = itemsService.create(item);

        assertThat(itemId)
                .isEqualTo(savedItem.getId());
        verify(itemRepository, times(1)).save(any(Item.class));
    }

    @Test
    void testFindById_withValue_successful() {
        Item savedItem = new Item("Produto A", "Descrição do produto", BigDecimal.valueOf(100.0), 5);
        Mockito.when(itemRepository.findById(savedItem.getId())).thenReturn(Optional.of(savedItem));

        ItemResponse itemFound = itemsService.findById(savedItem.getId());

        assertThat(savedItem.getName()).isEqualTo(itemFound.name());
        assertThat(savedItem.getDescription()).isEqualTo(itemFound.description());
        assertThat(savedItem.getPrice()).isEqualTo(itemFound.price());
        assertThat(savedItem.getQuantity()).isEqualTo(itemFound.quantity());

        verify(itemRepository, times(1)).findById(savedItem.getId());
    }
}