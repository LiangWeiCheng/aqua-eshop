package com.aqua.application.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.aqua.pingtai.entity.base.EntityBase;
import com.aqua.pingtai.entity.bean.authority.User;

//请假
@SuppressWarnings("serial")
@Entity
@Table(name="application_qingjia")
public class QingJia extends EntityBase {
	
	@ManyToOne(cascade={CascadeType.REFRESH}, fetch=FetchType.LAZY, optional=true, targetEntity=User.class)
	@JoinColumn(name="qingJiaUserIds",columnDefinition="bigint(20)",nullable=true)
	private User qingJiaUser;//请假者

	@Column(name="qingJiaDes",length=200,columnDefinition="varchar(200)",nullable=true)
	private String qingJiaDes;//请假原因

	@Column(nullable=true,name="countDay",length=20,columnDefinition="bigint(20)")
	private Long countDay;//请假天数
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=true,name="countDate")
	private Date countDate;//请假时间
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=true,name="startDate")
	private Date startDate;//开始时间
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=true,name="endDate")
	private Date endDate;//结束时间
	
	@Column(name="buMenJingLiShenPi",length=1,columnDefinition="char(1)",nullable=true)
	private String buMenJingLiShenPi;//部门经理审批
	
	@Column(name="buMenJingLiDes",length=200,columnDefinition="varchar(200)",nullable=true)
	private String buMenJingLiDes;//部门经理批注
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=true,name="buMenJingLiDate")
	private Date buMenJingLiDate;//部门经理审批时间
	
	@Column(name="zongJingLiShenPi",length=1,columnDefinition="char(1)",nullable=true)
	private String zongJingLiShenPi;//总经理审批
	
	@Column(name="zongJingLiDes",length=200,columnDefinition="varchar(200)",nullable=true)
	private String zongJingLiDes;//总经理批注
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=true,name="zongJingLiDate")
	private Date zongJingLiDate;//总经理审批时间
	
	@Column(name="qingJiaState",length=20,columnDefinition="varchar(20)",nullable=true)
	private String qingJiaState;//办理状态
	
	public User getQingJiaUser() {
		return qingJiaUser;
	}

	public void setQingJiaUser(User qingJiaUser) {
		this.qingJiaUser = qingJiaUser;
	}

	public String getQingJiaDes() {
		return qingJiaDes;
	}

	public void setQingJiaDes(String qingJiaDes) {
		this.qingJiaDes = qingJiaDes;
	}

	public Long getCountDay() {
		return countDay;
	}

	public void setCountDay(Long countDay) {
		this.countDay = countDay;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getBuMenJingLiShenPi() {
		return buMenJingLiShenPi;
	}

	public void setBuMenJingLiShenPi(String buMenJingLiShenPi) {
		this.buMenJingLiShenPi = buMenJingLiShenPi;
	}

	public String getBuMenJingLiDes() {
		return buMenJingLiDes;
	}

	public void setBuMenJingLiDes(String buMenJingLiDes) {
		this.buMenJingLiDes = buMenJingLiDes;
	}

	public String getZongJingLiShenPi() {
		return zongJingLiShenPi;
	}

	public void setZongJingLiShenPi(String zongJingLiShenPi) {
		this.zongJingLiShenPi = zongJingLiShenPi;
	}

	public String getZongJingLiDes() {
		return zongJingLiDes;
	}

	public void setZongJingLiDes(String zongJingLiDes) {
		this.zongJingLiDes = zongJingLiDes;
	}

	public Date getCountDate() {
		return countDate;
	}

	public void setCountDate(Date countDate) {
		this.countDate = countDate;
	}

	public Date getBuMenJingLiDate() {
		return buMenJingLiDate;
	}

	public void setBuMenJingLiDate(Date buMenJingLiDate) {
		this.buMenJingLiDate = buMenJingLiDate;
	}

	public Date getZongJingLiDate() {
		return zongJingLiDate;
	}

	public void setZongJingLiDate(Date zongJingLiDate) {
		this.zongJingLiDate = zongJingLiDate;
	}

	public String getQingJiaState() {
		return qingJiaState;
	}

	public void setQingJiaState(String qingJiaState) {
		this.qingJiaState = qingJiaState;
	}
	
	
}
