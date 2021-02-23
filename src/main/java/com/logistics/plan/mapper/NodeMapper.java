package com.logistics.plan.mapper;

import com.logistics.plan.domain.entity.Node;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author tianshihao
 * @since 2021-02-22
 */
@Mapper
public interface NodeMapper extends BaseMapper<Node> {

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
    List<Map<String, String>> selectCityListByPCode(@Param("pCode") String pCode);

}
