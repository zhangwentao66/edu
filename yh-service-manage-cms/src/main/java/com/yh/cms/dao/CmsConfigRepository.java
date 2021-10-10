package com.yh.cms.dao;

import com.lxw.framework.domain.cms.CmsConfig;
import com.lxw.framework.domain.cms.CmsPage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * 泛型参数MongoRepository<CmsPage,String>
 *     CmsPage与mongodb中的集合（表）一一对应
 *     String 主键类型
 */
@Repository
public interface CmsConfigRepository extends MongoRepository<CmsConfig,String> {

}
