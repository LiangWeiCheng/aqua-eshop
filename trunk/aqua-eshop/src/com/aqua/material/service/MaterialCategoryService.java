package com.aqua.material.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.aqua.material.entitybean.MaterialCategory;
import com.aqua.pingtai.common.QueryResult;

@Transactional
public interface MaterialCategoryService {
	
	public void saveMaterialCategory(MaterialCategory materialCategory);
	
	public void saveCommitMaterialCategory(MaterialCategory materialCategory);
	
	public MaterialCategory getMaterialCategory(Long id);
	
	public void updateMaterialCategory(MaterialCategory materialCategory);
	
	public void updateCommitMaterialCategory(MaterialCategory materialCategory);
	
	public void deleteMaterialCategory(MaterialCategory materialCategory);
	
	public void deleteCommitMaterialCategory(MaterialCategory materialCategory);
	
	public MaterialCategory queryMaterialCategoryByName(String name);
	
	public List<MaterialCategory> queryMaterialCategory(String queryString);
	
	public void splitPageQueryMaterialCategory(String filterString, String orderByString, QueryResult queryResult);

}
