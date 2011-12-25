package com.aqua.material.entitybean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name="material")
public class Material extends BaseEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Column(nullable=true,name="brand",length=50,columnDefinition="varchar(50)")
	private String brand;
	
	@Column(nullable=true,name="description",length=100,columnDefinition="varchar(100)")
	private String description;
	
	@ManyToOne(targetEntity=MaterialCategory.class)
	@JoinColumn(name="category_id",columnDefinition="bigint(20)",nullable=true)
	@ForeignKey(name = "FKMaterialCategory")
	private MaterialCategory category;
	
	@Column(nullable=false,unique=true,name="material_code",length=50,columnDefinition="varchar(50)")
    private String materialCode;

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public MaterialCategory getCategory() {
		return category;
	}

	public void setCategory(MaterialCategory category) {
		if(category!=null&&category.getLevel()==2){
			this.category = category;
		}
	}

	public String getMaterialCode() {
		return materialCode;
	}

	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}
	
	public boolean equals(Material material){
		if(this.getId()==null||this.getId()==0||material.getId()==null||material.getId()==0){
			return false;
		}
		return this.getId().equals(material.getId());
	}

}
