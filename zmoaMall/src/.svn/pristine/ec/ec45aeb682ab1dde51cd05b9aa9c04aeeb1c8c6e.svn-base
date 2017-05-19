package com.zm.mall.dao.system.impl;


import com.zm.mall.client.vo.system.MallConfigVo;
import com.zm.mall.dao.BaseDaoImpl;
import com.zm.mall.dao.system.MallConfigDao;
import com.zm.mall.domain.business.shop.PageableMallInfo;
import com.zm.mall.domain.system.MallConfig;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lp on 2017/2/22.
 */
public class MallConfigDaoImpl extends BaseDaoImpl<MallConfig> implements MallConfigDao {
    private final static String NAMESPACE = "com.zm.mall.dao.system.MallConfigDao.";
    @Override
    public String getNameSpace(String statement) {
        return NAMESPACE+statement;
    }

    @Override
    public Integer InsertMallConfig(MallConfig mallConfig) {
        return sqlTemplate.insert(getNameSpace("InsertMallConfig"), mallConfig);
    }

    @Override
    public MallConfig selSecretkeyByAppId(MallConfig mallConfig) {
        return sqlTemplate.selectOne(getNameSpace("selSecretkeyByAppId"), mallConfig);
    }

    @Override
    public List<MallConfigVo> getAllMallConfig(PageableMallInfo mallInfo) {
        return sqlTemplate.selectList(getNameSpace("getAllMallConfig"),mallInfo);
    }

    @Override
    public Long countMall() {
        return sqlTemplate.selectOne(getNameSpace("countMall"));
    }

    @Override
    public List<MallConfig> listMall(Long start, Long size) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("start", start);
        map.put("size", size);
        return sqlTemplate.selectList(getNameSpace("listMall"), map);
    }

    @Override
    public Long getCount(PageableMallInfo mallInfo){
        return  sqlTemplate.selectOne(getNameSpace("getCount"),mallInfo);
    }

    @Override
    public MallConfigVo getMallInfoById(Long id){
        return sqlTemplate.selectOne(getNameSpace("getMallInfoById"),id);
    }

    @Override
    public Integer updateStatus(MallConfigVo mall){
        return sqlTemplate.update(getNameSpace("updatasta"),mall);
    }

}
