package org.example.eccommerceinspringboot.dto;

import java.util.UUID;

public record ProductReq(
        String name,
        Integer price,
        UUID categoryID
) {
}
