package com.zm.mall.client.service.system;


import com.zm.mall.client.PageOfRegister;
import com.zm.mall.client.result.system.MoneyItemResult;
import com.zm.mall.client.result.system.MoneyManagerResult;
import com.zm.mall.client.vo.system.MoneyManagerVo;
import com.zm.mall.domain.system.Register;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.Date;
import java.util.List;

public interface RegisterService {
    void insertRegister(Register register);
    void updateRegister(Register register);
    //查询单个打卡记录
    Register selectRegister(Register register);
    //查询本人本月记录
    List<Register> selectRegisterAll(Register register);
    //查询所有人本月记录
    List<Register> selectAllRegister(Register register);
    //分页查询
    PageOfRegister selectAllRegisterByPage(PageOfRegister pageOfRegister);
}