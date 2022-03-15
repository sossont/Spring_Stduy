package jpabook.jpashop.domain.service;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.repository.OrderRepository;
import jpabook.jpashop.exception.NotEnoughStockException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderServiceTest {

    @Autowired
    EntityManager em;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;

    @Test
    public void Order() throws Exception {
        Member member = createMember();

        int bookPrice = 20000;
        Book book = createBook(bookPrice, "노인과 바다", 10);


        int orderCount = 2;
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

        Order findOrder = orderRepository.findOne(orderId);

        assertEquals("상품 주문 상태는 ORDER", OrderStatus.ORDER, findOrder.getStatus());
        assertEquals("주문한 상품 종류 수가 정확해야 한다.", 1, findOrder.getOrderItems().size());
        assertEquals("주문 가격은 가격 * 수량 이다.", bookPrice * orderCount, findOrder.getTotalPrice());
        assertEquals("주문 수량만큼 재고가 줄어야 한다.", 8, book.getStockQuantity());
    }

    private Member createMember() {
        Member member = new Member();
        member.setName("MemberA");
        member.setAddress(new Address("서울", "한강가", "21234"));
        em.persist(member);
        return member;
    }

    private Book createBook(int bookPrice, String bookName, int stockQuantity) {
        Book book = new Book();
        book.setName(bookName);
        book.setPrice(bookPrice);
        book.setStockQuantity(stockQuantity);
        book.setAuthor("누구더라");
        em.persist(book);
        return book;
    }

    @Test
    public void Cancel() throws Exception {
        Member member = createMember();
        Book book = createBook(10000, "JPA", 10);
        Long orderId = orderService.order(member.getId(), book.getId(), 10);


        orderService.cancelOrder(orderId);

        Order order = orderRepository.findOne(orderId);
        assertEquals("주문 취소시 상태는 CANCEL", OrderStatus.CANCEL, order.getStatus());
        assertEquals("주문 취소시 재고는 복구되어야 한다.", 10, book.getStockQuantity());
    }

    @Test(expected = NotEnoughStockException.class)
    public void OverStock() throws Exception {
        Member member = createMember();
        int bookPrice = 10000;
        Book book = createBook(bookPrice, "노인과 바다", 10);
        int orderCount = 11;

        orderService.order(member.getId(), book.getId(), orderCount);

        fail("재고 수량 부족 예외가 발생되야 한다.");
    }
}