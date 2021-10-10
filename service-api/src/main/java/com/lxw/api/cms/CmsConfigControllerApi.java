package com.lxw.api.cms;

import com.lxw.framework.domain.cms.CmsConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "cmsConfig管理接口，提供页面的增、删、改、查等功能")
public interface CmsConfigControllerApi {

    @ApiOperation("根据pageId查询cmsConfig信息")
    @ApiParam(name = "id",value = "pageConfig主键Id",type = "String")
    public CmsConfig findCmsConfigById(String id);
}
