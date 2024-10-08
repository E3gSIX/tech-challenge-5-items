package com.e3gsix.fiap.tech_challenge_5_items.model.dto.response;

import java.math.BigDecimal;

public record ItemResponse(
        Long id,
        String name,
        String description,
        BigDecimal price,
        Integer quantity
) {
}
