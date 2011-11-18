package com.aqua.pingtai.entity.bean.authority;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.aqua.pingtai.entity.base.EntityBase;

import org.compass.annotations.Index;
import org.compass.annotations.Searchable;
import org.compass.annotations.SearchableProperty;
import org.compass.annotations.Store;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@SuppressWarnings("serial")
@Entity
@Table(name="pingtai_userinfo")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
@Searchable(root=false)
public class UserInfo extends EntityBase {

	@OneToOne(mappedBy="userInfo",targetEntity=User.class,
			cascade={CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH},
			fetch=FetchType.EAGER,optional=true)
	private User user;

	@Column(nullable=true,name="integral",length=20,columnDefinition="bigint(20)")
	@SearchableProperty(name="integral",index=Index.NA,store=Store.YES,boost=1)//store是否存储; boost:优先级
	private Long integral;//用户总积分
	
	@Column(nullable=true,name="consumeIntegral",length=20,columnDefinition="bigint(20)")
	@SearchableProperty(name="consumeIntegral",index=Index.NA,store=Store.YES,boost=1)
	private Long consumeIntegral;//用户已消费积分
	
	@Column(nullable=true,name="inefficacyIntegral",length=20,columnDefinition="bigint(20)")
	@SearchableProperty(name="inefficacyIntegral",index=Index.NA,store=Store.YES,boost=1)
	private Long inefficacyIntegral;//用户无效积分

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=true,name="clientLevelStart")
	@SearchableProperty(name="clientLevelStart",index=Index.NA,store=Store.YES,boost=1)
	private Date clientLevelStart;//会员开始时间
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=true,name="clientLevelEnd")
	@SearchableProperty(name="clientLevelEnd",index=Index.NA,store=Store.YES,boost=1)
	private Date clientLevelEnd;//会员结束时间
	
	@Column(name="alipayUserIds",length=100,columnDefinition="varchar(100)",nullable=true)
	@SearchableProperty(name="alipayUserIds",index=Index.NA,store=Store.YES,boost=1)
	private String alipayUserIds;//支付宝ID
	
	@Column(name="names",length=25,columnDefinition="varchar(25)",nullable=true)
	@SearchableProperty(name="names",index=Index.NA,store=Store.YES,boost=1)
	private String names;//姓名
	
	@Column(name="sex",nullable=true,length=5,columnDefinition="varchar(5)")
	@SearchableProperty(name="sex",index=Index.NA,store=Store.YES,boost=1)
	private String sex;//性别
	
	@Column(name="email",length=100,columnDefinition="varchar(100)",nullable=true)
	@SearchableProperty(name="email",index=Index.NA,store=Store.YES,boost=1)
	private String email;//邮箱
	
	@Column(name="mobile",length=20,columnDefinition="varchar(20)",nullable=true)
	@SearchableProperty(name="mobile",index=Index.NA,store=Store.YES,boost=1)
	private String mobile;//手机
	
	@Column(name="telephone",length=20,columnDefinition="varchar(20)",nullable=true)
	@SearchableProperty(name="telephone",index=Index.NA,store=Store.YES,boost=1)
	private String telephone;//电话
	
	@Column(name="postboy",length=6,columnDefinition="varchar(6)",nullable=true)
	@SearchableProperty(name="postboy",index=Index.NA,store=Store.YES,boost=1)
	private String postboy;//邮编
	
	@Column(name="address",length=300,columnDefinition="varchar(300)",nullable=true)
	@SearchableProperty(name="address",index=Index.NA,store=Store.YES,boost=1)
	private String address;//地址
	
	@Column(name="idCard",length=25,columnDefinition="varchar(25)",nullable=true)
	@SearchableProperty(name="idCard",index=Index.NA,store=Store.YES,boost=1)
	private String idCard;//身份证
	
	@Column(name="qq",length=20,columnDefinition="varchar(20)",nullable=true)
	@SearchableProperty(name="qq",index=Index.NA,store=Store.YES,boost=1)
	private String qq;//QQ

	@Temporal(TemporalType.DATE)
	@Column(nullable=true,name="birthday")
	@SearchableProperty(name="birthday",index=Index.NA,store=Store.YES,boost=1)
	protected Date birthday;//生日
	
	@Column(name="folk",length=20,columnDefinition="varchar(20)",nullable=true)
	@SearchableProperty(name="folk",index=Index.NA,store=Store.YES,boost=1)
	private String folk;//民族
	
	@Column(name="marriage",nullable=true,length=20,columnDefinition="varchar(20)")
	@SearchableProperty(name="marriage",index=Index.NA,store=Store.YES,boost=1)
	private String marriage;//婚姻状况
	
	@Column(name="government",length=10,columnDefinition="varchar(25)",nullable=true)
	@SearchableProperty(name="government",index=Index.NA,store=Store.YES,boost=1)
	private String government;//政治面貌
	
	@Column(name="stature",length=5,columnDefinition="varchar(5)",nullable=true)
	@SearchableProperty(name="stature",index=Index.NA,store=Store.YES,boost=1)
	private String stature;//身高
	
	@Column(name="avoirdupois",length=5,columnDefinition="varchar(5)",nullable=true)
	@SearchableProperty(name="avoirdupois",index=Index.NA,store=Store.YES,boost=1)
	private String avoirdupois;//体重
	
	@Column(name="bloodGroup",length=15,columnDefinition="varchar(15)",nullable=true)
	@SearchableProperty(name="bloodGroup",index=Index.NA,store=Store.YES,boost=1)
	private String bloodGroup;//血型
	
	@Column(name="nativityAddress",length=20,columnDefinition="varchar(20)",nullable=true)
	@SearchableProperty(name="nativityAddress",index=Index.NA,store=Store.YES,boost=1)
	private String nativityAddress;//出生地
	
	@Column(name="householder",length=20,columnDefinition="varchar(20)",nullable=true)
	@SearchableProperty(name="householder",index=Index.NA,store=Store.YES,boost=1)
	private String householder;//户口所在地
	
	@Column(name="culture",length=30,columnDefinition="varchar(30)",nullable=true)
	@SearchableProperty(name="culture",index=Index.NA,store=Store.YES,boost=1)
	private String culture;//文化程度
	
	@Column(name="schoolName",length=20,columnDefinition="varchar(20)",nullable=true)
	@SearchableProperty(name="schoolName",index=Index.NA,store=Store.YES,boost=1)
	private String schoolName;//毕业院校
	
	@Column(name="speciality",length=20,columnDefinition="varchar(20)",nullable=true)
	@SearchableProperty(name="speciality",index=Index.NA,store=Store.YES,boost=1)
	private String speciality;//专业

	@Temporal(TemporalType.DATE)
	@Column(nullable=true,name="finishSchoolDate")
	@SearchableProperty(name="finishSchoolDate",index=Index.NA,store=Store.YES,boost=1)
	protected Date finishSchoolDate;//毕业时间
	
	@Column(name="homepage",length=100,columnDefinition="varchar(100)",nullable=true)
	@SearchableProperty(name="homepage",index=Index.NA,store=Store.YES,boost=1)
	private String homepage;//个人主页
	
	@Column(name="description",length=200,columnDefinition="varchar(200)",nullable=true)
	@SearchableProperty(name="description",index=Index.NA,store=Store.YES,boost=1)
	private String description;//人员说明
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getNames() {
		return names;
	}

	public void setNames(String names) {
		this.names = names;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getPostboy() {
		return postboy;
	}

	public void setPostboy(String postboy) {
		this.postboy = postboy;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getFolk() {
		return folk;
	}

	public void setFolk(String folk) {
		this.folk = folk;
	}

	public String getGovernment() {
		return government;
	}

	public void setGovernment(String government) {
		this.government = government;
	}

	public String getStature() {
		return stature;
	}

	public void setStature(String stature) {
		this.stature = stature;
	}

	public String getAvoirdupois() {
		return avoirdupois;
	}

	public void setAvoirdupois(String avoirdupois) {
		this.avoirdupois = avoirdupois;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public String getNativityAddress() {
		return nativityAddress;
	}

	public void setNativityAddress(String nativityAddress) {
		this.nativityAddress = nativityAddress;
	}

	public String getHouseholder() {
		return householder;
	}

	public void setHouseholder(String householder) {
		this.householder = householder;
	}

	public String getCulture() {
		return culture;
	}

	public void setCulture(String culture) {
		this.culture = culture;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	public Date getFinishSchoolDate() {
		return finishSchoolDate;
	}

	public void setFinishSchoolDate(Date finishSchoolDate) {
		this.finishSchoolDate = finishSchoolDate;
	}

	public String getHomepage() {
		return homepage;
	}

	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getMarriage() {
		return marriage;
	}

	public void setMarriage(String marriage) {
		this.marriage = marriage;
	}
	
	public Long getIntegral() {
		return integral;
	}

	public void setIntegral(Long integral) {
		this.integral = integral;
	}

	public Long getConsumeIntegral() {
		return consumeIntegral;
	}

	public void setConsumeIntegral(Long consumeIntegral) {
		this.consumeIntegral = consumeIntegral;
	}

	public Date getClientLevelStart() {
		return clientLevelStart;
	}

	public void setClientLevelStart(Date clientLevelStart) {
		this.clientLevelStart = clientLevelStart;
	}

	public Date getClientLevelEnd() {
		return clientLevelEnd;
	}

	public void setClientLevelEnd(Date clientLevelEnd) {
		this.clientLevelEnd = clientLevelEnd;
	}

	public Long getInefficacyIntegral() {
		return inefficacyIntegral;
	}

	public void setInefficacyIntegral(Long inefficacyIntegral) {
		this.inefficacyIntegral = inefficacyIntegral;
	}

	public String getAlipayUserIds() {
		return alipayUserIds;
	}

	public void setAlipayUserIds(String alipayUserIds) {
		this.alipayUserIds = alipayUserIds;
	}

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
		UserInfo other = (UserInfo) obj;
		if (ids == null) {
			if (other.ids != null)
				return false;
		} else if (!ids.equals(other.ids))
			return false;
		return true;
	}
}
