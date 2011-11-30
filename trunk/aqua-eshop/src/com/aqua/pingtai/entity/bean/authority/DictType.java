package com.aqua.pingtai.entity.bean.authority;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Index;

import com.aqua.pingtai.entity.base.EntityBase;

@SuppressWarnings("serial")
@Entity
@Table(name="pingtai_dicttype")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class DictType extends EntityBase{
	
	@Index(name="numbersIndex",columnNames="numbers")
	@Column(nullable=true,name="numbers",length=50,columnDefinition="varchar(50)",unique=true)
	private String numbers;//唯一编号
	
	@Column(nullable=true,name="names",length=25,columnDefinition="varchar(25)")
	private String names;//字典类型名称
	
	@Column(name="orderIds",length=20,nullable=true,columnDefinition="bigint(20)")
	private Long orderIds;//排序
	
	/**
	 * 字典类型拥有的字典
	 */
	@OneToMany(cascade={CascadeType.REFRESH},fetch=FetchType.LAZY,targetEntity=Dict.class,mappedBy="dictType")
	@OrderBy("createdDate orderIds asc")
	private Set<Dict> dictSet = new HashSet<Dict>();

	public String getNames() {
		return names;
	}

	public void setNames(String names) {
		this.names = names;
	}

	public Long getOrderIds() {
		return orderIds;
	}

	public void setOrderIds(Long orderIds) {
		this.orderIds = orderIds;
	}

	public Set<Dict> getDictSet() {
		return dictSet;
	}

	public void setDictSet(Set<Dict> dictSet) {
		this.dictSet = dictSet;
	}

	public String getNumbers() {
		return numbers;
	}

	public void setNumbers(String numbers) {
		this.numbers = numbers;
	}

	@Override
	public String toString() {
		return "DictType [numbers=" + numbers + ", names=" + names
				+ ", orderIds=" + orderIds + ", dictSet=" + dictSet + "]";
	}

	/*@Override
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
		DictType other = (DictType) obj;
		if (ids == null) {
			if (other.ids != null)
				return false;
		} else if (!ids.equals(other.ids))
			return false;
		return true;
	}*/
	
	
}
