package com.aqua.pingtai.service;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.aqua.pingtai.common.QueryResult;
import com.aqua.pingtai.entity.bean.authority.Dict;
import com.aqua.pingtai.entity.bean.authority.DictType;

@Transactional
public interface DictService {
	
	/**
	 * 获取一个数据字典类型
	 * @param dictTypeids
	 * @return
	 */
	public DictType getOneDictType(Long dictTypeIds);
	
	/**
	 * 查询字典类型列表
	 * @param queryResult
	 * @throws Exception
	 */
	public void splitPageQueryDictType(String filterString, QueryResult queryResult) throws Exception;
	
	/**
	 * 保存字典类型和字典
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void saveDictTypeAndDict(DictType dictType, List<Dict> dictList);
	
	/**
	 * 更新字典类型和字典
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void updateDictTypeAndDict(DictType dictType, List<Dict> dictList);
	
	/**
	 * 删除数据字典类型和字典
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void deleteDictTypeAndDict(DictType dictType);
	
}