package com.aqua.material.service;

import java.util.List;

import com.aqua.material.entitybean.Material;
import com.aqua.pingtai.common.QueryResult;

public interface MaterialService {
	
	public void saveMaterial(Material material);
	
	public void saveCommitMaterial(Material material);
	
	public Material getMaterial(Long id);
	
	public void updateMaterial(Material material);
	
	public void updateCommitMaterial(Material material);
	
	public void deleteMaterial(Material material);
	
	public void deleteCommitMaterial(Material material);
	
	public Material queryMaterialByName(String name);
	
	public List<Material> queryMaterial(String queryString);
	
	public void splitPageQueryMaterial(String filterString, String orderByString, QueryResult queryResult);
	
	public Material queryMaterialByCode(String materialCode);
	
	public List<Material> queryMaterialByBrand(String brand);

}
