package com.logistics.plan.factory;

import com.logistics.plan.domain.entity.Node;
import com.logistics.plan.service.NodeService;
import com.logistics.plan.utils.ComUtil;
import com.logistics.plan.utils.ImportUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class NodeImport implements Import {

    @Autowired
    private NodeService nodeService;

    @Override
    public void importCsv(List<String> list, Map<String, Integer> titleIndexMap) {
        //循环数据
        Class nodeClass = Node.class;
        List<Node> nodeList = new ArrayList<>();
        for (int i = 1; i < list.size(); i++) {
            Node node = new Node();
            ImportUtil.setValue(node, nodeClass, list.get(i), titleIndexMap);
            nodeList.add(node);
        }
        if(!ComUtil.isEmpty(nodeList)){
            nodeService.saveBatch(nodeList);
        }
    }
}
