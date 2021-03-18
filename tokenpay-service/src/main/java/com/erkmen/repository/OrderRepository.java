package com.erkmen.repository;

import com.erkmen.domain.Order;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface OrderRepository extends BaseRepository<Order> {

}
