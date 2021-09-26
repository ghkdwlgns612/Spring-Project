package com.example.ecommerce.service;

import com.example.ecommerce.domain.Item;
import com.example.ecommerce.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {
    private ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    @Transactional
    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    @Transactional
    public Item findOne(Long id) {
        return itemRepository.findOne(id);
    }
}
