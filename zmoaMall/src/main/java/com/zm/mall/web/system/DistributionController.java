package com.zm.mall.web.system;



import com.zm.mall.client.Page;
import com.zm.mall.client.result.system.UserResult;
import com.zm.mall.client.service.business.orders.OrderInfoService;
import com.zm.mall.client.service.business.purchase.PurchaseService;
import com.zm.mall.client.service.system.*;
import com.zm.mall.common.utils.SessionGetPlUteFromId;
import com.zm.mall.domain.business.purchase.PurchaseInfo;
import com.zm.mall.domain.business.purchase.PurchaseItems;
import com.zm.mall.domain.system.*;
import com.zm.mall.web.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * Created by liyanshuai on 2016/11/8.
 */

@Controller
@RequestMapping("/business")
public class DistributionController extends BaseController{
    @Resource
    PurchaseService purchaseService;
    @Resource
    DistributionService distributionService;
    @Resource
    DistributionSonService distributionSonService;
    @Resource
    ApplyCarService applyCarService;
    @Resource
    TruckManageService truckManageService;
    @Resource
    AlwaysProfitService alwaysProfitService;
    @Resource
    AlwaysProfitSonService alwaysProfitSonService;
    @Resource
    OrderInfoService orderInfoService;
    @RequestMapping(value = "/toIndentList.action")
    public ModelAndView toIndentList() throws Exception {

        try {


            return new ModelAndView("/system/indentList");
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * indentList 物流配送 查询已采购的订单
     * @param currentPage
     * @param orderCode
     * @param shipAddress
     * @return
     * @throws Exception
     */

    @RequestMapping(value = "/indentList.action")
    @ResponseBody
    public Page indentList(Integer currentPage,String orderCode,String shipAddress,HttpServletRequest request) throws Exception {

        try {

            Page page = new Page();
            if(currentPage!=null){
                page.setCurrentPage(currentPage);
            }

            PurchaseItems purchaseItems= new PurchaseItems();

            purchaseItems.setOrderCode(orderCode.trim());
            purchaseItems.setShipAddress(shipAddress.trim());
            UserResult user= SessionGetPlUteFromId.FromSessionGetPlUteFromId(request);
            Long id=user.getDepartment().getId();
            page.setDepartmentId(id);


            Page resultPage = purchaseService.selectAllIndentFinish(page,purchaseItems);

            return resultPage;

        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * distributionList 车辆添加已采购订单
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/distributionListOne.action")
    @Transactional
    public ModelAndView distributionListOne(HttpServletRequest request) throws Exception {
        String purchaseorderCode=request.getParameter("purchaseorderCode");
       /* String[] purchaseorderCodes=purchaseorderCode.split(",");
        for (int i =0;i<purchaseorderCodes.length;i++){
            //System.out.println(purchaseorderCodes[i]);
        }*/
        try {
            Map map = new HashMap();
            List<PurchaseItems> resultList= new ArrayList<PurchaseItems>();
            //物流配送中多个合计属性
            Integer allOrderNumber=0;
            Integer allRealNumber=0;
            Integer allRealWeigth=0;
            String[] purchaseorderCodes=purchaseorderCode.split(",");
            for (int i =0;i<purchaseorderCodes.length;i++){
                if(purchaseorderCodes[i]!=""){
                    PurchaseItems purchaseItems =  purchaseService.selectOnePurchaseItems(purchaseorderCodes[i]);
                    allOrderNumber +=purchaseItems.getOrderNumber();
                    allRealNumber +=purchaseItems.getRealNumber();
                    allRealWeigth +=purchaseItems.getRealWeigth();
                    resultList.add(purchaseItems);

                }
            }
            //获取部门id
            UserResult user= SessionGetPlUteFromId.FromSessionGetPlUteFromId(request);
            Long departmentId=user.getDepartment().getId();
            String departmentName=user.getDepartment().getName();



            map.put("resultList",resultList);
            map.put("allOrderNumber",allOrderNumber);
            map.put("allRealNumber",allRealNumber);
            map.put("allRealWeigth",allRealWeigth);
            map.put("departmentId",departmentId);
            map.put("departmentName",departmentName);
            return new ModelAndView("/system/Distribution",map);
        } catch (Exception e) {
            throw e;
        }
    }


    /**
     * distributionSave 订单分配车辆成功 去审批
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/distributionSave.action")
    @Transactional
    public ModelAndView distributionSave(HttpServletRequest request) throws Exception {

        //获取物流配送信息
        String group =request.getParameter("group");
        String person =request.getParameter("person");
        //车排号
        String number =request.getParameter("number");
        String startTime =request.getParameter("startTime");
        String endTime =request.getParameter("endTime");
        String money =request.getParameter("money");
        String truckFinishTime =request.getParameter("truckFinishTime");
        //物流配送中多个合计属性
        String allOrderNumber = request.getParameter("allOrderNumber");
        String allRealNumber = request.getParameter("allRealNumber");
        String allRealWeigth = request.getParameter("allRealWeigth");

        //获取部门id
        String departmentId =request.getParameter("departmentId");

        //物流配送 相关人员
       /* String groupLeader= request.getParameter("groupLeader");
        String ister= request.getParameter("ister");*/
        UserResult user= SessionGetPlUteFromId.FromSessionGetPlUteFromId(request);
        String ister =user.getRealName();


        //订单数据获取
        String[] purchaseCode=request.getParameterValues("purchaseCode");
        String[] orderCodes=request.getParameterValues("orderCode");
        String[] zhongleis=request.getParameterValues("zhonglei");
        String[] ddsls=request.getParameterValues("ddsl");
        String[] sjsls=request.getParameterValues("sjsl");
        String[] sjzls=request.getParameterValues("sjzl");
        String[] sAddress=request.getParameterValues("shipAddress");
        String[] shipCellPhones=request.getParameterValues("shipCellPhone");
        //公里数和耗油量 后续录入
      /*  String[] kilometres=request.getParameterValues("kilometre");
        String[] consumes=request.getParameterValues("consume");*/
        String[] remarks=request.getParameterValues("remark");

        try {
            String state ="1";
            Date date = new Date();
            SimpleDateFormat  simpleDateFormat=new SimpleDateFormat("yyyyMMddHHmmssSSS");
            String DistributionCode=simpleDateFormat.format(date).toString();
            Distribution distribution=new Distribution();

            distribution.setPurchaseCode(purchaseCode[0]);

            distribution.setDistributionCode(DistributionCode);
            distribution.setSmallgroup(group);
            distribution.setPerson(person);
            distribution.setNumber(number);
            distribution.setStartTime(startTime);
            distribution.setEndTime(endTime);
            distribution.setMoney(money);
            distribution.setTruckFinishTime(truckFinishTime);
            distribution.setState(state);
            distribution.setAllOrderNumber(allOrderNumber);
            distribution.setAllRealNumber(allRealNumber);
            distribution.setAllRealWeigth(allRealWeigth);
            //distribution.setGroupLeader(groupLeader);
            distribution.setIster(ister);
            //设置部门id
            distribution.setDepartmentId(Integer.parseInt(departmentId));
            distributionService.distributionSave(distribution);

            //同时需要修改车辆状态
            if(number!="" && number!=null){
                String carState="5";
                TruckManage truckManage =new TruckManage();
                truckManage.setState(carState);
                truckManage.setNumber(number);
                truckManageService.updateTruckState(truckManage);

            }
            //添加DistributionSon
           /* private Integer id;
            private String distributionCode;//物流配送费用报销编码
            private String  orderCode;//订单编码
            private String kind;//货物种类
            private String orderNumber;//订单数量
            private String practicalNumber;//实际数量
            private String weight;//重量
            private String address;//收货人地址
            private String phone;//电话
            private String kilometre;//公里
            private String consume;//油耗、元/KM
            private String remark;//电话*/

            for (int i=0;i<orderCodes.length;i++){
                DistributionSon distributionSon= new DistributionSon();
                distributionSon.setDistributionSonCode(DistributionCode+i);
                distributionSon.setDistributionCode(DistributionCode);
                distributionSon.setPurchaseCode(purchaseCode[i]);
                distributionSon.setOrderCode(orderCodes[i]);
                distributionSon.setKind(zhongleis[i]);
                distributionSon.setOrderNumber(ddsls[i]);
                distributionSon.setPracticalNumber(sjsls[i]);
                distributionSon.setWeight(sjzls[i]);
                distributionSon.setAddress(sAddress[i]);
                distributionSon.setPhone(shipCellPhones[i]);
                /*distributionSon.setKilometre(kilometres[i]);
                distributionSon.setConsume(consumes[i]);*/
                distributionSon.setRemark(remarks[i]);
                distributionSonService.distributionSonSave(distributionSon);

                //修改采购单中订单状态  标识订单分配车辆
                purchaseService.updateStateByOrderCode(orderCodes[i]);
            }

            //添加利润数据（预估利润）
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
            Date alwaysProfitDate = new Date();
            SimpleDateFormat alwaysProfitSimpleDateFormat=new SimpleDateFormat("yyyyMMddHHmmssSSS");
            String alwaysProfitCode=alwaysProfitSimpleDateFormat.format(alwaysProfitDate).toString();
            //查询物流信息 完成的物流
            List<Distribution> DList=distributionService.selectDistributionByPurchaseCode(purchaseCode[0]);
            for (int j=0;j<DList.size();j++){
                distributionmoney+=new Integer(DList.get(j).getMoney());
                if(DList.get(j).getRealDistributionMoney()!=null){
                    distributionRealMoney+= new Integer(DList.get(j).getRealDistributionMoney());
                }
                List<DistributionSon> DSList=distributionSonService.selectAllDistributionSon(DList.get(j).getDistributionCode());
                for (int l=0;l<DSList.size();l++){
                    if (DSList.get(l).getWornMoney()!=null){
                        alwaysWornMoney+=DSList.get(l).getWornMoney();
                    }

                }

            }

            //查询订单信息 完成的订单
            List<PurchaseItems> PList = purchaseService.selectPurchaseItemsByPurchaseCode(purchaseCode[0]);
            //添加采购单中的  架子费
            PurchaseInfo purchaseInfo =purchaseService.selectOnePurchaseInfo(purchaseCode[0]);
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

                alwaysProfitSon.setPurchaseCode(purchaseCode[0]);
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


            AlwaysProfit alwaysProfit =new AlwaysProfit();

            alwaysProfit.setAlwaysProfitCode(alwaysProfitCode);
            alwaysProfit.setPurchaseCode(purchaseCode[0]);
            alwaysProfit.setAlwaysOrderNumber(ordernumber.toString());
            alwaysProfit.setAlwaysOrderWeight(orderweigth.toString());
            alwaysProfit.setAlwaysRealNumber(purchasenumber.toString());
            alwaysProfit.setAlwaysRealWeight(purchaseweight.toString());

            alwaysProfit.setAlwaysOrderMoney(marketmoney.toString());
            alwaysProfit.setAlwaysRealMoney(purchasemoney.toString());
            alwaysProfit.setMoney(distributionmoney.toString());
            alwaysProfit.setProfit(profit);


            alwaysProfitService.saveAlwaysProfit(alwaysProfit);
            //数据添加完成 修改车的状态，保证不重复添加
            //distributionService.updateDistributionCarState(purchaseCode[0]);
            return new ModelAndView("/system/indentList");
        } catch (Exception e) {
            throw e;
        }
    }

    @RequestMapping(value = "/distributionUpdate.action")
    @Transactional
    public ModelAndView distributionUpdate(HttpServletRequest request) throws Exception {
        String distributionCode =request.getParameter("distributionCode");
        String group =request.getParameter("smallgroup");
        String person =request.getParameter("person");
        String number =request.getParameter("number");
        String startTime =request.getParameter("startTime");
        String endTime =request.getParameter("endTime");
        String money =request.getParameter("money");
        String truckFinishTime =request.getParameter("truckFinishTime");
        //物流配送中多个合计属性
        String allOrderNumber = request.getParameter("allOrderNumber");
        String allRealNumber = request.getParameter("allRealNumber");
        String allRealWeigth = request.getParameter("allRealWeigth");


        //订单数据获取
        //String[] purchaseCode=request.getParameterValues("purchaseCode");
        String[] orderCodes=request.getParameterValues("orderCode");
        String[] zhongleis=request.getParameterValues("zhonglei");
        String[] ddsls=request.getParameterValues("ddsl");
        String[] sjsls=request.getParameterValues("sjsl");
        String[] sjzls=request.getParameterValues("sjzl");
        String[] sAddress=request.getParameterValues("shipAddress");
        String[] shipCellPhones=request.getParameterValues("shipCellPhone");
        String[] kilometres=request.getParameterValues("kilometre");
        String[] consumes=request.getParameterValues("consume");
        String[] remarks=request.getParameterValues("remark");

        try {
            String state ="1";
            Distribution distribution=new Distribution();
            distribution.setDistributionCode(distributionCode);
            distribution.setSmallgroup(group);
            distribution.setPerson(person);
            distribution.setNumber(number);
            distribution.setStartTime(startTime);
            distribution.setEndTime(endTime);
            distribution.setMoney(money);
            distribution.setTruckFinishTime(truckFinishTime);
            distribution.setState(state);
            distribution.setAllOrderNumber(allOrderNumber);
            distribution.setAllRealNumber(allRealNumber);
            distribution.setAllRealWeigth(allRealWeigth);
            distributionService.distributionUpdate(distribution);


            for (int i=0;i<orderCodes.length;i++){
                DistributionSon distributionSon= new DistributionSon();
                distributionSon.setOrderCode(orderCodes[i]);
                distributionSon.setKind(zhongleis[i]);
                distributionSon.setOrderNumber(ddsls[i]);
                distributionSon.setPracticalNumber(sjsls[i]);
                distributionSon.setWeight(sjzls[i]);
                distributionSon.setAddress(sAddress[i]);
                distributionSon.setPhone(shipCellPhones[i]);
                distributionSon.setKilometre(kilometres[i]);
                distributionSon.setConsume(consumes[i]);
                distributionSon.setRemark(remarks[i]);
                distributionSonService.distributionSonUpdate(distributionSon);

            }
            return  new ModelAndView("redirect:/business/toSelectDistributionNoConsent.action");
        } catch (Exception e) {
            throw e;
        }

    }
    //后续 时间添加
    @RequestMapping(value = "/distributionTimeSave.action")
    @Transactional
    public ModelAndView distributionTimeSave(HttpServletRequest request) throws Exception {
        String distributionCode =request.getParameter("distributionCode");
        String startTime =request.getParameter("startTime");
        String truckFinishTime =request.getParameter("truckFinishTime");
        String carNumber =request.getParameter("carNumber");
        try {
                Distribution distribution=new Distribution();
                distribution.setDistributionCode(distributionCode);
                distribution.setStartTime(startTime);
                distribution.setTruckFinishTime(truckFinishTime);
                distributionService.distributionAddStartTime(distribution);
                //如果转车时间确定后    申请车辆之车辆不能改变
                if(!"".equals(truckFinishTime.trim())){
                    applyCarService.updateShowState(carNumber);
                }
            return  new ModelAndView("redirect:/business/toSelectDistributionConsent.action");
        } catch (Exception e) {
            throw e;
        }

    }
    //去审批不同意的页面
    @RequestMapping(value = "/toSelectDistributionNoConsent.action")
    public ModelAndView toSelectDistributionNoConsent(HttpServletRequest request) throws Exception {
        try {


            return new ModelAndView("/system/distributionNoConsent");
        } catch (Exception e) {
            throw e;
        }

    }
    //去审批同意查询小组的页面
    @RequestMapping(value = "/toSelectDistributionConsent.action")
    public ModelAndView toSelectDistributionConsent(HttpServletRequest request) throws Exception {
        try {


            return new ModelAndView("/system/distributionConsent");
        } catch (Exception e) {
            throw e;
        }
    }
    //去审批同意查询所有的页面
    @RequestMapping(value = "/toSelectDistributionAllConsent.action")
    public ModelAndView toSelectDistributionAllConsent(HttpServletRequest request) throws Exception {
        try {


            return new ModelAndView("/system/distributionAllConsent");
        } catch (Exception e) {
            throw e;
        }
    }
    //异步加载审批不同意数据
    @RequestMapping(value = "/selectDistributionNoConsent.action")
    @ResponseBody
    public Page selectDistributionNoConsent(Integer currentPage,HttpServletRequest request) throws Exception {

        try {

            Page page = new Page();
            if(currentPage!=null){
                page.setCurrentPage(currentPage);
            }
            //设置部门id
            UserResult user=(UserResult)request.getSession().getAttribute("userResult");
            Long departmentId=user.getDepartment().getId();
            page.setDepartmentId(departmentId);
            Page resultPage = distributionService.selectDistributionNoConsent(page);
            return resultPage;


        } catch (Exception e) {
            throw e;
        }
    }
    //异步加载审批同意小组数据
    @RequestMapping(value = "/selectDistributionConsent.action")
    @ResponseBody
    public Page selectDistributionConsent(Integer currentPage ,String distributionCode,String number,HttpServletRequest request) throws Exception {

        try {

            Page page = new Page();
            if(currentPage!=null){
                page.setCurrentPage(currentPage);
            }
            Distribution distribution= new Distribution();
            distribution.setDistributionCode(distributionCode.trim());
            distribution.setNumber(number.trim());
            //设置部门id
            UserResult user=(UserResult)request.getSession().getAttribute("userResult");
            Long departmentId=user.getDepartment().getId();
            page.setDepartmentId(departmentId);
            Page resultPage = distributionService.selectDistributionConsent(page, distribution);
            return resultPage;


        } catch (Exception e) {
            throw e;
        }
    }
    //异步加载审批同意的所有数据
    @RequestMapping(value = "/selectDistributionAllConsent.action")
    @ResponseBody
    public Page selectDistributionAllConsent(Integer currentPage ,String distributionCode,String number) throws Exception {

        try {

            Page page = new Page();
            if(currentPage!=null){
                page.setCurrentPage(currentPage);
            }
            Distribution distribution= new Distribution();
            distribution.setDistributionCode(distributionCode.trim());
            distribution.setNumber(number.trim());
            Page resultPage = distributionService.selectDistributionAllConsent(page,distribution);
            return resultPage;


        } catch (Exception e) {
            throw e;
        }
    }

    //查看 审批同意小组的详细数据
    @RequestMapping(value = "/distributionConsentParticulars.action")
    @Transactional
    public ModelAndView distributionConsentParticulars(HttpServletRequest request) throws Exception {
    String distributionCode = request.getParameter("distributionCode");

        try {
            Map map = new HashMap();
            Distribution resultDistribution = distributionService.selectOneDistribution(distributionCode);
            List<DistributionSon> resultList = distributionSonService.selectAllDistributionSon(distributionCode);


            map.put("resultDistribution", resultDistribution);
            map.put("resultList", resultList);

            return new ModelAndView("/system/SPDistributionCP", map);
        } catch (Exception e) {
            throw e;
        }
    }
    //查看 审批同意所有的详细数据
    @RequestMapping(value = "/distributionConsentAllParticulars.action")
    @Transactional
     public ModelAndView distributionConsentAllParticulars(HttpServletRequest request) throws Exception {
    String distributionCode = request.getParameter("distributionCode");

        try {
            Map map = new HashMap();
            Distribution resultDistribution = distributionService.selectOneDistribution(distributionCode);
            List<DistributionSon> resultList = distributionSonService.selectAllDistributionSon(distributionCode);


            map.put("resultDistribution", resultDistribution);
            map.put("resultList", resultList);

            return new ModelAndView("/system/SPDistributionAllCP", map);
        } catch (Exception e) {
            throw e;
        }
    }

   //审批不同意  去修改数据页面
    @RequestMapping(value = "/distributionSonUpdate.action")
    @Transactional
    public ModelAndView distributionSonUpdate(HttpServletRequest request) throws Exception {
    String distributionCode = request.getParameter("distributionCode");

        try {
            Map map = new HashMap();
            Distribution resultDistribution = distributionService.selectOneDistribution(distributionCode);
            List<DistributionSon> resultList = distributionSonService.selectAllDistributionSon(distributionCode);


            map.put("resultDistribution", resultDistribution);
            map.put("resultList", resultList);

            return new ModelAndView("/system/SPDistributionUpdate", map);
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/selectDistributionTime.action")
    @ResponseBody
    public Distribution selectDistributionTime(String distributionCode) throws Exception {

        try {



            Distribution distribution = distributionService.selectOneDistribution(distributionCode);


            return distribution;

        } catch (Exception e) {
            throw e;
        }
    }
    @RequestMapping(value = "/distributionEndTimeSave.action")
    @Transactional
    public ModelAndView distributionEndTimeSave(HttpServletRequest request) throws Exception {
        try {
            //物流配送中的截止时间和真实物流费用
            String distributionCode = request.getParameter("distributionCode");
            String purchaseCode = request.getParameter("purchaseCode");
            String endTime = request.getParameter("endTime");
            String realDistributionMoney = request.getParameter("realDistributionMoney");
            //获取抹零，实际收款金额
            String wipeOffMoney = request.getParameter("wipeOffMoney");
            String receiveMoney = request.getParameter("receiveMoney");
            String number = request.getParameter("number");
            String truckState="1";
            String state ="4";
            //创建物流配送对象
            Distribution distribution =new Distribution();
            distribution.setDistributionCode(distributionCode);
            distribution.setEndTime(endTime);
            distribution.setRealDistributionMoney(realDistributionMoney);
            distribution.setWipeOffMoney(Double.parseDouble(wipeOffMoney));
            distribution.setReceiveMoney(Double.parseDouble(receiveMoney));
            distribution.setState(state);

            distributionService.distributionSaveLaterData(distribution);
            purchaseService.purchaseUpdateFinishState(purchaseCode);
            //订单完成  需要修改车辆信息   运行-空闲
            TruckManage truckManage =new TruckManage();
            truckManage.setState(truckState);
            truckManage.setNumber(number);
            truckManageService.updateTruckState(truckManage);
            //订单数据获取 破损、耗油、公里数、破损的数量和金额，名称

            String[] distributionSonCodes=request.getParameterValues("distributionSonCode");
            String[] kilometres=request.getParameterValues("kilometre");
            String[] consumes=request.getParameterValues("consume");
            String[] remarks=request.getParameterValues("remark");
            String[] wornNumbers=request.getParameterValues("wornNumber");
            String[] wornMoneys=request.getParameterValues("wornMoney");
            String[] wornName=request.getParameterValues("wornName");
            for (int i=0;i<distributionSonCodes.length;i++){
                DistributionSon distributionSon= new DistributionSon();
                distributionSon.setDistributionSonCode(distributionSonCodes[i]);
                distributionSon.setKilometre(kilometres[i]);
                distributionSon.setConsume(consumes[i]);
                distributionSon.setRemark(remarks[i]);
                distributionSon.setWornNumber(wornNumbers[i]);
                distributionSon.setWornMoney(Double.parseDouble(wornMoneys[i]));
                distributionSon.setWornName(wornName[i]);

                distributionSonService.distributionSonSaveLaterData(distributionSon);
            }

            //修改利润数据（真实利润）
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
                //实收金额、抹零
                Double aWipeOffMoney =Double.parseDouble(wipeOffMoney);
                Double aReceiveMoney =Double.parseDouble(receiveMoney);
                //编码
                String alwaysProfitCode=apList.get(0).getAlwaysProfitCode();
                //查询物流信息 完成的物流
                List<Distribution> DList=distributionService.selectDistributionByPurchaseCode(purchaseCode);
                for (int j=0;j<DList.size();j++){
                    distributionmoney+=new Integer(DList.get(j).getMoney());
                    if(DList.get(j).getRealDistributionMoney()!=null){
                        distributionRealMoney+= new Integer(DList.get(j).getRealDistributionMoney());
                    }
                    List<DistributionSon> DSList=distributionSonService.selectAllDistributionSon(DList.get(j).getDistributionCode());
                    for (int l=0;l<DSList.size();l++){
                        alwaysWornMoney+=DSList.get(l).getWornMoney();
                    }

                }

                //查询订单信息 完成的订单
                List<PurchaseItems> PList = purchaseService.selectPurchaseItemsByPurchaseCode(purchaseCode);

                //修改订单的状态为已完成
                orderInfoService.updateOrderComplete(PList.get(0).getOrderCode());

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
                Double  realProfit=(aReceiveMoney-purchasemoney-distributionRealMoney-alwaysWornMoney-aWipeOffMoney);

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
                alwaysProfit.setaWipeOffMoney(aWipeOffMoney);
                alwaysProfit.setaReceiveMoney(aReceiveMoney);
                alwaysProfitService.updateAlwaysProfit(alwaysProfit);
                //数据添加完成 修改车的状态，保证不重复添加
                distributionService.updateDistributionCarState(purchaseCode);
            }
            return new ModelAndView("/system/distributionAllConsent");

        } catch (Exception e) {
            throw e;
        }
    }
}