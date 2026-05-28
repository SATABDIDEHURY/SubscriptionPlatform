package com.myProject.subscription_service.repository;

import com.myProject.subscription_service.entity.SubscriptionPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanRepository extends JpaRepository<SubscriptionPlan, Long> {
}
