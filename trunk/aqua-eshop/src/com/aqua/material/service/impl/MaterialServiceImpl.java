package com.aqua.material.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.aqua.material.dao.MaterialDao;
import com.aqua.material.entitybean.Material;
import com.aqua.material.service.MaterialService;
import com.aqua.pingtai.common.QueryResult;

@Transactional
@Service("materialServiceImpl") 
@Scope("singleton")
public class MaterialServiceImpl implements MaterialService {
	
	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(MaterialServiceImpl.class);
	
	@Resource(name="materialDaoImpl")
	public MaterialDao materialDaoImpl;
	
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void saveMaterial(Material material){
		this.materialDaoImpl.saveObject(material);
	}
	
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void saveCommitMaterial(Material material){
		this.materialDaoImpl.saveObject(material);
	}
	
	@Transactional(readOnly = true)
	public Material getMaterial(Long id) {
		return this.materialDaoImpl.findOneObject(Material.class, id);
	}
	
	@Transactional(readOnly = false)
	public void updateMaterial(Material material) {
		this.materialDaoImpl.updateObject(material);
	}
	
	@Transactional(readOnly = false)
	public void updateCommitMaterial(Material material) {
		this.materialDaoImpl.updateCommitObject(material);
	}
	
	@Transactional(readOnly = false)
	public void deleteMaterial(Material material) {
		this.materialDaoImpl.deleteObject(material);
	}
	
	@Transactional(readOnly = false)
	public void deleteCommitMaterial(Material material) {
		this.materialDaoImpl.deleteCommitObject(material);
	}
	
	@Transactional(readOnly = true)
	public Material queryMaterialByName(String name) {
		return this.materialDaoImpl.findObjectByName(Material.class, name);
	}
	
	@Transactional(readOnly = true)
	public Material queryMaterialByCode(String materialCode) {
		List<Material> list = this.materialDaoImpl.findObjectByFilter(Material.class, " o where o.materialCode='"+materialCode+"'");
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	@Transactional(readOnly = true)
	public List<Material> queryMaterial(String queryString) {
		return this.materialDaoImpl.findObjectByFilter(Material.class, queryString);
	}
	
	@Transactional(readOnly = true)
	public List<Material> queryMaterialByBrand(String brand) {
		return this.materialDaoImpl.findObjectByFilter(Material.class, " o where o.brand='"+brand+"'");
	}
	
	public void splitPageQueryMaterial(String filterString,
			String orderByString, QueryResult queryResult) {
		this.materialDaoImpl.findSplitPageByOrder(Material.class, filterString, orderByString, queryResult);
	}

	public MaterialDao getMaterialDaoImpl() {
		return materialDaoImpl;
	}

	public void setMaterialDaoImpl(MaterialDao materialDaoImpl) {
		this.materialDaoImpl = materialDaoImpl;
	}


}
