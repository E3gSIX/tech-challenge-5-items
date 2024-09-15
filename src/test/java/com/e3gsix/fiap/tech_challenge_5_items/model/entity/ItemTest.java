package com.e3gsix.fiap.tech_challenge_5_items.model.entity;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ItemTest {

    @Test
    void constructor_ValidItem_ShouldSetCorrectly() {
        String name = "Livro";
        String description = "Um livro interessante";
        BigDecimal price = new BigDecimal("29.99");
        Integer quantity = 5;
        Item item = new Item(name, description, price, quantity);

        assertEquals(name, item.getName());
        assertEquals(description, item.getDescription());
        assertEquals(price, item.getPrice());
        assertEquals(quantity, item.getQuantity());
    }

    @Test
    void constructor_ValidItemAlsoId_ShouldSetCorrectly() {
        Long id = 1L;
        String name = "Livro";
        String description = "Um livro interessante";
        BigDecimal price = new BigDecimal("29.99");
        Integer quantity = 5;
        Item item = new Item(id, name, description, price, quantity);

        assertEquals(id, item.getId());
        assertEquals(name, item.getName());
        assertEquals(description, item.getDescription());
        assertEquals(price, item.getPrice());
        assertEquals(quantity, item.getQuantity());
    }

    @Test
    void setName_ValidName_ShouldSetCorrectly() {
        Item item = new Item();
        item.setName("Laptop");
        assertEquals("Laptop", item.getName());
    }

    @Test
    void setName_withBlankSpacesINName_ShouldSetCorrectly() {
        Item item = new Item();
        String nameWithSpaces = "  Nome com espaços  ";

        item.setDescription(nameWithSpaces.trim());

        assertEquals("Nome com espaços", item.getDescription());
    }

    @Test
    void setName_nullData_shouldThrowIllegalArgumentException() {
        Item item = new Item();

        Exception exception = assertThrows(IllegalArgumentException.class, () -> item.setDescription(null));

        assertEquals("Descrição do item deve ser informado.", exception.getMessage());
    }

    @Test
    void setName_blankData_shouldThrowIllegalArgumentException() {
        Item item = new Item();

        Exception exception = assertThrows(IllegalArgumentException.class, () -> item.setDescription("   "));

        assertEquals("Descrição do item deve ser informado.", exception.getMessage());
    }

    @Test
    void setName_emptyData_shouldThrowIllegalArgumentException() {
        Item item = new Item();

        Exception exception = assertThrows(IllegalArgumentException.class, () -> item.setDescription(""));

        assertEquals("Descrição do item deve ser informado.", exception.getMessage());
    }

    @Test
    void setPrice_ValidPrice_ShouldSetCorrectly() {

        Item item = new Item();
        BigDecimal price = new BigDecimal("999.99");


        item.setPrice(price);

        assertEquals(price, item.getPrice());
    }

    @Test
    void setPrice_NegativePrice_ShouldThrowException() {

        Item item = new Item();
        BigDecimal negativePrice = new BigDecimal("-100.00");


        assertThrows(IllegalArgumentException.class, () -> item.setPrice(negativePrice));
    }

    @Test
    void setPrice_NullPrice_ShouldThrowException() {
        Item item = new Item();
        assertThrows(IllegalArgumentException.class, () -> item.setPrice(null));
    }

    @Test
    void setQuantity_ValidQuantity_ShouldSetCorrectly() {

        Item item = new Item();
        item.setQuantity(10);

        assertEquals(10, item.getQuantity());
    }

    @Test
    void setQuantity_NegativeQuantity_ShouldThrowException() {

        Item item = new Item();


        assertThrows(IllegalArgumentException.class, () -> item.setQuantity(-5));
    }

    @Test
    void setDescription_InvalidDescription_ShouldSetCorrectly() {
        Item item = new Item();
        item.setDescription("Test description");
        assertEquals("Test description", item.getDescription());
    }

    @Test
    void setDescription_NullDescription_ShouldThrowException() {
        Item item = new Item();
        assertThrows(IllegalArgumentException.class, () -> item.setDescription(null));
    }
}
