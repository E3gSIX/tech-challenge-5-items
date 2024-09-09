package com.e3gsix.fiap.tech_challenge_5_items.model.entity;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {

    @Test
    void testItemConstructor_ValidItem_ShouldSetCorrectly() {
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
    void testSetName_ValidName_ShouldSetCorrectly() {
        Item item = new Item();
        item.setName("Laptop");
        assertEquals("Laptop", item.getName());
    }

    @Test
    void testSetName_NullName_ShouldThrowException() {
        Item item = new Item();
        assertThrows(IllegalArgumentException.class, () -> item.setName(null));
    }

    @Test
    void testSetPrice_ValidPrice_ShouldSetCorrectly() {

        Item item = new Item();
        BigDecimal price = new BigDecimal("999.99");


        item.setPrice(price);

        assertEquals(price, item.getPrice());
    }

    @Test
    void testSetPrice_NegativePrice_ShouldThrowException() {

        Item item = new Item();
        BigDecimal negativePrice = new BigDecimal("-100.00");


        assertThrows(IllegalArgumentException.class, () -> item.setPrice(negativePrice));
    }

    @Test
    void testSetPrice_NullPrice_ShouldThrowException() {
        Item item = new Item();
        assertThrows(IllegalArgumentException.class, () -> item.setPrice(null));
    }

    @Test
    void testSetQuantity_ValidQuantity_ShouldSetCorrectly() {

        Item item = new Item();
        item.setQuantity(10);

        assertEquals(10, item.getQuantity());
    }

    @Test
    void testSetQuantity_NegativeQuantity_ShouldThrowException() {

        Item item = new Item();


        assertThrows(IllegalArgumentException.class, () -> item.setQuantity(-5));
    }

    @Test
    void testSetDescription_InvalidDescription_ShouldSetCorrectly() {
        Item item = new Item();
        item.setDescription("Test description");
        assertEquals("Test description", item.getDescription());
    }

    @Test
    void testSetDescription_NullDescription_ShouldThrowException() {
        Item item = new Item();
        assertThrows(IllegalArgumentException.class, () -> item.setDescription(null));
    }
}
