package com.zm.mall.service.system.impl;

import com.zm.mall.client.result.system.PrivilegeResult;
import com.zm.mall.client.service.system.PrivilegeService;
import com.zm.mall.client.vo.system.PrivilegeVo;
import com.zm.mall.dao.system.PrivilegeDao;
import com.zm.mall.domain.system.Privilege;
import com.zm.mall.service.SpaceBeanCopy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Administrator on 2016/11/16.
 */
@Service("privilegeService")
public class PrivilegeServiceImpl implements PrivilegeService {
    @Autowired
    private PrivilegeDao privilegeDao;
    @Override
    public List<PrivilegeResult> select(PrivilegeVo privilegeVo) {
        Privilege privilege= SpaceBeanCopy.privilegeVoToPrivilege(privilegeVo);
        List<Privilege> privileges=selectAll(privilege);
        if(privileges!=null && privileges.size()>0){
            List<PrivilegeResult> privilegeResults=new ArrayList<PrivilegeResult>();
            for(Privilege p:privileges){
                PrivilegeResult privilegeResult=SpaceBeanCopy.privilegeToPrivilegeResult(p);
                privilegeResults.add(privilegeResult);
            }
            return privilegeResults;
        }
        return null;
    }

    @Override
    public int add(PrivilegeVo privilegeVo) {
        Privilege privilege=SpaceBeanCopy.privilegeVoToPrivilege(privilegeVo);
        if(privilege.getPrivilege().getId()==0){
            privilege.getPrivilege().setId(null);
        }
        return privilegeDao.insert(privilege);
    }

    @Override
    public PrivilegeResult selectOne(PrivilegeVo privilegeVo) {
//        PrivilegeVo privilegeVo=new PrivilegeVo();
//        privilegeVo.setId(id);
        Privilege privilege=SpaceBeanCopy.privilegeVoToPrivilege(privilegeVo);
        Privilege p=privilegeDao.selectOne(privilege);
        return SpaceBeanCopy.privilegeToPrivilegeResult(p);
    }

    @Override
    public int update(PrivilegeVo privilegeVo) {
        Privilege privilege=SpaceBeanCopy.privilegeVoToPrivilege(privilegeVo);
        if(privilege.getPrivilege().getId()==0){
            privilege.getPrivilege().setId(null);
        }
        return  privilegeDao.update(privilege);
    }

    @Override
    public int delete(PrivilegeVo privilegeVo) {
//        PrivilegeVo privilegeVo=new PrivilegeVo();
//        privilegeVo.setId(id);
        Privilege privilege=SpaceBeanCopy.privilegeVoToPrivilege(privilegeVo);
        return privilegeDao.delete(privilege);
    }

    public List<Privilege> selectAll(Privilege Privilege) {
        List<Privilege> top=privilegeDao.selectTop(Privilege);
        return getAllPrivilegeList(top);
    }

    public List<Privilege> getAllPrivilegeList(Collection<Privilege> top) {
        List<Privilege> list = new ArrayList<Privilege>();
        createPrivilegeTree(top,"", list);
        return list;
    }
    private void createPrivilegeTree(Collection<Privilege> top,String string, List<Privilege> list) {
        for(Privilege p : top){
            Privilege depart = new Privilege();
            depart.setId(p.getId());
            depart.setName(string + p.getName());
            depart.setCion(p.getCion());
            depart.setPrivilege(p.getPrivilege());
            depart.setPluteformid(p.getPluteformid());
            list.add(depart);
            createPrivilegeTree(privilegeDao.selectNext(p), "&nbsp;&nbsp;" + string, list);
        }

    }
}
