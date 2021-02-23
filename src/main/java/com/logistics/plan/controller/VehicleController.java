package com.logistics.plan.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.logistics.plan.domain.entity.Node;
import com.logistics.plan.domain.entity.Vehicle;
import com.logistics.plan.service.VehicleService;
import com.logistics.plan.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author tianshihao
 * @since 2021-02-22
 */
@RestController
@RequestMapping("/vehicle")
@Api(tags = "VehicleController", description = "车辆控制器")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @ApiOperation("新增")
    @PostMapping("/save")
    public R save(@RequestBody Vehicle vehicle){
        vehicleService.save(vehicle);
        return R.ok();
    }

    /**
     * 列表
     */
    @ApiOperation("查询列表")
    @GetMapping("/list")
    public R list(Page page){
        IPage iPage = vehicleService.page(page);
        return R.ok(iPage);
    }

    /**
     * 修改
     */
    @ApiOperation("修改")
    @PutMapping("/update")
    public R update(@RequestBody Vehicle vehicle){
        vehicleService.updateById(vehicle);
        return R.ok();
    }

    /**
     * 删除
     */
    @ApiOperation("删除")
    @DeleteMapping("/delete/{vehicleId}")
    public R delete(@PathVariable Long vehicleId){
        vehicleService.removeById(vehicleId);
        return R.ok();
    }

}
