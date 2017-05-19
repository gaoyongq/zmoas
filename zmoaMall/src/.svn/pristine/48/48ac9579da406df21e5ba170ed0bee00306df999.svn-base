package com.zm.mall.web.system;




import com.zm.mall.client.Page;
import com.zm.mall.client.result.system.UserResult;
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
import javax.servlet.http.HttpServletRequest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by liyanshuai on 2016/11/8.
 */

@Controller
@RequestMapping("/business")
public class ExamineApproveController extends BaseController {
    @Resource
    DistributionService distributionService;
    @Resource
    DistributionSonService distributionSonService;
    @Resource
    OrderInfoService orderInfoService;
    @Resource
    PurchaseService purchaseService;


    @RequestMapping(value = "/toExamineApproveWL.action")
    public ModelAndView toExamineApproveWL() throws Exception {
        try {


            return new ModelAndView("/system/toExamineApproveWL");
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * distributionList 查询物流配送列表分页
     * @param currentPage
     * @param distributionCode
     * @param number
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/distributionList.action")
    @ResponseBody
    public Page distributionList(Integer currentPage,String distributionCode,String number,HttpServletRequest request) throws Exception {

        try {

            Page page = new Page();
            if(currentPage!=null){
                page.setCurrentPage(currentPage);
            }
            Distribution distribution= new Distribution();
            distribution.setDistributionCode(distributionCode.trim());
            distribution.setNumber(number.trim());
            //获取部门id
            UserResult user=(UserResult)request.getSession().getAttribute("userResult");
            Long departmentId=user.getDepartment().getId();
            page.setDepartmentId(departmentId);
            Page resultPage = distributionService.selectDistributionSP(page,distribution);
            return resultPage;


        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * distributionSonList 查询物流配送 根据主表编号查询子表记录
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/distributionSonList.action")
    @Transactional
    public ModelAndView distributionSonList(HttpServletRequest request) throws Exception {
        String distributionCode=request.getParameter("distributionCode");

        try {
            Map map = new HashMap();
            Distribution resultDistribution = distributionService.selectOneDistribution(distributionCode);
            List<DistributionSon> resultList= distributionSonService.selectAllDistributionSon(distributionCode);



            map.put("resultDistribution",resultDistribution);
            map.put("resultList",resultList);

            return new ModelAndView("/system/SPDistribution",map);
        } catch (Exception e) {
            throw e;
        }

    }

    /**
     * distributionConsent 物流审批同意
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/distributionConsent.action")
    @Transactional
    public ModelAndView distributionConsent(HttpServletRequest request) throws Exception {
        String distributionCode=request.getParameter("distributionCode");
        //String approvePeople=request.getParameter("approvePeople");
        UserResult user=(UserResult)request.getSession().getAttribute("userResult");
        String approvePeople =user.getRealName();
        String state ="2";
        Distribution distribution =  new Distribution();

        try {

            String[] distributionCodes=distributionCode.split(",");
            for (int i =0;i<distributionCodes.length;i++){
                if(distributionCodes[i]!=""){
                    distribution.setApprovePeople(approvePeople);
                    distribution.setState(state);
                    distribution.setDistributionCode(distributionCodes[i]);
                    distributionService.updateDistributionState(distribution);

                    //修改订单状态、2物流审批已通过
                        //1 先查询出所有的 订单编码
                    List<DistributionSon> distributionSonList= distributionSonService.selectAllDistributionSon(distributionCodes[i]);
                    for (int j=0;j<distributionSonList.size();j++){
                        orderInfoService.updateOrderPurchasing(distributionSonList.get(j).getOrderCode());
                    }

                    //获取添加利润
                    Distribution db = distributionService.selectOneDistribution(distributionCodes[i]);
                    //修改采购单的状态
                    //purchaseService.purchaseUpdateFinishState(db.getPurchaseCode());

                }

            }


            return  new ModelAndView("redirect:/business/toExamineApproveWL.action");
        } catch (Exception e) {
            throw e;
        }

    }

    /**
     * distributionNoConsent 物流审批不同意
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/distributionNoConsent.action")
    @Transactional
    public ModelAndView distributionNoConsent(HttpServletRequest request) throws Exception {
        String distributionCode=request.getParameter("distributionCode");
        //String approvePeople=request.getParameter("approvePeople");
        UserResult user=(UserResult)request.getSession().getAttribute("userResult");
        String approvePeople =user.getRealName();
        String state ="3";
        Distribution distribution =  new Distribution();
        try {

            String[] distributionCodes=distributionCode.split(",");
            for (int i =0;i<distributionCodes.length;i++){
                if(distributionCodes[i]!=""){
                    distribution.setApprovePeople(approvePeople);
                    distribution.setState(state);
                    distribution.setDistributionCode(distributionCodes[i]);
                    distributionService.updateDistributionNoState(distribution);
                }

            }
            return  new ModelAndView("redirect:/business/toExamineApproveWL.action");
        } catch (Exception e) {
            throw e;
        }

    }


}