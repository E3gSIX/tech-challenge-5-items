package com.e3gsix.fiap.tech_challenge_5_items.model.dto.request;

import java.math.BigDecimal;

public record ItemCreateRequest(
        String name,
        String description,
        BigDecimal price,
        Integer quantity
) {
}
