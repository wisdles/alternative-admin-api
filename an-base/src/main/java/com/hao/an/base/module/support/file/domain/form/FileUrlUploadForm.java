package com.hao.an.base.module.support.file.domain.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.hao.an.base.common.swagger.SchemaEnum;
import com.hao.an.base.common.validator.enumeration.CheckEnum;
import com.hao.an.base.module.support.file.constant.FileFolderTypeEnum;

import javax.validation.constraints.NotBlank;

/**
 * url上传文件
 *
 * @Author hao
 * @Date 2019年10月11日 15:34:47
 * @Wechat zhuoda1024
 * @Email lab1024@163.com
 * @Copyright  <a href="https://1024lab.net">1024创新实验室</a>
 */
@Data
public class FileUrlUploadForm {

    @SchemaEnum(value = FileFolderTypeEnum.class, desc = "业务类型")
    @CheckEnum(value = FileFolderTypeEnum.class, required = true, message = "业务类型错误")
    private Integer folder;

    @Schema(description = "文件url")
    @NotBlank(message = "文件url不能为空")
    private String fileUrl;

}
