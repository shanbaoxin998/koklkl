package com.atguigu.gmall.product.controller;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.product.BaseTrademark;
import com.atguigu.gmall.product.service.BaseTrademarkService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Zhangjun
 * @create 2020-03-14 15:10
 */
@Api(description = "品牌管理")
@RestController
@RequestMapping("/admin/product/baseTrademark")
@CrossOrigin
public class BaseTrademarkController {
    @Autowired
    BaseTrademarkService baseTrademarkService;

    @ApiOperation(value = "分页列表")
    @GetMapping("{page}/{size}")
    public Result index (@ApiParam(name = "page",value = "当前页码",required = true)  @PathVariable Long page,
                         @ApiParam(name = "size",value = "每页条数",required = true)  @PathVariable Long size) {
        Page<BaseTrademark> objectPage = new Page<>(page, size);
        IPage<BaseTrademark> pageModel = baseTrademarkService.selectPage(objectPage);
        return Result.ok(pageModel);
    }

    @ApiOperation(value = "添加商标")
    @PostMapping("save")
    public Result save (@RequestBody BaseTrademark baseTrademark) {
        baseTrademarkService.save(baseTrademark);
        return Result.ok();
    }
    //http://api.gmall.com/admin/product/baseTrademark/get/1

    @ApiOperation(value = "根据id获取商标信息")
    @GetMapping("get/{id}")
    public Result get(@PathVariable Long id) {
        BaseTrademark baseTrademark = baseTrademarkService.getById(id);
        return  Result.ok(baseTrademark);
    }

    //http://api.gmall.com/admin/product/baseTrademark/remove/1
    @ApiOperation(value = "删除商标")
    @DeleteMapping("remove/{id}")
    public Result delete(@PathVariable Long id) {
        baseTrademarkService.removeById(id);
        return Result.ok();
    }

    //http://api.gmall.com/admin/product/baseTrademark/update
    @ApiOperation(value = "修改商标")
    @PutMapping("update")
    public Result update(@RequestBody BaseTrademark baseTrademark) {
        baseTrademarkService.updateById(baseTrademark);
        return Result.ok();
    }
}
