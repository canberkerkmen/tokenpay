package com.erkmen.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "\"OrderElement\"")
@EqualsAndHashCode(callSuper = false)
public class OrderElement extends BaseEntity {

    @Column
    private Double quantity = 0d;

    @Column
    private Double totalPrice = 0d;

    @OneToOne
    private Product product;

    @ManyToOne
    private Order order;

}
