package com.logistics.plan.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest
class NodeMapperTest {

    @Autowired
    private NodeMapper nodeMapper;

    @Test
    void selectProvinceList() {
        List<Map<String, String>> maps = nodeMapper.selectProvinceList();
        System.out.println(maps.toString());
    }

    @Test
    void selectCityListByPCode() {
    }
}
