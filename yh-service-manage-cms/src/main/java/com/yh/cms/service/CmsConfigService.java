package com.yh.cms.service;

import com.lxw.framework.domain.cms.CmsConfig;
import com.yh.cms.dao.CmsConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CmsConfigService {

    @Autowired
    CmsConfigRepository cmsConfigRepository;

    public CmsConfig findCmsConfigById(String id) {
        cmsConfigRepository.findById(id)
        return ;
    }
}
