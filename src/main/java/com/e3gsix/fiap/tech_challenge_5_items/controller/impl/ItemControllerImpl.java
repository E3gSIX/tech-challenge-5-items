package com.e3gsix.fiap.tech_challenge_5_items.controller.impl;

import com.e3gsix.fiap.tech_challenge_5_items.controller.ItemController;
import com.e3gsix.fiap.tech_challenge_5_items.model.dto.request.ItemCreateRequest;
import com.e3gsix.fiap.tech_challenge_5_items.model.dto.response.ItemResponse;
import com.e3gsix.fiap.tech_challenge_5_items.service.ItemsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;

import static com.e3gsix.fiap.tech_challenge_5_items.controller.impl.ItemControllerImpl.URL_ITEM;

@Tag(name = "Itens", description = "Administração de itens.")
@RestController
@RequestMapping(URL_ITEM)
public class ItemControllerImpl implements ItemController {

    public static final String URL_ITEM = "/items";
    public static final String URL_ITEM_BY_ID = "/{id}";

    private final ItemsService itemsService;

    public ItemControllerImpl(ItemsService itemsService) {
        this.itemsService = itemsService;
    }

    @Operation(
            summary = "Cadastrar Item",
            description = "Endpoint voltado à criação de um Item na base de dados.",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Item registrado com sucesso.",
                            headers = {
                                    @Header(
                                            name = "Location",
                                            description = "Localização do recurso criado."
                                    )
                            }),
                    @ApiResponse(responseCode = "401", description = "Token não foi informado ou está inválido."),
                    @ApiResponse(responseCode = "403", description = "Requisitante não tem permissões de administrador."),
            }
    )
    @Override
    @PostMapping
    public ResponseEntity create(
            @RequestBody ItemCreateRequest request,
            UriComponentsBuilder uriComponentsBuilder
    ) {
        Long createdItemId = this.itemsService.create(request);

        URI uri = uriComponentsBuilder.path(URL_ITEM.concat(URL_ITEM_BY_ID))
                .buildAndExpand(createdItemId)
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @Operation(
            summary = "Consultar Item por ID",
            description = "Recurso dedicado à buscar um Item através do ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Item encontrado com sucesso."),
                    @ApiResponse(responseCode = "401", description = "Token não foi informado ou está inválido."),
                    @ApiResponse(responseCode = "403", description = "Requisitante não tem permissões de administrador."),
                    @ApiResponse(responseCode = "404", description = "Item com ID informado não foi encontrado.")
            }
    )
    @Override
    @GetMapping(URL_ITEM_BY_ID)
    public ResponseEntity<ItemResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(this.itemsService.findById(id));
    }

    @Operation(
            summary = "Atualizar Item",
            description = "Funcionalidade de atualização de dados de um Item, apenas quantidade em estoque e preço podem ser alterados.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Item alterado com sucesso."),
                    @ApiResponse(responseCode = "400", description = "Inconsistência nos dados recebidos."),
                    @ApiResponse(responseCode = "401", description = "Token não foi informado ou está inválido."),
                    @ApiResponse(responseCode = "403", description = "Requisitante não tem permissões de administrador."),
                    @ApiResponse(responseCode = "404", description = "Item com ID informado não foi encontrado.")
            }
    )
    @Override
    @PatchMapping(URL_ITEM_BY_ID)
    public ResponseEntity<ItemResponse> update(
            @PathVariable Long id,
            @RequestParam(required = false) Integer quantity,
            @RequestParam(required = false) BigDecimal price
    ) {
        ItemResponse updatedItem = this.itemsService.update(id, quantity, price);

        return ResponseEntity.ok(updatedItem);
    }

    @Operation(
            summary = "Excluir Item",
            description = "Endpoint destinado à deleção de um Item na base de dados.",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Item excluso com sucesso."),
                    @ApiResponse(responseCode = "401", description = "Token não foi informado ou está inválido."),
                    @ApiResponse(responseCode = "403", description = "Requisitante não tem permissões de administrador."),
                    @ApiResponse(responseCode = "404", description = "Item com ID informado não foi encontrado.")
            }
    )
    @Override
    @DeleteMapping(URL_ITEM_BY_ID)
    public ResponseEntity delete(@PathVariable Long id) {
        this.itemsService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
