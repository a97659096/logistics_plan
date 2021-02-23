package com.logistics.plan.service;

import java.math.BigDecimal;

public interface PlaningService {

    void routeAndDistCreate(String path) throws Exception;

    BigDecimal getSchedule();

}
