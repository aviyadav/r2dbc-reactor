package org.sample.javase.r2dbc.repository;

import java.util.UUID;
import org.sample.javase.r2dbc.model.OrderRequest;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface OrderRepository extends ReactiveCrudRepository<OrderRequest, UUID> {
}
