package com.aqua.material.service.impl;

import java.util.HashSet;
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
import com.aqua.material.entitybean.MaterialCategory;
import com.aqua.material.service.MaterialCategoryService;
import com.aqua.pingtai.common.QueryResult;

@Transactional
@Service("materialCategoryServiceImpl")
@Scope("singleton")
public class MaterialCategoryServiceImpl  implements MaterialCategoryService{
	
	public MaterialCategoryServiceImpl(){
		
	}
	
	@SuppressWarnings("unused")
	private static final Log log = LogFactory.getLog(MaterialCategoryServiceImpl.class);
	
	@Resource(name="materialDaoImpl")
	public MaterialDao materialDaoImpl;
	
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void saveMaterialCategory(MaterialCategory materialCategory) {
		this.materialDaoImpl.saveObject(materialCategory);
	}
	
	@Transactional(readOnly = false,propagation=Propagation.REQUIRED)
	public void saveCommitMaterialCategory(MaterialCategory materialCategory) {
		this.materialDaoImpl.saveCommitObject(materialCategory);
	}
	
	@Transactional(readOnly = true)
	public MaterialCategory getMaterialCategory(Long id) {
		return this.materialDaoImpl.findOneObject(MaterialCategory.class, id);
	}
	
	@Transactional(readOnly = false)
	public void updateMaterialCategory(MaterialCategory materialCategory) {
		if(materialCategory.getLevel()==1){
			materialCategory.setMaterials(new HashSet<Material>());
			materialCategory.setParentCategory(null);
		}else {
			materialCategory.setCategories(new HashSet<MaterialCategory>());
		}
		this.materialDaoImpl.updateObject(materialCategory);
	}
	
	@Transactional(readOnly = false)
	public void updateCommitMaterialCategory(MaterialCategory materialCategory) {
		if(materialCategory.getLevel()==1){
			materialCategory.setMaterials(new HashSet<Material>());
			materialCategory.setParentCategory(null);
		}else {
			materialCategory.setCategories(new HashSet<MaterialCategory>());
		}
		this.materialDaoImpl.updateCommitObject(materialCategory);
	}
	
	@Transactional(readOnly = true)
	public MaterialCategory queryMaterialCategoryByName(String name) {
		return this.materialDaoImpl.findObjectByName(MaterialCategory.class, name);
	}
	
	@Transactional(readOnly = false)
	public void deleteMaterialCategory(MaterialCategory materialCategory) {
		if(materialCategory.getLevel()==1){
			materialCategory.setCategories(new HashSet<MaterialCategory>());
		}else {
			materialCategory.setMaterials(new HashSet<Material>());
		}
		this.materialDaoImpl.updateObject(materialCategory);
		this.materialDaoImpl.deleteObject(materialCategory);
	}
	
	@Transactional(readOnly = false)
	public void deleteCommitMaterialCategory(MaterialCategory materialCategory) {
		if(materialCategory.getLevel()==1){
			materialCategory.setCategories(new HashSet<MaterialCategory>());
		}else {
			materialCategory.setMaterials(new HashSet<Material>());
		}
		this.materialDaoImpl.updateCommitObject(materialCategory);
		this.materialDaoImpl.deleteCommitObject(materialCategory);
	}
	
	@Transactional(readOnly = true)
	public List<MaterialCategory> queryMaterialCategory(String queryString) {
		return this.materialDaoImpl.findObjectByFilter(MaterialCategory.class, queryString);
	}
	
	public void splitPageQueryMaterialCategory(String filterString, String orderByString, QueryResult queryResult) {
		this.materialDaoImpl.findSplitPageByOrder(MaterialCategory.class, filterString, orderByString, queryResult);
	}

	public MaterialDao getMaterialDaoImpl() {
		return materialDaoImpl;
	}

	public void setMaterialDaoImpl(MaterialDao materialDaoImpl) {
		this.materialDaoImpl = materialDaoImpl;
	}


}
