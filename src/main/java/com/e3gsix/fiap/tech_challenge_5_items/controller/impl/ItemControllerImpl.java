package com.e3gsix.fiap.tech_challenge_5_items.controller.impl;

import com.e3gsix.fiap.tech_challenge_5_items.controller.ItemController;
import com.e3gsix.fiap.tech_challenge_5_items.model.dto.request.ItemCreateRequest;
import com.e3gsix.fiap.tech_challenge_5_items.model.dto.response.ItemResponse;
import com.e3gsix.fiap.tech_challenge_5_items.service.ItemsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

import static com.e3gsix.fiap.tech_challenge_5_items.controller.impl.ItemControllerImpl.URL_ITEM;

@RestController
@RequestMapping(URL_ITEM)
public class ItemControllerImpl implements ItemController {

    public static final String URL_ITEM = "/items";
    public static final String URL_FIND_ITEM_BY_ID = "/{id}";
    public static final String URL_UPDATE_QUANTITY_ITEM_BY_ID = "/{id}";

    private final ItemsService itemsService;

    public ItemControllerImpl(ItemsService itemsService) {
        this.itemsService = itemsService;
    }

    @Override
    @PostMapping
    public ResponseEntity create(
            @RequestBody ItemCreateRequest request,
            UriComponentsBuilder uriComponentsBuilder
    ) {
        Long createdItemId = this.itemsService.create(request);

        URI uri = uriComponentsBuilder.path(URL_ITEM.concat(URL_FIND_ITEM_BY_ID))
                .buildAndExpand(createdItemId)
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @Override
    @GetMapping(URL_FIND_ITEM_BY_ID)
    public ResponseEntity<ItemResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(this.itemsService.findById(id));
    }

    @Override
    @PatchMapping(URL_UPDATE_QUANTITY_ITEM_BY_ID)
    public ResponseEntity<ItemResponse> updateQuantity(
            @PathVariable Long id,
            @RequestParam Integer quantity
    ) {
        ItemResponse updatedItem = this.itemsService.updateQuantity(id, quantity);

        return ResponseEntity.ok(updatedItem);
    }
}
