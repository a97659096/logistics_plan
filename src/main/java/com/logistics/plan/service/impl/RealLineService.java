package com.logistics.plan.service.impl;

import com.logistics.plan.domain.entity.RealLine;
import com.logistics.plan.mapper.RealLineRepository;
import com.logistics.plan.service.IRealLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RealLineService implements IRealLineService {

    @Autowired
    private RealLineRepository realLineRepository;

    @Override
    public List<RealLine> findByCode() {
        return realLineRepository.findByCode();
    }

    @Override
    public List<RealLine> findAll() {
        return realLineRepository.findAll();
    }

    @Override
    public void doCreate(RealLine line) {
        realLineRepository.doCreate(line);
    }

    @Override
    public void updatePosting(RealLine realLine) {
        realLineRepository.updatePosting(realLine);
    }

    @Override
    public void updateReach(RealLine realLine) {
        realLineRepository.updateReach(realLine);
    }

    @Override
    public void doCreateList(List<RealLine> realLineList) {
        realLineRepository.doCreateList(realLineList);
    }

    @Override
    public void updateData(RealLine realLine) {
        realLineRepository.updateData(realLine);
    }
}
