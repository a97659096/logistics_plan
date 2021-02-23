package com.logistics.plan.service;

import com.logistics.plan.domain.entity.Node;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tianshihao
 * @since 2021-02-22
 */
public interface NodeService extends IService<Node> {

    /**
     * 去重查询省份列表
     * @return
     */
    List<Map<String, String>> selectProvinceList();

    /**
     * 根据省份code去重查询城市集合
     * @param pCode
     * @return
     */
    List<Map<String, String>> selectCityListByPCode(String pCode);
}
