package com.zm.mall.service.business;


import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.transport.http.AbstractHTTPDestination;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Iterator;
import java.util.List;


/**
 * Created by 胡道谱 on 2016/12/23. webServices拦截器使用
 */
public class CheckUserInterceptor extends AbstractPhaseInterceptor {
    public CheckUserInterceptor() {
        super(Phase.PRE_PROTOCOL);// 准备协议化时拦截
    }

    @Override
    public void handleMessage(Message message) throws Fault {
      /*  Request.ServerVariables.Get("Local_Addr").ToString();//服务器Ip
        Request.ServerVariables["Local_Addr"];*/
        HttpServletRequest request = (HttpServletRequest) message.get(AbstractHTTPDestination.HTTP_REQUEST);
        String ipAddress="";//客户端Ip 访问者的
        String ip="";//xml中
        String filePath=CheckUserInterceptor.class.getClassLoader().getResource("ipConfig.xml").getPath();
        try {
            SAXReader sax=new SAXReader();
            Document document=sax.read(new File(filePath));
            Element root=document.getRootElement();//获取根节点
            for(Iterator it=root.elementIterator();it.hasNext();){
                Element element = (Element) it.next();
                ip=String.valueOf(element.getText());//遍历 获取Ip
            }
        }catch (Exception e) {
           e.printStackTrace();
        }
        if (null != request) {
           // ipAddress = request.getLocalAddr(); // 取服务器IP地址
            ipAddress = request.getRemoteAddr();//获取客户端ip地址
            if(ipAddress.equals(ip)){
                return;
            }
        }
        throw new Fault(new RuntimeException("对不起，你没有该权限!!"));
    }
}
