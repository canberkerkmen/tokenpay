package com.erkmen.domain;

import com.erkmen.domain.enums.OrderStatus;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "\"Order\"")
public class Order extends BaseEntity {

    @Column
    private String paymentApprove;

    @Column
    private LocalDateTime paymentDate;

    @Column
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderElement> orderElementList = new ArrayList<>();

    public void addOrderElement(OrderElement orderElement) {
        orderElementList.add(orderElement);
    }

}
