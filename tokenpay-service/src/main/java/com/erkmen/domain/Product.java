package com.erkmen.domain;

import com.erkmen.domain.enums.QuantityType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "Product")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Product extends BaseEntity {

    @Id
    @Size(min = 4, max = 255, message = "Minimum code length: 4 characters")
    @Column(unique = true, nullable = false)
    @EqualsAndHashCode.Include
    private String code;

    @NotNull(message = "Product name must not be null")
    @Column(nullable = false)
    private String name;

    @NotNull(message = "Product description must not be null")
    @Column(nullable = false)
    private String description;

    @NotNull(message = "Quantity type must not be null")
    @Column(nullable = false)
    private QuantityType quantityType;

    @NotNull(message = "Price type must not be null")
    @Column(nullable = false)
    private Double price;

    @Column
    private Boolean isActive = Boolean.TRUE;

}
