package com.zm.mall.dao.business.accountsUsers.impl;

import com.zm.mall.client.Page;
import com.zm.mall.dao.BaseDaoImpl;
import com.zm.mall.dao.business.accountsUsers.UserListDao;
import com.zm.mall.domain.business.accountsUsers.*;
import com.zm.mall.domain.business.product.SupplierInfo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by hudaopu on 2016/11/11.
 */
public class UserListDaoImpl extends BaseDaoImpl<Users> implements UserListDao {

    private final static String NAMESPACE = "com.zm.mall.dao.business.accountsUsers.UserListDao.";

    @Override
    public String getNameSpace(String statement) {
        return NAMESPACE+statement;
    }

    @Override
    public List<Users> selectAll(String key,String before,String after,Integer activity) {
        Users users=new Users();
        users.setBeforeTime(before);
        users.setAfterTime(after);
        users.setActivity(activity);
        users.setKey(key);
        return sqlTemplate.selectList(getNameSpace("selectAll"),users);
    }

    @Override
    public List getSelectActiv(Users users) {
        return sqlTemplate.selectList(getNameSpace("getSelectActiv"),users);
    }

    //根据Id查询 详细 信息
    @Override
    public Users getUserIdSelect(Users users) {
        return  sqlTemplate.selectOne(getNameSpace("getUserIdSelect"),users);
    }

    @Override
    public List getKeyword(Page page) {
        return sqlTemplate.selectList(getNameSpace("getKeyword"),page);
    }

    @Override
    public Users getTopUp(Users users) {
        return  sqlTemplate.selectOne(getNameSpace("getTopUp"),users);
    }

    @Override
    public List getTimeSearch(Users users) {
        return sqlTemplate.selectList(getNameSpace("getTimeSearch"),users);
    }

    @Override
    public int updateRBP(UsersExp usersExp) {
        return sqlTemplate.update(getNameSpace("updateRBP"),usersExp);
    }

    @Override
    public List getScoreDetail(PointsDetail pointsDetail) {
        return sqlTemplate.selectList(getNameSpace("getScoreDetail"),pointsDetail);
    }

    @Override
    public List getTypeSearch(PointsDetail pointsDetail) throws Exception {
        try {
            return sqlTemplate.selectList(getNameSpace("getTypeSearch"), pointsDetail);
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public int updateFreeze(Users users) throws Exception {
        try {
            return sqlTemplate.update(getNameSpace("updateFreeze"), users);
        }catch (Exception e)
        {
            throw e;
        }
    }

    @Override
    public int activationUser(Users users) throws Exception {
        try {
            return sqlTemplate.update(getNameSpace("activationUser"), users);
        }catch (Exception e)
        {
            throw e;
        }
    }

    @Override
    public Integer getCount(Page page) throws Exception{
        try {
            Integer total = sqlTemplate.selectOne(getNameSpace("getCount"), page);
            return total;
        }catch (Exception e)
         {
                throw e;
         }
    }

    @Override
    public List<Users> getFenYe(Page page) throws Exception
    {
        try {
            return sqlTemplate.selectList(getNameSpace("getFenYe"), page);
        }catch (Exception e)
        {
            throw e;
        }
    }

    @Override
    public List getAddress(ShippingAddress shippingAddress) throws  Exception{
        return sqlTemplate.selectList(getNameSpace("getAddress"),shippingAddress);
    }

    @Override
    public List getUserScore(ItemSystemCode itemSystemCode) throws Exception {
        try{
            return sqlTemplate.selectList(getNameSpace("getUserScore"),itemSystemCode);
        }catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Integer updateScore(UserScore userScore) throws Exception {
        try {
            return sqlTemplate.update(getNameSpace("updateScore"), userScore);
        }catch (Exception e)
        {
            throw e;
        }
    }

    @Override
    public Integer insertScore(UserScore userScore) throws Exception {
        try {
            return sqlTemplate.insert(getNameSpace("insertScore"), userScore);
        }catch (Exception e)
        {
            throw e;
        }
    }

    @Override
    public List getUserList(UserScore userScore) throws Exception {
        return sqlTemplate.selectList(getNameSpace("getUserList"),userScore);
    }

    @Override
    public MsRegions msRegions(MsRegions msRegions) throws Exception {
        try {
            return sqlTemplate.selectOne(getNameSpace("selmsRegions"), msRegions);
        }catch (Exception e)
        {
            throw e;
        }
    }

    @Override
    public List getUserLevel(Users users) throws Exception {
        try {
            return sqlTemplate.selectList(getNameSpace("getUserLevel"),users);
        }catch (Exception e)
        {
            throw e;
        }
    }

    @Override
    public UserRank userRank(UserRank userRank) throws Exception {
        return sqlTemplate.selectOne(getNameSpace("userRank"),userRank);
    }

    @Override
    public Integer getUpdateUser(UserRank userRank) throws Exception {
        return sqlTemplate.update(getNameSpace("getUpdateUser"),userRank);
    }

    @Override
    public Integer deleteUserLevel(UserRank userRank) throws Exception {
        return sqlTemplate.delete(getNameSpace("deleteUserLevel"),userRank);
    }

    @Override
    public Integer addUserLevel(UserRank userRank) throws Exception {
        return sqlTemplate.insert(getNameSpace("addUserLevel"),userRank);
    }

    @Override
    public List getUserCard(String cardCode) throws Exception {
        return sqlTemplate.selectList(getNameSpace("getUserCard"),cardCode);
    }

    @Override
    public SystemCode getCodeUserId(SystemCode systemCode) throws Exception {
        return sqlTemplate.selectOne(getNameSpace("getCodeUserId"),systemCode);
    }

    @Override
    public List getCodeType(ItemSystemCode itemSystemCode) throws Exception {
        return sqlTemplate.selectList(getNameSpace("getCodeType"),itemSystemCode);
    }

    @Override
    public Integer delCodeType(UserScore userScore) throws Exception {
        return sqlTemplate.delete(getNameSpace("delCodeType"),userScore);
    }

    @Override
    public Integer getUserAllScore(Users users) throws Exception {
        Integer total = sqlTemplate.selectOne(getNameSpace("getUserAllScore"), users);
        return total;
    }

    @Override
    public List getProvince(MsRegions msRegions) throws Exception {
        return sqlTemplate.selectList(getNameSpace("getProvince"),msRegions);
    }

    @Override
    public List getCity(MsRegions msRegions) throws Exception {
        return sqlTemplate.selectList(getNameSpace("getCity"),msRegions);
    }

    @Override
    public List getArea(MsRegions msRegions) throws Exception {
        return sqlTemplate.selectList(getNameSpace("getArea"),msRegions);
    }

    @Override
    public Integer insertShippingAddress(ShippingAddress shippingAddress) throws Exception {
        return sqlTemplate.insert(getNameSpace("InsertShippingAddress"),shippingAddress);

    }

    ////----------------------------用户接口添加
    @Override
    public Integer insertUsers(Users users) throws Exception {
        try {
            String password=users.getPassWord();
            users.setUserId(null);
            int a=sqlTemplate.insert(getNameSpace("insertUsers"), users);
            users.setUserId(users.getUserId());
            int b=sqlTemplate.insert(getNameSpace("insertUsersExp"), users);
            int c=sqlTemplate.insert(getNameSpace("insertPointsDetail"), users);
            int d=0;
            if(users.getRankDetail()!=null){
                d=sqlTemplate.insert(getNameSpace("insertRankDetail"), users);
            }
            if(a==1&&b==1&&c==1||a==1&&b==1&&c==1&&d==1){
                return 1;
            }else {
                return 0;
            }
        }catch (Exception e)
        {
            throw e;
        }
    }
    //-----------------------账户修改 和 记录增加
    @Override
    public Integer insertBalanceDetails(UsersExp usersExp,BalanceDetails balanceDetails) throws Exception {
           int a=  sqlTemplate.update(getNameSpace("updateUsersExp"), usersExp);
           int b= sqlTemplate.insert(getNameSpace("insertBalanceDetails"),balanceDetails);
            if (a==1&&b==1)
            {
                return 1;
            }else {
                return 0;
            }
    }
    //---------------------美豆修改个 记录增加
    @Override
    public Integer insertPoints(UsersExp usersExp,PointsDetail pointsDetail) throws Exception{
        int a=sqlTemplate.update(getNameSpace("updateExpPoints"),usersExp);
        int b=sqlTemplate.insert(getNameSpace("addPointsDetail"),pointsDetail);
        if (a==1&&b==1)
        {
            return 1;
        }else {
            return 0;
        }
    }
    //------------------------成长值修改 记录增加
    @Override
    public Integer insertRankScore(UsersExp usersExp, RankDetail rankDetail) throws Exception {
        int a=sqlTemplate.update(getNameSpace("updateExpRankScore"),usersExp);
        int b=sqlTemplate.insert(getNameSpace("addRankDetail"),rankDetail);
        if (a==1&&b==1)
        {
            return 1;
        }else {
            return 0;
        }
    }
    //添加用户等级
    @Override
    public Integer insertUserLevel(UserLog userLog, UserRank userRank) throws Exception {
        int b=sqlTemplate.insert(getNameSpace("insertUserLevel"),userRank);
        int a=sqlTemplate.insert(getNameSpace("insertUserLog"),userLog);
        if (a==1&&b==1)
        {
            return 1;
        }else {
            return 0;
        }
    }

    /**
     * 删除用户的等级
     * @param userRank
     * @return
     * @throws Exception
     */
    @Override
    public Integer web_deleteUserLevel(UserRank userRank,UserLog userLog) throws Exception {
        int a=sqlTemplate.delete(getNameSpace("web_deleteUserLevel"),userRank);
        int b=sqlTemplate.insert(getNameSpace("insertUserLog"),userLog);
        if(a==1&&b==1){
            return 1;
        }else
        {
            return 0;
        }
    }

    @Override
    public Integer web_getUpdateUserLevel(UserRank userRank, UserLog userLog) throws Exception {
        int a=sqlTemplate.update(getNameSpace("web_getUpdateUserLevel"), userRank);
        int b=sqlTemplate.insert(getNameSpace("insertUserLog"),userLog);
        if(a==1&&b==1){
            return 1;
        }else
        {
            return 0;
        }
    }

    @Override
    public Integer web_UpdateUserOrExp(Users users) throws Exception {
        int a=sqlTemplate.update(getNameSpace("updateUsers"), users);
        int b=sqlTemplate.update(getNameSpace("updateExp"), users);
        if(a==1&&b==1){
            return 1;
        }else
        {
            return 0;
        }
    }

    @Override
    public Integer web_UpdateUserPwd(Users users) throws Exception {
        int a=sqlTemplate.update(getNameSpace("updateUserPwd"), users);
        if(a==1){
            return 1;
        }else
        {
            return 0;
        }
    }

    @Override
    public Integer web_UpdateUserGravatar(UsersExp usersExp) throws Exception {
        int a=sqlTemplate.update(getNameSpace("updateUserGravatar"), usersExp);
        if(a==1){
            return 1;
        }else {
            return 0;
        }
    }

    @Override
    public Integer web_UserFrozenOrActivation(Users users) throws Exception {
        int a=sqlTemplate.update(getNameSpace("frozenOrActivation"), users);
        if(a==1){
            return 1;//成功
        }else {
            return 0;//失败
        }
    }

    @Override
    public Integer web_BalanceDraw(UsersExp usersExp, BalanceDetails balanceDetails, BalanceDrawRequest balanceDrawRequest) throws Exception {
        int a=sqlTemplate.insert(getNameSpace("insertBalanceDraw"),balanceDrawRequest);
        int b=sqlTemplate.update(getNameSpace("updateReduceBalance"),usersExp);
        int c=sqlTemplate.insert(getNameSpace("insertBalance"),balanceDetails);
        if(a==1&&b==1&&c==1){
            return 1;
        }else {
            return 0;
        }
    }

    @Override
    public Integer web_PointsChange(ShopExchangeDetail shopExchangeDetail, ShopCouponInfo shopCouponInfo, UsersExp usersExp, PointsDetail pointsDetail) throws Exception {
        int a=sqlTemplate.insert(getNameSpace("shopExchangeDetail"),shopExchangeDetail);
        int b=sqlTemplate.insert(getNameSpace("shopCouponInfo"),shopCouponInfo);
        int c=sqlTemplate.update(getNameSpace("updateReducePoints"),usersExp);
        int d=sqlTemplate.insert(getNameSpace("addPointsDetail"),pointsDetail);
        if(a==1&&b==1&&c==1&&d==1){
            return 1;
        }else {
            return 0;
        }
    }

    @Override
    public Integer web_insertShippingAddress(ShippingAddress shippingAddress) throws Exception {
        int a=sqlTemplate.insert(getNameSpace("webShippingAddress"),shippingAddress);
        if(a==1){
            return 1;
        }else {
            return 0;
        }
    }

    @Override
    public Integer web_updateShippingAddress(ShippingAddress shippingAddress) throws Exception {
        int a=sqlTemplate.update(getNameSpace("updateShippingAddress"),shippingAddress);
        if(a==1) {
            return 1;
        }else{
            return 0;
        }
    }

    @Override
    public Integer web_deleteShippingAddress(ShippingAddress shippingAddress) throws Exception {
        int a=sqlTemplate.delete(getNameSpace("webDeleteAddress"),shippingAddress);
        if(a==1){
            return 1;
        }else {
            return 0;
        }
    }

    @Override
    public Integer web_insertCoupon(ShopCouponClass shopCouponClass) throws Exception {
        int a=sqlTemplate.insert(getNameSpace("insertCouponClass"),shopCouponClass);
        if(a==1){
            return 1;
        }else
        {
            return 0;
        }
    }

    @Override
    public Integer web_updateCoupon(ShopCouponClass shopCouponClass) throws Exception {
        int a=sqlTemplate.update(getNameSpace("updateCouponClass"),shopCouponClass);
        if(a==1){
            return 1;
        }else {
            return 0;
        }
    }

    @Override
    public Integer web_deleteCoupon(ShopCouponClass shopCouponClass) throws Exception {
        int a=sqlTemplate.delete(getNameSpace("deleteCouponClass"),shopCouponClass);
        if(a==1){
            return 1;
        }else {
            return 0;
        }
    }

    @Override
    public Integer web_updateCouponSequence(List list) throws Exception {
        int a=0;
        for (int i = 0; i <list.size() ; i++) {
                ShopCouponClass coupon=(ShopCouponClass)list.get(i);
                int classId=coupon.getClassId();
                int  sequence=coupon.getSequence();
                coupon.setClassId(classId);
                coupon.setSequence(sequence);
            try {
                sqlTemplate.update(getNameSpace("updateCouponSequence"), coupon);
                a++;
            }catch (Exception e)
            {
                throw e;
            }

        }
        if(list.size()==a){
            return 1;
        }else {
            return 0;
        }
    }

    @Override
    public Integer web_deleteCouponRule(ShopCouponRule shopCouponRule) throws Exception {
        int a=sqlTemplate.delete(getNameSpace("deleteRule"),shopCouponRule);
        if(a==1){
            return 1;
        }else
        {
            return 0;
        }
    }

    @Override
    public Integer web_deleteCouponInfoHistory(ShopCouponInfo shopCouponInfo, ShopCouponHistory shopCouponHistory) throws Exception {
       int a=0;
        try{
          int c= sqlTemplate.delete(getNameSpace("deleteInfo"),shopCouponInfo);
          sqlTemplate.delete(getNameSpace("deleteHistory"),shopCouponHistory);
          a=1;
        }catch (Exception e)
        {
            throw e;
        }
        if(a==1){
            return 1;
        }else
        {
            return 0;
        }

    }

    @Override
    public Integer web_updateCouponClassStatus(ShopCouponClass shopCouponClass) throws Exception {
        int a=sqlTemplate.update(getNameSpace("updateCouponStatus"),shopCouponClass);
        if(a==1){
            return 1;
        }else
        {
            return 0;
        }
    }

    @Override
    public Integer web_insertCouponRuleOrInfo(ShopCouponRule shopCouponRule, List list) throws Exception {
        int a=sqlTemplate.update(getNameSpace("updateRuleOrInfo"),shopCouponRule);
        int c=0;
        for (int i = 0; i <list.size() ; i++) {
            ShopCouponInfo info=(ShopCouponInfo)list.get(i);
            System.out.print("");
            sqlTemplate.insert("insertRuleOrInfo",info);
            c++;
        }
        if(list.size()==c){
            return 1;
        }else
        {
            return 0;
        }
    }

    @Override
    public Integer web_insertCouponInfo(List<ShopCouponInfo> infoList,ShopCouponRule shopCouponRule) throws Exception {
      int a = 0;
       try {
           String date1="2038-12-31";//mysql最大时间戳
           DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
           Date d1;
           d1 = dateformat.parse(date1);
           Date date=shopCouponRule.getEndDate();
           if(date.after(d1)){//传过来的参数大于sql时间
               shopCouponRule.setEndDate(d1);
           }
           sqlTemplate.insert(getNameSpace("insertCouponRule"), shopCouponRule);
           if (infoList !=null) {
               for (int i = 0; i < infoList.size(); i++) {
                   ShopCouponInfo info = infoList.get(i);
                   sqlTemplate.insert(getNameSpace("insertRuleOrInfo"), info);
               }
           }
           a=1;
       }catch (Exception e)
       {
           throw e;
       }
        if(a==1){
            return 1;
        }else
        {
            return 0;
        }
    }

    @Override
    public Integer web_insertTransferCoupon(List<ShopCouponHistory> shopCouponHistoryList) throws Exception {
        int a=0;
        for (int i = 0; i < shopCouponHistoryList.size(); i++) {
            ShopCouponHistory info=shopCouponHistoryList.get(i);
            sqlTemplate.insert(getNameSpace("insertHistoryTransfer"),info);
            String couponCode=info.getCouponCode();
            sqlTemplate.delete(getNameSpace("deleteInfoTransfer"),couponCode);
            a++;
        }
        if(shopCouponHistoryList.size()==a){
            return 1;
        }else
        {
            return 0;
        }
    }

    @Override
    public Integer web_delCouponInfo(String couponCode) throws Exception {
        int a=0;
        try {
            String[] aa=couponCode.split(",");
            for (int i = 0; i <aa.length ; i++) {
                String cou=aa[i].replace("'","");
                couponCode=cou;
                sqlTemplate.delete(getNameSpace("delCouponInfo"),couponCode);
            }
            a=1;
        }catch (Exception e)
        {
            throw e;
        }
        if(a==1){
            return 1;
        }else {
            return 0;
        }
    }

    @Override
    public Integer web_updateCouponInfo(String couponCode, Integer status) throws Exception {
        int a=0;
        try {
            String[] aa=couponCode.split(",");
            for (int i = 0; i <aa.length ; i++) {
                String cou=aa[i].replace("'","");
                couponCode=cou;

                ShopCouponInfo info=new ShopCouponInfo();
                info.setCouponCode(couponCode);
                info.setStatus(status);
                if(status==2)
                {
                    Date userDate= new Date();
                    info.setUsedDate(userDate);
                }
                sqlTemplate.update(getNameSpace("updateCouponInfo"),info);
            }
            a=1;
        }catch (Exception e)
        {
            throw e;
        }
        if(a==1){
            return 1;
        }else {
            return 0;
        }
    }

    @Override
    public Integer web_deleteExpiredCoupons(String history) throws Exception {
        int a=0;
        try {
            String[] aa=history.split(",");
            for (int i = 0; i <aa.length ; i++) {
                String cou=aa[i].replace("'","");
                history=cou;
                sqlTemplate.delete(getNameSpace("deleteExpiredCoupons"),history);
            }
            a=1;
        }catch (Exception e)
        {
            throw e;
        }
        if(a==1){
            return 1;
        }else {
            return 0;
        }
    }

    /**
     * 添加供应商
     * @param users
     * @param supplierInfo
     * @return
     * @throws Exception
     */
    @Override
    public Integer web_InsertSupplierInfo(Users users, SupplierInfo supplierInfo) throws Exception {
        int i=0;
        try {
            sqlTemplate.insert(getNameSpace("insertUsers"), users);
            sqlTemplate.insert(getNameSpace("insertUsersExp"), users);
            sqlTemplate.insert(getNameSpace("insertSupplierInfo"), supplierInfo);
            i=1;
        }catch (Exception e)
        {
            throw e;
        }
        if(i==1){
            return  1;
        }else
        {
            return 0;
        }
    }

    /**
     * 编辑供应商
     * @param supplierInfo
     * @param list
     * @return
     * @throws Exception
     */
    @Override
    public Integer web_updateSupplierInfo(SupplierInfo supplierInfo, List list) throws Exception {
        int m=0;
        try {
            sqlTemplate.update(getNameSpace("updateISuppInfo"), supplierInfo);
            sqlTemplate.delete(getNameSpace("delSupplierBrands"), supplierInfo.getSupplierId());
            Long supplierId = supplierInfo.getSupplierId();
            for (int i = 0; i < list.size(); i++) {
                ShopSupplierBrands shop=new ShopSupplierBrands();
                int brandId =(Integer)list.get(i);
                shop.setBrandId(brandId);
                shop.setSupplierId(Integer.parseInt(String.valueOf(supplierId)));
                sqlTemplate.insert(getNameSpace("addSupplierBrands"), shop);
            }
            m=1;
        }catch (Exception e)
        {
            throw e;
        }
        if(m==1){
            return 1;
        }else {
            return 0;
        }
    }

    /**
     * 删除供应商
     * @param supplierId
     * @return
     * @throws Exception
     */
    @Override
    public Integer web_delSupplierInfo(Integer supplierId) throws Exception {
        int m=0;
        try {
            sqlTemplate.delete(getNameSpace("delSuppProductStatModes"), supplierId);
            sqlTemplate.delete(getNameSpace("delSupplierCategories"), supplierId);
            sqlTemplate.delete(getNameSpace("delSupplierAD"), supplierId);
            sqlTemplate.delete(getNameSpace("delSupplierConfig"), supplierId);
            sqlTemplate.delete(getNameSpace("delSupplierMenus"), supplierId);
            sqlTemplate.delete(getNameSpace("delSupplierScoreDetails"), supplierId);
            sqlTemplate.delete(getNameSpace("delISupplierBrands"), supplierId);
            sqlTemplate.delete(getNameSpace("delShopSuppliers"), supplierId);
            sqlTemplate.update(getNameSpace("updateUserSupplier"), supplierId);
            m=1;
        }catch (Exception e)
        {
            throw e;
        }
        if(m==1){
            return 1;
        }else {
            return 0;
        }
    }

    @Override
    public Integer insertUsersWX(Users users) throws Exception {
        return  sqlTemplate.insert(getNameSpace("insertUsersWX"),users);
    }

    @Override
    public Users selectUserWX(Users users) {
        return  sqlTemplate.selectOne(getNameSpace("selectUserWX"),users);
    }

    //查询微信帐号
    @Override
    public Users selectUserByWeChat(Users users) {
        return sqlTemplate.selectOne("selectUserByWeChat",users);
    }

    @Override
    public Users selUsersByAppIdopenId(Users users) {
        return  sqlTemplate.selectOne(getNameSpace("selUsersByAppIdOpenId"),users);
    }

    @Override
    public UsersExp selectBalanceAndPointsByUserId(UsersExp usersExp) {
        return  sqlTemplate.selectOne(getNameSpace("selectBalanceAndPointsByUserId"),usersExp);
    }
    //初始美豆积分表
    @Override
    public Integer InsertUserExp(Users users) {
        return sqlTemplate.insert(getNameSpace("insertUsersExp"), users);
    }
    //微信余额消费

    @Override
    public Integer updateBalanceConsumeWX(UsersExp usersExp) {
        return sqlTemplate.update(getNameSpace("updateBalanceConsumeWX"),usersExp);
    }
    @Override
    public Users getAccountsUserApp(String userId) {

        return sqlTemplate.selectOne(getNameSpace("getAccountsUserApp"),userId);
    }
    //开启店铺预先修改用户的状态
    @Override
    public Integer updateUserType(Users users) {
        return sqlTemplate.update(getNameSpace("updateUserType"),users);
    }
    //完善店铺下用户的资料
    @Override
    public Integer perfectAccountUser(Users users) {
        return sqlTemplate.update(getNameSpace("perfectAccountUser"),users);
    }
    //查询用户最大userId
    @Override
    public Long selectUserIdMix() {
        return sqlTemplate.selectOne(getNameSpace("selectUserIdMix"),null);
    }
    //查询全部用户
    @Override
    public List<Users> selAllUsers(PageableUsers users) {
        return sqlTemplate.selectList(getNameSpace("getAllUsers"),users);
    }
    @Override
    public Long selAllUsersCount(PageableUsers users) {
        return sqlTemplate.selectOne(getNameSpace("getAllUsersCount"), users);
    }
}
