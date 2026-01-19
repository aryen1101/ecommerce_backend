package com.example.ecommerce.controller;

import com.example.ecommerce.dto.PaymentWebhookRequest;
import com.example.ecommerce.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/webhooks/payment")
@RequiredArgsConstructor
public class PaymentWebhookController {
    private final PaymentService service;

    @PostMapping
    public String webhook(@RequestBody PaymentWebhookRequest req) {
        service.update(req.getOrderId());
        return "Payment processed";
    }
}
