package com.example.demo.service.impl;

import com.evan.mangocore.page.MybatisPageHelper;
import com.evan.mangocore.page.PageRequest;
import com.evan.mangocore.page.PageResult;
import com.example.demo.model.Dict;
import com.example.demo.dao.DictMapper;
import com.example.demo.service.DictService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 数典表 服务实现类
 * </p>
 *
 * @author evan
 * @since 2026-05-16
 */
@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements DictService {

    @Autowired
    private DictMapper dictMapper;
    @Override
    public int saveDict(Dict record) {
        if(record.getId()==null||record.getId()==0){
            return dictMapper.insert(record);
        }
        return dictMapper.updateById(record);
    }
    @Override
    public int delete(Dict record) {
        return dictMapper.deleteById(record.getId());
    }
    @Override
    public int delete(List<Dict> records){
        for(Dict record:records){
            delete(record);
        }
        return 1;
    }
    @Override
    public Dict findById(Long id) {
        return dictMapper.selectById(id);
    }
    @Override
    public PageResult findPage(PageRequest pageRequest){
        Object label = pageRequest.getParam("label");
        if(label==null){
            return MybatisPageHelper.findPage(pageRequest,dictMapper);
        }
        return MybatisPageHelper.findPage(pageRequest,dictMapper,"findByLabel",label);
    }
    @Override
    public List<Dict> findByLabel(String label) {
        return dictMapper.findByLabel(label);
    }
}
