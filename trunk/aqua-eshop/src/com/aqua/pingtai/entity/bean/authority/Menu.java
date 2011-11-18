package com.aqua.pingtai.entity.bean.authority;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.aqua.pingtai.entity.base.EntityBase;

@SuppressWarnings("serial")
@Entity
@Table(name="pingtai_menu")
public class Menu extends EntityBase{
	
	@Column(nullable=true,name="names",length=25,columnDefinition="varchar(25)")
	private String names;
	
	@Column(nullable=true,name="url",length=200,columnDefinition="varchar(200)")
	private String url;
	
	@Column(nullable=true,name="menuType",length=50,columnDefinition="varchar(50)")
	private String menuType;
	
	@Column(nullable=true,name="menuLevel",length=20,columnDefinition="bigint(20)")
	private Long menuLevel;

	@Column(nullable=true,name="orderIds",length=20,columnDefinition="bigint(20)")
	private Long orderIds;
	
	@Column(nullable=true,name="images",length=50,columnDefinition="varchar(50)")
	private String images;
	
	@Column(name="description",length=200,columnDefinition="varchar(200)",nullable=true)
	private String description;
	
	@OneToOne(cascade={CascadeType.REFRESH},fetch=FetchType.LAZY,optional=true,targetEntity=Operator.class)//optional=true,optional声明关系是否是必须存在的,即是否允许其中一端为null
	@JoinColumn(name="operatorIds",columnDefinition="bigint(20)",nullable=true)
	private Operator operator;
	
	@ManyToOne(targetEntity=Menu.class,
			cascade={CascadeType.REFRESH},
			fetch=FetchType.EAGER,optional=true)//多对一
	@JoinColumn(name="parentMenuIds",columnDefinition="bigint(20)",nullable=true)//外键名称
	private Menu parentMenu;
	
	@OneToMany(cascade={CascadeType.REFRESH},
			fetch=FetchType.LAZY,
			targetEntity=Menu.class,
			mappedBy="parentMenu")//****使用了mappedBy属性的一端就是关系被维护端
	@OrderBy("orderIds asc")
	private Set<Menu> menuSet = new HashSet<Menu>();

	public String getNames() {
		return names;
	}

	public void setNames(String names) {
		this.names = names;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Menu getParentMenu() {
		return parentMenu;
	}

	public void setParentMenu(Menu parentMenu) {
		this.parentMenu = parentMenu;
	}

	public Set<Menu> getMenuSet() {
		return menuSet;
	}

	public void setMenuSet(Set<Menu> menuSet) {
		this.menuSet = menuSet;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMenuType() {
		return menuType;
	}

	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}

	public Long getMenuLevel() {
		return menuLevel;
	}

	public void setMenuLevel(Long menuLevel) {
		this.menuLevel = menuLevel;
	}

	public Long getOrderIds() {
		return orderIds;
	}

	public void setOrderIds(Long orderIds) {
		this.orderIds = orderIds;
	}

	public Operator getOperator() {
		return operator;
	}

	public void setOperator(Operator operator) {
		this.operator = operator;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}
	
	/*
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ids == null) ? 0 : ids.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Menu other = (Menu) obj;
		if (ids == null) {
			if (other.ids != null)
				return false;
		} else if (!ids.equals(other.ids))
			return false;
		return true;
	}
	*/
	
}
