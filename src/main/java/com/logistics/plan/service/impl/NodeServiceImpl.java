package com.logistics.plan.service.impl;

import com.logistics.plan.domain.entity.Node;
import com.logistics.plan.mapper.NodeMapper;
import com.logistics.plan.service.NodeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tianshihao
 * @since 2021-02-22
 */
@Service
public class NodeServiceImpl extends ServiceImpl<NodeMapper, Node> implements NodeService {

    @Autowired
    private NodeMapper nodeMapper;

    @Override
    public List<Map<String, String>> selectProvinceList() {
        return nodeMapper.selectProvinceList();
    }

    @Override
    public List<Map<String, String>> selectCityListByPCode(String pCode) {
        return nodeMapper.selectCityListByPCode(pCode);
    }
}
