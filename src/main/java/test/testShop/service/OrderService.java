package test.testShop.service;

import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import test.testShop.domain.*;
import test.testShop.domain.item.Item;
import test.testShop.repository.ItemRepository;
import test.testShop.repository.MemberRepository;
import test.testShop.repository.OrderRepository;
import test.testShop.repository.OrderSearch;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final MemberRepository memberRepository;
    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;

    /** 주문 */
    @Transactional
    public Long order(Long memberId, Long itemId, int count) {

        //엔티티 조회
        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);

        //배송 정보 생성
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());
        //delivery.setStatus(DeliveryStatus.READY);

        //주문 상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        //주문 생성
        Order order = Order.createOrder(member, delivery, orderItem);

        //주문 저장
        orderRepository.save(order);

        return order.getId();
    }

    /** 주문 취소 */
    @Transactional
    public void cancelOrder(Long orderId) {

        //주문 엔티티 조회
        Order order = orderRepository.findOne(orderId);

        //주문 최소
        order.cancel();
    }


    @Transactional
    public List<Order> findOrders(OrderSearch orderSearch){
        return orderRepository.findAllByString(orderSearch);
    }


}
