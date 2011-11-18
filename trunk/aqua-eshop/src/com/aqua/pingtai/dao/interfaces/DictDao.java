package com.aqua.pingtai.dao.interfaces;

import com.aqua.pingtai.entity.bean.authority.Dict;
import com.aqua.pingtai.entity.bean.authority.DictType;

public interface DictDao {
	
	/**
	 * 获取一个数据字典类型
	 * @param dictTypeids
	 * @return
	 */
	public DictType getOneDictType(Long dictTypeids);
	
	/**
	 * 保存一个字典
	 * @param user
	 */
	public void saveOneDict(Dict dict);
	
	
}