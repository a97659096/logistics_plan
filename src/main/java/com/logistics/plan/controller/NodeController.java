package com.logistics.plan.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.logistics.plan.domain.entity.Mail;
import com.logistics.plan.domain.entity.Node;
import com.logistics.plan.service.MailService;
import com.logistics.plan.service.NodeService;
import com.logistics.plan.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author tianshihao
 * @since 2021-02-22
 */
@RestController
@RequestMapping("/node")
@Api(tags = "NodeController", description = "节点控制器")
public class NodeController {

    @Autowired
    private NodeService nodeService;

    @ApiOperation("新增")
    @PostMapping("/save")
    public R save(@RequestBody Node node){
        nodeService.save(node);
        return R.ok();
    }

    /**
     * 列表
     */
    @ApiOperation("查询列表")
    @GetMapping("/list")
    public R list(Page page){
        IPage iPage = nodeService.page(page);
        return R.ok(iPage);
    }

    /**
     * 修改
     */
    @ApiOperation("修改")
    @PutMapping("/update")
    public R update(@RequestBody Node node){
        nodeService.updateById(node);
        return R.ok();
    }

    /**
     * 删除
     */
    @ApiOperation("删除")
    @DeleteMapping("/delete/{nodeId}")
    public R delete(@PathVariable Long nodeId){
        nodeService.removeById(nodeId);
        return R.ok();
    }

    @ApiOperation("获取省份下拉列表")
    @GetMapping("province-list")
    public R provinceList(){
        List<Map<String, String>> maps = nodeService.selectProvinceList();
        return R.ok(maps);
    }

    @ApiOperation("根据省份code获取城市下拉列表")
    @GetMapping("city-list")
    public R cityList(@RequestParam String pCode){
        List<Map<String, String>> maps = nodeService.selectCityListByPCode(pCode);
        return R.ok(maps);
    }
}
