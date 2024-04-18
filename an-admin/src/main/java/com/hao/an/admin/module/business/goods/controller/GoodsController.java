package com.hao.an.admin.module.business.goods.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.alibaba.excel.EasyExcel;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import com.hao.an.admin.constant.AdminSwaggerTagConst;
import com.hao.an.admin.module.business.goods.domain.form.GoodsAddForm;
import com.hao.an.admin.module.business.goods.domain.form.GoodsQueryForm;
import com.hao.an.admin.module.business.goods.domain.form.GoodsUpdateForm;
import com.hao.an.admin.module.business.goods.domain.vo.GoodsExcelVO;
import com.hao.an.admin.module.business.goods.domain.vo.GoodsVO;
import com.hao.an.admin.module.business.goods.service.GoodsService;
import com.hao.an.base.common.domain.PageResult;
import com.hao.an.base.common.domain.ResponseDTO;
import com.hao.an.base.common.domain.ValidateList;
import com.hao.an.base.common.util.SmartResponseUtil;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

/**
 * 商品业务
 *
 * @Author 1024创新实验室: 胡克
 * @Date 2021-10-25 20:26:54
 * @Wechat zhuoda1024
 * @Email lab1024@163.com
 * @Copyright <a href="https://1024lab.net">1024创新实验室</a>
 */
@RestController
@Tag(name = AdminSwaggerTagConst.Business.MANAGER_GOODS)
public class GoodsController {

    @Resource
    private GoodsService goodsService;

    @Operation(summary = "分页查询 @author 胡克")
    @PostMapping("/goods/query")
    @SaCheckPermission("goods:query")
    public ResponseDTO<PageResult<GoodsVO>> query(@RequestBody @Valid GoodsQueryForm queryForm) {
        return goodsService.query(queryForm);
    }

    @Operation(summary = "添加商品 @author 胡克")
    @PostMapping("/goods/add")
    @SaCheckPermission("goods:add")
    public ResponseDTO<String> add(@RequestBody @Valid GoodsAddForm addForm) {
        return goodsService.add(addForm);
    }

    @Operation(summary = "更新商品 @author 胡克")
    @PostMapping("/goods/update")
    @SaCheckPermission("goods:update")
    public ResponseDTO<String> update(@RequestBody @Valid GoodsUpdateForm updateForm) {
        return goodsService.update(updateForm);
    }

    @Operation(summary = "删除 @author 卓大")
    @GetMapping("/goods/delete/{goodsId}")
    @SaCheckPermission("goods:delete")
    public ResponseDTO<String> delete(@PathVariable Long goodsId) {
        return goodsService.delete(goodsId);
    }

    @Operation(summary = "批量 @author 卓大")
    @PostMapping("/goods/batchDelete")
    @SaCheckPermission("goods:batchDelete")
    public ResponseDTO<String> batchDelete(@RequestBody @Valid ValidateList<Long> idList) {
        return goodsService.batchDelete(idList);
    }

    // --------------- 导出和导入 -------------------

    @Operation(summary = "导入 @author 卓大")
    @PostMapping("/goods/importGoods")
    @SaCheckPermission("goods:importGoods")
    public ResponseDTO<String> importGoods(@RequestParam MultipartFile file) {
        return goodsService.importGoods(file);
    }

    @Operation(summary = "导出 @author 卓大")
    @GetMapping("/goods/exportGoods")
    @SaCheckPermission("goods:exportGoods")
    public void exportGoods(HttpServletResponse response) throws IOException {

        List<GoodsExcelVO> goodsList = goodsService.getAllGoods();

        // 设置下载消息头
        SmartResponseUtil.setDownloadFileHeader(response, "商品列表.xls", null);

        // 下载
        EasyExcel.write(response.getOutputStream(), GoodsExcelVO.class)
                .autoCloseStream(Boolean.FALSE)
                .sheet("商品")
                .doWrite(goodsList);
    }

}
