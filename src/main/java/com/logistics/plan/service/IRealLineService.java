package com.logistics.plan.service;


import com.logistics.plan.domain.entity.RealLine;

import java.util.List;

public interface IRealLineService {
    List<RealLine> findByCode();

    List<RealLine> findAll();

    void doCreate(RealLine line);

    void updatePosting(RealLine realLine);

    void updateReach(RealLine realLine);

    void doCreateList(List<RealLine> realLineList);

    void updateData(RealLine realLine);
}
