package com.zm.mall.client.service.test;

import com.zm.mall.client.service.business.accountsUsers.UserListService;
import com.zm.mall.domain.business.accountsUsers.PointsDetail;
import com.zm.mall.domain.business.accountsUsers.UsersExp;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;

/**
 * Created by Administrator on 2016/12/10.
 */
public class ServiceClient {
    public static void main(String[] args){
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(UserListService.class);
        UsersExp usersExp=new UsersExp();
        PointsDetail pointsDetail =new PointsDetail();
        usersExp.setUserId(1);
        usersExp.setBalance(20.00);
        pointsDetail.setUserId(1);
        pointsDetail.setScore(12);
        pointsDetail.setExtData("2012");

        factory.setAddress("http://192.168.1.107:8080/webservice/accountsUsers?wsdl");//设置请求接口
        UserListService userListService = (UserListService)factory.create();//创建客户端对象
       Client client = ClientProxy.getClient(userListService);
        HTTPConduit http = (HTTPConduit) client.getConduit();
        HTTPClientPolicy httpClientPolicy =  new  HTTPClientPolicy();
        httpClientPolicy.setConnectionTimeout( 36000 );//链接超时
        httpClientPolicy.setAllowChunking(false);
        httpClientPolicy.setReceiveTimeout(300000);//请求超时时间
        http.setClient(httpClientPolicy);
        try {
            userListService.insertPoints(usersExp,pointsDetail);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("invoke webservice...");
    }
}
