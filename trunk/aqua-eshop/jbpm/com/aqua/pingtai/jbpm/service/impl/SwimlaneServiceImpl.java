package com.aqua.pingtai.jbpm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.aqua.pingtai.common.Context;
import com.aqua.pingtai.dao.base.DaoHibernateBase;
import com.aqua.pingtai.entity.bean.authority.User;
import com.aqua.pingtai.jbpm.dao.interfaces.SwimlaneDao;
import com.aqua.pingtai.jbpm.entity.Swimlane;
import com.aqua.pingtai.jbpm.service.SwimlaneService;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("swimlaneServiceImpl")
@Scope("singleton")
public class SwimlaneServiceImpl implements SwimlaneService {
	
	//private static final Log log = LogFactory.getLog(SwimlaneServiceImpl.class);
	
	@Resource(name="swimlaneDaoImpl")
	private SwimlaneDao swimlaneDaoImpl;
	
	@Resource(name="daoHibernateBase")
	private DaoHibernateBase daoHibernateBase;
	
	public SwimlaneServiceImpl(){
		
	}

	public SwimlaneDao getSwimlaneDaoImpl() {
		return swimlaneDaoImpl;
	}

	public void setSwimlaneDaoImpl(SwimlaneDao swimlaneDaoImpl) {
		this.swimlaneDaoImpl = swimlaneDaoImpl;
	}

	public DaoHibernateBase getDaoHibernateBase() {
		return daoHibernateBase;
	}

	public void setDaoHibernateBase(DaoHibernateBase daoHibernateBase) {
		this.daoHibernateBase = daoHibernateBase;
	}
	
	/**
	 * 更新泳道
	 * @param swimlaneList
	 * @param processDefinitionId
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void swimlaneUpdate(List<Swimlane> swimlaneList, String processDefinitionId) {
		User createUser = Context.getCurrentUser();
		try {
			StringBuffer hqlFilter = new StringBuffer("(");
			for (int j = 0; j < swimlaneList.size()-1; j++) {
				Swimlane swimlane = swimlaneList.get(j);
				if(null != swimlane.getIds()){
					daoHibernateBase.updateOneEntity(swimlane, Context.getCurrentUser());
				}else{
					daoHibernateBase.saveOneEntity(swimlane, createUser);
				}
				hqlFilter.append(swimlane.getIds()).append(",");
			}
			hqlFilter.append(swimlaneList.get(swimlaneList.size()-1).getIds());
			hqlFilter.append(")");
			daoHibernateBase.excuteHQL("delete from Swimlane where processDefinitionId='"+processDefinitionId+"' and ids not in " + hqlFilter.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * HQL查询泳道
	 * @param hql
	 * @return
	 */
	public List<Swimlane> getSwimlaneByHql(String hql){
		List<Swimlane> SwimlaneList = null;
		try {
			SwimlaneList =  daoHibernateBase.findManyEntity(Swimlane.class, hql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SwimlaneList;
	}
	
	
}
