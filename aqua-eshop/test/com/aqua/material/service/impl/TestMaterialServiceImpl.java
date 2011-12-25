package com.aqua.material.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.test.AbstractTransactionalSpringContextTests;

import com.aqua.material.entitybean.Material;
import com.aqua.material.entitybean.MaterialCategory;
import com.aqua.material.service.MaterialCategoryService;
import com.aqua.material.service.MaterialService;

public class TestMaterialServiceImpl extends AbstractTransactionalSpringContextTests{
	
	private MaterialCategoryService materialCategoryServiceImpl;
	
	private MaterialService materialServiceImpl;
	
	@Override
	protected String[] getConfigLocations() {
		String[] configLocations = { "applicationContext.xml", "applicationContext-compass.xml" }; 
        return configLocations; 
	}
	
	@Test
	public void testMaterial(){
		MaterialCategory materialCategory = new MaterialCategory();
		materialCategory.setDescription("description_sub");
		materialCategory.setLevel(2);
		materialCategory.setName("category_sub1");
		
		MaterialCategory materialCategory2 = new MaterialCategory();
		materialCategory2.setDescription("description_sub");
		materialCategory2.setLevel(2);
		materialCategory2.setName("category_sub2");
		
		MaterialCategory materialCategory3 = new MaterialCategory();
		materialCategory3.setDescription("description_parent");
		materialCategory3.setLevel(1);
		materialCategory3.setName("category_parent");
		
		Material material1 = new Material();
		material1.setBrand("brand");
		material1.setDescription("material_description1");
		material1.setMaterialCode("materialCode1");
		material1.setName("材料1");
		
		Material material2 = new Material();
		material2.setBrand("brand");
		material2.setDescription("material_description2");
		material2.setMaterialCode("materialCode2");
		material2.setName("材料2");
		
		Material material3 = new Material();
		material3.setBrand("brand2");
		material3.setDescription("material_description3");
		material3.setMaterialCode("materialCode3");
		material3.setName("材料3");
		
		Material material4 = new Material();
		material4.setBrand("brand");
		material4.setDescription("material_description4");
		material4.setMaterialCode("materialCode4");
		material4.setName("材料4");
		
		materialCategory.addMaterials(material1);
		materialCategory2.addMaterials(material2);
		materialCategory2.addMaterials(material4);
		
		materialCategory3.addCategories(materialCategory);
		materialCategory3.addCategories(materialCategory2);
		
		try {
			this.materialCategoryServiceImpl.saveMaterialCategory(materialCategory3);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<MaterialCategory> queryMaterialCategoryList = this.materialCategoryServiceImpl.queryMaterialCategory("");
		
		assertEquals(queryMaterialCategoryList.size(), 3);
		assertEquals(materialCategory2.getParentCategory().getName(), "category_parent");
		
		MaterialCategory parentCategory = this.materialCategoryServiceImpl.queryMaterialCategoryByName("category_parent");
		assertTrue(parentCategory.getCategories().contains(materialCategory2));
		
		List<Material> list = new ArrayList<Material>();
		for (MaterialCategory subCategory : parentCategory.getCategories()) {
			assertNotNull(subCategory.getId());
			assertTrue(subCategory.getLevel()==2);
			list.addAll(subCategory.getMaterials());
			if(subCategory.getName().equals("category_sub1")){
				material3.setCategory(subCategory);
			}
		}
		
		assertEquals(list.size(), 3);
		for (Material material : list) {
			System.out.println(material.getName());
			System.out.println(material.getCategory().getName());
		}
		
		this.materialServiceImpl.saveMaterial(material3);
		
		List<Material> materialList = this.materialServiceImpl.queryMaterial("");
		assertEquals(materialList.size(), 4);
		
		Material materialByCode = this.materialServiceImpl.queryMaterialByCode("materialCode2");
		assertEquals(materialByCode.getName(), "材料2");
		
		List<Material> materialByBandList = this.materialServiceImpl.queryMaterialByBrand("brand");
		assertEquals(materialByBandList.size(), 3);
	}

	public MaterialCategoryService getMaterialCategoryServiceImpl() {
		return materialCategoryServiceImpl;
	}

	public void setMaterialCategoryServiceImpl(
			MaterialCategoryService materialCategoryServiceImpl) {
		this.materialCategoryServiceImpl = materialCategoryServiceImpl;
	}

	public MaterialService getMaterialServiceImpl() {
		return materialServiceImpl;
	}

	public void setMaterialService(MaterialService materialServiceImpl) {
		this.materialServiceImpl = materialServiceImpl;
	}

}
