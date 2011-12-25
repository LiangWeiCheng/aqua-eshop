package com.aqua.material.entitybean;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;


@Entity
@Table(name="material_category")
public class MaterialCategory extends BaseEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Column(nullable=false,name="level",length=1,columnDefinition="int(1)")
	private int level=1;
	
	@ManyToOne(targetEntity=MaterialCategory.class,cascade={CascadeType.MERGE,CascadeType.REFRESH},fetch=FetchType.EAGER,optional=true)
	@JoinColumn(name="parent_category",columnDefinition="bigint(20)",nullable=true)
	private MaterialCategory parentCategory;
	
	@Column(nullable=true,name="description",length=100,columnDefinition="varchar(100)")
	private String description;
	
	@OneToMany(cascade={CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH},fetch=FetchType.LAZY)
	@JoinColumn(name = "parent_category")
	@OrderBy(" name asc ")
	private Set<MaterialCategory> categories = new HashSet<MaterialCategory>();
	
    @OneToMany(cascade={CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH},fetch=FetchType.LAZY)
    @JoinColumn(name = "category_id")
    @OrderBy(" name asc ")
	private Set<Material> materials = new HashSet<Material>();

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		if(level>2){
			level=2;
		}
		this.level = level;
	}

	public MaterialCategory getParentCategory() {
		return parentCategory;
	}

	public void setParentCategory(MaterialCategory parentCategory) {
		if(this.level>1){
			this.parentCategory = parentCategory;
		}else {
			this.parentCategory=null;
		}
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void addMaterials(Material material){
		if(this.level==2){
			this.materials.add(material);
			material.setCategory(this);
		}
	}
	
	public void addCategories(MaterialCategory materialCategory){
		if(this.level==1&&materialCategory.level==2){
			this.categories.add(materialCategory);
			materialCategory.setParentCategory(this);
		}
	}

	public Set<MaterialCategory> getCategories() {
		return categories;
	}

	public void setCategories(Set<MaterialCategory> categories) {
		this.categories = categories;
	}

	public Set<Material> getMaterials() {
		return materials;
	}

	public void setMaterials(Set<Material> materials) {
		this.materials = materials;
	}
	
	public void removeCategory(MaterialCategory category){
		this.categories.remove(category);
	}
	
	public void removeMaterial(Material material){
		this.materials.remove(material);
	}
	
	public boolean equals(MaterialCategory materialCategory){
		if(this.getId()==null||this.getId()==0||materialCategory.getId()==null||materialCategory.getId()==0){
			return false;
		}
		return this.getId().equals(materialCategory.getId());
	}

}
