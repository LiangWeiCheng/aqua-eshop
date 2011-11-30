package com.aqua.pingtai.action;

import java.util.List;

import javax.annotation.Resource;

import com.aqua.pingtai.common.QueryParameter;
import com.aqua.pingtai.entity.bean.authority.Dict;
import com.aqua.pingtai.entity.bean.authority.DictType;
import com.aqua.pingtai.service.DictService;

@SuppressWarnings({"serial"})
public class DictManagerAction extends BaseAction {
	
	@Resource(name="dictServiceImpl")
	protected DictService dictServiceImpl;
	
	private DictType dictType;
	private Dict dict;
	private List<Dict> dictList;
	
	/**
	 * 保存数据字典类型
	 * @return
	 */
	public String saveDictType(){
		if(null!=dictType && null!=dictList){
			dictServiceImpl.saveDictTypeAndDict(dictType, dictList);
		}
		returnPageURL = "/pingTai/dictPingTaiAction!dictTypeList.action";
		return "redirect";
	}
	
	/**
	 * 分页显示数据字典类型
	 * @return
	 */
	public String dictTypeList(){
		try {
			String hqlFilter = initQueryList(true);//过滤HQL
			hqlFilter += " order by orderIds asc";
			dictServiceImpl.splitPageQueryDictType(hqlFilter, queryResult);
		} catch (Exception e) {
			e.printStackTrace();
		}
		returnPageURL = "/WEB-INF/jsp/pingtai/dict/dictTypeList.jsp";
		return "dispatcher";
	}
	
	/**
	 * 查看某类型的数据字典
	 * @return
	 */
	public String viewDicts(){
		if(null!=dictType && 0l!=dictType.getIds()){
			dictType = dictServiceImpl.getOneDictType(dictType.getIds());
		}
		returnPageURL = "/WEB-INF/jsp/pingtai/dict/dictTypeView.jsp";
		return "dispatcher";
	}
	
	/**
	 * 准备更新某类型的数据字典
	 * @return
	 */
	public String toUpdateDictType(){
		if(null!=dictType && 0l!=dictType.getIds()){
			dictType = dictServiceImpl.getOneDictType(dictType.getIds());
		}
		returnPageURL = "/WEB-INF/jsp/pingtai/dict/dictTypeUpdate.jsp";
		return "dispatcher";
	}
	
	/**
	 * 更新数据字典类型
	 * @return
	 */
	public String updateDictType(){
		if(null!=dictType && null!=dictList){
			dictServiceImpl.updateDictTypeAndDict(dictType, dictList);
		}
		returnPageURL = "/pingTai/dictPingTaiAction!dictTypeList.action";
		return "redirect";
	}
	
	/**
	 * 删除字典类型
	 * @return
	 */
	public String deleteDictType(){
		if(null!=dictType && dictType.getIds()!=0l){
			dictServiceImpl.deleteDictTypeAndDict(dictType);
		}
		returnPageURL = "/pingTai/dictPingTaiAction!dictTypeList.action";
		return "redirect";
	}
	
	@Override
	protected String getActionClassFullName() {
		return "com.aqua.pingtai.action.pingtai.DictManagerAction.java";
	}

	@Override
	protected String makeFilterString(QueryParameter queryParameter) {
		StringBuffer sb = new StringBuffer();
		
		String names = queryParameter.getParameter("names");
		if(null!=names && !names.equals("")){
			sb.append(" names like '%").append(names.trim()).append("%'").append(" and ");
		}
		
		String numbers = queryParameter.getParameter("numbers");
		if(null!=numbers && !numbers.equals("")){
			sb.append(" numbers like '%").append(numbers.trim()).append("%'").append(" and ");
		}
		
		String sbString = sb.toString().trim();
		if(sbString.endsWith("and")){
			sbString = sbString.substring(0, sbString.length()-3);
		}
		return sbString;
	}

	public DictService getDictServiceImpl() {
		return dictServiceImpl;
	}

	public void setDictServiceImpl(DictService dictServiceImpl) {
		this.dictServiceImpl = dictServiceImpl;
	}

	public DictType getDictType() {
		return dictType;
	}

	public void setDictType(DictType dictType) {
		this.dictType = dictType;
	}

	public Dict getDict() {
		return dict;
	}

	public void setDict(Dict dict) {
		this.dict = dict;
	}

	public List<Dict> getDictList() {
		return dictList;
	}

	public void setDictList(List<Dict> dictList) {
		this.dictList = dictList;
	}

}
