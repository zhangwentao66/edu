package com.yh.cms.service;

import com.lxw.framework.domain.cms.CmsSite;
import com.lxw.framework.domain.cms.response.CmsCode;
import com.lxw.framework.domain.cms.response.CmsPageResult;
import com.lxw.framework.exception.CustomException;
import com.lxw.framework.exception.ExceptionCast;
import com.lxw.framework.model.response.ResponseResult;
import com.yh.cms.dao.CmsPageRepository;
import com.lxw.framework.domain.cms.CmsPage;
import com.lxw.framework.domain.cms.request.QueryPageRequest;
import com.lxw.framework.model.response.CommonCode;
import com.lxw.framework.model.response.QueryResponseResult;
import com.lxw.framework.model.response.QueryResult;
import com.yh.cms.dao.CmsSiteRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CmsPageService {

    @Autowired
    private CmsPageRepository cmsPageRepository;

    @Autowired
    private CmsSiteRepository cmsSiteRepository;


    public QueryResponseResult findCmsPageList(Integer pageNum, Integer pageSize, QueryPageRequest queryPageRequest) {
//         这里是因为mongodb分页查询时是从0页开始查询的，查询结构会和数据库的数据不一致
        if (pageNum <= 0) {
            pageNum = 1;
        }
        pageNum = pageNum - 1;

        //设置分页条件
        PageRequest pageRequest = PageRequest.of(pageNum, pageSize);

        // 这里的cmsPage用来存储参数信息
        CmsPage cmsPage = new CmsPage();
//        BeanUtils.copyProperties(queryPageRequest,cmsPage);

        if (StringUtils.isNotEmpty(queryPageRequest.getSiteId())) {
            cmsPage.setSiteId(queryPageRequest.getSiteId());
        }

        if (StringUtils.isNotEmpty(queryPageRequest.getTemplateId())) {
            cmsPage.setTemplateId(queryPageRequest.getTemplateId());
        }

        //创建匹配器，提供匹配规则
        ExampleMatcher matching = ExampleMatcher.matching();

        if (StringUtils.isNotEmpty(queryPageRequest.getPageAliase())) {
            cmsPage.setPageAliase(queryPageRequest.getPageAliase());
            matching = matching.withMatcher("pageAliase", ExampleMatcher.GenericPropertyMatchers.contains());
        }
        //创建Example对象
        Example<CmsPage> example = Example.of(cmsPage, matching);

        //分页查询列表
        //List<CmsPage> list = cmsPageRepository.findAll();
        Page<CmsPage> pages = cmsPageRepository.findAll(example, pageRequest);

        //创建查询结果对象
        QueryResult<CmsPage> result = new QueryResult<>();
//        result.setList(list);
        result.setTotal(pages.getTotalElements());
        result.setList(pages.getContent());
        //作为参数传入响应对象
        QueryResponseResult queryResponseResult = new QueryResponseResult(CommonCode.SUCCESS, result);
        //
        return queryResponseResult;
    }

    /**
     * 查询站点集合
     *
     * @return
     */
    public List<CmsSite> findCmsSiteList() {
        List<CmsSite> list = cmsSiteRepository.findAll();
        return list;
    }

    /**
     * 添加页面
     *
     * @param cmsPage
     * @return
     */
    public CmsPageResult addCmsPage(CmsPage cmsPage) {
        //必要参数校验
        if (cmsPage == null) {
            return new CmsPageResult(CommonCode.FAIL, null);
        }
        //校验要添加的页面是否已经存在
        CmsPage page = cmsPageRepository.findBySiteIdAndPageNameAndPageWebPath
                (cmsPage.getSiteId(), cmsPage.getPageName(), cmsPage.getPageWebPath());
        //不存在则添加
        if (page == null) {
            cmsPageRepository.insert(cmsPage);
            return new CmsPageResult(CommonCode.SUCCESS, cmsPage);
        }
        return new CmsPageResult(CmsCode.CMS_ADDPAGE_EXISTSNAME, null);
    }

    /**
     * 根据pageId查询cmsPage信息
     *
     * @param pageId
     * @return
     */
    public CmsPageResult findCmsPageById(String pageId) {
        System.out.println(1/0);
        //必要参数校验
        if (StringUtils.isEmpty(pageId)) {
//            return new CmsPageResult(CommonCode.FAIL, null);
            throw new CustomException(CommonCode.FEIFACANSHU);
        }
        //根据主键id pageId查询cmsPage  这里拿到的是容器
//        Optional<CmsPage> cmsPage = cmsPageRepository.findById(pageId);
        //                                      这里意思是，如果查到为空，就返回空，不为空获取容器的内容
        CmsPage cmsPage = cmsPageRepository.findById(pageId).orElse(null);
        if (cmsPage == null) {
//            throw new CustomException(CmsCode.CMS_PAGE_EXISTSID);
            ExceptionCast.cast(CmsCode.CMS_PAGE_EXISTSID);
        }
        return new CmsPageResult(CommonCode.SUCCESS, cmsPage);
    }

    /**
     * cmsPage修改方法
     *
     * @param pageId
     * @param cmsPage
     * @return
     */
    public CmsPageResult updateCmsPage(String pageId, CmsPage cmsPage) {
        //必要参数校验
        if (StringUtils.isEmpty(pageId)) {
            return new CmsPageResult(CommonCode.FAIL, null);
        }
        if (cmsPage == null) {
            return new CmsPageResult(CommonCode.FAIL, null);
        }
        //修改 这里save方法是 如果存在则修改，不存在就添加
        cmsPageRepository.save(cmsPage);
        return new CmsPageResult(CommonCode.SUCCESS, cmsPage);
    }

    /**
     * cmsPage删除方法
     *
     * @param pageId
     * @return
     */
    public ResponseResult delCmsPage(String pageId) {
        Integer integer = cmsPageRepository.deleteByPageId(pageId);
        if (integer > 0) {
            return new ResponseResult(CommonCode.SUCCESS);
        }
        return new ResponseResult(CommonCode.FAIL);
    }
}
