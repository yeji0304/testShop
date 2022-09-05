package test.testShop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import test.testShop.domain.Member;
import test.testShop.domain.Order;
import test.testShop.domain.item.Item;
import test.testShop.repository.OrderSearch;
import test.testShop.service.ItemService;
import test.testShop.service.MemberService;
import test.testShop.service.OrderService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final ItemService itemService;
    private final MemberService memberService;

    /**
     * 상품 주문
     */
    @GetMapping("/order")
    public String createForm(Model model) {

        List<Member> members = memberService.findMembers();
        List<Item> items = itemService.findItems();

        model.addAttribute("members", members);
        model.addAttribute("items", items);

        return "order/orderForm";
    }

    @PostMapping("/order")
    public String order(@RequestParam("memberId")Long memberId,
                        @RequestParam("itemId")Long itemId,
                        @RequestParam("count")int count) {

        orderService.order(memberId, itemId, count);
        return "redirect:/orders";
    }

    /**
     * 주문 목록 검색, 취소
     */
    @GetMapping("/orders")
    public String orderList(@ModelAttribute("orderSearch")OrderSearch orderSearch, Model model) {

        List<Order> orders = orderService.findOrders(orderSearch);
        model.addAttribute("orders", orders);

        return "order/orderList";
    }

    /**
     * 주문 취소
     */
    @PostMapping("/orders/{orderId}/cancel")
    public String cancelOrder(@PathVariable("orderId")Long orderId) {

        orderService.cancelOrder(orderId);
        return "redirect:/orders";
    }
}
