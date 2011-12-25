package com.aqua.material.action;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.aqua.material.entitybean.MaterialCategory;
import com.aqua.material.service.MaterialCategoryService;
import com.aqua.material.util.AjaxCodeUtil;
import com.aqua.pingtai.action.BaseAction;
import com.aqua.pingtai.common.QueryParameter;

public class MaterialCategoryAction extends BaseAction{

	private static final long serialVersionUID = 1L;
	
	@Resource(name="materialCategoryServiceImpl")
	private MaterialCategoryService materialCategoryServiceImpl;
	
	private long selectedId;
	
	private long[] selection;
	
	private MaterialCategory selectedCategory = new MaterialCategory();
	
	private long parentCategoryId;
	
	private boolean update=false;
	
	private String categoryName;
	
	private int level;
	
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	public HashMap<String, Serializable> categoryMap = new JSONObject();
	
	private List<MaterialCategory> materialCategories = new ArrayList<MaterialCategory>();
	
	public String categoryList(){
		returnPageURL = "/WEB-INF/jsp/material_management/material_category_list.jsp";
		return "dispatcher";
	}
	
	public String categoryListJson(){
		try {
			List<MaterialCategory> list = null;
			if(this.categoryName==null||this.categoryName.replaceAll(" ", "").equals("")){
				list = materialCategoryServiceImpl.queryMaterialCategory(" order by createdDate desc");
			}else {
				list = materialCategoryServiceImpl.queryMaterialCategory(" where name='"+this.categoryName+"' order by createdDate desc");
			}
			JSONArray jsonArray = new JSONArray();
			for (MaterialCategory materialCategory : list) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("categoryId", materialCategory.getId());
				jsonObject.put("name", materialCategory.getName());
				jsonObject.put("level", materialCategory.getLevel());
				if(materialCategory.getParentCategory()==null){
					jsonObject.put("parentCategory.name", "");
				}else{
					jsonObject.put("parentCategory.name", materialCategory.getParentCategory().getName());
				}
				jsonObject.put("description", materialCategory.getDescription());
				if(materialCategory.getCreatedDate()!=null){
					jsonObject.put("createdDate", dateFormat.format(materialCategory.getCreatedDate()));
				}else {
					jsonObject.put("createdDate", "");
				}
				jsonArray.add(jsonObject);
			}
			categoryMap.put("items", jsonArray);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "categoryList";
	}
	
	public String categoryShow(){
		if(this.selectedId!=0){
			this.selectedCategory = materialCategoryServiceImpl.getMaterialCategory(selectedId);
		}
		returnPageURL = "/WEB-INF/jsp/material_management/material_category_edit.jsp";
		return "dispatcher";		
	}
	
	public String categoryEdit(){
		if(this.parentCategoryId==0){
			selectedCategory.setParentCategory(null);
		}else {
			MaterialCategory parentCategory = this.materialCategoryServiceImpl.getMaterialCategory(this.parentCategoryId);
			selectedCategory.setParentCategory(parentCategory);
		}
		selectedCategory.setName(AjaxCodeUtil.unescape(selectedCategory.getName()));
		selectedCategory.setDescription(AjaxCodeUtil.unescape(selectedCategory.getDescription()));
		if(update){
			selectedCategory.setCreatedDate(materialCategoryServiceImpl.getMaterialCategory(selectedCategory.getId()).getCreatedDate());
			materialCategoryServiceImpl.updateMaterialCategory(selectedCategory);
		}else {
			materialCategoryServiceImpl.saveMaterialCategory(selectedCategory);
		}
		returnPageURL = "/material/materialCategoryAction!categoryList.action";
		return "redirect";	
	}
	
	public String categoryDelete(){
		if(selection!=null&&selection.length>0){
			for (long deleteCategoryId : selection) {
				materialCategoryServiceImpl.deleteMaterialCategory(materialCategoryServiceImpl.getMaterialCategory(deleteCategoryId));
			}
		}
		return "";
	}
	
	public String selectCategories(){
		if(this.categoryName==null||this.categoryName.equals("")){
			materialCategories = this.materialCategoryServiceImpl.queryMaterialCategory(" where level="+level+" order by createdDate desc");
		}else {
			this.categoryName=AjaxCodeUtil.unescape(this.categoryName);
			materialCategories = this.materialCategoryServiceImpl.queryMaterialCategory(" where level="+level+" and name='"+ this.categoryName+ "' order by createdDate desc");
		}
		returnPageURL = "/WEB-INF/jsp/material_management/select_category.jsp";
		return "dispatcher";
	}
	
	@Override
	protected String makeFilterString(QueryParameter queryParameter) {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	protected String getActionClassFullName() {
		return "com.aqua.material.action.MaterialCategoryAction.java";
	}

	public MaterialCategoryService getMaterialCategoryServiceImpl() {
		return materialCategoryServiceImpl;
	}

	public void setMaterialCategoryServiceImpl(
			MaterialCategoryService materialCategoryServiceImpl) {
		this.materialCategoryServiceImpl = materialCategoryServiceImpl;
	}

	public long getSelectedId() {
		return selectedId;
	}

	public void setSelectedId(long selectedId) {
		this.selectedId = selectedId;
	}

	public MaterialCategory getSelectedCategory() {
		return selectedCategory;
	}

	public void setSelectedCategory(MaterialCategory selectedCategory) {
		this.selectedCategory = selectedCategory;
	}

	public long getParentCategoryId() {
		return parentCategoryId;
	}

	public void setParentCategoryId(long parentCategoryId) {
		this.parentCategoryId = parentCategoryId;
	}

	public boolean isUpdate() {
		return update;
	}

	public void setUpdate(boolean update) {
		this.update = update;
	}

	public HashMap<String, Serializable> getCategoryMap() {
		return categoryMap;
	}

	public void setCategoryMap(HashMap<String, Serializable> categoryMap) {
		this.categoryMap = categoryMap;
	}

	public long[] getSelection() {
		return selection;
	}

	public void setSelection(long[] selection) {
		this.selection = selection;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public List<MaterialCategory> getMaterialCategories() {
		return materialCategories;
	}

	public void setMaterialCategories(List<MaterialCategory> materialCategories) {
		this.materialCategories = materialCategories;
	}

}
