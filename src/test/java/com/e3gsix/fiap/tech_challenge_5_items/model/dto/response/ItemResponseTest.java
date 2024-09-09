package com.e3gsix.fiap.tech_challenge_5_items.model.dto.response;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ItemResponseTest {
    @Test
    void testItemResponse() {
        ItemResponse item = new ItemResponse("Produto A", "Descrição do produto", BigDecimal.valueOf(100.0), 5);

        assertEquals("Produto A", item.name());
        assertEquals("Descrição do produto", item.description());
        assertEquals(BigDecimal.valueOf(100.0), item.price());
        assertEquals(Integer.valueOf(5), item.quantity());
    }
}