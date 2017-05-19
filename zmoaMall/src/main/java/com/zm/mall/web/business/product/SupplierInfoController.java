package com.zm.mall.web.business.product;

import com.zm.mall.client.PageVo;
import com.zm.mall.client.result.business.accounts.SystemCodeResult;
import com.zm.mall.client.result.business.product.SupplierInfoResult;
import com.zm.mall.client.result.system.PageResult;
import com.zm.mall.client.result.system.UserResult;
import com.zm.mall.client.service.business.accountsUsers.UserListService;
import com.zm.mall.client.service.business.product.ProductInfoService;
import com.zm.mall.client.service.business.product.SupplierInfoService;
import com.zm.mall.client.vo.business.accounts.ItemSystemCodeVo;
import com.zm.mall.client.vo.business.accounts.SystemCodeVo;
import com.zm.mall.client.vo.business.product.RegionInfoVo;
import com.zm.mall.client.vo.business.product.SupplierInfoVo;
import com.zm.mall.common.utils.SessionGetPlUteFromId;
import com.zm.mall.domain.business.product.BrandInfo;
import com.zm.mall.domain.business.product.RegionInfo;
import com.zm.mall.domain.business.product.SupplierInfo;
import com.zm.mall.service.SpaceBeanCopy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by Administrator on 2016/11/25.
 */
//@SessionAttributes("supplierInfoVo")
@Controller
@RequestMapping("supplierInfo")
public class SupplierInfoController {

    @Resource
    private SupplierInfoService supplierInfoService;
    @Resource
    private ProductInfoService productInfoService;

    @Resource
    private UserListService userListService;
    ;


    @RequestMapping("/show.action")
    public ModelAndView getAll(PageVo<SupplierInfo> pageVo,HttpServletRequest request)throws Exception{
        Map<String,Object> map=new HashMap<String, Object>();
        if(request.getSession().getAttribute("supplierInfo")!=null){
            SupplierInfo s=(SupplierInfo)request.getSession().getAttribute("supplierInfo");
            pageVo.setT(s);
        }
        PageResult<SupplierInfo> pageResult=supplierInfoService.select(pageVo);
        map.put("pageResult",pageResult);
        return new ModelAndView("business/supplierInfos", map);
    }

    @RequestMapping("/blur.action")
    public ModelAndView blur(SupplierInfoVo supplierInfoVo,HttpServletRequest request)throws Exception{
        Map<String,Object> map=new HashMap<String, Object>();
        SupplierInfo s=SpaceBeanCopy.supplierInfoVoToSupplierInfo(supplierInfoVo);
        PageVo<SupplierInfo> pageVo=new PageVo<SupplierInfo>();
        pageVo.setT(s);
        request.getSession().setAttribute("supplierInfo",s);
        return getAll(pageVo, request);
    }

   /* @ModelAttribute
    public void getSupplierInfoVo(@RequestParam(value = "supplierId",required = false)Long id,Map<String,Object> map){
        System.out.println("hello");
        SupplierInfoVo s=new SupplierInfoVo();
        s.setSupplierId(id);
        SupplierInfoResult supplierInfoResult=supplierInfoService.selectOne(s);
        SupplierInfoVo supplierInfoVo=SpaceBeanCopy.supplierInfoResultToSupplierInfoVo(supplierInfoResult);
        map.put("supplierInfoVo",supplierInfoVo);

    }*/

    @RequestMapping("/get.action")
    public ModelAndView toUpdate(SupplierInfoVo supplierInfoVo){
//---------------------------------------------------------------------------------------------------------------
//        Map<String,Object> map= new HashMap<String, Object>();
//        SupplierInfoResult supplierInfoResult=supplierInfoService.selectOne(supplierInfoVo);
//        map.put("supplierInfoResult",supplierInfoResult);
//        List<RegionInfo> regionInfos=productInfoService.selRegionInfoAll(null, null);
//        map.put("regionInfos",regionInfos);
//        map.put("brands",supplierInfoService.getAllBrands());
//        map.put("city",productInfoService.selRegionInfoAll(supplierInfoResult.getAaregionpriceimport().getProvinceId(),2));
//        map.put("county",productInfoService.selRegionInfoAll(supplierInfoResult.getAaregionpriceimport().getCityId(),3));
//        map.put("regionInfosNext",productInfoService.selRegionInfoAll(supplierInfoResult.getEstablishedPlace().getProvinceId(),2));
//        map.put("regionInfosLast",productInfoService.selRegionInfoAll(supplierInfoResult.getEstablishedPlace().getCityId(),3));
//-------------------------------------------------------------------------------------------------------------------------------------------
        Map<String,Object> map= new HashMap<String, Object>();
        SupplierInfoResult supplierInfoResult=supplierInfoService.selectOne(supplierInfoVo);
        map.put("supplierInfoResult",supplierInfoResult);

        RegionInfoVo regionInfoVo = new RegionInfoVo();
        List<RegionInfo> regionInfos=productInfoService.selRegionInfoAll(regionInfoVo);
        map.put("regionInfos",regionInfos);
        map.put("brands",supplierInfoService.getAllBrands());
        regionInfoVo.setParentId(supplierInfoResult.getAaregionpriceimport().getProvinceId());
        regionInfoVo.setDepth(2);
        map.put("city",productInfoService.selRegionInfoAll(regionInfoVo));
        regionInfoVo.setParentId(supplierInfoResult.getAaregionpriceimport().getCityId());
        regionInfoVo.setDepth(3);
        map.put("county",productInfoService.selRegionInfoAll(regionInfoVo));
        regionInfoVo.setParentId(supplierInfoResult.getEstablishedPlace().getProvinceId());
        regionInfoVo.setDepth(2);
        map.put("regionInfosNext",productInfoService.selRegionInfoAll(regionInfoVo));
        regionInfoVo.setParentId(supplierInfoResult.getEstablishedPlace().getCityId());
        regionInfoVo.setDepth(3);
        map.put("regionInfosLast",productInfoService.selRegionInfoAll(regionInfoVo));
        return new ModelAndView("business/supplierInfo",map);
    }

    @RequestMapping("/{id}/delete.action")
    public ModelAndView delete(@PathVariable("id") String id,PageVo<SupplierInfo> pageVo,HttpServletRequest request)throws  Exception{
        String []str=id.split(",");
        for(String s:str){
            Long l=Long.parseLong(s);
            SupplierInfoVo supplierInfoVo=new SupplierInfoVo();
            supplierInfoVo.setSupplierId(l);
            supplierInfoService.delete(supplierInfoVo);
        }
        return getAll(pageVo,request);
    }

    @RequestMapping(value = "/getAddress.action")
    @ResponseBody
    public List<RegionInfo> selAll(Integer fatherId,Integer depath) {
        RegionInfoVo regionInfoVo = new RegionInfoVo();
        regionInfoVo.setParentId(fatherId);
        regionInfoVo.setDepth(depath);
        List<RegionInfo> regionInfos=  productInfoService.selRegionInfoAll(regionInfoVo);
//        List<RegionInfo> regionInfos=  productInfoService.selRegionInfoAll(null,null);
        return regionInfos;

    }

    @RequestMapping(value = "/toAddPage.action")
    public ModelAndView toAddPage(){
        RegionInfoVo regionInfoVo = new RegionInfoVo();
        Map<String,Object> map=new HashMap<String, Object>();
        List<RegionInfo> regionInfos=productInfoService.selRegionInfoAll(regionInfoVo);
//       List<RegionInfo> regionInfos=  productInfoService.selRegionInfoAll(null,null);
        map.put("regionInfos",regionInfos);
        map.put("brands",supplierInfoService.getAllBrands());
        return new ModelAndView("business/addSupplierInfo",map);
    }

    @RequestMapping(value = "/addSupplierInfo.action")
    public ModelAndView add(SupplierInfoVo supplierInfoVo,PageVo<SupplierInfo> pageVo,HttpServletRequest request)throws Exception{
        try {
            supplierInfoVo.setCreatedDate(new Date());
            supplierInfoService.insert(supplierInfoVo);
            Map<String,Object> map=new HashMap<String, Object>();
            if(request.getSession().getAttribute("supplierInfo")!=null){
                SupplierInfo s=(SupplierInfo)request.getSession().getAttribute("supplierInfo");
                pageVo.setT(s);
            }
            PageResult<SupplierInfo> pageResult=supplierInfoService.select(pageVo);
            map.put("pageResult",pageResult);
            return new ModelAndView("business/supplierInfos", map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param supplierInfoVo
     * @param pageVo
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/updateSupplierInfo.action")
    public ModelAndView update(SupplierInfoVo supplierInfoVo,PageVo<SupplierInfo> pageVo,HttpServletRequest request)throws Exception{
        String []str= request.getParameterValues("ids");
        if(str!=null){
            Set<BrandInfo> set=new HashSet<BrandInfo>();
            for(String s:str){
                BrandInfo b=new BrandInfo();
                Integer i=Integer.parseInt(s);
                b.setBrandId(i);
                set.add(b);
            }
            supplierInfoVo.setBrandInfos(set);
        }
        supplierInfoService.update(supplierInfoVo);
        Map<String,Object> map=new HashMap<String, Object>();
        if(request.getSession().getAttribute("supplierInfo")!=null){
            SupplierInfo s=(SupplierInfo)request.getSession().getAttribute("supplierInfo");
            pageVo.setT(s);
        }
        PageResult<SupplierInfo> pageResult=supplierInfoService.select(pageVo);
        map.put("pageResult",pageResult);
        return new ModelAndView("business/supplierInfos", map);
    }

   /* @RequestMapping("/getProductSale.action")
    @ResponseBody
    public Object getProductSale(Integer id){

        return new String("hello");
    }*/

    /**
     * 供应商积分查询
     * @param
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/supplierInfoScore.action")
    public ModelAndView getSupplierInfoScore(Integer id) throws  Exception
    {
        //根据名称查询id
//        UserResult user= SessionGetPlUteFromId.FromSessionGetPlUteFromId(request);
//        String code="khpf";
        String code = "gyspf";
        SystemCodeVo systemCodeVo = new SystemCodeVo();
//        systemCodeVo.setPluteformid(user.getPluteformid());
        systemCodeVo.setCode(code);
        SystemCodeResult systemCode=userListService.getCodeUserId(systemCodeVo);
        ItemSystemCodeVo itemSystemCodeVo = new ItemSystemCodeVo();
        itemSystemCodeVo.setFid(systemCode.getId());
        itemSystemCodeVo.setUserId(id);
        List list=userListService.getUserScore(itemSystemCodeVo);
//        String code="gyspf";//供应商不变 属性
//        SystemCode systemCode=userListService.getCodeUserId(code);
//        int id=systemCode.getId();//获取对应的Id
//        List list=userListService.getUserScore(id,text);//text为供应商Id
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("list",list);
        map.put("listSize",list.size());
        map.put("pid",systemCode.getId());//Id得到Pid
        map.put("supplierId",id);
        return new ModelAndView("/business/supplierInfoScore",map);
    }

    /**
     * 供应商积分更改操作
     * @param listSize
     * @param supplierId
     * @param percentage
     * @param scoreVal
     * @param codeId
     * @param pid_01
     * @return
     * @throws Exception
     */
//    @RequestMapping(value="/supplierUpdateScore.action")
//    public ModelAndView getUpdateScore(Integer listSize,Integer supplierId,String percentage,String scoreVal,String codeId,Integer pid_01,PageVo<SupplierInfo> pageVo,HttpServletRequest request) throws  Exception
//    {
//
//        //分为4个逻辑
//        //1.当自己表中为空 时 怎没有数据 开始添加数据
//        //2.当自己表中数据大于a表时，怎说明a表删除了数据 则自己表中要删除数据 在修改
//        //3.当自己表中数据小于a表时，怎说明a表数据增加了 则自己也要增加数据 在修改
//        //4.当2个表相同时 怎么说明是更改 操作 进行更改就可以了
//        //  percentage 百分数  score 输入的值
//        Double totalScore=0.00;
//        HashMap<String, Object> map = new HashMap<String, Object>();
//        //获取当前的时间
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
//        Date date=df.parse(df.format(new Date()));
//
//        String [] a=percentage.split(",");//百分数
//        String [] b=scoreVal.split(",");//输入的值
//        String [] e=codeId.split(",");//id 为itemCodeId
//        //首先判断这个经销商是否存在 不存在添加 存在直接修改
//        List list=userListService.getUserList(supplierId,2);
//        if(list.size()==0){//数据库没有这条记录 增加
//            for (int i = 0; i <a.length ; i++) {
//                String c=a[i];
//                Double aa=Double.parseDouble(c);//百分比
//                String d=b[i];
//                int bb=Integer.parseInt(d);//输入的值
//                String f=e[i];
//                int ff=Integer.parseInt(f);//为itemCodeId
//                Double score=aa*bb;//获取这个的实际分数
//                totalScore+=score;
//                userListService.insertScore(supplierId,ff,new Date(),score,2,d);
//            }
//        }else if (list.size()>listSize)//原先的类型删除了一些数据 进行删除操作 遍历list
//        {
//            for (int i = 0; i < list.size(); i++) {
//                UserScore userScore=(UserScore)list.get(i);
//                int pid=userScore.getPid();
//                List list1=userListService.getCodeType(pid,null);//判断itemId没有的
//                if(list1.size()==0){
//                    userListService.delCodeType(supplierId,pid);//删除评分表的数据
//                }
//            }
//            for (int k = 0; k <a.length ; k++) {
//                String c=a[k];
//                Double aa=Double.parseDouble(c);//百分比
//                String d=b[k];
//                int bb=Integer.parseInt(d);//输入的值
//                String f=e[k];//其他表对应的Id
//                int ff=Integer.parseInt(f);
//                Double score=aa*bb;//得到分数
//                userListService.updateScore(supplierId,ff,new Date(),score,d);
//            }
//        }
//        else if (list.size()<listSize)//原先的类型添加了一些数据  进行添加操作  遍历 listSize listSize item的表 list是score的表
//        {
//
//            List list1=userListService.getCodeType(null,pid_01);//判断item中有几个类型
//            List list2=new ArrayList();
//            int f=listSize-list.size();//以防添加不止一条数据
//            for (int j = 0; j <list.size() ; j++) {
//                UserScore userScore=(UserScore)list.get(j);
//                int pid=userScore.getPid();//Item的Id
//                list2.add(pid);
//            }
//            for (int i = 0; i <list1.size() ; i++) {//根据Pid查询所有Id的 编例所有的类型 判断跟userScore的类型是否存在 不存在 加上
//                ItemSystemCode item=(ItemSystemCode)list1.get(i);
//                int id=item.getId();
//                //获取id判断下面list是否存在
//                if(list2.contains(id)==false)//查找userScore没有的
//                {
//                    for (int m = a.length-f; m <a.length ; m++) {/*a.length-f目的添加最后一条的数据*/
//                        String c=a[m];
//                        Double aa=Double.parseDouble(c);//百分比
//                        String d=b[m];
//                        int bb=Integer.parseInt(d);//输入的值
//                        Double score=aa*bb;
//                        userListService.insertScore(supplierId,id,new Date(),score,2,d);//分数，输入的值
//                        f=f+1;
//                        break;
//                    }
//                }
//            }
//            for (int k = 0; k <a.length ; k++) {
//                String c=a[k];
//                Double aa=Double.parseDouble(c);//百分比
//                String d=b[k];
//                int bb=Integer.parseInt(d);//输入的值
//                String fff=e[k];//其他表对应的Id
//                int ff=Integer.parseInt(fff);
//                Double score=aa*bb;//得到分数
//                userListService.updateScore(supplierId,ff,new Date(),score,d);
//            }
//        }else
//        {
//            //修改 根据用户ID和关联表Id进行修改
//            for (int i = 0; i <a.length ; i++) {
//                String c=a[i];
//                Double aa=Double.parseDouble(c);//百分比
//                String d=b[i];
//                int bb=Integer.parseInt(d);//输入的值
//                String f=e[i];//其他表对应的Id
//                int ff=Integer.parseInt(f);
//                Double score=aa*bb;//得到分数
//                totalScore+=score;
//                userListService.updateScore(supplierId,ff,new Date(),score,d);
//            }
//
//        }
//        return getAll(pageVo,request);
//        //return new ModelAndView("business/supplierInfos", map);
//    }

    @RequestMapping(value="/supplierUpdateScore.action")
    public ModelAndView getUpdateScore(Integer listSize,Integer supplierId,String percentage,String scoreVal,String codeId,Integer pid_01,PageVo<SupplierInfo> pageVo,HttpServletRequest request) throws  Exception
    {
        try{
            //分为4个逻辑
            //1.当自己表中为空 时 怎没有数据 开始添加数据
            //2.当自己表中数据大于a表时，怎说明a表删除了数据 则自己表中要删除数据 在修改
            //3.当自己表中数据小于a表时，怎说明a表数据增加了 则自己也要增加数据 在修改
            //4.当2个表相同时 怎么说明是更改 操作 进行更改就可以了
            //  percentage 百分数  score 输入的值
            UserResult user= SessionGetPlUteFromId.FromSessionGetPlUteFromId(request);
            HashMap<String, Object> map = new HashMap<String, Object>();
            //获取当前的时间
            Double totalScore = userListService.AddScoreOnSuppOrUser(user.getPluteformid(), listSize, supplierId, percentage, scoreVal, codeId, pid_01);
            return getAll(pageVo,request);
            //return new ModelAndView("business/supplierInfos", map);
        }catch (Exception e){
            throw e;
        }
    }
}



