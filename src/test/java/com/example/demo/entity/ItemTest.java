package com.example.demo.entity;


import com.example.demo.config.QuerydslConfig;
import com.example.demo.repository.ItemRepository;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;


import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
//@Import({QuerydslConfig.class})
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // 실제 DB 사용
class ItemTest {

    @Autowired
    private ItemRepository itemRepository;
//    @Autowired
//    private UserRepository userRepository;

    @Test
    @DisplayName("status nullable= false 테스트")
    void testStatusNotNullConstraint(){

        // Given
//        User manager = userRepository.save(new User("admin", "diqkraud2@naver.com", "장대산", "hs1156711"));
//        User owner =userRepository.save( new User("user", "diqkraud3@naver.com", "유저장대산", "hs1156711"));

        Item item = new Item("아이템이름", "설명", Mockito.mock(User.class), Mockito.mock(User.class));
        // When
        itemRepository.save(item);
        Item foundItem = itemRepository.findById(item.getId()).orElseThrow();

        // Then
        assertThat(foundItem).isNotNull();
        assertThat(foundItem.getStatus()).isEqualTo("PENDING");
    }

}