package com.erkmen.domain.factory;

import com.erkmen.domain.*;
import com.erkmen.domain.enums.OrderStatus;
import com.erkmen.domain.enums.QuantityType;
import com.erkmen.domain.enums.Role;
import com.erkmen.dto.*;

import java.util.ArrayList;
import java.util.List;

public class DefaultApplicationFactory implements ApplicationFactory {

    @Override
    public Product createProduct(ProductInfoDTO productInfoDTO) {
        return new Product(productInfoDTO.getCode(), productInfoDTO.getName(), productInfoDTO.getDescription(), QuantityType.ofName(productInfoDTO.getQuantityType()), productInfoDTO.getPrice(), true);
    }

    @Override
    public User createUser(UserDataDTO userDataDTO) {
        User user = new User();
        user.setEmail(userDataDTO.getEmail());
        user.setUsername(userDataDTO.getUsername());
        user.setPassword(userDataDTO.getPassword());
        user.setRole(Role.USER);
        return user;
    }

    @Override
    public UserResponseDTO createUserResponseDTO(User user) {
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(user.getId());
        userResponseDTO.setEmail(user.getEmail());
        userResponseDTO.setUsername(user.getUsername());
        userResponseDTO.setRole(user.getRole());
        userResponseDTO.setOrderList(getOrderDTOList(user.getOrderList()));
        return userResponseDTO;
    }

    private List<OrderDTO> getOrderDTOList(List<Order> orderList) {
        List<OrderDTO> orderDTOList = new ArrayList<>();
        orderList.stream().forEach(order -> {
            OrderDTO orderDTO = new OrderDTO();
            orderDTO.setStatus(order.getStatus());
            orderDTO.setPaymentApprove(order.getPaymentApprove());
            orderDTO.setPaymentDate(order.getPaymentDate());
            orderDTO.setOrderElementList(getOrderElementDTOList(order.getOrderElementList()));
            orderDTOList.add(orderDTO);
        });
        return orderDTOList;
    }

    private List<OrderElementDTO> getOrderElementDTOList(List<OrderElement> orderElementList) {
        List<OrderElementDTO> orderElementDTOList = new ArrayList<>();
        orderElementList.stream().forEach(orderElement -> {
            OrderElementDTO orderElementDTO = new OrderElementDTO();
            orderElementDTO.setQuantity(orderElement.getQuantity());
            orderElementDTO.setTotalPrice(orderElement.getTotalPrice());
            orderElementDTO.setProduct(getProduct(orderElement.getProduct()));
            orderElementDTOList.add(orderElementDTO);
        });
        return orderElementDTOList;
    }

    private ProductDTO getProduct(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setCode(product.getCode());
        productDTO.setDescription(product.getDescription());
        productDTO.setName(product.getName());
        productDTO.setPrice(product.getPrice());
        productDTO.setQuantityType(product.getQuantityType());
        return productDTO;
    }

    @Override
    public Order addOrderElement(OrderElementInfoDTO orderElementInfoDTO, Product product, User user, Order order) {
        OrderElement orderElement = createOrderElement(orderElementInfoDTO, product, order);
        order.addOrderElement(orderElement);
        order.setUser(user);
        order.setStatus(OrderStatus.IN_PROGRESS);
        return order;
    }

    @Override
    public OrderElement createOrderElement(OrderElementInfoDTO orderElementInfoDTO, Product product, Order order) {
        OrderElement orderElement = order.getOrderElementList().stream().filter(oe -> oe.getProduct().equals(product)).findAny().orElse(new OrderElement());
        orderElement.setProduct(product);
        orderElement.setQuantity(orderElement.getQuantity() + orderElementInfoDTO.getQuantity());
        orderElement.setTotalPrice(orderElement.getTotalPrice() + (product.getPrice() * orderElementInfoDTO.getQuantity()));
        orderElement.setOrder(order);
        return orderElement;
    }

    @Override
    public Order editOrderElement(OrderElementInfoDTO orderElementInfoDTO, Product product, User user, Order order) {

        if (orderElementInfoDTO.getQuantity().equals(0)) {
            order.getOrderElementList().removeIf(orderElement -> orderElement.getProduct().equals(product));
        } else {
            order.getOrderElementList().stream()
                    .filter(orderElement -> orderElement.getProduct().equals(product))
                    .findFirst()
                    .ifPresent(orderElement -> {
                        orderElement.setQuantity(orderElementInfoDTO.getQuantity());
                        orderElement.setTotalPrice((product.getPrice() * orderElementInfoDTO.getQuantity()));
                    });
        }
        return order;
    }
}
