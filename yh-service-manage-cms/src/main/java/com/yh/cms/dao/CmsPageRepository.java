package com.yh.cms.dao;

import com.lxw.framework.domain.cms.CmsPage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * 泛型参数MongoRepository<CmsPage,String>
 *     CmsPage与mongodb中的集合（表）一一对应
 *     String 主键类型
 */
@Repository
public interface CmsPageRepository extends MongoRepository<CmsPage,String> {
    /**
     * MongoRepository<CmsPage,String>  测试方法，mongodb只要符合命名规则，会自动生成sql语句
     *     CmsPage:要查询的实体类
     *     String： 主键的类型
     */
    public CmsPage findByPageName(String pageName);

    /**
     * 根据站点id pageName webPath地址查询指定的页面
     * @param siteId
     * @param pageName
     * @param pageWebPath
     * @return
     */
    public CmsPage findBySiteIdAndPageNameAndPageWebPath(String siteId,String pageName,String pageWebPath);

    /**
     * 扩展一个删除的方法（原方法删除没有返回值）
     * @param pageId
     * @return
     */
    public Integer deleteByPageId(String pageId);
}
