package com.aqua.pingtai.taglib.bean;

import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;
import java.util.Set;

import com.aqua.pingtai.cache.OscacheFactory;
import com.aqua.pingtai.entity.bean.authority.Dict;

import org.apache.struts2.components.Component;

import com.opensymphony.xwork2.util.ValueStack;

public class DictSelectBean extends Component {
	
	private String nodeId;
	private String nodeClass;
	private String nodeStyle;
	private String selectName;
	private String dictTypeNumbers;
	private String dictNumbers;

	//静态oscacheFactory
	public static OscacheFactory oscacheFactory ;
	
	public DictSelectBean(ValueStack stack) {
		super(stack);
	}
	
    @SuppressWarnings("unchecked")
	public boolean start(Writer writer) {
    	boolean result = super.start(writer);
    	try {
    		//获取spring bean
    		//DaoHibernateBase daoHibernateBase = (DaoHibernateBase)Context.getSpringBean("daoHibernateBase");
    		//查询指定的字典类型
    		//DictType dictType = daoHibernateBase.findOneEntity(DictType.class, " where numbers='" + dictTypeNumbers + "'");
    		
    		//查询指定的字典类型
    		//DictType dictType = (DictType) oscacheFactory.getObject(dictTypeNumbers);
    		//字典
    		Set<Dict> dictSet = (Set<Dict>) oscacheFactory.getObject(dictTypeNumbers + "dictSet");
    		//Set<Dict> dictSet = dictType.getDictSet();
    		//select_option字符串
    		StringBuilder selectOptionSrc = new StringBuilder();
    		if(null!=nodeId && nodeId.trim().length()!=0){
    			selectOptionSrc.append("<select id=\"").append(nodeId).append("\" name=\"").append(selectName).append("\" class=\"").append(nodeClass).append("\" style=\"").append(nodeStyle).append("\" >");
    		}else{
    			selectOptionSrc.append("<select name=\"").append(selectName).append("\" class=\"").append(nodeClass).append("\" style=\"").append(nodeStyle).append("\" >");
    		}	
    		
    		for (Iterator<Dict> iterator = dictSet.iterator(); iterator.hasNext();) {
				Dict dict = (Dict) iterator.next();
				if(!dictNumbers.equals(dict.getNumbers())){//非默认选中
					selectOptionSrc.append("<option value=\"").append(dict.getValue()).append("\">")
												.append(dict.getNames())
									.append("</option>");
				}else{//默认选中
					selectOptionSrc.append("<option value=\"").append(dict.getValue()).append("\" selected=\"selected\">")
										.append(dict.getNames())
									.append("</option>");
				}
			}
            selectOptionSrc.append("</select>");
            
            writer.write(selectOptionSrc.toString());//输出seelct_option代码
        }catch(IOException ex){
            ex.printStackTrace();
        } catch (Exception e) {
			e.printStackTrace();
		}
        return result;
    }

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public String getSelectName() {
		return selectName;
	}

	public void setSelectName(String selectName) {
		this.selectName = selectName;
	}

	public String getDictTypeNumbers() {
		return dictTypeNumbers;
	}

	public void setDictTypeNumbers(String dictTypeNumbers) {
		this.dictTypeNumbers = dictTypeNumbers;
	}

	public String getDictNumbers() {
		return dictNumbers;
	}

	public void setDictNumbers(String dictNumbers) {
		this.dictNumbers = dictNumbers;
	}

	public String getNodeClass() {
		return nodeClass;
	}

	public void setNodeClass(String nodeClass) {
		this.nodeClass = nodeClass;
	}

	public String getNodeStyle() {
		return nodeStyle;
	}

	public void setNodeStyle(String nodeStyle) {
		this.nodeStyle = nodeStyle;
	}

	

}
