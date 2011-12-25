package com.aqua.material.service.impl;

import java.util.List;

import org.junit.Test;
import org.springframework.test.AbstractTransactionalSpringContextTests;

import com.aqua.material.entitybean.MaterialCategory;
import com.aqua.material.service.MaterialCategoryService;
import com.aqua.material.service.MaterialService;

public class TestMaterialCategoryServiceImpl extends AbstractTransactionalSpringContextTests{
	
	private MaterialCategoryService materialCategoryServiceImpl;
	
	private MaterialService materialServiceImpl;
	
	@Override
	protected String[] getConfigLocations() {
		String[] configLocations = { "applicationContext.xml" }; 
        return configLocations; 
	}

//	@Test
//	public void testMaterialCategory(){
//		MaterialCategory materialCategory = new MaterialCategory();
//		materialCategory.setDescription("description_sub");
//		materialCategory.setLevel(2);
//		materialCategory.setName("category_subB1");
//		
//		MaterialCategory materialCategory2 = new MaterialCategory();
//		materialCategory2.setDescription("description_subB");
//		materialCategory2.setLevel(2);
//		materialCategory2.setName("category_subB2");
//		
//		MaterialCategory materialCategory3 = new MaterialCategory();
//		materialCategory3.setDescription("description_parent");
//		materialCategory3.setLevel(1);
//		materialCategory3.setName("category_parent");
//		
//		MaterialCategory materialCategory4 = new MaterialCategory();
//		materialCategory4.setDescription("description_subB");
//		materialCategory4.setLevel(2);
//		materialCategory4.setName("category_subB1");
//		
//		assertEquals(materialCategory.getName(), materialCategory4.getName());
//		
//		materialCategory3.addCategories(materialCategory);
//		materialCategory3.addCategories(materialCategory2);
////		materialCategory3.addCategories(materialCategory4);
//		
//		
//		try {
//			this.materialCategoryServiceImpl.saveMaterialCategory(materialCategory3);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		List<MaterialCategory> queryMaterialCategoryList = this.materialCategoryServiceImpl.queryMaterialCategory("");
//		
//		assertEquals(queryMaterialCategoryList.size(), 3);
//		assertEquals(materialCategory2.getParentCategory().getName(), "category_parent");
//	}
	
	@Test
	public void testInitMaterialCategory(){
		MaterialCategory parentCategory = new MaterialCategory();
		parentCategory.setDescription("A New Parent");
		parentCategory.setLevel(1);
		parentCategory.setName("New_Parent");
		
		MaterialCategory categorySub1 = new MaterialCategory();
		categorySub1.setDescription("A New Child1");
		categorySub1.setLevel(2);
		categorySub1.setName("New_Child1");
		
		MaterialCategory categorySub2 = new MaterialCategory();
		categorySub2.setDescription("A New Child2");
		categorySub2.setLevel(2);
		categorySub2.setName("New_Child2");
		
		MaterialCategory categorySub3 = new MaterialCategory();
		categorySub3.setDescription("A New Child3");
		categorySub3.setLevel(2);
		categorySub3.setName("New_Child3");
		
		MaterialCategory categorySub4 = new MaterialCategory();
		categorySub4.setDescription("A New Child4");
		categorySub4.setLevel(2);
		categorySub4.setName("New_Child4");
		
		MaterialCategory categorySub5 = new MaterialCategory();
		categorySub5.setDescription("A New Child5");
		categorySub5.setLevel(2);
		categorySub5.setName("New_Child5");
		
		MaterialCategory categorySub6 = new MaterialCategory();
		categorySub6.setDescription("A New Child6");
		categorySub6.setLevel(2);
		categorySub6.setName("New_Child6");
		
		MaterialCategory categorySub7 = new MaterialCategory();
		categorySub7.setDescription("A New Child7");
		categorySub7.setLevel(2);
		categorySub7.setName("New_Child7");
		
		MaterialCategory categorySub8 = new MaterialCategory();
		categorySub8.setDescription("A New Child8");
		categorySub8.setLevel(2);
		categorySub8.setName("New_Child8");		
		
		MaterialCategory categorySub9 = new MaterialCategory();
		categorySub9.setDescription("A New Child9");
		categorySub9.setLevel(2);
		categorySub9.setName("New_Child9");
		
		MaterialCategory categorySub10 = new MaterialCategory();
		categorySub10.setDescription("A New Child10");
		categorySub10.setLevel(2);
		categorySub10.setName("New_Child10");
		
		parentCategory.addCategories(categorySub1);
		parentCategory.addCategories(categorySub2);
		parentCategory.addCategories(categorySub3);
		parentCategory.addCategories(categorySub4);
		parentCategory.addCategories(categorySub5);
		parentCategory.addCategories(categorySub6);
		parentCategory.addCategories(categorySub7);
		parentCategory.addCategories(categorySub8);
		parentCategory.addCategories(categorySub9);
		parentCategory.addCategories(categorySub10);
		this.materialCategoryServiceImpl.saveCommitMaterialCategory(parentCategory);
		
//		categorySub1.setParentCategory(parentCategory);
//		this.materialCategoryServiceImpl.saveCommitMaterialCategory(categorySub1);
	}
	
//	@Test
//	public void testUpdateCategoryLevel(){
//		MaterialCategory category = this.materialCategoryServiceImpl.queryMaterialCategoryByName("New_Parent");
////		category.setLevel(2);
//		this.materialCategoryServiceImpl.deleteCommitMaterialCategory(category);
//	}
//	
//	@Test
//	public void testDeleteCategory(){
//		MaterialCategory category = this.materialCategoryServiceImpl.queryMaterialCategoryByName("category_sub14");
//		this.materialCategoryServiceImpl.deleteCommitMaterialCategory(category);
//		category.removeMaterial(this.materialServiceImpl.getMaterial(new Long(2)));
//		category.addMaterials(this.materialServiceImpl.getMaterial(new Long(5)));
//		this.materialCategoryServiceImpl.updateCommitMaterialCategory(category);
//	}
	
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

	public void setMaterialServiceImpl(MaterialService materialServiceImpl) {
		this.materialServiceImpl = materialServiceImpl;
	}

}
