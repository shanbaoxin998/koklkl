package com.atguigu.gmall.product.controller;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.product.*;
import com.atguigu.gmall.product.service.ManagerService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Zhangjun
 * @create 2020-03-13 15:53
 */
@RestController
@Api(tags = "商品信息")
@RequestMapping("admin/product")
@CrossOrigin
public class BaseManagerController {
    @Autowired
    private ManagerService managerService;

    /**
     *
     * @return
     */
    @GetMapping("getCategory1")
    public Result getCategory1() {
        List<BaseCategory1> baseCategory1List = managerService.getCategory1();
        return Result.ok(baseCategory1List);
    }

    /**
     *
     * @param category1Id
     * @return
     */
    @GetMapping("getCategory2/{category1Id}")
    public Result getCategory2(@PathVariable Long category1Id) {
        List<BaseCategory2> baseCategory2List = managerService.getCategory2(category1Id);
        return Result.ok(baseCategory2List);
    }

    /**
     *
     * @param category2Id
     * @return
     */
    @GetMapping("getCategory3/{category2Id}")
    public Result getCategory3(@PathVariable Long category2Id) {
        List<BaseCategory3> baseCategory3List = managerService.getCategory3(category2Id);
        return Result.ok(baseCategory3List);
    }

    /**
     *
     * @param category1Id
     * @param category2Id
     * @param category3Id
     * @return
     */
    @GetMapping("attrInfoList/{category1Id}/{category2Id}/{category3Id}")
    public Result attrInfoList(@PathVariable long category1Id,
                               @PathVariable long category2Id,
                               @PathVariable long category3Id) {
        List<BaseAttrInfo> baseAttrInfoList = managerService.getAttrInfoList(category1Id, category2Id, category3Id);
        return Result.ok(baseAttrInfoList);
    }

    @PostMapping("saveAttrInfo")
    public Result saveAttrInfo(@RequestBody BaseAttrInfo baseAttrInfo) {
        managerService.saveAttrInfo(baseAttrInfo);
        return Result.ok();

    }

    /**
     * 根据平台属性ID获取平台属性
     * @param attrId
     * @return
     */
    @GetMapping("getAttrValueList/{attrId}")
    public Result getAttrValueList(@PathVariable long attrId) {

        BaseAttrInfo baseAttrInfo = managerService.getAttrInfo(attrId);
        List<BaseAttrValue> attrValueList = baseAttrInfo.getAttrValueList();
        return Result.ok(attrValueList);
    }

    @GetMapping("{page}/{size}")
    public Result<IPage<SpuInfo>> index(@ApiParam(name="page",value = "当前页码",required = true) @PathVariable Long page,
                        @ApiParam(name="size",value = "每页显示的记录数",required = true) @PathVariable Long size,
                        @ApiParam(name="spuInfo",value = "查询对象",required = false)   SpuInfo spuInfo){
        Page<SpuInfo> spuParam = new Page<>(page,size);
        IPage<SpuInfo> spuInfoIPage = managerService.selectPage(spuParam, spuInfo);
        return Result.ok(spuInfoIPage);
    }

}
