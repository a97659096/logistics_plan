package com.logistics.plan.mapper;

import com.logistics.plan.domain.entity.RealLine;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 路线表
 * @author wsq
 */
@Mapper
public interface RealLineRepository {

    /**
     *
     */
    List<RealLine> findAll();

    /**
     *
     */
    List<RealLine> findByCode();

    /**
     * 插入数据到路线
     * @param realLine
     */
    void doCreate(RealLine realLine);

    /**
     * 批量插入数据到路线
     * @param realLine
     */
    void doCreateList(List<RealLine> realLineList);

    /**
     * 修改路线表数据
     * @param realLine
     */
    void updatePosting(RealLine realLine);

    /**
     * 修改路线表数据
     * @param realLine
     */
    void updateReach(RealLine realLine);

    /**
     * 修改路线表数据
     * @param realLine
     */
    void updateData(RealLine realLine);

}
