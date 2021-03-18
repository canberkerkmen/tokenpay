package com.erkmen.service;

import com.erkmen.domain.Order;
import com.erkmen.domain.Product;
import com.erkmen.domain.User;
import com.erkmen.domain.enums.OrderStatus;
import com.erkmen.domain.factory.ApplicationFactory;
import com.erkmen.dto.OrderElementInfoDTO;
import com.erkmen.dto.PayDTO;
import com.erkmen.exception.CustomException;
import com.erkmen.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Service
public class OrderService extends BaseService<Order, OrderRepository> {

    private final ApplicationFactory applicationFactory;
    private final UserService userService;
    private final ProductService productService;

    @Autowired
    public OrderService(OrderRepository repository, ApplicationFactory applicationFactory, UserService userService, ProductService productService) {
        super(repository);
        this.applicationFactory = applicationFactory;
        this.userService = userService;
        this.productService = productService;
    }

    public void add(HttpServletRequest req, OrderElementInfoDTO orderElementInfoDTO) {
        User user = userService.whoami(req);
        Product product = productService.getByProductCode(orderElementInfoDTO.getProductCode());
        if (product == null) {
            throw new CustomException("product not found", HttpStatus.BAD_REQUEST);
        }
        Order existOrder = user.getOrderList().stream().filter(o -> o.getStatus().equals(OrderStatus.IN_PROGRESS)).findAny().orElse(new Order());
        Order order = applicationFactory.addOrderElement(orderElementInfoDTO, product, user, existOrder);
        save(order);
    }

    public void pay(PayDTO payDTO) {
        Order order = getRepository().getOne(Long.valueOf(payDTO.getOrderId()));
        order.setStatus(OrderStatus.WAITING_PAYMENT);
        save(order);
        System.out.println("ödeme alınacak");
        order.setPaymentApprove("onaylandı");
        order.setStatus(OrderStatus.COMPLETED);
        order.setPaymentDate(LocalDateTime.now());
        save(order);

    }

    public void edit(HttpServletRequest req, OrderElementInfoDTO orderElementInfoDTO) {
        User user = userService.whoami(req);
        Product product = productService.getByProductCode(orderElementInfoDTO.getProductCode());
        if (product == null) {
            throw new CustomException("product not found", HttpStatus.OK);
        }
        Order existOrder = user.getOrderList().stream().filter(o -> o.getStatus().equals(OrderStatus.IN_PROGRESS)).findAny().orElse(new Order());
        Order order = applicationFactory.editOrderElement(orderElementInfoDTO, product, user, existOrder);
        save(order);
    }
}
