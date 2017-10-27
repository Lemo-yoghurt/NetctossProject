package com.lanou.service.impl;

import com.lanou.bean.Module;
import com.lanou.mapper.ModuleMapper;
import com.lanou.service.ModuleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by dllo on 17/10/27.
 */
@Service
public class ModuleServiceImpl implements ModuleService {

    @Resource
    private ModuleMapper moduleMapper;

    public List<Module> findAllModules() {
        return moduleMapper.findAllModules();
    }
}
