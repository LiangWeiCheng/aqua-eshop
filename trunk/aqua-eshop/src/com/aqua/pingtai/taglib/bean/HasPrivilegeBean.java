package com.aqua.pingtai.taglib.bean;

import java.io.IOException;
import java.io.Writer;

import com.aqua.pingtai.cache.OscacheFactory;

import org.apache.struts2.components.Component;

import com.opensymphony.xwork2.util.ValueStack;

public class HasPrivilegeBean extends Component {

	private String operator;
	private String htmlSrc;
	
	//静态oscacheFactory
	public static OscacheFactory oscacheFactory ;

	public HasPrivilegeBean(ValueStack stack) {
		super(stack);
	}

    public boolean start(Writer writer) {
    	boolean result = super.start(writer);
    	try {
            StringBuilder str = new StringBuilder();
            //boolean isValid = Context.hasPrivilege(operator);//权限判断
            Object operatorObj = oscacheFactory.getObject(operator);
            if(operatorObj != null){
                str.append(htmlSrc);//输出显示代码
            }
            writer.write(str.toString());
        }catch(IOException ex){
            ex.printStackTrace();
        }
        return result;
    }

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getHtmlSrc() {
		return htmlSrc;
	}

	public void setHtmlSrc(String htmlSrc) {
		this.htmlSrc = htmlSrc;
	}

}
