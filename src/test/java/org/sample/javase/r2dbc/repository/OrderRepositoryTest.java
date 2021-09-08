package org.sample.javase.r2dbc.repository;

import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.sample.javase.r2dbc.H2ConnectionConfiguration;
import org.sample.javase.r2dbc.model.OrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.test.StepVerifier;

@ExtendWith({SpringExtension.class})
@Import({H2ConnectionConfiguration.class})
class OrderRepositoryTest {
    
    @Autowired
    private OrderRepository orderRepository; 
    
    @Test
    void testSave() {
        UUID id = UUID.randomUUID();
        OrderRequest request = new OrderRequest();
        request.setId(id);
        request.setCreatedBy("test-user");
        
        var persisted = orderRepository.save(request)
                .map(a -> orderRepository.findById(a.getId()))
                .flatMap((a -> a.map(b -> b.getId())));
        
        StepVerifier.create(persisted).expectNext(id).verifyComplete();
    }
}
