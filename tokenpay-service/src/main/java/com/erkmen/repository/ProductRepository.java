package com.erkmen.repository;

import com.erkmen.domain.Product;
import com.erkmen.domain.User;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends BaseRepository<Product> {

    Product findByCode(String code);

    Integer deleteByCode(String code);


}
