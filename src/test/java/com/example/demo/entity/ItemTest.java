package com.example.demo.entity;

import com.example.demo.repository.ItemRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
//@Import({QuerydslConfig.class})
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // 실제 DB 사용
class ItemTest {

    @Autowired
    private ItemRepository itemRepository;

    @Test
    @DisplayName("status nullable= false 테스트")
    void testStatusNotNullConstraint(){

        // Given
        Item item = new Item("아이템이름", "설명", Mockito.mock(User.class), Mockito.mock(User.class));

        // When
        itemRepository.save(item);
        Item foundItem = itemRepository.findById(item.getId()).orElseThrow();

        // Then
        assertThat(foundItem).isNotNull();
        assertThat(foundItem.getStatus()).isEqualTo("PENDING");
    }

}