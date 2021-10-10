package com.yh.cms.controller;

import com.lxw.api.cms.CmsConfigControllerApi;
import com.lxw.framework.domain.cms.CmsConfig;
import com.yh.cms.service.CmsConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CmsConfigController implements CmsConfigControllerApi {

    @Autowired
    private CmsConfigService cmsConfigService;

    @Override
    @GetMapping("/")
    public CmsConfig findCmsConfigById(String id) {
        return cmsConfigService.findCmsConfigById(id);
    }
}
