package com.erkmen.domain.factory;

import com.erkmen.domain.Order;
import com.erkmen.domain.OrderElement;
import com.erkmen.domain.Product;
import com.erkmen.domain.User;
import com.erkmen.dto.*;

public interface ApplicationFactory {

    public Product createProduct(ProductInfoDTO productInfoDTO);

    public User createUser(UserDataDTO userDataDTO);

    public UserResponseDTO createUserResponseDTO(User user);

    public Order addOrderElement(OrderElementInfoDTO orderElementInfoDTO, Product product, User user, Order order);

    public OrderElement createOrderElement(OrderElementInfoDTO orderElementInfoDTO, Product product, Order order);

    public Order editOrderElement(OrderElementInfoDTO orderElementInfoDTO, Product product, User user, Order existOrder);
}
