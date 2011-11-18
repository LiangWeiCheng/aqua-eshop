package com.aqua.pingtai.taglib.bean;

import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;
import java.util.Set;

import com.aqua.pingtai.cache.OscacheFactory;
import com.aqua.pingtai.entity.bean.authority.Dict;

import org.apache.struts2.components.Component;

import com.opensymphony.xwork2.util.ValueStack;

public class DictReadBean extends Component {
	
	private String nodeId;
	private String nodeClass;
	private String nodeStyle;
	private String dictTypeNumbers;
	private String dictNumbers;
	
	//静态oscacheFactory
	public static OscacheFactory oscacheFactory ;
	
	public DictReadBean(ValueStack stack) {
		super(stack);
	}
	
	@SuppressWarnings("unchecked")
	public boolean start(Writer writer) {
    	boolean result = super.start(writer);
    	try {
    		//查询指定的字典类型
    		//DictType dictType = (DictType) oscacheFactory.getObject(dictTypeNumbers);
    		//字典
    		Set<Dict> dictSet = (Set<Dict>) oscacheFactory.getObject(dictTypeNumbers + "dictSet");
    		//Set<Dict> dictSet = dictType.getDictSet();
    		String writeString = "";
    		for (Iterator<Dict> iterator = dictSet.iterator(); iterator.hasNext();) {
				Dict dict = (Dict) iterator.next();
				if(dictNumbers.equals(dict.getNumbers())){//默认选中
					writeString = dict.getNames();
					break;
				}
			}
            writer.write(writeString);//输出
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


}
