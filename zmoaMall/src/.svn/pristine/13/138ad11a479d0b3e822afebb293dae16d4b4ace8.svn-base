package com.zm.mall.web.system;


import com.zm.mall.client.Page;
import com.zm.mall.client.service.business.orders.OrderInfoService;
import com.zm.mall.client.service.business.purchase.PurchaseService;
import com.zm.mall.client.service.system.AlwaysProfitService;
import com.zm.mall.client.service.system.AlwaysProfitSonService;
import com.zm.mall.client.service.system.DistributionService;
import com.zm.mall.client.service.system.DistributionSonService;
import com.zm.mall.domain.business.purchase.PurchaseInfo;
import com.zm.mall.domain.business.purchase.PurchaseItems;
import com.zm.mall.domain.system.AlwaysProfit;
import com.zm.mall.domain.system.AlwaysProfitSon;
import com.zm.mall.domain.system.Distribution;
import com.zm.mall.domain.system.DistributionSon;
import com.zm.mall.web.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * Created by liyanshuai on 2016/11/8.
 */
@Controller
@RequestMapping("/business")
public class alwaysProfitController extends BaseController{

    @Resource
    DistributionService distributionService;

    @Resource
    DistributionSonService distributionSonService;

    @Resource
    PurchaseService purchaseService;
    @Resource
    OrderInfoService orderInfoService;
    @Resource
    AlwaysProfitService alwaysProfitService;
    @Resource
    AlwaysProfitSonService alwaysProfitSonService;

    /**
     * toAlwaysProfit  跳转页面的方法  这里需要查询数据（订单，采购订单，车辆） 还需要保存数据便于页面展示
     * @return
     * @throws Exception
     */
   /* @RequestMapping(value = "/toAlwaysProfit.action")
    public ModelAndView toAlwaysProfit() throws Exception {

        try {
            Map map = new HashMap();
            List<Distribution> Dlist= distributionService.selectFinishTask();
            for (int i=0;i<Dlist.size();i++){
                //一辆车的总数量 和价格
                Integer alwaysOrderNumber=0;
                Integer alwaysRealNumber=0;
                Double alwaysOrderMoney=0.00;
                Double alwaysRealMoney=0.00;
                Integer money =new Integer(Dlist.get(i).getMoney());//物流费用

                String distributionCode=Dlist.get(i).getDistributionCode();
                List<DistributionSon> DSList= distributionSonService.selectAllDistributionSon(distributionCode);
                for (int j=0;j<DSList.size();j++){
                    String orderCode = DSList.get(j).getOrderCode();
                    //查询出单个订单的总数据
                    PurchaseItems purchaseItems =purchaseService.selectFinishPurchaseItems(orderCode);
                    alwaysOrderNumber +=purchaseItems.getOrderNumber();
                    alwaysRealNumber +=purchaseItems.getRealNumber();
                    alwaysOrderMoney +=purchaseItems.getOrderMoney();
                    alwaysRealMoney +=purchaseItems.getRealMoney();
                }



                Dlist.get(i).setAllOrderNumber(alwaysOrderNumber.toString());
                Dlist.get(i).setAllRealNumber(alwaysRealNumber.toString());
                Dlist.get(i).setAllOrderMoney(alwaysOrderMoney);
                Dlist.get(i).setAllRealMoney(alwaysRealMoney);
                Dlist.get(i).setProfit(alwaysOrderMoney -(alwaysRealMoney+money));


            }

            map.put("Dlist",Dlist);
            return new ModelAndView("/system/alwaysProfit",map);
        } catch (Exception e) {
            throw e;
        }
    }*/

    @RequestMapping(value = "/toAlwaysProfit.action")
    @Transactional
    public ModelAndView toAlwaysProfit() throws Exception {

        try {
          /*  Map map = new HashMap();*/
           /* List<PurchaseInfo> list= purchaseService.selectAllFinishState();
            for (int i=0;i<list.size();i++){
                //采购单编号
                String purchaseCode =list.get(i).getPurchaseCode();
                //根据采购单的编号，查询总利润，如果查询结果为空：添加，如果查询结果不为空：修改
                List<AlwaysProfit> apList =alwaysProfitService.selectOneProfit(purchaseCode);
                if(apList.size()>0){

                        //采购单的信息 产品数量、重量、花销
                        Double purchasenumber= 0.00;
                        Double purchaseweight= 0.00;

                        //订单总数量
                        Double ordernumber=0.00;
                        Double orderweigth=0.00;
                        //采购总价
                        //Double purchasemoney= list.get(i).getTotalmoney();
                        Double purchasemoney=0.00;
                        //物流配送费用
                        Double distributionmoney=0.00;
                        Double distributionRealMoney=0.00;
                        //销售总价
                        Double marketmoney=0.00;
                        //总破损费用
                        Double alwaysWornMoney=0.00;
                        //编码
                        String alwaysProfitCode=apList.get(0).getAlwaysProfitCode();
                        //查询物流信息 完成的物流
                        List<Distribution> DList=distributionService.selectDistributionByPurchaseCode(purchaseCode);
                        for (int j=0;j<DList.size();j++){
                            distributionmoney+=new Integer(DList.get(j).getMoney());
                            if(DList.get(i).getRealDistributionMoney()!=null){
                                distributionRealMoney+= new Integer(DList.get(i).getRealDistributionMoney());
                            }
                            List<DistributionSon> DSList=distributionSonService.selectAllDistributionSon(DList.get(j).getDistributionCode());
                            for (int l=0;l<DSList.size();l++){
                                alwaysWornMoney+=DSList.get(l).getWornMoney();
                            }

                        }

                        //查询订单信息 完成的订单
                        List<PurchaseItems> PList = purchaseService.selectPurchaseItemsByPurchaseCode(purchaseCode);
                        //添加采购单中的  架子费
                        PurchaseInfo purchaseInfo =purchaseService.selectOnePurchaseInfo(purchaseCode);
                        purchasemoney=Double.parseDouble(purchaseInfo.getRackMoney().toString());
                        for(int k=0;k<PList.size();k++){
                            //订单中数量、重量和采购总数量、重量
                            purchasenumber +=PList.get(k).getRealNumber();
                            purchaseweight +=PList.get(k).getRealWeigth();
                            ordernumber += PList.get(k).getOrderNumber();
                            orderweigth += PList.get(k).getOrderWeigth();
                            //根据订单获取采购单的总销售价和采购总价
                            marketmoney+=PList.get(k).getOrderMoney();
                            purchasemoney+=PList.get(k).getRealMoney();

                            //创建子对象
                            AlwaysProfitSon alwaysProfitSon=  new AlwaysProfitSon();

                            alwaysProfitSon.setAlwaysProfitSonCode(alwaysProfitCode+k);
                            alwaysProfitSon.setAlwaysProfitCode(alwaysProfitCode);

                            alwaysProfitSon.setPurchaseCode(purchaseCode);
                            alwaysProfitSon.setOrderCode(PList.get(k).getOrderCode());

                            alwaysProfitSon.setOrderNumber(PList.get(k).getOrderNumber().toString());
                            alwaysProfitSon.setOrderWeight(PList.get(k).getOrderWeigth().toString());

                            alwaysProfitSon.setPurchaseNumber(PList.get(k).getRealNumber().toString());
                            alwaysProfitSon.setPurchaseWeight(PList.get(k).getRealWeigth().toString());

                            alwaysProfitSon.setOrderPrice(PList.get(k).getOrderMoney());
                            alwaysProfitSon.setPurchasePrice(PList.get(k).getRealMoney());
                            alwaysProfitSon.setGrossProfit(PList.get(k).getOrderMoney()-PList.get(k).getRealMoney());
                            alwaysProfitSonService.updateAlwaysSonProfit(alwaysProfitSon);


                        }
                        //利润
                        Double  profit=(marketmoney-purchasemoney-distributionmoney-alwaysWornMoney);
                        Double  realProfit=(marketmoney-purchasemoney-distributionRealMoney-alwaysWornMoney);

                        AlwaysProfit alwaysProfit =new AlwaysProfit();

                        alwaysProfit.setAlwaysProfitCode(alwaysProfitCode);
                        alwaysProfit.setPurchaseCode(purchaseCode);
                        alwaysProfit.setAlwaysOrderNumber(ordernumber.toString());
                        alwaysProfit.setAlwaysOrderWeight(orderweigth.toString());
                        alwaysProfit.setAlwaysRealNumber(purchasenumber.toString());
                        alwaysProfit.setAlwaysRealWeight(purchaseweight.toString());

                        alwaysProfit.setAlwaysOrderMoney(marketmoney.toString());
                        alwaysProfit.setAlwaysRealMoney(purchasemoney.toString());
                        alwaysProfit.setMoney(distributionmoney.toString());
                        alwaysProfit.setRealMoney(distributionRealMoney.toString());
                        alwaysProfit.setAlwaysWornMoney(alwaysWornMoney);
                        alwaysProfit.setProfit(profit);
                        alwaysProfit.setRealProfit(realProfit);

                        alwaysProfitService.updateAlwaysProfit(alwaysProfit);
                        //数据添加完成 修改车的状态，保证不重复添加
                        distributionService.updateDistributionCarState(purchaseCode);
                }else {

                    //判断采购单是否全部完成

                        //采购单的信息 产品数量、重量、花销
                        Double purchasenumber= 0.00;
                        Double purchaseweight= 0.00;

                        //订单总数量
                        Double ordernumber=0.00;
                        Double orderweigth=0.00;
                        //采购总价
                        //Double purchasemoney= list.get(i).getTotalmoney();
                        Double purchasemoney=0.00;
                        //物流配送费用
                        Double distributionmoney=0.00;
                        Double distributionRealMoney=0.00;
                        //销售总价
                        Double marketmoney=0.00;
                        //总破损费用
                        Double alwaysWornMoney=0.00;
                        //编码
                        Date date = new Date();
                        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyyMMddHHmmssSSS");
                        String alwaysProfitCode=simpleDateFormat.format(date).toString();
                        //查询物流信息 完成的物流
                        List<Distribution> DList=distributionService.selectDistributionByPurchaseCode(purchaseCode);
                        for (int j=0;j<DList.size();j++){
                            distributionmoney+=new Integer(DList.get(j).getMoney());
                            if(DList.get(i).getRealDistributionMoney()!=null){
                                distributionRealMoney+= new Integer(DList.get(i).getRealDistributionMoney());
                            }
                            List<DistributionSon> DSList=distributionSonService.selectAllDistributionSon(DList.get(j).getDistributionCode());
                            for (int l=0;l<DSList.size();l++){
                                if (DSList.get(l).getWornMoney()!=null){
                                    alwaysWornMoney+=DSList.get(l).getWornMoney();
                                }

                            }

                        }

                        //查询订单信息 完成的订单
                        List<PurchaseItems> PList = purchaseService.selectPurchaseItemsByPurchaseCode(purchaseCode);
                        //添加采购单中的  架子费
                        PurchaseInfo purchaseInfo =purchaseService.selectOnePurchaseInfo(purchaseCode);
                        purchasemoney=Double.parseDouble(purchaseInfo.getRackMoney().toString());
                        for(int k=0;k<PList.size();k++){
                            //订单中数量、重量和采购总数量、重量
                            purchasenumber +=PList.get(k).getRealNumber();
                            purchaseweight +=PList.get(k).getRealWeigth();
                            ordernumber += PList.get(k).getOrderNumber();
                            orderweigth += PList.get(k).getOrderWeigth();
                            //根据订单获取采购单的总销售价和采购总价
                            marketmoney+=PList.get(k).getOrderMoney();
                            purchasemoney+=PList.get(k).getRealMoney();

                            //创建子对象
                            AlwaysProfitSon alwaysProfitSon=  new AlwaysProfitSon();

                            alwaysProfitSon.setAlwaysProfitSonCode(alwaysProfitCode+k);
                            alwaysProfitSon.setAlwaysProfitCode(alwaysProfitCode);

                            alwaysProfitSon.setPurchaseCode(purchaseCode);
                            alwaysProfitSon.setOrderCode(PList.get(k).getOrderCode());

                            alwaysProfitSon.setOrderNumber(PList.get(k).getOrderNumber().toString());
                            alwaysProfitSon.setOrderWeight(PList.get(k).getOrderWeigth().toString());

                            alwaysProfitSon.setPurchaseNumber(PList.get(k).getRealNumber().toString());
                            alwaysProfitSon.setPurchaseWeight(PList.get(k).getRealWeigth().toString());

                            alwaysProfitSon.setOrderPrice(PList.get(k).getOrderMoney());
                            alwaysProfitSon.setPurchasePrice(PList.get(k).getRealMoney());
                            alwaysProfitSon.setGrossProfit(PList.get(k).getOrderMoney()-PList.get(k).getRealMoney());
                            alwaysProfitSonService.saveAlwaysSonProfit(alwaysProfitSon);


                        }
                        //利润
                        Double  profit=(marketmoney-purchasemoney-distributionmoney-alwaysWornMoney);
                        Double  realProfit=(marketmoney-purchasemoney-distributionRealMoney-alwaysWornMoney);

                        AlwaysProfit alwaysProfit =new AlwaysProfit();

                        alwaysProfit.setAlwaysProfitCode(alwaysProfitCode);
                        alwaysProfit.setPurchaseCode(purchaseCode);
                        alwaysProfit.setAlwaysOrderNumber(ordernumber.toString());
                        alwaysProfit.setAlwaysOrderWeight(orderweigth.toString());
                        alwaysProfit.setAlwaysRealNumber(purchasenumber.toString());
                        alwaysProfit.setAlwaysRealWeight(purchaseweight.toString());

                        alwaysProfit.setAlwaysOrderMoney(marketmoney.toString());
                        alwaysProfit.setAlwaysRealMoney(purchasemoney.toString());
                        alwaysProfit.setMoney(distributionmoney.toString());
                        alwaysProfit.setRealMoney(distributionRealMoney.toString());
                        alwaysProfit.setAlwaysWornMoney(alwaysWornMoney);
                        alwaysProfit.setProfit(profit);
                        alwaysProfit.setRealProfit(realProfit);

                        alwaysProfitService.saveAlwaysProfit(alwaysProfit);
                        //数据添加完成 修改车的状态，保证不重复添加
                        distributionService.updateDistributionCarState(purchaseCode);
                }


            }*/

            return new ModelAndView("/system/alwaysProfit");
        } catch (Exception e) {
            throw e;
        }
    }

    @RequestMapping(value = "/alwaysProfitList.action")
    @ResponseBody
    public Page alwaysProfitList(Integer currentPage,String purchaseCode,Double profit) throws Exception {

        try {

            Page page = new Page();
            if(currentPage!=null){
                page.setCurrentPage(currentPage);
            }
            AlwaysProfit alwaysProfit = new AlwaysProfit();
            alwaysProfit.setPurchaseCode(purchaseCode.trim());
            if (profit==null){
                profit=-100000.00;
            }

            alwaysProfit.setProfit(profit);

            Page resultPage = alwaysProfitService.selectAllAlwaysProfit(page ,alwaysProfit);

            return resultPage;

        } catch (Exception e) {
            throw e;
        }
    }

    @RequestMapping(value = "/alwaysProfitSonList.action")
    @ResponseBody
    public Object alwaysProfitSonList(String alwaysProfitCode) throws Exception {

        try {

            return alwaysProfitSonService.selectAlwaysProfitByAlwaysProfitCode(alwaysProfitCode);

        } catch (Exception e) {
            throw e;
        }
    }
}