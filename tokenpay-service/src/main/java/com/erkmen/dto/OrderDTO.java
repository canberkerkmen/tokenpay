package com.erkmen.dto;

import com.erkmen.domain.enums.OrderStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderDTO {

    private String paymentApprove;

    private LocalDateTime paymentDate;

    private Double orderTotalPrice;

    private OrderStatus status;

    private List<OrderElementDTO> orderElementList;

}
