package com.zm.mall.taglib;
/**
 * Created by Administrator on 2016/12/21.
 */

import com.zm.mall.client.result.system.UserResult;
import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.directive.Directive;
import org.apache.velocity.runtime.parser.node.Node;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.Writer;

/**
 * @author
 * @create 2016-12-21 14:13
 */
public class VelocityTaglib extends Directive{
    public String getName() {
            return "vela";
    }
    public int getType() {
        return LINE;
    }
    public boolean render(InternalContextAdapter context, Writer writer,Node node) throws IOException, ResourceNotFoundException, ParseErrorException, MethodInvocationException {
        String href = null;
        String name = null;
        String id = null;
        String clazz = null;
        String status="";
        if(node.jjtGetChild(0) != null){
            href = String.valueOf(node.jjtGetChild(0).value(context));
        }
        if(node.jjtGetChild(1) != null){
            name = String.valueOf(node.jjtGetChild(1).value(context));
        }
        if(node.jjtGetChild(2) != null){
            id = String.valueOf(node.jjtGetChild(2).value(context));
        }
        if(node.jjtGetChild(3) != null){
            clazz = String.valueOf(node.jjtGetChild(3).value(context));
        }
        if(node.jjtGetNumChildren()>4 ){
            status = String.valueOf(node.jjtGetChild(4).value(context));
        }
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        UserResult user=(UserResult)request.getSession().getAttribute("userResult");
        int pos = href.indexOf("?");
        String subhref="";
        if (pos > -1) {
            subhref = href.substring(0, pos);
        }else{
            subhref =href;
        }
        if (user.hasPrivilegeByUri(subhref,request)) {
            writer.write("<a   href='"+href+"' id='"+id+"' class='"+clazz+"' status='"+status+"'  >"+name+"</a>");
            return true;
        } else {
            return false;
        }

    }
}
