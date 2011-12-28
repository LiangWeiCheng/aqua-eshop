package com.aqua.material.action;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.aqua.material.entitybean.Material;
import com.aqua.material.entitybean.MaterialCategory;
import com.aqua.material.service.MaterialCategoryService;
import com.aqua.material.service.MaterialService;
import com.aqua.material.util.AjaxCodeUtil;
import com.aqua.pingtai.action.BaseAction;
import com.aqua.pingtai.common.Context;
import com.aqua.pingtai.common.QueryParameter;

public class MaterialAction extends BaseAction{

	private static final long serialVersionUID = 1L;
	
	@Resource(name="materialServiceImpl")
	private MaterialService materialServiceImpl;
	
	@Resource(name="materialCategoryServiceImpl")
	private MaterialCategoryService materialCategoryServiceImpl;
	
	private long selectedId;
	
	private Material selectedMaterial = new Material();
	
	private boolean update=false;
	
	private long categoryId;
	
	private String materialName;
	
	private long[] selection;
	
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	public HashMap<String, Serializable> materialMap = new JSONObject();
	
	public String materialList(){
		initQueryList(true);
		if(this.materialName==null||this.materialName.replaceAll(" ", "").equals("")){
			materialServiceImpl.splitPageQueryMaterial(""," order by createdDate desc", queryResult);
		}else {
			this.materialName = AjaxCodeUtil.unescape(this.materialName);
			materialServiceImpl.splitPageQueryMaterial(" where name='"+this.materialName+"'", " order by createdDate desc", queryResult);
		}
		returnPageURL = "/WEB-INF/jsp/material_management/material_list.jsp";
		return "dispatcher";
	}
	
//	public String materialList(){
//		returnPageURL = "/WEB-INF/jsp/material_management/material_list.jsp";
//		return "dispatcher";
//	}
//	
//	public String materialListJson(){
//		try {
//			List<Material> list = null;
//			if(this.materialName==null||this.materialName.replaceAll(" ", "").equals("")){
//				list = materialServiceImpl.queryMaterial(" order by createdDate desc");
//			}else {
//				list = materialServiceImpl.queryMaterial(" where name='"+this.materialName+"' order by createdDate desc");
//			}
//			JSONArray jsonArray = new JSONArray();
//			for (Material material : list) {
//				JSONObject jsonObject = new JSONObject();
//				jsonObject.put("materialId", material.getId());
//				jsonObject.put("name", material.getName());
//				jsonObject.put("materialCode", material.getMaterialCode());
//				if(material.getCategory()!=null){
//					jsonObject.put("categoryName", material.getCategory().getName());
//				}else {
//					jsonObject.put("categoryName", "");
//				}
//				jsonObject.put("description", material.getDescription());
//				if(material.getCreatedDate()!=null){
//					jsonObject.put("createdDate", dateFormat.format(material.getCreatedDate()));
//				}else {
//					jsonObject.put("createdDate", "");
//				}
//				jsonArray.add(jsonObject);
//			}
//			materialMap.put("items", jsonArray);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return "materialList";
//	}
	
	public String materialShow(){
		if(this.selectedId!=0){
			this.selectedMaterial = materialServiceImpl.getMaterial(selectedId);
		}
		returnPageURL = "/WEB-INF/jsp/material_management/material_edit.jsp";
		return "dispatcher";		
	}
	
	public String materialEdit(){
		if(categoryId==0){
			selectedMaterial.setCategory(null);
		}else {
			MaterialCategory category = this.materialCategoryServiceImpl.getMaterialCategory(categoryId);
			selectedMaterial.setCategory(category);
		}
		selectedMaterial.setName(AjaxCodeUtil.unescape(selectedMaterial.getName()));
		selectedMaterial.setBrand(AjaxCodeUtil.unescape(selectedMaterial.getBrand()));
		selectedMaterial.setDescription(AjaxCodeUtil.unescape(selectedMaterial.getDescription()));
		selectedMaterial.setMaterialCode(AjaxCodeUtil.unescape(selectedMaterial.getMaterialCode()));
		if(update){
			selectedMaterial.setCreatedDate(materialServiceImpl.getMaterial(selectedMaterial.getId()).getCreatedDate());
			materialServiceImpl.updateMaterial(selectedMaterial);
		}else {
			materialServiceImpl.saveMaterial(selectedMaterial);
		}
		returnPageURL = "/material/materialAction!materialList.action";
		return "redirect";	
	}
	
	public String materialDelete(){
		if(selection!=null&&selection.length>0){
			for (long deleteMaterialId : selection) {
				materialServiceImpl.deleteMaterial(materialServiceImpl.getMaterial(deleteMaterialId));
			}
		}
		return "";
	}

	@Override
	protected String makeFilterString(QueryParameter queryParameter) {
		return "";
	}

	@Override
	protected String getActionClassFullName() {
		return "com.aqua.material.action.MaterialAction.java";
	}

	public MaterialService getMaterialServiceImpl() {
		return materialServiceImpl;
	}

	public void setMaterialServiceImpl(MaterialService materialServiceImpl) {
		this.materialServiceImpl = materialServiceImpl;
	}

	public long getSelectedId() {
		return selectedId;
	}

	public void setSelectedId(long selectedId) {
		this.selectedId = selectedId;
	}

	public Material getSelectedMaterial() {
		return selectedMaterial;
	}

	public void setSelectedMaterial(Material selectedMaterial) {
		this.selectedMaterial = selectedMaterial;
	}

	public boolean isUpdate() {
		return update;
	}

	public void setUpdate(boolean update) {
		this.update = update;
	}

	public long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

	public MaterialCategoryService getMaterialCategoryServiceImpl() {
		return materialCategoryServiceImpl;
	}

	public void setMaterialCategoryServiceImpl(
			MaterialCategoryService materialCategoryServiceImpl) {
		this.materialCategoryServiceImpl = materialCategoryServiceImpl;
	}

	public HashMap<String, Serializable> getMaterialMap() {
		return materialMap;
	}

	public void setMaterialMap(HashMap<String, Serializable> materialMap) {
		this.materialMap = materialMap;
	}

	public long[] getSelection() {
		return selection;
	}

	public void setSelection(long[] selection) {
		this.selection = selection;
	}

	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	protected String initQueryList(boolean isRowFilter) {
		queryParameter = new QueryParameter();
		initQueryResult(BaseAction.queryResultCountPingTai);
		return "";
	}
	
}
