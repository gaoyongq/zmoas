package com.zm.mall.service.business.impl;

import com.zm.mall.client.Page;
import com.zm.mall.client.result.business.accounts.*;
import com.zm.mall.client.result.business.product.RegionInfoResult;
import com.zm.mall.client.service.business.accountsUsers.UserListService;
import com.zm.mall.client.vo.business.accounts.*;
import com.zm.mall.client.vo.business.product.RegionInfoVo;
import com.zm.mall.dao.business.accountsUsers.ShippingAddressDao;
import com.zm.mall.dao.business.accountsUsers.TradingRecordDao;
import com.zm.mall.dao.business.accountsUsers.UserListDao;
import com.zm.mall.dao.business.orders.OrderInfoDao;
import com.zm.mall.dao.business.product.RegionInfoDao;
import com.zm.mall.dao.system.UserDao;
import com.zm.mall.domain.business.accountsUsers.*;
import com.zm.mall.domain.business.orders.OrderInfo;
import com.zm.mall.domain.business.product.RegionInfo;
import com.zm.mall.domain.business.product.SupplierInfo;
import com.zm.mall.domain.system.User;
import com.zm.mall.domain.system.coupon.EasyUIListResult;
import com.zm.mall.service.SpaceBeanCopy;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.jws.WebService;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/11/11.
 */


@Service("userListService")
@WebService(endpointInterface = "com.zm.mall.client.service.business.accountsUsers.UserListService",serviceName = "UserListService")
public class UserListServiceImpl  implements UserListService  {

    private static Log log = LogFactory.getLog(UserListService.class);

    @Autowired
    private  UserListDao userListDao;
    @Autowired
    private ShippingAddressDao shippingAddressDao;
    @Autowired
    private RegionInfoDao regionInfoDao;
    @Autowired
    private TradingRecordDao tradingRecordDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private OrderInfoDao orderInfoDao;
    public UserListDao getUserListDao() {
        return userListDao;
    }

    public void setUserListDao(UserListDao userListDao) {
        this.userListDao = userListDao;
    }

    public static Log getLog() {
        return log;
    }

    public static void setLog(Log log) {
        UserListServiceImpl.log = log;
    }

    @Override
    public List<Users> selectAll(String keyWord,String beforeTime,String afterTime,Integer activity) {
        return userListDao.selectAll(keyWord,beforeTime,afterTime,activity);
    }
    @Override
    public List<AccountUserResult> getSelectActiv(AccountUserVo usersVo) {
        Users users = SpaceBeanCopy.accounUserVoToAccountUser(usersVo);
        List<Users> selectActiv = userListDao.getSelectActiv(users);
        List<AccountUserResult> accountUserResults = new ArrayList<AccountUserResult>();
        for (int i=0;i<selectActiv.size();i++){
            accountUserResults.add(SpaceBeanCopy.AccountToUserResult(selectActiv.get(i)));
        }
        return accountUserResults ;
    }

    /*  @Override
      public List<Users> updateBalance(String balance) {
          return userListDao.updateBalance(balance);
      }
  */  //根据Id查询 详细 信息
    @Override
    public AccountUserResult getUserIdSelect(AccountUserVo usersVo) {
        Users users = SpaceBeanCopy.accounUserVoToAccountUser(usersVo);
        return SpaceBeanCopy.AccountToUserResult(userListDao.getUserIdSelect(users));
    }

    @Override
    public List<AccountUserResult> getKeyword(AccountUserVo usersVo) {
        Users users = SpaceBeanCopy.accounUserVoToAccountUser(usersVo);
        Page page = new Page();
        page.setUsers(users);
        page.setPluteformid(usersVo.getPluteformid());
        List<Users> userses = userListDao.getKeyword(page);
        List<AccountUserResult> accountUserResults = new ArrayList<AccountUserResult>();
        for (int i=0;i<userses.size();i++){
            accountUserResults.add(SpaceBeanCopy.AccountToUserResult(userses.get(i)));
        }
        return accountUserResults;
    }

    @Override
    public AccountUserResult getTopUp(AccountUserVo usersVo) {
        Users users = SpaceBeanCopy.accounUserVoToAccountUser(usersVo);
        return SpaceBeanCopy.AccountToUserResult(userListDao.getTopUp(users));
    }

    @Override
    public List<AccountUserResult> getTimeSearch(AccountUserVo usersVo) {
        Users users = SpaceBeanCopy.accounUserVoToAccountUser(usersVo);
        List<Users> list = userListDao.getTimeSearch(users);
        List<AccountUserResult> accountUserResults = new ArrayList<AccountUserResult>();
        for (int i=0;i<list.size();i++){
            accountUserResults.add(SpaceBeanCopy.AccountToUserResult(list.get(i)));
        }
        return accountUserResults ;
    }

    @Override
    public int updateRBP(UsersExpVo usersExpVo) {
        UsersExp usersExp = SpaceBeanCopy.usersExpVoToUsersExp(usersExpVo);
        return userListDao.updateRBP(usersExp);
    }

    @Override
    public List<PointsDetailResult> getScoreDetail(PointsDetailVo pointsDetailVo) {
        PointsDetail pointsDetail = SpaceBeanCopy.pointsDetailVoToPointsDetail(pointsDetailVo);
        List<PointsDetail> scoreDetail = userListDao.getScoreDetail(pointsDetail);
        List<PointsDetailResult> pointsDetailResults = new ArrayList<PointsDetailResult>();
        for(int i=0;i<scoreDetail.size();i++){
            pointsDetailResults.add(SpaceBeanCopy.pointsDetailToPointsDetailResult(scoreDetail.get(i)));
        }
        return pointsDetailResults;
    }

    @Override
    public List<PointsDetailResult> getTypeSearch(PointsDetailVo pointsDetailVo)throws Exception{
        PointsDetail pointsDetail = SpaceBeanCopy.pointsDetailVoToPointsDetail(pointsDetailVo);
        List<PointsDetail> scoreDetail = userListDao.getTypeSearch(pointsDetail);
        List<PointsDetailResult> pointsDetailResults = new ArrayList<PointsDetailResult>();
        for(int i=0;i<scoreDetail.size();i++){
            pointsDetailResults.add(SpaceBeanCopy.pointsDetailToPointsDetailResult(scoreDetail.get(i)));
        }
        return pointsDetailResults;
    }

    @Override
    public int updateFreeze(AccountUserVo usersVo) throws Exception {
        Users users = SpaceBeanCopy.accounUserVoToAccountUser(usersVo);
        return userListDao.updateFreeze(users);
    }

    @Override
    public int activationUser(AccountUserVo usersVo) throws Exception {
        Users users = SpaceBeanCopy.accounUserVoToAccountUser(usersVo);
        return userListDao.activationUser(users);
    }

    @Override
    @Transactional
    public Page getFenYe(Page page,AccountUserVo usersVo) throws Exception{
        //分页开始行数
        page.setBeginNumber((page.getCurrentPage()-1)*page.getPageSize());
        page.setUsers(SpaceBeanCopy.accounUserVoToAccountUser(usersVo));
        List<Users> usersList = userListDao.getFenYe(page);
        Integer pageCount = userListDao.getCount(page);
        page.setUsersList(usersList);
        page.setTotalPages(pageCount % page.getPageSize() == 0 ? pageCount / page.getPageSize() : pageCount / page.getPageSize() + 1);
        page.setTotalCount(pageCount);
        return page;
    }

    @Override
    public List<ShippingAddressResult> getAddress(ShippingAddressVo shippingAddressVo) throws Exception {
        ShippingAddress shippingAddress = SpaceBeanCopy.shippingAddressVoToshippingAddress(shippingAddressVo);
        List<ShippingAddress> address = userListDao.getAddress(shippingAddress);
        List<ShippingAddressResult>  shippingAddressResults= new ArrayList<ShippingAddressResult>();
        for (int i=0;i<address.size();i++){
            shippingAddressResults.add(SpaceBeanCopy.shippingAddressToshippingAddressResult(address.get(i)));
        }
        return shippingAddressResults;
    }

    @Override
    public List getUserScore(ItemSystemCodeVo itemSystemCodeVo) throws Exception {
        ItemSystemCode itemSystemCode = SpaceBeanCopy.userItemSystemCodeVoToItemSystemCode(itemSystemCodeVo);
        return userListDao.getUserScore(itemSystemCode);
    }

    @Override
    public Integer updateScore(UserScore userScoreVo) throws Exception {
        return userListDao.updateScore(userScoreVo);
    }

    @Override
    public Integer insertScore(UserScore userScoreVo) throws Exception {
        return userListDao.insertScore(userScoreVo);
    }

    @Override
    public List getUserList(UserScore userScoreVo) throws Exception {
        return userListDao.getUserList(userScoreVo);
    }

    @Override
    public MsRegionsResult msRegions( MsRegionsVo msRegionsVo ) throws Exception {
        MsRegions msRegions = SpaceBeanCopy.msRegionsVoToMsRegions(msRegionsVo);
        return SpaceBeanCopy.msRegionsToMsRegionsResult(userListDao.msRegions(msRegions));
    }

    @Override
    public List getUserLevel(AccountUserVo usersVo) throws Exception {
        Users users = SpaceBeanCopy.accounUserVoToAccountUser(usersVo);
        List userLevel = userListDao.getUserLevel(users);
        return userLevel;
    }

    @Override
    public UserRankResult userRank(UserRankVo userRankVo) throws Exception {
        UserRank userRank = SpaceBeanCopy.userRankVoToUserRank(userRankVo);
        return SpaceBeanCopy.userRankToUserRankResult(userListDao.userRank(userRank));
    }

    @Override
    public Integer getUpdateUser(UserRankVo userRankVo) throws Exception {
        UserRank userRank = SpaceBeanCopy.userRankVoToUserRank(userRankVo);
        return userListDao.getUpdateUser(userRank);
    }

    @Override
    public Integer deleteUserLevel(UserRankVo userRankVo) throws Exception {
        UserRank userRank = SpaceBeanCopy.userRankVoToUserRank(userRankVo);
        return userListDao.deleteUserLevel(userRank);
    }

    @Override
    public  List<ShippingAddressResult> getProvince(MsRegionsVo msRegionsVo) throws Exception {
        MsRegions msRegions = SpaceBeanCopy.msRegionsVoToMsRegions(msRegionsVo);
        return userListDao.getProvince(msRegions);
    }

    @Override
    public  List<ShippingAddressResult> getCity(MsRegionsVo msRegionsVo) throws Exception {
        MsRegions msRegions = SpaceBeanCopy.msRegionsVoToMsRegions(msRegionsVo);
        return userListDao.getCity(msRegions);
    }

    @Override
    public List<ShippingAddressResult> getArea(MsRegionsVo msRegionsVo) throws Exception {
        MsRegions msRegions = SpaceBeanCopy.msRegionsVoToMsRegions(msRegionsVo);
        return userListDao.getArea(msRegions);
    }

    @Override
    public Integer insertShippingAddress(ShippingAddressVo shippingAddressVo) throws Exception {
        ShippingAddress shippingAddress = SpaceBeanCopy.shippingAddressVoToshippingAddress(shippingAddressVo);
        return userListDao.insertShippingAddress(shippingAddress);
    }

    @Override
    public Integer addUserLevel(UserRankVo userRankVo) throws Exception {
        UserRank userRank = SpaceBeanCopy.userRankVoToUserRank(userRankVo);
        return userListDao.addUserLevel(userRank);
    }

    @Override
    public List getUserCard(String cardCode) throws Exception {
        return userListDao.getUserCard(cardCode);
    }
    /**
     * 添加用户或者供应商的评分
     * @return
     */
    @Override
    public Double AddScoreOnSuppOrUser(String pluteformid, Integer listSize, Integer userId, String percentage, String scoreVal, String codeId, Integer pid_01) {
        Double totalScore=0.00;
        try{
            //获取当前的时间
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            Date date=df.parse(df.format(new Date()));
            String [] a=percentage.split(",");//百分数
            String [] b=scoreVal.split(",");//输入的值
            String [] e=codeId.split(",");//id
            //首先判断这个用户是否存在 不存在添加 存在直接修改
            UserScore userScoreVo = new UserScore();
            userScoreVo.setPluteformid(pluteformid);
            userScoreVo.setUserId(userId);
            userScoreVo.setStatus(2);
            List list=this.getUserList(userScoreVo);
//            创建对象。添加平台Id
            ItemSystemCode itemSystemCodeVo = new ItemSystemCode();
            itemSystemCodeVo.setPluteformid(pluteformid);
            UserScore userScoreVo_insert = new UserScore();
            userScoreVo_insert.setPluteformid(pluteformid);
            UserScore userScoreVo_del = new UserScore();
            userScoreVo_del.setPluteformid(pluteformid);
            UserScore userScoreVo_update = new UserScore();
            userScoreVo_update.setPluteformid(pluteformid);
            if(list.size()==0){//数据库没有这条记录 增加
                for (int i = 0; i <a.length ; i++) {
                    String c=a[i];
                    Double aa=Double.parseDouble(c);//百分比
                    String d=b[i];
                    int bb=Integer.parseInt(d);//输入的值
                    String f=e[i];//其他表对应的Id
                    int ff=Integer.parseInt(f);
                    Double score=aa*bb;
                    totalScore+=score;
                    //封装对象
                    userScoreVo_insert.setUserId(userId);
                    userScoreVo_insert.setPid(ff);
                    userScoreVo_insert.setScore(score);
                    userScoreVo_insert.setCreateTime(date);
                    userScoreVo_insert.setStatus(2);
                    userScoreVo_insert.setInputScore(d);
                    this.insertScore(userScoreVo_insert);
                }
            }else if (list.size()>listSize)//原先的类型删除了一些数据 进行删除操作 遍历list
            {
                for (int i = 0; i < list.size(); i++) {
                    UserScore userScore=(UserScore)list.get(i);
                    int pid=userScore.getPid();
                    itemSystemCodeVo.setId(pid);
                    itemSystemCodeVo.setFid(null);
                    List list1=this.getCodeType(itemSystemCodeVo);//id没有的
                    if(list1.size()==0){
                        userScoreVo_del.setUserId(userId);
                        userScoreVo_del.setPid(pid);
                        this.delCodeType(userScoreVo_del);
                    }
                }
                for (int k = 0; k <a.length ; k++) {
                    String c=a[k];
                    Double aa=Double.parseDouble(c);//百分比
                    String d=b[k];
                    int bb=Integer.parseInt(d);//输入的值
                    String f=e[k];//其他表对应的Id
                    int ff=Integer.parseInt(f);
                    Double score=aa*bb;//得到分数
                    userScoreVo_update.setUserId(userId);
                    userScoreVo_update.setPid(ff);
                    userScoreVo_update.setScore(score);
                    userScoreVo_update.setUpdateTime(date);
                    userScoreVo_update.setInputScore(d);
                    this.updateScore(userScoreVo_update);
                }
            }
            else if (list.size()<listSize)//原先的类型添加了一些数据  进行添加操作  遍历 listSize
            {
                itemSystemCodeVo.setId(null);
                itemSystemCodeVo.setFid(pid_01);
                List list1=this.getCodeType(itemSystemCodeVo);
                List list2=new ArrayList();
                int f=listSize-list.size();
                for (int j = 0; j <list.size() ; j++) {
                    UserScore userScore=(UserScore)list.get(j);
                    int pid=userScore.getPid();
                    list2.add(pid);
                }
                for (int i = 0; i <list1.size() ; i++) {//根据Pid查询所有Id的
                    ItemSystemCode item=(ItemSystemCode)list1.get(i);
                    int id=item.getId();
                    //获取id判断下面list是否存在
                    if(list2.contains(id)==false)
                    {
                        for (int m = a.length-f; m <a.length ; m++) {
                            String c=a[m];
                            Double aa=Double.parseDouble(c);//百分比
                            String d=b[m];
                            int bb=Integer.parseInt(d);//输入的值
                            Double score=aa*bb;
                            userScoreVo_insert.setUserId(userId);
                            userScoreVo_insert.setPid(id);
                            userScoreVo_insert.setScore(score);
                            userScoreVo_insert.setCreateTime(date);
                            userScoreVo_insert.setStatus(2);
                            userScoreVo_insert.setInputScore(d);
                            this.insertScore(userScoreVo_insert);//分数，输入的值
                            f=f+1;
                            break;
                        }
                    }
                }
                for (int k = 0; k <a.length ; k++) {
                    String c=a[k];
                    Double aa=Double.parseDouble(c);//百分比
                    String d=b[k];
                    int bb=Integer.parseInt(d);//输入的值
                    String fff=e[k];//其他表对应的Id
                    int ff=Integer.parseInt(fff);
                    Double score=aa*bb;//得到分数
                    userScoreVo_update.setUserId(userId);
                    userScoreVo_update.setPid(ff);
                    userScoreVo_update.setScore(score);
                    userScoreVo_update.setUpdateTime(date);
                    userScoreVo_update.setInputScore(d);
                    this.updateScore(userScoreVo_update);
                }
            }else
            {
                //修改 根据用户ID和关联表Id进行修改
                for (int i = 0; i <a.length ; i++) {
                    String c=a[i];
                    Double aa=Double.parseDouble(c);//百分比
                    String d=b[i];
                    int bb=Integer.parseInt(d);//输入的值
                    String f=e[i];//其他表对应的Id
                    int ff=Integer.parseInt(f);
                    Double score=aa*bb;//得到分数
                    totalScore+=score;
                    userScoreVo_update.setUserId(userId);
                    userScoreVo_update.setPid(ff);
                    userScoreVo_update.setScore(score);
                    userScoreVo_update.setUpdateTime(date);
                    userScoreVo_update.setInputScore(d);
                    this.updateScore(userScoreVo_update);
                }
            }
        }catch (Exception e){
            return null;
        }
        return totalScore;
    }

    @Override
    public SystemCodeResult getCodeUserId(SystemCodeVo systemCodeVo) throws Exception {
        SystemCode systemCode = SpaceBeanCopy.userSystemCodeVoToSystemCode(systemCodeVo);
        return SpaceBeanCopy.userSystemCodeToSystemCodeResult(userListDao.getCodeUserId(systemCode));
    }

    @Override
    public List getCodeType(ItemSystemCode itemSystemCodeVo) throws Exception {
        return userListDao.getCodeType(itemSystemCodeVo);
    }

    @Override
    public Integer delCodeType(UserScore userScoreVo) throws Exception {
        return userListDao.delCodeType(userScoreVo);
    }

    @Override
    public Integer getUserAllScore(AccountUserVo usersVo) throws Exception {
        Users users = SpaceBeanCopy.accounUserVoToAccountUser(usersVo);
        return userListDao.getUserAllScore(users);
    }



    @Override
    @Transactional
    public Integer insertUsers(Users users) throws Exception {
        int result=userListDao.insertUsers(users);
        return result;
    }

//商城webService
    @Override
    @Transactional
    public Integer insertBalanceDetails(UsersExp usersExp,BalanceDetails balanceDetails) throws Exception {
      //  UserListDao userListDao1=new UserListDaoImpl();
//        SqlSessionFactoryBuilder sqf = new SqlSessionFactoryBuilder();
        int result = userListDao.insertBalanceDetails(usersExp, balanceDetails);
        return result;
    }

    @Override
    @Transactional
    public Integer insertPoints(UsersExp usersExp, PointsDetail pointsDetail) throws Exception {
        log.error("dao====================="+userListDao);
        int result=userListDao.insertPoints(usersExp,pointsDetail);
        return result;
    }

    @Override
    @Transactional
    public Integer insertRankScore(UsersExp usersExp, RankDetail rankDetail) throws Exception {
        int result=userListDao.insertRankScore(usersExp,rankDetail);
        return result;
    }

    @Override
    @Transactional
    public Integer insertUserLevel(UserLog userLog, UserRank userRank) throws Exception {
        int result=userListDao.insertUserLevel(userLog,userRank);
        return result;
    }

    @Override
    @Transactional
    public Integer web_deleteUserLevel(UserRank userRank,UserLog userLog) throws Exception {
        int result=userListDao.web_deleteUserLevel(userRank, userLog);
        return result;
    }

    @Override
    @Transactional
    public Integer web_getUpdateUserLevel(UserRank userRank, UserLog userLog) throws Exception {
        System.out.print("12");
        int result=userListDao.web_getUpdateUserLevel(userRank,userLog);
        return result;
    }

    @Override
    @Transactional
    public Integer web_UpdateUserOrExp(Users users) throws Exception {
        int result=userListDao.web_UpdateUserOrExp(users);
        return result;
    }

    @Override
    @Transactional
    public Integer web_UpdateUserPwd(Users users) throws Exception {
        int result=userListDao.web_UpdateUserPwd(users);
        return result;
    }

    @Override
    @Transactional
    public Integer web_UpdateUserGravatar(UsersExp usersExp) throws Exception {
        int result=userListDao.web_UpdateUserGravatar(usersExp);
        return result;
    }

    @Override
    @Transactional
    public Integer web_UserFrozenOrActivation(Users users) throws Exception {
        int result=userListDao.web_UserFrozenOrActivation(users);
        return result;
    }

    @Override
    @Transactional
    public Integer web_BalanceDraw(UsersExp usersExp, BalanceDetails balanceDetails, BalanceDrawRequest balanceDrawRequest) throws Exception {
       int result=userListDao.web_BalanceDraw(usersExp,balanceDetails,balanceDrawRequest);
        return result;
    }

    @Override
    @Transactional
    public Integer web_PointsChange(ShopExchangeDetail shopExchangeDetail, ShopCouponInfo shopCouponInfo, UsersExp usersExp, PointsDetail pointsDetail) throws Exception {
       int result=userListDao.web_PointsChange(shopExchangeDetail,shopCouponInfo,usersExp,pointsDetail);
        return result;
    }

    @Override
    @Transactional
    public Integer web_insertShippingAddress(ShippingAddress shippingAddress) throws Exception {
        int result=userListDao.web_insertShippingAddress(shippingAddress);
        return result;
    }

    @Override
    @Transactional
    public Integer web_updateShippingAddress(ShippingAddress shippingAddress) throws Exception {
        int result=userListDao.web_updateShippingAddress(shippingAddress);
        return result;
    }

    @Override
    @Transactional
    public Integer web_deleteShippingAddress(ShippingAddress shippingAddress) throws Exception {
        int result=userListDao.web_deleteShippingAddress(shippingAddress);
        return result;
    }

    @Override
    @Transactional
    public Integer web_insertCoupon(ShopCouponClass shopCouponClass) throws Exception {
        int result=userListDao.web_insertCoupon(shopCouponClass);
        return result;
    }

    @Override
    @Transactional
    public Integer web_updateCoupon(ShopCouponClass shopCouponClass) throws Exception {
        int result=userListDao.web_updateCoupon(shopCouponClass);
        return result;
    }

    @Override
    @Transactional
    public Integer web_deleteCoupon(ShopCouponClass shopCouponClass) throws Exception {
        int result=userListDao.web_deleteCoupon(shopCouponClass);
        return result;
    }

    @Override
    @Transactional
    public Integer web_updateCouponSequence(List list) throws Exception {
        int result=userListDao.web_updateCouponSequence(list);
        return result;
    }

    @Override
    @Transactional
    public Integer web_deleteCouponRule(ShopCouponRule shopCouponRule) throws Exception {
        int result=userListDao.web_deleteCouponRule(shopCouponRule);
        return result;
    }

    @Override
    @Transactional
    public Integer web_deleteCouponInfoHistory(ShopCouponInfo shopCouponInfo, ShopCouponHistory shopCouponHistory) throws Exception {
        int result=userListDao.web_deleteCouponInfoHistory(shopCouponInfo,shopCouponHistory);
        return result;
    }

    @Override
    @Transactional
    public Integer web_updateCouponClassStatus(ShopCouponClass shopCouponClass) throws Exception {
       int result=userListDao.web_updateCouponClassStatus(shopCouponClass);
        return result;
    }

    @Override
    @Transactional
    public Integer web_insertCouponRuleOrInfo(ShopCouponRule shopCouponRule, List list) throws Exception {
       int result=userListDao.web_insertCouponRuleOrInfo(shopCouponRule,list);
       return result;
    }

    @Override
    @Transactional
    public Integer web_insertCouponInfo(List<ShopCouponInfo> infoList,ShopCouponRule shopCouponRule) throws Exception {
        int result=userListDao.web_insertCouponInfo(infoList,shopCouponRule);
        return result;
    }

    @Override
    @Transactional
    public Integer web_insertTransferCoupon(List<ShopCouponHistory> shopCouponHistoryList) throws Exception {
        int result=userListDao.web_insertTransferCoupon(shopCouponHistoryList);
        return result;
    }

    @Override
    @Transactional
    public Integer web_delCouponInfo(String couponCode) throws Exception {
        Integer result=userListDao.web_delCouponInfo(couponCode);
        return result;
    }

    @Override
    @Transactional
    public Integer web_updateCouponInfo(String couponCode, Integer status) throws Exception {
        int result=userListDao.web_updateCouponInfo(couponCode,status);
        return result;
    }

    @Override
    @Transactional
    public Integer web_deleteExpiredCoupons(String history) throws Exception {
        int result=userListDao.web_deleteExpiredCoupons(history);
        return result;
    }

    @Override
    @Transactional
    public Integer web_InsertSupplierInfo(Users users, SupplierInfo supplierInfo) throws Exception {
        int result=userListDao.web_InsertSupplierInfo(users,supplierInfo);
        return result;
    }

    @Override
    @Transactional
    public Integer web_updateSupplierInfo(SupplierInfo supplierInfo, List list) throws Exception {
        int result=userListDao.web_updateSupplierInfo(supplierInfo,list);
        return result;
    }

    @Override
    @Transactional
    public Integer web_delSupplierInfo(Integer supplierId) throws Exception {
        int result=userListDao.web_delSupplierInfo(supplierId);
        return result;
    }


    @Override
    //微信
    @Transactional
    public Users selectUserByWeChat(AccountUserVo accountUserVo) {
        Users users = SpaceBeanCopy.accounUserVoToAccountUser(accountUserVo);
        Users user = userListDao.selectUserByWeChat(users);
        return user;
    }

    @Override
    @Transactional
    public List<ShippingAddress> selectShopAddressByUserId(AccountUserVo accountUserVo) {
        Users users = SpaceBeanCopy.accounUserVoToAccountUser(accountUserVo);
        List<ShippingAddress> shippingAddresses = shippingAddressDao.selectShopAddressByUserId(users);
        return shippingAddresses;
    }

    @Override
    @Transactional
    public int addShopAddress(ShippingAddressVo shippingAddressVo) {
        ShippingAddress shippingAddress = SpaceBeanCopy.shippingAddressVoToshippingAddress(shippingAddressVo);
        Users users=new Users();
        users.setPluteformid(shippingAddress.getPluteformid());
        users.setUserId(shippingAddress.getUserId());
        List<ShippingAddress> shippingAddresses = shippingAddressDao.selectShopAddressByUserId(users);
        if (shippingAddresses.size()!=0){
            shippingAddress.setState("0");
        }else {
            shippingAddress.setState("1");
        }
        return shippingAddressDao.addShopAddress(shippingAddress);
    }

    /**
     * 修改地址
     * @param shippingAddressVo
     * @return
     */
    @Override
    public int updateShippingAddress(ShippingAddressVo shippingAddressVo) {
        ShippingAddress shippingAddress = SpaceBeanCopy.shippingAddressVoToshippingAddress(shippingAddressVo);
        return shippingAddressDao.updateShippingAddress(shippingAddress);
    }

    @Override
    @Transactional
    public int removeShopAddress(ShippingAddressVo shippingAddressVo) {
        ShippingAddress shippingAddress = SpaceBeanCopy.shippingAddressVoToshippingAddress(shippingAddressVo);
        return shippingAddressDao.removeShopAddress(shippingAddress);
    }

    @Override
    @Transactional
    public int updateShopAddressState(ShippingAddressVo shippingAddressVo) {
        ShippingAddress shippingAddress = SpaceBeanCopy.shippingAddressVoToshippingAddress(shippingAddressVo);
        int i = shippingAddressDao.updateAllShopAddressState(shippingAddress);
        int j = shippingAddressDao.updateShopAddressState(shippingAddress);
        return i&j;
    }

    @Override
    @Transactional
    public int updateAllShopAddressState(ShippingAddressVo shippingAddressVo) {
        ShippingAddress shippingAddress = SpaceBeanCopy.shippingAddressVoToshippingAddress(shippingAddressVo);
       return shippingAddressDao.updateAllShopAddressState(shippingAddress);
    }

    @Override
    @Transactional
    public ShippingAddressResult selectAddressState(ShippingAddressVo shippingAddressVo) {
        ShippingAddress shippingAddress = SpaceBeanCopy.shippingAddressVoToshippingAddress(shippingAddressVo);
        ShippingAddressResult shippingAddressResult = SpaceBeanCopy.shippingAddressToshippingAddressResult(shippingAddressDao.selectAddressState(shippingAddress));
        return shippingAddressResult;
    }
    //微信根据id查询收货地址信息
    @Override
    public ShippingAddressResult selectAddressOne(ShippingAddressVo shippingAddressVo) {
        ShippingAddress shippingAddress = SpaceBeanCopy.shippingAddressVoToshippingAddress(shippingAddressVo);
        ShippingAddressResult shippingAddressResult = SpaceBeanCopy.shippingAddressToshippingAddressResult(shippingAddressDao.selectAddressOne(shippingAddress));
        return shippingAddressResult;
    }

    @Override
    public RegionInfoResult selectRegionById(RegionInfoVo regionInfoVo) {
        RegionInfo regionInfo = SpaceBeanCopy.regionInfoVoToregionInfo(regionInfoVo);
        RegionInfoResult regionInfoResult = SpaceBeanCopy.regionInfoToregionInfoResult(regionInfoDao.selectRegionId(regionInfo));
        return regionInfoResult;
    }

    /**
     * 根据AppId 、openId 获得用户
     * @param accountUserVo
     * @return
     */
    @Override
    public AccountUserResult selUsersByAppIdAndOpenId(AccountUserVo accountUserVo) {
        Users users = SpaceBeanCopy.accounUserVoToAccountUser(accountUserVo);
        return SpaceBeanCopy.AccountToUserResult(userListDao.selUsersByAppIdopenId(users));
    }

    /**
     * //交易记录查询  分页
     * @param page
     * @return
     */
    @Override
    public List<TradingRecordResult> selectTradingRecordByUserId(Page page) {
        page.setBeginNumber((page.getCurrentPage()-1)*page.getPageSize());
        List<TradingRecord> tradingRecordResults = tradingRecordDao.selTradingRecordResultByPage(page);
        List<TradingRecordResult> tradingRecordResultsList = new ArrayList<TradingRecordResult>();
            for (int i=0;i<tradingRecordResults.size();i++){
                tradingRecordResultsList.add(SpaceBeanCopy.tradingRecordToTradingRecordResult(tradingRecordResults.get(i)));
            }
        return tradingRecordResultsList;
    }

    /**
     * 查询没豆  余额
     * @param usersExpVo
     * @return
     */

    @Override
    public UsersExpResult selectBalanceAndPointsByUserId(UsersExpVo usersExpVo) {
        UsersExp usersExp = SpaceBeanCopy.usersExpVoToUsersExp(usersExpVo);
        return SpaceBeanCopy.usersExpToUsersExpResult(userListDao.selectBalanceAndPointsByUserId(usersExp));
    }

    /**
     * 增加余额交易记录
     * @param tradingRecordVo
     * @return
     */
    @Override
    @Transactional
    public Integer
    insertTrading(TradingRecordVo tradingRecordVo) {
        TradingRecord tradingRecord=SpaceBeanCopy.tradingRecordVoToTradingRecord(tradingRecordVo);
        AccountUserVo accountUserVo=new AccountUserVo();
        accountUserVo.setOpenId(tradingRecordVo.getOpenId());
        accountUserVo.setPluteformid(tradingRecordVo.getPluteformid());
        Users users = SpaceBeanCopy.accounUserVoToAccountUser(accountUserVo);
        Users u=userListDao.selUsersByAppIdopenId(users);
        tradingRecord.setUserId(u.getUserId());
        return tradingRecordDao.insertTrading(tradingRecord);
    }

    /**
     * 微信的余额充值
     * @param usersExpVo
     * @return
     */
    @Override
    @Transactional
    public Integer updateBalanceRechargeWX(UsersExpVo usersExpVo) {
        UsersExp usersExp = SpaceBeanCopy.usersExpVoToUsersExp(usersExpVo);
        return  userListDao.updateRBP(usersExp);

    }

    /**
     * 微信的余额消费
     * @param usersExpVo
     * @return
     */
    @Override
    @Transactional
    public Integer updateBalanceConsumeWX(UsersExpVo usersExpVo) {
        UsersExp usersExp = SpaceBeanCopy.usersExpVoToUsersExp(usersExpVo);
        return userListDao.updateBalanceConsumeWX(usersExp);
    }

    /**
     * 根据prderCode查询记录表
     * @param tradingRecordVo
     * @return
     */
    @Override
    public TradingRecord selTradingRecordByOrderCode(TradingRecordVo tradingRecordVo) {
        TradingRecord tradingRecords=SpaceBeanCopy.tradingRecordVoToTradingRecord(tradingRecordVo);
        TradingRecord tradingRecord=tradingRecordDao.selTradingRecordByOrderCode(tradingRecords);
        return tradingRecord;
    }

    @Override
    public Users getAccountsUserApp(String userId) {
        Users users = userListDao.getAccountsUserApp(userId);
        return users;
    }
    /**
     * 开启店铺预先修改用户的状态
     * @param accountUserVo
     * @return
     */
    @Override
    @Transactional
    public Integer updateUserType(AccountUserVo accountUserVo) {
        Users users = SpaceBeanCopy.accounUserVoToAccountUser(accountUserVo);
        Users u=userListDao.selUsersByAppIdopenId(users);
        Integer i=userListDao.updateUserType(u);
        return i;
    }
    //完善店铺下用户的资料
    @Override
    @Transactional
    public Integer perfectAccountUser(AccountUserVo accountUserVo) {
        Users users = SpaceBeanCopy.accounUserVoToAccountUser(accountUserVo);
        User user=new User();
        user.setParentPluteformId(users.getPluteformid());
        user.setOpenId(users.getOpenId());
        user.setGender(users.getSex());
        user.setPassword(users.getPassWord());
        user.setName(users.getNickName());
        user.setRealName(users.getNickName());
        Integer i=userDao.perfectUser(user);
        Integer ii=userListDao.perfectAccountUser(users);
        return i+ii;
    }

    /**
     * 首先修改记录表如果修改记录表成功,根据remark进行区分0是充值给用户增加余额
     * 1是订单消费减少用户的余额，再根据paymentTypeId(paymentTypeId  订单类型 0全额-线下  1全额-线上  2全额-余额  3定金-线上  4定金-余额)
     * paymentTypeId为2时设置orderStatus为1(0未付款 1已付款 2已付定金)
     * 2是余额提现减少用户的余额
     * @param tradingRecordVo
     * @return
     */
    @Override
    @Transactional
    public Integer updateSuccessState(TradingRecordVo tradingRecordVo) {
        TradingRecord tradingRecord=SpaceBeanCopy.tradingRecordVoToTradingRecord(tradingRecordVo);
        AccountUserVo accountUserVo=new AccountUserVo();
        accountUserVo.setOpenId(tradingRecordVo.getOpenId());
        accountUserVo.setPluteformid(tradingRecordVo.getPluteformid());
        Users users = SpaceBeanCopy.accounUserVoToAccountUser(accountUserVo);
        Users u=userListDao.selUsersByAppIdopenId(users);
        tradingRecord.setUserId(u.getUserId());
        Integer i;
        //如果successState状为3说明是余额扣钱,直接新增加交易记录表,否则修改交易记录表
        if(tradingRecordVo.getSuccessState()==3){
             i=tradingRecordDao.insertTrading(tradingRecord);
        }else {
             i = tradingRecordDao.updateSuccessState(tradingRecord);
        }
        if(i>0){
            UsersExpVo usersExpVo = new UsersExpVo();
            usersExpVo.setBalance(tradingRecordVo.getBalance());//充值的钱
            usersExpVo.setPoints(1);//充值钱送积分
            usersExpVo.setRankScore(2);//充值送成长值
            usersExpVo.setPluteformid(tradingRecordVo.getPluteformid());
            usersExpVo.setUserId(u.getUserId());
            UsersExp usersExp = SpaceBeanCopy.usersExpVoToUsersExp(usersExpVo);
            //remark为0是充值直接增加用户余额表返回值2  为1为余额消费减少用户余额表 修改订单表返回值3   为2提现直接减少用户余额表返回值2
            if (tradingRecordVo.getRemark()==0) {
                Integer ii=userListDao.updateRBP(usersExp);
                return ii+i;
            }else if (tradingRecordVo.getRemark()==1&&tradingRecordVo.getPaymentTypeId()!=null){
                Integer iii=userListDao.updateBalanceConsumeWX(usersExp);
                //订单表
                OrderInfo orderInfo=new OrderInfo();
                orderInfo.setPluteformid(tradingRecord.getPluteformid());
                orderInfo.setOrderCode(tradingRecord.getOrderCode());
                orderInfo.setBuyerName(tradingRecord.getOpenId());
                Integer paymentTypeId=tradingRecord.getPaymentTypeId();
                if(paymentTypeId==2) {
                    orderInfo.setOrderStatus(1);
                }else {
                    orderInfo.setOrderStatus(2);
                }
                Integer iiii = null;
                if(tradingRecord.getOrderCode().endsWith("yy")){
                    //余额支付成功时，修改预约表
                    iiii = orderInfoDao.updateAppointmentStatusWX(orderInfo);
                }else{
                    //余额支付成功时，修改订单表
                    iiii = orderInfoDao.updateOrderStatusWX(orderInfo);
                }

                return iii+i+iiii;
            }else {
                Integer iii=userListDao.updateBalanceConsumeWX(usersExp);
                return iii+i;
            }
        }else {
            return 0;
        }
    }
	@Override
    public EasyUIListResult<Users> selAllUser(PageableUsers users) {
        List<Users> usersList = userListDao.selAllUsers(users);
        Long total = userListDao.selAllUsersCount(users);
        return new EasyUIListResult<Users>(total,usersList);
    }
}
