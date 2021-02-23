package com.logistics.plan.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.logistics.plan.domain.entity.Mail;
import com.logistics.plan.service.MailService;
import com.logistics.plan.utils.PageUtils;
import com.logistics.plan.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author tianshihao
 * @since 2021-02-22
 */
@RestController
@RequestMapping("/mail")
@Api(tags = "MailController", description = "邮件量控制器")
public class MailController {

    @Autowired
    private MailService mailService;

    @ApiOperation("新增")
    @PostMapping("/save")
    public R save(@RequestBody Mail mail){
        mailService.save(mail);
        return R.ok();
    }

    /**
     * 列表
     */
    @ApiOperation("查询列表")
    @GetMapping("/list")
    public R list(Page page){
        IPage iPage = mailService.page(page);
        return R.ok(iPage);
    }

    /**
     * 修改
     */
    @ApiOperation("修改")
    @PutMapping("/update")
    public R update(@RequestBody Mail mail){
        mailService.updateById(mail);
        return R.ok();
    }

    /**
     * 删除
     */
    @ApiOperation("删除")
    @DeleteMapping("/delete/{mailId}")
    public R delete(@PathVariable Long mailId){
        mailService.removeById(mailId);
        return R.ok();
    }

}
