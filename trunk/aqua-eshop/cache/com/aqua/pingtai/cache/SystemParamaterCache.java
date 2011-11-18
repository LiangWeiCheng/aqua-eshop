package com.aqua.pingtai.cache;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import org.springframework.jdbc.core.RowMapper;

import com.aqua.pingtai.dao.base.DaoHibernateBase;
import com.aqua.pingtai.dao.base.DaoJdbcBase;
import com.aqua.pingtai.entity.bean.authority.Dict;
import com.aqua.pingtai.entity.bean.authority.DictType;
import com.aqua.pingtai.entity.bean.authority.Group;
import com.aqua.pingtai.entity.bean.authority.Operator;

/**
 * 系统缓存操作类
 */
public class SystemParamaterCache {
	
	//静态oscacheFactory
	public static OscacheFactory oscacheFactory ;
	
	//静态daoHibernateBase
	public static DaoHibernateBase daoHibernateBase ;
	
	//静态daoJdbcBase
	public static DaoJdbcBase daoJdbcBase;
	
	//缓存业务字典
	public static void cacheAllDict(){
		try {
			List<DictType> dictTypeList = daoHibernateBase.findManyEntity(DictType.class, "");
			for (DictType dictType : dictTypeList) {
				oscacheFactory.putObject(dictType.getNumbers(), dictType);
				Set<Dict> dictSet = dictType.getDictSet();
				oscacheFactory.putObject(dictType.getNumbers() + "dictSet", dictSet);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//缓存操作
	public static void cacheAllOperator(){
		try {
			List<Operator> operatorList = daoHibernateBase.findManyEntity(Operator.class, "");
			for (Operator operator : operatorList) {
				oscacheFactory.putObject(operator.getUrl(), operator);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//缓存所有组对应的操作
	@SuppressWarnings("unchecked")
	public static void cacheAllGroupOperator(){
		try {
			RowMapper mapper = new RowMapper() {
		        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		            Operator operator = new Operator();
		            operator.setIds(rs.getLong("ids"));
		            operator.setNames(rs.getString("names"));
		            operator.setUrl(rs.getString("url"));
		            return operator;
		        }
		    };
			List<Group> groupList = daoHibernateBase.findManyEntity(Group.class, "");
			for (Group group : groupList) {
				String selectOperator = "select ids,names,url from pingtai_operator where ids in (select operatorIds from pingtai_roleoperator where roleIds in (select roleIds from pingtai_rolegroup where groupIds="+group.getIds()+"))";
			    List<Operator> operatorList =  (List<Operator>)( daoJdbcBase.getJdbcTemplate().query(selectOperator, mapper));
			    oscacheFactory.putObject(group.getIds()+"groupIds", operatorList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
