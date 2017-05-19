package com.zm.mall.web.business.accountsUsers;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zm.mall.client.Page;
import com.zm.mall.client.result.business.accounts.*;
import com.zm.mall.client.result.business.product.RegionInfoResult;
import com.zm.mall.client.result.system.UserResult;
import com.zm.mall.client.service.business.accountsUsers.UserListService;
import com.zm.mall.client.service.business.appointment.AppointmentOrderService;
import com.zm.mall.client.service.business.orders.OrderInfoService;
import com.zm.mall.client.service.business.product.ProductInfoService;
import com.zm.mall.client.service.system.UserService;
import com.zm.mall.client.service.wechat.CartService;
import com.zm.mall.client.vo.business.accounts.*;
import com.zm.mall.client.vo.business.product.RegionInfoVo;
import com.zm.mall.client.vo.system.UserVo;
import com.zm.mall.common.utils.SessionGetPlUteFromId;
import com.zm.mall.domain.business.accountsUsers.*;
import com.zm.mall.domain.business.orders.OrderByTip;
import com.zm.mall.domain.business.orders.OrderInfo;
import com.zm.mall.domain.business.product.RegionInfo;
import com.zm.mall.domain.system.MallConfig;
import com.zm.mall.domain.system.User;
import com.zm.mall.domain.system.coupon.EasyUIListResult;
import com.zm.mall.domain.wechat.WeChat_cart;
import com.zm.mall.web.BaseController;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Administrator on 2016/11/11.
 */

@Controller
@RequestMapping("/business")
@PropertySource("classpath:urlPrefix.properties")
public class UserListController extends BaseController {

    @Resource
    private CartService cartService;
    @Resource
    private UserListService userListService;
    @Resource
    private OrderInfoService orderInfoService;
    @Resource
    private UserService userService;
    @Resource
    private ProductInfoService productInfoService;
    @Autowired
    private Environment env;
    @Resource
    private AppointmentOrderService appointmentOrderService;


    /**
     * 根据用户激活状态 是否查询
     * @param activity
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/activityList.action")
    public ModelAndView getActive(HttpServletRequest request,@RequestParam(value="activity",required = false) Integer activity) throws Exception
    {
        try{
            UserResult user= SessionGetPlUteFromId.FromSessionGetPlUteFromId(request);
            AccountUserVo usersVo = new AccountUserVo();
            usersVo.setActivity(activity);
            usersVo.setPluteformid(user.getPluteformid());
            ModelAndView MV = new ModelAndView("/business/userList");
            List<AccountUserResult> selectActiv = userListService.getSelectActiv(usersVo);
            MV.addObject("userList",selectActiv);
            return MV;
        }catch (Exception e){
            throw e;
        }
    }

   //根据Id查询 详细 信息
      @RequestMapping(value="/UserId.action")
    public ModelAndView getUserIdSelect(HttpServletRequest request,Integer userId,String text,Integer page,String keyWord,String beforeTime,String afterTime,Integer activity) throws  Exception
    {
        try{
            Map<String,Object> map=new HashMap<String, Object>();
            UserResult user= SessionGetPlUteFromId.FromSessionGetPlUteFromId(request);
            AccountUserVo usersVo = new AccountUserVo();
            usersVo.setUserId(userId);
            usersVo.setPluteformid(user.getPluteformid());
            AccountUserResult accountUserResult = userListService.getUserIdSelect(usersVo);
            map.put("Users", accountUserResult);
            map.put("text",text);
            map.put("keyWord",keyWord);
            map.put("beforeTime",beforeTime);
            map.put("afterTime",afterTime);
            map.put("activity",activity);
            map.put("page",page);
            return  new ModelAndView("/business/UsersUpdate",map);
        }catch (Exception e){
            throw e;
        }
    }

    /**
     * 根据关键词查询
     * @param keyWord
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/keyWord.action")
    public ModelAndView getKeyWord(HttpServletRequest request,String keyWord,String beforeTime,String afterTime,Integer activity,Map model) throws  Exception
    {
        try{
            UserResult user= SessionGetPlUteFromId.FromSessionGetPlUteFromId(request);
            AccountUserVo usersVo = new AccountUserVo();
            usersVo.setBeforeTime(beforeTime);
            usersVo.setAfterTime(afterTime);
            usersVo.setActivity(activity);
            usersVo.setKey(keyWord);
            usersVo.setPluteformid(user.getPluteformid());
            List<AccountUserResult> accountUserResults = userListService.getKeyword(usersVo);
            Map<String,Object> map=new HashMap<String, Object>();
            map.put("keyWord",keyWord);
            map.put("beforeTime",beforeTime);
            map.put("afterTime",afterTime);
            map.put("activity",activity);
            map.put("userList", accountUserResults);
            return  new ModelAndView("/business/userList",map);
        }catch (Exception e){
            throw e;
        }
    }

    /**
     * 给账户充值等
     * @param userId
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/TopUp.action")
    public ModelAndView getTopUp(HttpServletRequest request,String text,Integer userId,Double balance,Integer points,Integer rankScore,Integer page,String keyWord,String beforeTime,String afterTime,Integer activity) throws  Exception
    {
        try{
            HashMap<String, Object> map = new HashMap<String, Object>();
            UserResult user= SessionGetPlUteFromId.FromSessionGetPlUteFromId(request);
            AccountUserVo usersVo = new AccountUserVo();
            usersVo.setUserId(userId);
            usersVo.setPluteformid(user.getPluteformid());
            AccountUserResult userResult = userListService.getTopUp(usersVo);
            map.put("Users", userResult);
            if(balance!=null){
                map.put("mapBalance", balance);
                map.put("mapPoints", "");
                map.put("mapRankScore", "");
            }else if(points!=null)
            {
                map.put("mapPoints", points);
                map.put("mapBalance", "");
                map.put("mapRankScore", "");

            }else
            {
                map.put("mapRankScore", rankScore);
                map.put("mapPoints", "");
                map.put("mapBalance", "");
            }
            map.put("keyWord",keyWord);
            map.put("beforeTime",beforeTime);
            map.put("afterTime",afterTime);
            map.put("activity",activity);
            map.put("page",page);
            map.put("text",text);
            return new ModelAndView("/business/update",map);
        }catch (Exception e){
            throw e;
        }
    }

    /**
     * 根据时间查询数据
     * @param beforeTime
     * @param afterTime
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/TimeSearch.action")
    public ModelAndView getTimeSearch(HttpServletRequest request,String beforeTime,String afterTime) throws  Exception
    {
        try{
            UserResult user= SessionGetPlUteFromId.FromSessionGetPlUteFromId(request);
            AccountUserVo usersVo = new AccountUserVo();
            usersVo.setBeforeTime(beforeTime);
            usersVo.setAfterTime(afterTime);
            usersVo.setPluteformid(user.getPluteformid());
            ModelAndView MV = new ModelAndView("/business/userList");
            List<AccountUserResult> userList = new ArrayList<AccountUserResult>();
            userList = userListService.getTimeSearch(usersVo);
            MV.addObject("userList", userList);
            return MV;
        }catch (Exception e){
            throw e;
        }
    }

    /**
     * 更改余额 美豆 成长值
     * @param balance
     * @param points
     * @param rankScore
     * @param userId
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/updateRPB.action")
    public ModelAndView getUpdateRPB(HttpServletRequest request,String text,Double balance,Integer points,Integer rankScore,Integer userId,Integer page,Integer pageSize,String keyWord,String beforeTime,String afterTime,Integer activity) throws  Exception
    {
        try{
            UserResult user= SessionGetPlUteFromId.FromSessionGetPlUteFromId(request);
            UsersExpVo usersExpVo = new UsersExpVo();
            usersExpVo.setBalance(balance);
            usersExpVo.setPoints(points);
            usersExpVo.setRankScore(rankScore);
            usersExpVo.setUserId(userId);
            usersExpVo.setPluteformid(user.getPluteformid());
            userListService.updateRBP(usersExpVo);
            return pageBeanUser(request,page,pageSize,keyWord,beforeTime,afterTime,activity,null,text);
        }catch (Exception e){
            throw e;
        }
    }

    /**
     * 查看美豆详细信息
     * @param userId
     * @param type
     * @param nickName
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/PointDetail.action")
    public ModelAndView getPointDetail(HttpServletRequest request,Integer page,String keyWord,String beforeTime,String afterTime,Integer activity,Integer userId,Integer type,String nickName,String text) throws  Exception
    {
        try{
            UserResult user= SessionGetPlUteFromId.FromSessionGetPlUteFromId(request);
            PointsDetailVo pointsDetailVo = new PointsDetailVo();
            pointsDetailVo.setUserId(userId);
            pointsDetailVo.setType(type);
            pointsDetailVo.setPluteformid(user.getPluteformid());
            List<PointsDetailResult> list=new ArrayList<PointsDetailResult>();
            if(type==null){
                list=userListService.getScoreDetail(pointsDetailVo);
            }else
            {
                list=userListService.getTypeSearch(pointsDetailVo);
            }
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("pointsDetail",list);
            map.put("nickName",nickName);
            map.put("type",type);
            map.put("userId", userId);
            map.put("text",text);
            map.put("keyWord",keyWord);
            map.put("beforeTime",beforeTime);
            map.put("afterTime",afterTime);
            map.put("activity",activity);
            map.put("page",page);
            return new ModelAndView("/business/pointDetail",map);
        }catch (Exception e){
            throw e;
        }
    }

    //全部冻结
    @RequestMapping(value="/freezeUser.action")
    public ModelAndView getPointDetail(HttpServletRequest request,Integer frost,String text,Integer page,Integer pageSize,String keyWord,String beforeTime,String afterTime,Integer activity) throws  Exception
    {
        //text 为userId
        try{
            UserResult user= SessionGetPlUteFromId.FromSessionGetPlUteFromId(request);
            AccountUserVo usersVo = new AccountUserVo();
            usersVo.setPluteformid(user.getPluteformid());
            String[] a = text.split(",");//获取所有的Id和Name 拆分得到数组
            if(frost==1){//给他激活
                for (int i = 0; i < a.length; i++) {
                    String userStr = a[i].split("/")[0];
                    Integer userId = Integer.parseInt(userStr);
                    usersVo.setUserId(userId);
                    userListService.activationUser(usersVo);
                }
            }else {
                for (int i = 0; i < a.length; i++) {//给他冻结
                    String userStr = a[i].split("/")[0];//得到Id
                    Integer userId = Integer.parseInt(userStr);
                    usersVo.setUserId(userId);
                    userListService.updateFreeze(usersVo);
                }
            }
            return pageBeanUser(request,page,pageSize,keyWord,beforeTime,afterTime,activity,null,text);
        }catch (Exception e){
            throw e;
        }
    }


    //显示分页
    @RequestMapping(value="/pageBeanUser.action")
    public ModelAndView pageBeanUser(HttpServletRequest request,Integer currentPage,Integer pageSize,String keyWord,String beforeTime,String afterTime,Integer activity,Double totalScore,String text) throws  Exception
    {
        try{
            UserResult user= SessionGetPlUteFromId.FromSessionGetPlUteFromId(request);
            Page page  =new Page();
            AccountUserVo userVo = new AccountUserVo();
            userVo.setBeforeTime(beforeTime);
            userVo.setAfterTime(afterTime);
            userVo.setActivity(activity);
            userVo.setKey(keyWord);
            page.setPluteformid(user.getPluteformid());
            page.setShopId(user.getShopId());
            if(currentPage!=null){
                page.setCurrentPage(currentPage);
            }
            HashMap<String, Object> map = new HashMap<String, Object>();
            Page pageResult = userListService.getFenYe(page, userVo);//当前页开始排列数
            map.put("userList",pageResult.getUsersList());
            map.put("page",page.getCurrentPage());//第几页
            map.put("totalPage",pageResult.getTotalPages());//多少页
            map.put("count",pageResult.getTotalCount());//多少条数据
            map.put("keyWord",keyWord);
            map.put("beforeTime",beforeTime);
            map.put("afterTime",afterTime);
            map.put("activity",activity);
            map.put("totalScore",totalScore);
            map.put("text",text);
            return new ModelAndView("/business/userList",map);
        }catch (Exception e){
            throw e;
        }
    }


    /**
     *
     * @throws Exception
     */
    @RequestMapping(value="/pageExcel.action")
    public ModelAndView pageExcel(HttpServletResponse response,Integer page,Integer pageSize,String keyWord,String beforeTime,String afterTime,Integer activity) throws  Exception
    {
       // HttpServletResponse response;
        HashMap<String, Object> map = new HashMap<String, Object>();
        XSSFWorkbook wb = new XSSFWorkbook();//创建工作簿
        XSSFSheet sheet = wb.createSheet("用户表");//创建工作表 sheet名称

        sheet.setColumnWidth((short) 0, (short) (35.7 * 150));
        sheet.setColumnWidth((short) 1, (short) (35.7 * 150));
        sheet.setColumnWidth((short) 2, (short) (35.7 * 150));
        sheet.setColumnWidth((short) 3, (short) (35.7 * 100));
        sheet.setColumnWidth((short) 4, (short) (35.7 * 250));

        XSSFRow row = sheet.createRow((int) 0);//产生表格标题行


        // 创建两种单元格格式
        CellStyle cs = wb.createCellStyle();
        CellStyle cs2 = wb.createCellStyle();


        // 创建两种字体
        Font f = wb.createFont();
        Font f2 = wb.createFont();

        // 创建第一种字体样式
        f.setFontHeightInPoints((short) 10);
        f.setColor(IndexedColors.RED.getIndex());
        f.setBoldweight(Font.BOLDWEIGHT_BOLD);


        // 创建第二种字体样式
        f2.setFontHeightInPoints((short) 10);
        f2.setColor(IndexedColors.BLACK.getIndex());
        f2.setBoldweight(Font.BOLDWEIGHT_BOLD);


        // 设置第一种单元格的样式
        cs.setFont(f);
        cs.setBorderLeft(CellStyle.BORDER_THIN);
        cs.setBorderRight(CellStyle.BORDER_THIN);
        cs.setBorderTop(CellStyle.BORDER_THIN);
        cs.setBorderBottom(CellStyle.BORDER_THIN);
        // cs.setDataFormat(df.getFormat("#,##0.0"));

        // 设置第二种单元格的样式
        cs2.setFont(f2);
        cs2.setBorderLeft(CellStyle.BORDER_THIN);
        cs2.setBorderRight(CellStyle.BORDER_THIN);
        cs2.setBorderTop(CellStyle.BORDER_THIN);
        cs2.setBorderBottom(CellStyle.BORDER_THIN);
        // cs2.setDataFormat(df.getFormat("text"));



        XSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

        XSSFCell cell = row.createCell((short) 0);
        cell.setCellValue("编号");
        cell.setCellStyle(style);
        cell.setCellStyle(cs);

        cell = row.createCell((short) 1);
        cell.setCellValue("名称");
        cell.setCellStyle(style);
        cell.setCellStyle(cs);


        cell = row.createCell((short) 2);
        cell.setCellValue("昵称");
        cell.setCellStyle(style);
        cell.setCellStyle(cs);


        cell = row.createCell((short) 3);
        cell.setCellValue("电话");
        cell.setCellStyle(style);
        cell.setCellStyle(cs);

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        List<Users> userList = new ArrayList<Users>();
        userList = userListService.selectAll(keyWord,beforeTime,afterTime,activity);
        for (int i = 0; i < userList.size(); i++)
        {
            row = sheet.createRow((int) i + 1);
            Users stu = (Users) userList.get(i);
            // 第四步，创建单元格，并设置值
            row.createCell((short) 0).setCellValue(stu.getUserId());
            cell.setCellStyle(cs2);
            row.createCell((short) 1).setCellValue(stu.getUserName());
            cell.setCellStyle(cs2);
            row.createCell((short) 2).setCellValue(stu.getNickName());
            cell.setCellStyle(cs2);
            row.createCell((short) 3).setCellValue(stu.getPhone());
            cell.setCellStyle(cs2);
             /* cell = row.createCell((short) 3);
            cell.setCellValue(new SimpleDateFormat("yyyy-mm-dd").format(stu
                    .getBirth()));*/
        }
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try
        {
          //  FileOutputStream font = new FileOutputStream("E:/user.xls");
            wb.write(os);
          //  font.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        byte []content=os.toByteArray();
        Date date=df.parse(df.format(new Date()));
        String filename=date.getTime()+".xls";
        InputStream inputStream=new ByteArrayInputStream(content);
        response.reset();
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-disposition", "attachment; filename="+ new String(filename.getBytes("UTF-8"),"UTF-8"));
        ServletOutputStream out = response.getOutputStream();

        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            bis = new BufferedInputStream(inputStream);
            bos = new BufferedOutputStream(out);

            byte[] buff = new byte[2048];
            int bytesRead;

            // Simple read/write loop.
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
        }catch (final IOException e) {
            throw e;
        } finally {
            if (bis != null)
                bis.close();
            if (bos != null)
                bos.close();
        }
        return null;


    }

    /**
     * json 获取地址
     * @param request
     * @param userId
     * @return
     */
    @RequestMapping(value = "address.action")
    @ResponseBody
    public Object getAddress(HttpServletRequest request,Integer userId) throws Exception{
        try{
            UserResult user= SessionGetPlUteFromId.FromSessionGetPlUteFromId(request);
            ShippingAddressVo shippingAddressVo = new ShippingAddressVo();
            MsRegionsVo msRegionsoVo = new MsRegionsVo();
            msRegionsoVo.setPluteformid(user.getPluteformid());
            shippingAddressVo.setPluteformid(user.getPluteformid());
            shippingAddressVo.setUserId(userId);
            List<ShippingAddressResult> list =userListService.getAddress(shippingAddressVo);
            for (int i = 0; i < list.size(); i++) {
                ShippingAddressResult shippingAddressResult=list.get(i);
                String name="";
                String path=shippingAddressResult.getMsRegions().getPath();
                String [] path2=path.split(",");
                for (int j = 1; j <path2.length ; j++) {
                    String path3=path2[j];
                    Integer path4=Integer.parseInt(path3);
                    msRegionsoVo.setRegionId(path4);
                    MsRegionsResult msRegionsResult=userListService.msRegions(msRegionsoVo);
                    name+=msRegionsResult.getRegionName()+"-";
                }
                String regionName=shippingAddressResult.getMsRegions().getRegionName();
                list.get(i).getMsRegions().setRegionName(name+regionName);
            }
            return JSON.toJSON(list);
        }catch (Exception e){
            throw e;
        }
    }

    /**
     * 获取所有省份
     * @param request
     * @param depth
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "Province.action")
    @ResponseBody
    public Object getProvince(HttpServletRequest request,Integer depth) throws Exception{
        try{
            UserResult user= SessionGetPlUteFromId.FromSessionGetPlUteFromId(request);
            MsRegionsVo msRegionsVo = new MsRegionsVo();
            msRegionsVo.setPluteformid(user.getPluteformid());
            msRegionsVo.setDepth(depth);
            List<ShippingAddressResult> list =userListService.getProvince(msRegionsVo);
            return JSON.toJSON(list);
        }catch (Exception e){
            throw e;
        }
    }

    @RequestMapping(value = "city.action")
    @ResponseBody
    public Object getCity(HttpServletRequest request,Integer parentId) throws Exception{
        try{
            UserResult user= SessionGetPlUteFromId.FromSessionGetPlUteFromId(request);
            MsRegionsVo msRegionsVo = new MsRegionsVo();
            msRegionsVo.setPluteformid(user.getPluteformid());
            msRegionsVo.setParentId(parentId);
            List<ShippingAddressResult> list =userListService.getCity(msRegionsVo);
            return JSON.toJSON(list);
        }catch (Exception e){
            throw e;
        }
    }

    @RequestMapping(value = "area.action")
    @ResponseBody
    public Object getArea(HttpServletRequest request,Integer parentId) throws Exception{
        try{
            UserResult user= SessionGetPlUteFromId.FromSessionGetPlUteFromId(request);
            MsRegionsVo msRegionsVo = new MsRegionsVo();
            msRegionsVo.setPluteformid(user.getPluteformid());
            msRegionsVo.setParentId(parentId);
            List<ShippingAddressResult> list =userListService.getArea(msRegionsVo);
            return JSON.toJSON(list);
        }catch (Exception e){
            throw e;
        }
    }
    /**
     * 获取用户下多少个类型
     * @param
     * @param userId
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/userScore.action")
    public ModelAndView getUserScore(HttpServletRequest request,String text,Integer page,Integer userId,String keyWord,String beforeTime,String afterTime,Integer activity,String  totalScore) throws  Exception
    {
       /* text1是value里面的确保返回是选择状态*/
        //根据名称查询id
        try{
            UserResult user= SessionGetPlUteFromId.FromSessionGetPlUteFromId(request);
            String code="khpf";
            SystemCodeVo systemCodeVo = new SystemCodeVo();
            systemCodeVo.setPluteformid(user.getPluteformid());
            systemCodeVo.setCode(code);
            SystemCodeResult systemCodeResult=userListService.getCodeUserId(systemCodeVo);
            ItemSystemCodeVo itemSystemCodeVo = new ItemSystemCodeVo();
            itemSystemCodeVo.setFid(systemCodeResult.getId());
            itemSystemCodeVo.setUserId(userId);
            List list=userListService.getUserScore(itemSystemCodeVo);
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("list",list);
            map.put("userId",userId);
            map.put("keyWord",keyWord);
            map.put("beforeTime",beforeTime);
            map.put("afterTime",afterTime);
            map.put("activity",activity);
            map.put("page",page);
            map.put("text1",text);
            map.put("listSize",list.size());
            map.put("pid",systemCodeResult.getId());
            map.put("totalScore",totalScore);//暂且不用   涉及到类型删除
            return new ModelAndView("/business/userScore",map);
        }catch (Exception e){
            throw e;
        }
    }

    /**
     * 获取用户的等级
     * @param userId
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/getUserAllScore.action")
    public ModelAndView getUserAllScore(HttpServletRequest request,Integer userId) throws  Exception
    {
        try{
            UserResult user= SessionGetPlUteFromId.FromSessionGetPlUteFromId(request);
            AccountUserVo usersVo = new AccountUserVo();
            usersVo.setPluteformid(user.getPluteformid());
            usersVo.setUserId(userId);
            int total=userListService.getUserAllScore(usersVo);
            Map<String,Object> map=new Hashtable<String, Object>();
            map.put("total",total);
            return new ModelAndView("/business/UserRank",map);
        }catch (Exception e){
            throw e;
        }
    }
    /**
     * 更改用户的分数
     * @param
     * @param
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/updateScore.action")
    public ModelAndView getUpdateScore(HttpServletRequest request,Integer listSize,String text,Integer pageSize,Integer userId,String percentage,String scoreVal,String codeId,String keyWord,String beforeTime,String afterTime,Integer activity,Integer page,Integer pid_01) throws  Exception
    {
        //分为4个逻辑
                //1.当自己表中为空 时 怎没有数据 开始添加数据
                //2.当自己表中数据大于a表时，怎说明a表删除了数据 则自己表中要删除数据 在修改
                //3.当自己表中数据小于a表时，怎说明a表数据增加了 则自己也要增加数据 在修改
                //4.当2个表相同时 怎么说明是更改 操作 进行更改就可以了
      //  percentage 百分数  score 输入的值
        try{
            UserResult user= SessionGetPlUteFromId.FromSessionGetPlUteFromId(request);
            HashMap<String, Object> map = new HashMap<String, Object>();
            Double totalScore = userListService.AddScoreOnSuppOrUser(user.getPluteformid(), listSize, userId, percentage, scoreVal, codeId, pid_01);
            map.put("keyWord",keyWord);
            map.put("beforeTime",beforeTime);
            map.put("afterTime",afterTime);
            map.put("activity",activity);
            map.put("page",page);
            map.put("totalScore",totalScore);
            return pageBeanUser(request,page,pageSize,keyWord,beforeTime,afterTime,activity,totalScore,text);
        }catch (Exception e){
            throw e;
        }
    }
    //0----------------------------------会员等级管理----------------------
    /**
     *查询所有信息
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/UserLevel.action")
    public ModelAndView getUserLevel(HttpServletRequest request,String name) throws  Exception
    {
        try{
            UserResult user= SessionGetPlUteFromId.FromSessionGetPlUteFromId(request);
            AccountUserVo usersVo = new AccountUserVo();
            usersVo.setPluteformid(user.getPluteformid());
            usersVo.setUserName(name);
            List list=userListService.getUserLevel(usersVo);
            Map<String,Object> map=new HashMap<String, Object>();
            map.put("userLevel",list);
            map.put("name",name);
            return new ModelAndView("/business/userLevel",map);
        }catch (Exception e){
            throw e;
        }
    }

    /**
     * 编辑
     * @param
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/updateUserLevel.action")
    public ModelAndView updateUserLevel(HttpServletRequest request,Integer rankId,String name) throws  Exception
    {
        try{
            UserResult user= SessionGetPlUteFromId.FromSessionGetPlUteFromId(request);
            UserRankVo userRankVo = new UserRankVo();
            userRankVo.setPluteformid(user.getPluteformid());
            userRankVo.setRankId(rankId);
            UserRankResult userRankResult=userListService.userRank(userRankVo);
            Map<String,Object> map=new HashMap<String, Object>();
            map.put("UserRank",userRankResult);
            map.put("name",name);
            return new ModelAndView("/business/UserRank",map);
        }catch (Exception e){
            throw e;
        }
    }
    /**
     * 修改 信息
     * @param
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/updateUser.action")
    public ModelAndView updateUser(HttpServletRequest request,String name1,UserRankVo userRankVo) throws  Exception
    {
        try{
            UserResult user= SessionGetPlUteFromId.FromSessionGetPlUteFromId(request);
            userRankVo.setPluteformid(user.getPluteformid());
            userListService.getUpdateUser(userRankVo);
            return getUserLevel(request,name1);
        }catch (Exception e){
            throw e;
        }
    }

    /**
     * 删除用户等级
     * @param name
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/deleteUser.action")
    public ModelAndView deleteUser(HttpServletRequest request,String name,UserRankVo userRankVo) throws  Exception
    {
        try{
            UserResult user= SessionGetPlUteFromId.FromSessionGetPlUteFromId(request);
            userRankVo.setPluteformid(user.getPluteformid());
            userListService.deleteUserLevel(userRankVo);
            return getUserLevel(request,name);
        }catch (Exception e){
            throw e;
        }
    }

    /**
     * 点击添加跳转页面 客户等级添加
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/addUser.action")
    public ModelAndView addUser(HttpServletRequest request,String name) throws  Exception
    {
        try{
            Map<String,Object> map=new HashMap<String, Object>();
            map.put("name",name);
            return new ModelAndView("/business/addUserLevel",map);
        }catch (Exception e){
            throw e;
        }
    }

    /**
     * 添加用户的收货地址跳转页面
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/userAddress.action")
    public ModelAndView addUserAddress(HttpServletRequest request,Integer id,String name,Integer page,String keyWord,String beforeTime,String afterTime,Integer activity,String text) throws  Exception
    {
        try{
            Map<String,Object> map=new HashMap<String, Object>();
            map.put("userId",id);
            map.put("name",name);
            map.put("page",page);
            map.put("keyWord",keyWord);
            map.put("beforeTime",beforeTime);
            map.put("afterTime",afterTime);
            map.put("activity",activity);
            map.put("text",text);
            return new ModelAndView("/business/addUserAddress",map);
        }catch (Exception e){
            throw e;
        }
    }

    /**
     * 添加用户的收货地址
     * @param shippingAddressVo
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/userShipAddress.action")
    public ModelAndView addUserShipAddress(HttpServletRequest request,ShippingAddressVo shippingAddressVo,Integer page,String keyWord,String beforeTime,String afterTime,Integer activity,String text) throws  Exception
    {
        try{
            Map<String,Object> map=new HashMap<String, Object>();
            map.put("ship",shippingAddressVo);
            UserResult user= SessionGetPlUteFromId.FromSessionGetPlUteFromId(request);
            shippingAddressVo.setPluteformid(user.getPluteformid());
            int result= userListService.insertShippingAddress(shippingAddressVo);
            if(result==1){
                return pageBeanUser(request,page,null,keyWord,beforeTime,afterTime,activity,null,text);
            }
            return null;
        }catch (Exception e){
            throw e;
        }
    }
    /**
     * 添加用户等级
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/addUserLevel.action")
    public ModelAndView addUserLevel(HttpServletRequest request,UserRankVo userRankVo) throws  Exception
    {
        try{
            UserResult user= SessionGetPlUteFromId.FromSessionGetPlUteFromId(request);
            userRankVo.setPluteformid(user.getPluteformid());
            userListService.addUserLevel(userRankVo);
            return  getUserLevel(request,null);
        }catch (Exception e){
            throw e;
        }
    }
    //会员卡管理
    /**
     * 查询所有的用户卡号信息
     * @param userCard
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/userCard.action")
    public ModelAndView userCard(HttpServletRequest request,String userCard) throws  Exception
    {
        try{
            List list= userListService.getUserCard(userCard);
            Map<String,Object> map=new HashMap<String, Object>();
            map.put("userCardList",list);
            return  new ModelAndView("/business/userCard",map);
        }catch (Exception e){
            throw e;
        }
    }

    //***********************************接口调用方法
    @RequestMapping(value="/insertUsers.action")
    public boolean insertUsers(HttpServletRequest request,Users users) throws  Exception
    {
        try{
            int a= userListService.insertUsers(users);
            if (a!=1)
            {
                return false;
            }else {
                return true;
            }
        }catch (Exception e){
            throw e;
        }
    }

    /**
     * 更爱用户的余额
     * @param usersExp
     * @param balanceDetails
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/updateUsers.action")
    public boolean updateUsers(HttpServletRequest request,UsersExp usersExp,BalanceDetails balanceDetails,PointsDetail pointsDetail,RankDetail  rankDetail) throws  Exception
    {
        try{
            usersExp.setUserId(1);
            usersExp.setBalance(20.00);
            userListService.web_updateCouponInfo("ZMYHSHZH1215165841",2);
            pointsDetail.setUserId(1);
            pointsDetail.setScore(12);
            pointsDetail.setExtData("2012");
            pointsDetail.setCurrentPoints(12);
            pointsDetail.setDescription("哈哈");
            pointsDetail.setCreatedDate(new Date());
            pointsDetail.setType(1);
            userListService.insertPoints(usersExp, pointsDetail);
            return false;
        }catch (Exception e){
            throw e;
        }
    }

    /**
     * 查询微信用户
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value ="/selWeChatUserByAppId.action")
    @ResponseBody
    public Object selWeChatUserByAppId(HttpServletRequest request)throws  Exception{
        try{
            StringBuffer sb = new StringBuffer() ;
            InputStream is = request.getInputStream();
            InputStreamReader isr = new InputStreamReader(is,"UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String s = "" ;
            while((s=br.readLine())!=null){
                sb.append(s) ;
            }
            br.close();
            is.close();
            String str =sb.toString();
            isr.close();
            AccountUserVo accountUserVo=JSONObject.parseObject(str, AccountUserVo.class);
            Users  users = userListService.selectUserByWeChat(accountUserVo);
            return users;
        }catch (Exception e){
            throw e;
        }
    }
    /**
     * 微信查询收货地址
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value ="/selShipPingAddressesById.action")
    @ResponseBody
    public Object selShipPingAddressesById(HttpServletRequest request)throws  Exception{
        try{
            StringBuffer sb = new StringBuffer() ;
            InputStream is = request.getInputStream();
            InputStreamReader isr = new InputStreamReader(is,"UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String s = "" ;
            while((s=br.readLine())!=null){
                sb.append(s) ;
            }
            br.close();
            is.close();
            String str =sb.toString();
            isr.close();
            AccountUserVo accountUserVo=JSONObject.parseObject(str, AccountUserVo.class);
            List<ShippingAddress> shippingAddresses = userListService.selectShopAddressByUserId(accountUserVo);
            return shippingAddresses;
        }catch (Exception e){
            throw e;
        }
    }
    /**
     * 微信添加收货地址
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value ="/AddShipPingAddressesById.action")
    @ResponseBody
    public Object AddShipPingAddressesById(HttpServletRequest request)throws  Exception{
        try{
            StringBuffer sb = new StringBuffer() ;
            InputStream is = request.getInputStream();
            InputStreamReader isr = new InputStreamReader(is,"UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String s = "" ;
            while((s=br.readLine())!=null){
                sb.append(s) ;
            }
            br.close();
            is.close();
            String str =sb.toString();
            isr.close();
            ShippingAddressVo shippingAddressVo=JSONObject.parseObject(str, ShippingAddressVo.class);
            int i  = userListService.addShopAddress(shippingAddressVo);
            return i;
        }catch (Exception e){
            throw e;
        }
    }

    /**
     * 微信删除收货地址
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value ="/removeShipPingAddressesById.action")
    @ResponseBody
    public Object removeShipPingAddressesById(HttpServletRequest request)throws  Exception{
        try{
            StringBuffer sb = new StringBuffer() ;
            InputStream is = request.getInputStream();
            InputStreamReader isr = new InputStreamReader(is,"UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String s = "" ;
            while((s=br.readLine())!=null){
                sb.append(s) ;
            }
            br.close();
            is.close();
            String str =sb.toString();
            isr.close();
            ShippingAddressVo shippingAddressVo=JSONObject.parseObject(str, ShippingAddressVo.class);
            int i  = userListService.removeShopAddress(shippingAddressVo);
            return i;
        }catch (Exception e){
            throw e;
        }
    }
    /**
     * 添加收货地址三级联动
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value ="/shipPingInRegion.action")
    @ResponseBody
    public Object addShipPingInRegion(HttpServletRequest request)throws  Exception{
        try{
            List<RegionInfo> regionInfo = orderInfoService.getRegionInfo();
            return regionInfo;
        }catch (Exception e){
            throw e;
        }
    }
    /**
     * 收货地址回显
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value ="/getRegionInfoOrPath.action")
    @ResponseBody
    public Object getRegionInfo(HttpServletRequest request)throws  Exception{
        try{
            StringBuffer sb = new StringBuffer() ;
            InputStream is = request.getInputStream();
            InputStreamReader isr = new InputStreamReader(is,"UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String s = "" ;
            while((s=br.readLine())!=null){
                sb.append(s) ;
            }
            br.close();
            is.close();
            String str =sb.toString();
            isr.close();
            RegionInfoVo regionInfoVo=JSONObject.parseObject(str, RegionInfoVo.class);
            RegionInfo regionInfo  = orderInfoService.selRegionInfo(regionInfoVo);
            return regionInfo;
        }catch (Exception e){
            throw e;
        }
    }
    /**
     * 收货地址详细回显
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value ="/backDetailedRegionInfo.action")
    @ResponseBody
    public Object backDetailedRegionInfo(HttpServletRequest request)throws  Exception{
        try{
            StringBuffer sb = new StringBuffer() ;
            InputStream is = request.getInputStream();
            InputStreamReader isr = new InputStreamReader(is,"UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String s = "" ;
            while((s=br.readLine())!=null){
                sb.append(s) ;
            }
            br.close();
            is.close();
            String str =sb.toString();
            isr.close();
            ShippingAddressVo shippingAddressVo=JSONObject.parseObject(str, ShippingAddressVo.class);
            ShippingAddressResult shippingAddressResult  = userListService.selectAddressOne(shippingAddressVo);
            return shippingAddressResult;
        }catch (Exception e){
            throw e;
        }
    }
    /**
     * 收货地址费率
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value ="/getRegionInfoRate.action")
    @ResponseBody
    public Object getRegionInfoRate(HttpServletRequest request)throws  Exception{
        try{
            StringBuffer sb = new StringBuffer() ;
            InputStream is = request.getInputStream();
            InputStreamReader isr = new InputStreamReader(is,"UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String s = "" ;
            while((s=br.readLine())!=null){
                sb.append(s) ;
            }
            br.close();
            is.close();
            String str =sb.toString();
            isr.close();
            RegionInfoVo regionInfoVo=JSONObject.parseObject(str, RegionInfoVo.class);
            RegionInfoResult regionInfoResult  = userListService.selectRegionById(regionInfoVo);
            return regionInfoResult;
        }catch (Exception e){
            throw e;
        }
    }

    /**
     * 微信修改收货地址
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value ="/updateShipPingAddressesById.action")
    @ResponseBody
    public Object updateShipPingAddressesById(HttpServletRequest request)throws  Exception{
        try{
            StringBuffer sb = new StringBuffer() ;
            InputStream is = request.getInputStream();
            InputStreamReader isr = new InputStreamReader(is,"UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String s = "" ;
            while((s=br.readLine())!=null){
                sb.append(s) ;
            }
            br.close();
            is.close();
            String str =sb.toString();
            isr.close();
            ShippingAddressVo shippingAddressVo=JSONObject.parseObject(str, ShippingAddressVo.class);int i  = userListService.updateShippingAddress(shippingAddressVo);
            return i;
        }catch (Exception e){
            throw e;
        }
    }
    /**
     * 设置默认收货地址
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value ="/editShipPingAddresses.action")
    @ResponseBody
    public Object editShipPingAddresses(HttpServletRequest request)throws  Exception{
        try{
            StringBuffer sb = new StringBuffer() ;
            InputStream is = request.getInputStream();
            InputStreamReader isr = new InputStreamReader(is,"UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String s = "" ;
            while((s=br.readLine())!=null){
                sb.append(s) ;
            }
            br.close();
            is.close();
            String str =sb.toString();
            isr.close();
            ShippingAddressVo shippingAddressVo=JSONObject.parseObject(str, ShippingAddressVo.class);
            userListService.updateAllShopAddressState(shippingAddressVo);
            int i = userListService.updateShopAddressState(shippingAddressVo);
            return i;
        }catch (Exception e){
            throw e;
        }
    }
    /**
     * 获得地址默认
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value ="/selectAddressState.action")
    @ResponseBody
    public Object selectAddressState(HttpServletRequest request)throws  Exception{
        try{
            StringBuffer sb = new StringBuffer() ;
            InputStream is = request.getInputStream();
            InputStreamReader isr = new InputStreamReader(is,"UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String s = "" ;
            while((s=br.readLine())!=null){
                sb.append(s) ;
            }
            br.close();
            is.close();
            String str =sb.toString();
            isr.close();
            ShippingAddressVo shippingAddressVo=JSONObject.parseObject(str, ShippingAddressVo.class);
            ShippingAddressResult shippingAddress = userListService.selectAddressState(shippingAddressVo);
            return shippingAddress;
        }catch (Exception e){
            throw e;
        }
    }

    /**
     * //根据AppId 、openId 获得用户
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value ="/selUsersByAppIdAndOpenId.action")
    @ResponseBody
    public Object selUsersByAppIdAndOpenId(HttpServletRequest request)throws  Exception {
        try {
            StringBuffer sb = new StringBuffer();
            InputStream is = request.getInputStream();
            InputStreamReader isr = new InputStreamReader(is, "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String s = "";
            while ((s = br.readLine()) != null) {
                sb.append(s);
            }
            br.close();
            is.close();
            String str = sb.toString();
            isr.close();
            ObjectMapper om = new ObjectMapper();
            AccountUserVo accountUserVo = om.readValue(str, AccountUserVo.class);
           //AccountUserVo accountUserVo = JSONObject.parseObject(str, AccountUserVo.class);
            AccountUserResult accountUserResult = userListService.selUsersByAppIdAndOpenId(accountUserVo);
            return accountUserResult;
        } catch (Exception e) {
            throw e;
        }
    }
    /**
     * 交易记录查询  分页
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value ="/selectTradingRecordByUserId.action")
    @ResponseBody
    public Object selectTradingRecordByUserId(HttpServletRequest request)throws  Exception{
        try{
            StringBuffer sb = new StringBuffer() ;
            InputStream is = request.getInputStream();
            InputStreamReader isr = new InputStreamReader(is,"UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String s = "" ;
            while((s=br.readLine())!=null){
                sb.append(s) ;
            }
            br.close();
            is.close();
            String str =sb.toString();
            isr.close();
            ObjectMapper om = new ObjectMapper();
           // Page page= om.readValue(str, Page.class);
            Page page=JSONObject.parseObject(str, Page.class);
            List<TradingRecordResult> tradingRecordResults = userListService.selectTradingRecordByUserId(page);
            return tradingRecordResults;
        }catch (Exception e){
            throw e;
        }
    }
    /**
     *查询没豆  余额
     */
    @RequestMapping(value = "/selectBalanceAndPointsByUserId.action")
    @ResponseBody
    public Object selectBalanceAndPointsByUserId(HttpServletRequest request)throws Exception{
        try {
            StringBuffer sb = new StringBuffer();
            InputStream inputStream = request.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String str= "";
            while ((str=bufferedReader.readLine())!=null){
                sb.append(str);
            }
            inputStream.close();
            bufferedReader.close();
            String accountUser = sb.toString();
            inputStreamReader.close();
            UsersExpVo usersExpVo = JSONObject.parseObject(accountUser, UsersExpVo.class);
            UsersExpResult usersExpResult = userListService.selectBalanceAndPointsByUserId(usersExpVo);
            return usersExpResult;
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 增加余额交易记录
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/insertTradingRecord.action")
    @ResponseBody
    public Object insertTradingRecord(HttpServletRequest request)throws  Exception{
        try{
            StringBuffer sb = new StringBuffer();
            InputStream inputStream = request.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String str= "";
            while ((str=bufferedReader.readLine())!=null){
                sb.append(str);
            }
            inputStream.close();
            bufferedReader.close();
            String Trading = sb.toString();
            inputStreamReader.close();
            TradingRecordVo tradingRecordVo=JSONObject.parseObject(Trading, TradingRecordVo.class);
            return  userListService.insertTrading(tradingRecordVo);
        }catch (Exception e){
            throw  e;
        }
    }

    /**
     * 修改微信余额(操作b2c_accounts_usersexp,修改account_trading_record交易记录表)
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/updatePointsWX.action")
    @ResponseBody
    public Object updateBalanceWX(HttpServletRequest request)throws Exception{
        try{
            StringBuffer sb = new StringBuffer();
            InputStream inputStream = request.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String str= "";
            while ((str=bufferedReader.readLine())!=null){
                sb.append(str);
            }
            inputStream.close();
            bufferedReader.close();
            String Trading = sb.toString();
            inputStreamReader.close();
            JSONObject obj = JSONObject.parseObject(Trading);
            String orderCode=obj.getString("orderCode");
            String plateFormid=obj.getString("pluteformid");
            String openId=obj.getString("openId");
            String balance=obj.getString("balance");
            Integer remark=obj.getInteger("remark");
            Integer paymentTypeId=obj.getInteger("paymentTypeId"); //2代表全额 修改订单状态为1  4代表定金 修改订单状态为2
            Integer successState=obj.getInteger("successState");   //0银联没有返回成功标志   1充值成功    2消费成功(提现)  3(余额扣钱)
            //首先根据OpenId和sppid查询user的Id
            AccountUserVo accountUserVo=new AccountUserVo();
            accountUserVo.setOpenId(openId);
            accountUserVo.setPluteformid(plateFormid);
            AccountUserResult accountUserResult=userListService.selUsersByAppIdAndOpenId(accountUserVo);
            //消费记录表
            TradingRecordVo tradingRecordVo=new TradingRecordVo();
            tradingRecordVo.setOpenId(openId);
            tradingRecordVo.setPluteformid(plateFormid);
            tradingRecordVo.setBalance(Double.parseDouble(balance));
            tradingRecordVo.setOrderCode(orderCode);
            tradingRecordVo.setUserId(accountUserResult.getUserId());
            tradingRecordVo.setSuccessState(successState);
            tradingRecordVo.setRemark(remark);
            if (paymentTypeId!=null) {
                tradingRecordVo.setPaymentTypeId(paymentTypeId);
            }
            Integer ii=userListService.updateSuccessState(tradingRecordVo);
            return ii;
        }catch (Exception e){
            throw  e;
        }
    }
    /**
     * 查询单个账户的个人信息-移动端
     */
    @RequestMapping(value = "/getAccountsUserApp.action")
    @ResponseBody
    public Object getAccountsUserApp(HttpServletRequest request) throws Exception{
        try {

            StringBuffer sb = new StringBuffer();
            InputStream is = request.getInputStream();
            InputStreamReader isr = new InputStreamReader(is,"UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String s = "" ;
            while((s=br.readLine())!=null){
                sb.append(s) ;
            }
            br.close();
            is.close();
            String user =sb.toString();
            isr.close();


            JSONObject object = JSONObject.parseObject(user);
            String userId = object.getString("userId");
            Users users = userListService.getAccountsUserApp(userId);

            int favoriteNum = productInfoService.getFavoriteInfoCount(userId);

            WeChat_cart cart= JSONObject.parseObject(user, WeChat_cart.class);
            int cartNum = cartService.selectAllCartNum(cart);

            JSONObject obj=new JSONObject();
            obj.put("users",users);
            obj.put("cartNum",cartNum);
            obj.put("favoriteNum",favoriteNum);

            return obj;
        }catch (Exception e){
            return e;
        }
    }
    /**
     * 用户开启店铺预先修改用户的状态
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/updateUserType.action")
    @ResponseBody
    public Object updateUserType(HttpServletRequest request)throws Exception{
        try{
            StringBuffer sb = new StringBuffer();
            InputStream inputStream = request.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String str= "";
            while ((str=bufferedReader.readLine())!=null){
                sb.append(str);
            }
            inputStream.close();
            bufferedReader.close();
            String users = sb.toString();
            inputStreamReader.close();
            ObjectMapper om=new ObjectMapper();
            AccountUserVo accountUserVo=om.readValue(users, AccountUserVo.class);
            //首先根据平台ID和openId修改商城客户表客户状态为ss(店铺的意思)
            userListService.updateUserType(accountUserVo);
            User u=new User();
            u.setOpenId(accountUserVo.getOpenId());
            u.setParentPluteformId(accountUserVo.getPluteformid());
            //根据平台Id和openId去查询用户表
            User uu=userService.selectWX(u);
            UserVo userVo=new UserVo();
            userVo.setId(uu.getId());
            userVo.setRealName(accountUserVo.getPhone());
            //给用户表也是开店铺的人增加手机号
            userService.addPhone(userVo);
            User uuu=userService.selectWX(u);
            uuu.setUrl(env.getProperty("url.server") + "/privileges/shopToLoginOne.action");
            return uuu;
        }catch (Exception e){
            throw  e;
        }
    }

    /**
     * 微信注册店铺的人
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/insertUserPartShop.action")
    @ResponseBody
    public Object insertUserPartShop (HttpServletRequest request)throws  Exception{
        try{
            StringBuffer sb = new StringBuffer() ;
            InputStream is = request.getInputStream();
            InputStreamReader isr = new InputStreamReader(is,"UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String s = "" ;
            while((s=br.readLine())!=null){
                sb.append(s) ;
            }
            br.close();
            is.close();
            String userWX =sb.toString();
            isr.close();
            User user= JSONObject.parseObject(userWX, User.class);
            Integer ii= userService.insertUserPartShop(user);
            return ii;
        }catch (Exception e){
            throw e;
        }
    }

    /**
     * 完善店铺下用户的资料
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/perfectUser.action")
    @ResponseBody
    public  Object perfectUser(HttpServletRequest request)throws  Exception{
        try{
            StringBuffer sb = new StringBuffer() ;
            InputStream is = request.getInputStream();
            InputStreamReader isr = new InputStreamReader(is,"UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String s = "" ;
            while((s=br.readLine())!=null){
                sb.append(s) ;
            }
            br.close();
            is.close();
            String userWX =sb.toString();
            isr.close();
            AccountUserVo accountUserVo= JSONObject.parseObject(userWX, AccountUserVo.class);
            Integer i=userListService.perfectAccountUser(accountUserVo);
            return  i;
        }catch (Exception e){
            throw  e;
        }
    }

    /**
     * 完善店铺下的用户之前需要判断NickName是否为空
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "judgeAccountUserByNickName.action")
    @ResponseBody
    public  Object judgeAccountUserByNickName(HttpServletRequest request)throws  Exception{
        try{
            StringBuffer sb = new StringBuffer() ;
            InputStream is = request.getInputStream();
            InputStreamReader isr = new InputStreamReader(is,"UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String s = "" ;
            while((s=br.readLine())!=null){
                sb.append(s) ;
            }
            br.close();
            is.close();
            String userWX =sb.toString();
            isr.close();
            AccountUserVo accountUserVo= JSONObject.parseObject(userWX, AccountUserVo.class);
            AccountUserResult userVo=userListService.selUsersByAppIdAndOpenId(accountUserVo);
            String NickName=userVo.getNickName();
            if (NickName==null||NickName.equals("")){
                return  0;   //需要完善店铺下的用户
            }else {
                return 1;    //不需要完善店铺下的用户信息
            }
        }catch (Exception e){
            throw  e;
        }
    }

    /**
     * 增加account_trading_record交易记录表
     * @param request
     * @return
     */
    @RequestMapping(value = "insertTrading.action")
    @ResponseBody
    public Integer insertTrading(HttpServletRequest request){
        try{
            StringBuffer sb = new StringBuffer();
            InputStream inputStream = request.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String str= "";
            while ((str=bufferedReader.readLine())!=null){
                sb.append(str);
            }
            inputStream.close();
            bufferedReader.close();
            String Trading = sb.toString();
            inputStreamReader.close();
            JSONObject obj = JSONObject.parseObject(Trading);
            String orderCode=obj.getString("orderCode");
            String plateFormid=obj.getString("pluteformid");
            String openId=obj.getString("openId");
            String balance=obj.getString("balance");
            Integer remark=obj.getInteger("remark");
            Integer paymentTypeId=obj.getInteger("paymentTypeId"); //2代表全额 修改订单状态为1  4代表定金 修改订单状态为2
            Integer successState=obj.getInteger("successState");   //0银联没有返回成功标志   1充值成功    2消费成功(提现)
            //首先根据OpenId和sppid查询user的Id
            AccountUserVo accountUserVo=new AccountUserVo();
            accountUserVo.setOpenId(openId);
            accountUserVo.setPluteformid(plateFormid);
            AccountUserResult accountUserResult=userListService.selUsersByAppIdAndOpenId(accountUserVo);
            //消费记录表
            TradingRecordVo tradingRecordVo=new TradingRecordVo();
            tradingRecordVo.setOpenId(openId);
                tradingRecordVo.setPluteformid(plateFormid);
            tradingRecordVo.setBalance(Double.parseDouble(balance));
            tradingRecordVo.setOrderCode(orderCode);
            tradingRecordVo.setUserId(accountUserResult.getUserId());
            tradingRecordVo.setRemark(remark);//0代表的是充值 1代表的是消费   2代表的提现
            tradingRecordVo.setSuccessState(successState);//0银联没有返回成功标志   1充值成功    2消费成功(提现)
            TradingRecord tradingRecord= userListService.selTradingRecordByOrderCode(tradingRecordVo);
            if(tradingRecord==null) {
                Integer ii = userListService.insertTrading(tradingRecordVo);
                return ii;
            }else {
                return 0;
            }
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 根据orderCode和balance查询平台ID和openID
     * 帮助微信端获取session
     * @param request
     * @return
     */
    @RequestMapping(value = "selTradingRecordByOrderCode.action")
    @ResponseBody
    public Object selTradingRecordByOrderCode(HttpServletRequest request){
        try{
            StringBuffer sb = new StringBuffer();
            InputStream inputStream = request.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String str= "";
            while ((str=bufferedReader.readLine())!=null){
                sb.append(str);
            }
            inputStream.close();
            bufferedReader.close();
            String Trading = sb.toString();
            inputStreamReader.close();
            JSONObject obj = JSONObject.parseObject(Trading);
            String orderCode=obj.getString("orderCode");
            String balance=obj.getString("balance");
            TradingRecordVo tradingRecordVo=new TradingRecordVo();
            if(balance!=null) {
                tradingRecordVo.setBalance(Double.parseDouble(balance));
            }
            tradingRecordVo.setOrderCode(orderCode);
            OrderByTip orderInfo=new OrderByTip();
            orderInfo.setOrderCode(orderCode);
            OrderInfo info=new OrderInfo();
            //如果订单号是以yy结尾,查询预约表
            info.setOrderCode(orderCode);
            if (orderCode.endsWith("yy")){
               OrderInfo o= appointmentOrderService.selectOneAppointment(info);
                return  o;
            }else  if(orderCode.endsWith("cz")){
                //如果订单号是以cz结尾，查询交易记录表
                TradingRecord tradingRecord=userListService.selTradingRecordByOrderCode(tradingRecordVo);
                return tradingRecord;
            }else {
                //如果订单号是以普通结尾，查询订单表
                OrderInfo or=orderInfoService.selectOneOrderByOrderCode(info);
                return  or;
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 通过父平台id和openId来查询用户
     */
    @RequestMapping(value = "/selUsersBypId.action")
    @ResponseBody
    public Object selUserBypId(HttpServletRequest request) throws Exception{
        try{
            StringBuffer sb = new StringBuffer() ;
            InputStream is = request.getInputStream();
            InputStreamReader isr = new InputStreamReader(is,"UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String s = "" ;
            while((s=br.readLine())!=null){
                sb.append(s) ;
            }
            br.close();
            is.close();
            String user =sb.toString();
            isr.close();

            JSONObject obj = JSONObject.parseObject(user);
            String openId = obj.getString("openId");
            String parentpluteformid = obj.getString("Parentpluteformid");
            MallConfig mallConfig = new MallConfig();
            mallConfig.setOpenId(openId);
            mallConfig.setParentpId(parentpluteformid);
            User users = userService.selUserBypId(mallConfig);
            return users;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 跳转用户交易额界面
     * @return
     */
    @RequestMapping(value = "/toUsersShow.action")
    public String toUserTurnover(){
        return "/business/usersShow";
    }
	

	@RequestMapping(value = "/getAllUsers.action")
    @ResponseBody
    public EasyUIListResult<Users> getAllUsers(HttpServletRequest request,PageableUsers users)throws Exception {
        try {
            return userListService.selAllUser(users);
        } catch (Exception e) {
            e.printStackTrace();
            return new EasyUIListResult<Users>();
        }
    }

    @RequestMapping(value = "/userTurnover.action")
    public String userTurnover(HttpServletRequest request,AccountUserVo usersVo,Model model) throws Exception{
        AccountUserResult users = userListService.getUserIdSelect(usersVo);
        OrderInfo orderInfo = new OrderInfo();
        List list = orderInfoService.selUserOrderTotal(usersVo, orderInfo);
        model.addAttribute("userName",users.getUserName());
        model.addAttribute("list",list);
        return "/business/userTurnover";
    }
}