package com.myProject.subscription_service.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sub_Id;
    @JoinColumn(nullable = false)
    private String userId;
    @ManyToOne
    @JoinColumn(name = "plan_id", nullable = false)
    private SubscriptionPlan plan;
    private String status;
    private LocalDate startDate;
    private LocalDate endDate;
}
