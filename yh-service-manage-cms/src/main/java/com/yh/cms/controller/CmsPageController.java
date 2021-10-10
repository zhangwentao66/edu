package com.yh.cms.controller;

import com.lxw.api.cms.CmsPageControllerApi;
import com.lxw.framework.domain.cms.CmsPage;
import com.lxw.framework.domain.cms.CmsSite;
import com.lxw.framework.domain.cms.response.CmsPageResult;
import com.lxw.framework.model.response.ResponseResult;
import com.yh.cms.service.CmsPageService;
import com.lxw.framework.domain.cms.request.QueryPageRequest;
import com.lxw.framework.model.response.QueryResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 */
@RestController
@RequestMapping("/cms/page")
public class CmsPageController implements CmsPageControllerApi {

    @Autowired
    CmsPageService cmsPageService;

    /**
     * 根据条件分页查询CmsPage集合下的数据
     *
     * @param pageNum          页码
     * @param pageSize         条数
     * @param queryPageRequest 请求参数
     * @return
     */
    @Override
    @GetMapping("/list/{pageNum}/{pageSize}")
    public QueryResponseResult findCmsPageList(
            @PathVariable Integer pageNum,
            @PathVariable Integer pageSize, QueryPageRequest queryPageRequest){
        return cmsPageService.findCmsPageList(pageNum,pageSize,queryPageRequest);
    }

    /**
     * 查询站点页面列表
     */
    @Override
    @GetMapping("/site/list")
    public List<CmsSite> findCmsSiteList() {
        return cmsPageService.findCmsSiteList();
    }

    /**
     * 添加cmsPage页面
     * @param cmsPage
     * @return
     */
    @Override
    @PostMapping("/add")
    public CmsPageResult addCmsPage(@RequestBody CmsPage cmsPage) {
        return cmsPageService.addCmsPage(cmsPage);
    }

    /**
     * 根据pageId查询cmsPage信息
     * @param pageId
     * @return
     */
    @Override
    @GetMapping("/get/{id}")
    public CmsPageResult findCmsPageById(@PathVariable("id") String pageId) {
        return cmsPageService.findCmsPageById(pageId);
    }

    /**
     * cmsPage修改方法
     * @param pageId
     * @param cmsPage
     * @return
     */
    @Override
    @PutMapping("/edit/{id}") //跟新请求使用putMapping
    public CmsPageResult updateCmsPage(@PathVariable("id")String pageId, @RequestBody CmsPage cmsPage) {
        return cmsPageService.updateCmsPage(pageId,cmsPage);
    }

    /**
     * cmsPage删除方法
     * @param pageId
     * @return
     */
    @Override
    @DeleteMapping("/del/{id}")
    public ResponseResult delCmsPage(@PathVariable("id")String pageId) {
        return cmsPageService.delCmsPage(pageId);
    }

}
