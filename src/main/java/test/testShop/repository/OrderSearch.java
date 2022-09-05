package test.testShop.repository;

import lombok.Getter;
import lombok.Setter;
import test.testShop.domain.OrderStatus;

@Getter @Setter
public class OrderSearch {

    private String memberName;
    private OrderStatus orderStatus;
}
