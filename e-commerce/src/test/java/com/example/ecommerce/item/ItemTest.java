package com.example.ecommerce.item;

import com.example.ecommerce.domain.Item;
import com.example.ecommerce.domain.items.Book;
import com.example.ecommerce.repository.ItemRepository;
import com.example.ecommerce.service.ItemService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@Transactional
public class ItemTest {

    private final ItemService itemService;
    private final ItemRepository itemRepository;

    @Autowired
    public ItemTest(ItemService itemService, ItemRepository itemRepository) {
        this.itemService = itemService;
        this.itemRepository = itemRepository;
    }

    @Test
    void itemSave() {
        Item item = new Book();
        item.setName("jihun");
        item.setPrice(20000);
        item.setStockQuantity(12);
        Long num = itemService.saveItem(item);
        Assertions.assertEquals(item,itemRepository.findOne(num));
  }
}
