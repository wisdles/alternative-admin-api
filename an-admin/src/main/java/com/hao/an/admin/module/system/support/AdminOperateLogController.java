package com.hao.an.admin.module.system.support;

import cn.dev33.satoken.annotation.SaCheckPermission;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import com.hao.an.base.common.controller.SupportBaseController;
import com.hao.an.base.common.domain.PageResult;
import com.hao.an.base.common.domain.ResponseDTO;
import com.hao.an.base.constant.SwaggerTagConst;
import com.hao.an.base.module.support.operatelog.OperateLogService;
import com.hao.an.base.module.support.operatelog.domain.OperateLogQueryForm;
import com.hao.an.base.module.support.operatelog.domain.OperateLogVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 *  操作日志
 *
 * @Author 1024创新实验室: 罗伊
 * @Date 2021-12-08 20:48:52
 * @Wechat zhuoda1024
 * @Email lab1024@163.com
 * @Copyright  <a href="https://1024lab.net">1024创新实验室</a>
 */
@RestController
@Tag(name = SwaggerTagConst.Support.OPERATE_LOG)
public class AdminOperateLogController extends SupportBaseController {

    @Resource
    private OperateLogService operateLogService;

    @Operation(summary = "分页查询 @author 罗伊")
    @PostMapping("/operateLog/page/query")
    @SaCheckPermission("support:operateLog:query")
    public ResponseDTO<PageResult<OperateLogVO>> queryByPage(@RequestBody OperateLogQueryForm queryForm) {
        return operateLogService.queryByPage(queryForm);
    }

    @Operation(summary = "详情 @author 罗伊")
    @GetMapping("/operateLog/detail/{operateLogId}")
    @SaCheckPermission("support:operateLog:detail")
    public ResponseDTO<OperateLogVO> detail(@PathVariable Long operateLogId) {
        return operateLogService.detail(operateLogId);
    }

}
