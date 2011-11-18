package com.aqua.pingtai.entity.bean.authority;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Index;

import com.aqua.pingtai.entity.base.EntityBase;

@SuppressWarnings("serial")
@Entity
@Table(name="pingtai_dict")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class Dict extends EntityBase{
	
	@Index(name="numbersIndex",columnNames="numbers")
	@Column(nullable=true,name="numbers",length=50,columnDefinition="varchar(50)")
	private String numbers;//唯一编号
	
	@Column(nullable=true,name="names",length=25,columnDefinition="varchar(25)")
	private String names;//字典名称
	
	@Column(nullable=true,name="value",length=50,columnDefinition="varchar(50)")
	private String value;//字典值
	
	@Column(name="orderIds",length=20,nullable=true,columnDefinition="bigint(20)")
	private Long orderIds;//排序
	
	/**
	 * 多个字典对应的字典类型
	 */
	@ManyToOne(targetEntity=DictType.class,
			cascade={CascadeType.REFRESH,CascadeType.MERGE,CascadeType.PERSIST},
			fetch=FetchType.EAGER,optional=true)//多对一
	@JoinColumn(name="dictTypeIds",columnDefinition="bigint(20)",nullable=true,updatable=false)//外键名称
	private DictType dictType;//dictTypeIds;

	public String getNames() {
		return names;
	}

	public void setNames(String names) {
		this.names = names;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Long getOrderIds() {
		return orderIds;
	}

	public void setOrderIds(Long orderIds) {
		this.orderIds = orderIds;
	}

	public DictType getDictType() {
		return dictType;
	}

	public void setDictType(DictType dictType) {
		this.dictType = dictType;
	}
	
	public String getNumbers() {
		return numbers;
	}

	public void setNumbers(String numbers) {
		this.numbers = numbers;
	}

/*	@Override
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
		Dict other = (Dict) obj;
		if (ids == null) {
			if (other.ids != null)
				return false;
		} else if (!ids.equals(other.ids))
			return false;
		return true;
	}*/
	
}
