package com.example.ecommerce.service;


import com.example.ecommerce.dto.PaymentRequest;
import com.example.ecommerce.model.Order;
import com.example.ecommerce.model.Payment;
import com.example.ecommerce.repository.OrderRepository;
import com.example.ecommerce.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository repo;
    private final OrderRepository orderRepo;

    public Payment create(PaymentRequest req) {
        Payment p = new Payment();
        p.setOrderId(req.getOrderId());
        p.setAmount(req.getAmount());
        p.setStatus("PENDING");
        p.setCreatedAt(Instant.now());
        return repo.save(p);
    }

    public void update(String orderId) {
        Payment p = repo.findByOrderId(orderId).orElseThrow();
        p.setStatus("SUCCESS");
        repo.save(p);

        Order o = orderRepo.findById(orderId).orElseThrow();
        o.setStatus("PAID");
        orderRepo.save(o);
    }
}
