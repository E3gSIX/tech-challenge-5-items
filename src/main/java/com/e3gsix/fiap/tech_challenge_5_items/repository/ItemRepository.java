package com.e3gsix.fiap.tech_challenge_5_items.repository;

import com.e3gsix.fiap.tech_challenge_5_items.model.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
}
