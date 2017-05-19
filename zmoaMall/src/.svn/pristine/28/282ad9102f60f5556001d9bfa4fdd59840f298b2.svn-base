package com.zm.mall.web.system;

import com.zm.mall.client.result.system.MoneyManagerResult;
import com.zm.mall.client.service.business.orders.OrderInfoService;
import com.zm.mall.client.service.business.purchase.PurchaseService;
import com.zm.mall.client.service.system.*;
import com.zm.mall.client.vo.system.MoneyManagerVo;
import com.zm.mall.domain.business.purchase.PurchaseInfo;
import com.zm.mall.domain.system.AlwaysProfit;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/12/14.
 */
@Controller
@RequestMapping("/print")
public class PrintController {
    @Resource
    private DistributionService distributionService;
    @Resource
    private DistributionSonService distributionSonService;
    @Resource
    private OrderInfoService orderInfoService;
    @Resource
    private AlwaysProfitService alwaysProfitService;
    @Resource
    private AlwaysProfitSonService alwaysProfitSonService;
    @Resource
    private MoneyManagerService moneyManagerService;
    @Resource
    private PurchaseService purchaseService;


    @RequestMapping("/logistics.action")
    @ResponseBody
    public Object printLogistics (String distributionCodes){
        String[] ids=distributionCodes.split(",");
        if(ids!=null && ids.length>0){
            List<Map<String,Object>> list=new ArrayList<Map<String, Object>>();
            for(String s:ids){
                Map<String,Object> map=new HashMap<String, Object>();
                map.put("distribution",distributionService.selectOneDistribution(s));
                map.put("distributionSon",distributionSonService.selectAllDistributionSon(s));
                list.add(map);
            }
            return list;
        }
        return null;
    }

    @RequestMapping("/orders.action")
    @ResponseBody
    public Object printOrder(String ids){
        String[] id=ids.split(",");
        if(id!=null && id.length>0){
            List<Map<String,Object>> list=new ArrayList<Map<String, Object>>();
            for(String s:id){
                Map<String,Object> map=orderInfoService.selectForPrint(s);
                list.add(map);
            }
            return list;
        }
        return null;
    }

    @RequestMapping("/profit.action")
    @ResponseBody
    public Object printProfit(String ids){
        String[] id=ids.split(",");
        if(id!=null && id.length>0){
            List<Map<String,Object>> list=new ArrayList<Map<String, Object>>();
            for(String s:id){
                Map<String,Object> map=new HashMap<String, Object>();
                List<AlwaysProfit> alwaysProfitList=alwaysProfitService.selectAllAlwaysProfit(s);
                map.put("alwaysProfit",alwaysProfitList.get(0));
                map.put("list",alwaysProfitSonService.selectAlwaysProfitByAlwaysProfitCode(s));
                list.add(map);
            }
            return list;
        }
        return null;
    }

    @RequestMapping("/moneyManger.action")
    @ResponseBody
    public Object printMoneyManger(String ids){
        String[] id=ids.split(",");
        if(id!=null && id.length>0){
            List<List<MoneyManagerResult>> list=new ArrayList<List<MoneyManagerResult>>();
            for(String s:id){
                Long l=Long.valueOf(s);
                MoneyManagerVo moneyManagerVo = new MoneyManagerVo();
                moneyManagerVo.setId(l);
                List <MoneyManagerResult> moneyManagerResults=moneyManagerService.selectMoneyManagerById(moneyManagerVo);
                list.add(moneyManagerResults);
            }
            return list;
        }
        return null;
    }

    @RequestMapping("/purchase.action")
    @ResponseBody
    public Object printPurchase(String ids){
        String[] id=ids.split(",");
        if(id!=null && id.length>0){
            List<PurchaseInfo> list=new ArrayList<PurchaseInfo>();
            for(String s:id){
                s=s.trim();
                Long l=Long.valueOf(s);
                PurchaseInfo purchaseInfo =purchaseService.showOnePurchase(l);
                list.add(purchaseInfo);
            }
            return list;
        }
        return null;
    }
}
