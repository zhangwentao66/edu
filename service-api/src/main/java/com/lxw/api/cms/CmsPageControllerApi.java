package com.lxw.api.cms;

import com.lxw.framework.domain.cms.CmsPage;
import com.lxw.framework.domain.cms.CmsSite;
import com.lxw.framework.domain.cms.request.QueryPageRequest;
import com.lxw.framework.domain.cms.response.CmsPageResult;
import com.lxw.framework.model.response.QueryResponseResult;
import com.lxw.framework.model.response.ResponseResult;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


@Api(tags = "cms页面管理接口，提供页面的增、删、改、查,页面预览，发布等功能")
public interface CmsPageControllerApi {

    /**
     * 根据条件分页查询CmsPage集合下的数据
     *
     * @param pageNum          页码
     * @param pageSize         条数
     * @param queryPageRequest 请求参数
     * @return
     */
    @ApiOperation("分页查询页面列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码", dataType = "Integer", paramType = "path", required = true),
            @ApiImplicitParam(name = "pageSize", value = "每页条数", dataType = "int", paramType = "path", required = true),
            @ApiImplicitParam(name = "queryPageRequest", value = "参数", dataType = "QueryPageRequest", required = false)
    })
    public QueryResponseResult findCmsPageList
    (Integer pageNum, Integer pageSize, QueryPageRequest queryPageRequest);

    /**
     * 查询站点页面列表
     */
    @ApiOperation("查询站点页面列表")
    public List<CmsSite> findCmsSiteList();

    /**
     * 添加cms页面
     * @param cmsPage
     * @return
     */
    @ApiOperation("添加cmsPage信息")
    @ApiParam(name = "cmsPage",value = "cmsPage信息",type = "CmsPage")
    public CmsPageResult addCmsPage(CmsPage cmsPage);

    @ApiOperation("根据pageId查询cmsPage信息")
    @ApiParam(name = "pageId",value = "pageId主键Id",type = "String")
    public CmsPageResult findCmsPageById(String pageId);

    @ApiOperation("修改cmsPage信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageId", value = "pageId主键", dataType = "String", paramType = "path", required = true),
            @ApiImplicitParam(name = "cmsPage", value = "cmsPage信息", dataType = "CmsPage", paramType = "body", required = true),
    })
    public CmsPageResult updateCmsPage(String pageId, CmsPage cmsPage);

    @ApiOperation("根据pageId删除cmsPage信息")
    @ApiParam(name = "pageId",value = "pageId主键Id",type = "String")
    public ResponseResult delCmsPage(String pageId);
}
