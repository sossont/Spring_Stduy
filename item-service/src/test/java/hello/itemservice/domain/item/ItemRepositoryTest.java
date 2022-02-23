package hello.itemservice.domain.item;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();

    // 테스트 할 때마다 값 없애주기
    @AfterEach
    void afterEach(){
        itemRepository.clearStore();
    }

    @Test
    void save() {
        Item item = new Item("itemA", 10000, 10);

        Item savedItem = itemRepository.save(item);

        Item findItem = itemRepository.findById(item.getId());
        assertThat(findItem).isEqualTo(savedItem);
    }

    @Test
    void findAll() {
        Item itemA = new Item("itemA", 20000, 20);
        Item itemB = new Item("itemB", 10000, 10);

        itemRepository.save(itemA);
        itemRepository.save(itemB);

        List<Item> result = itemRepository.findAll();


        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(itemA,itemB);

    }

    @Test
    void updateItem(){
        Item itemA = new Item("itemA", 20000, 30);
        Item savedItem = itemRepository.save(itemA);

        Item updateParam = new Item("update item",30000,30);
        itemRepository.update(savedItem.getId(), updateParam);

        Item findItem = itemRepository.findById(savedItem.getId());

        assertThat(findItem.getItemName()).isEqualTo(updateParam.getItemName());
        assertThat(findItem.getPrice()).isEqualTo(updateParam.getPrice());
        assertThat(findItem.getQuantity()).isEqualTo(updateParam.getQuantity());
    }
}