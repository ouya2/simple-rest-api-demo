package com.acme.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;

/**
 * Health check
 */
public class CustomerServiceHealthChecker implements HealthIndicator {

  @Autowired
  private ServiceProperties configuration;

  @Override
  public Health health() {
    return Health.up().withDetail("details", "{ 'internals' : 'getting close to limit', 'profile' : '" +
        this.configuration.getName() + "' }").status("itsok!").build();
  }
}
