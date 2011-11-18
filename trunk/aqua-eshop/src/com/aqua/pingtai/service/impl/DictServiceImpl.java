package com.aqua.pingtai.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import com.aqua.pingtai.cache.OscacheFactory;
import com.aqua.pingtai.common.Context;
import com.aqua.pingtai.common.QueryResult;
import com.aqua.pingtai.dao.base.DaoHibernateBase;
import com.aqua.pingtai.dao.interfaces.DictDao;
import com.aqua.pingtai.entity.bean.authority.Dict;
import com.aqua.pingtai.entity.bean.authority.DictType;
import com.aqua.pingtai.entity.bean.authority.User;
import com.aqua.pingtai.service.DictService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("dictServiceImpl")
@Scope("singleton")
public class DictServiceImpl implements DictService {
	
	private static final Log log = LogFactory.getLog(DictServiceImpl.class);
	
	@Resource(name="dictDaoImpl")
	private DictDao dictDaoImpl;
	
	@Resource(name="daoHibernateBase")
	private DaoHibernateBase daoHibernateBase;
	
	@Resource(name="oscacheFactory")
	private OscacheFactory oscacheFactory;
	
	public DictServiceImpl(){
		
	}

	public DictDao getDictDaoImpl() {
		return dictDaoImpl;
	}

	public void setDictDaoImpl(DictDao dictDaoImpl) {
		this.dictDaoImpl = dictDaoImpl;
	}

	public DaoHibernateBase getDaoHibernateBase() {
		return daoHibernateBase;
	}

	public void setDaoHibernateBase(DaoHibernateBase daoHibernateBase) {
		this.daoHibernateBase = daoHibernateBase;
	}

	public OscacheFactory getOscacheFactory() {
		return oscacheFactory;
	}

	public void setOscacheFactory(OscacheFactory oscacheFactory) {
		this.oscacheFactory = oscacheFactory;
	}
	
	/**
	 * 获取一个数据字典类型
	 * @param dictTypeids
	 * @return
	 */
	public DictType getOneDictType(Long dictTypeIds){
		DictType dictType = null;
		try {
			if(null!=dictTypeIds && dictTypeIds!=0l){
				dictType = daoHibernateBase.findOneEntity(DictType.class, dictTypeIds);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dictType;
	}

	/**
	 * 查询字典类型列表
	 * @param queryResult
	 * @throws Exception
	 */
	public void splitPageQueryDictType(String filterString, QueryResult queryResult) throws Exception {
		daoHibernateBase.findSplitPage(DictType.class, filterString, queryResult);
	}
	
	/**
	 * 保存字典类型和字典
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void saveDictTypeAndDict(DictType dictType, List<Dict> dictList){
		try {
			User createUser = Context.getCurrentUser();
			daoHibernateBase.saveOneEntity(dictType, createUser);//Context.getCurrentUser()
			Dict dict = null;
			Set<Dict> dictSet = new HashSet<Dict>();
			for (int i=0; i<dictList.size(); i++) {
				dict = null;
				dict = dictList.get(i);
				if(null==dict){
					continue;
				}
				dict.setDictType(dictType);
				daoHibernateBase.saveOneEntity(dict, createUser);//Context.getCurrentUser()
				dictSet.add(dict);
			}
			oscacheFactory.putObject(dictType.getNumbers(), dictType);//缓存
			oscacheFactory.putObject(dictType.getNumbers() + "dictSet", dictSet);//缓存
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 更新字典类型和字典
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void updateDictTypeAndDict(DictType dictType, List<Dict> dictList){
		User updateUser = Context.getCurrentUser();
		try {
			daoHibernateBase.excuteHQL("delete from Dict where dictType.ids="+dictType.getIds());//先清除以前的字典
			daoHibernateBase.updateOneEntity(dictType, updateUser);//更新字典类型Context.getCurrentUser()
			Dict dict = null;
			Set<Dict> dictSet = new HashSet<Dict>();
			for (int i=0; i<dictList.size(); i++) {
				dict = null;
				dict = dictList.get(i);
				if(null==dict){
					continue;
				}
				dict.setDictType(dictType);
				daoHibernateBase.saveOneEntity(dict, updateUser);//保存字典Context.getCurrentUser()
				dictSet.add(dict);
			}
			oscacheFactory.putObject(dictType.getNumbers(), dictType);//缓存
			oscacheFactory.putObject(dictType.getNumbers() + "dictSet", dictSet);//缓存
			log.info(updateUser.getUserName()+",更新字典类型和字典,字典类型:"+dictType.getNames());
		} catch (Exception e) {
			log.error(updateUser.getUserName()+",更新字典类型和字典异常!!!字典类型:"+dictType.getNames());
			e.printStackTrace();
		}
	}
	
	/**
	 * 删除数据字典类型和字典
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void deleteDictTypeAndDict(DictType dictType){
		try {
			dictType = daoHibernateBase.findOneEntity(DictType.class, dictType.getIds());
			oscacheFactory.removeObject(dictType.getNumbers());//删除缓存
			oscacheFactory.removeObject(dictType.getNumbers() + "dictSet");//删除缓存
			daoHibernateBase.excuteHQL("delete from Dict where dictType.ids="+dictType.getIds());
			daoHibernateBase.excuteHQL("delete from DictType where ids="+dictType.getIds());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
}
