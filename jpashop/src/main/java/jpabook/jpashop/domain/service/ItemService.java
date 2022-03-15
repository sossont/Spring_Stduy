package jpabook.jpashop.domain.service;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.domain.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }

    // 변경 감지
    @Transactional
    public void updateItem(Long itemId, Book bookParam) {
        Item findItem = itemRepository.findOne(itemId);
        findItem.setName(bookParam.getName());
        findItem.setPrice(bookParam.getPrice());
        findItem.setStockQuantity(bookParam.getStockQuantity());
    }
}
