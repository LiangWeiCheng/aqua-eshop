package com.aqua.pingtai.dao.base;

import java.util.List;

import com.aqua.pingtai.entity.base.EntityBase;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

/**
 * Spring JDBC 操作封装
 */
@SuppressWarnings("unchecked")
public class DaoJdbcBase extends JdbcDaoSupport{
	
	/**例子:内部类实现
		String sql = "select id, first_name, last_name from T_ACTOR where id = ?";
	    RowMapper mapper = new RowMapper() {
	        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
	            Actor actor = new Actor();
	            actor.setId(rs.getLong("id"));
	            actor.setFirstName(rs.getString("first_name"));
	            actor.setLastName(rs.getString("last_name"));
	            return actor;
	        }
	    };
	    return (Actor) jdbcTemplate.queryForObject(sql, mapper, new Object[] {Long.valueOf(id)});
	*/
	
	/**
	 * 保存、更新、删除一个实体
	 * @param sql
	 * @param objectArray
	 * @param intArray
	 */
	public void saveOrUpdate(String sql, Object[] objectArray, int[] intArray) {
		getJdbcTemplate().update(sql, objectArray, intArray);
		/**
		 * 1.删除
		 * getJdbcTemplate().update("delete from module where ids=?", new Object[]{personid},new int[]{java.sql.Types.BIGINT});
		 * 2.保存
		 * getJdbcTemplate().update("insert into module(name) values(?)", new Object[]{module.getNames()}, new int[]{java.sql.Types.VARCHAR});
		 * 3.更新
		 * getJdbcTemplate().update("update set module name=? where ids=?", new Object[]{module.getNames(), module.getIds()}, new int[]{java.sql.Types.VARCHAR,java.sql.Types.BIGINT});
		 */
	}

	/**
	 * 查询单个实体
	 * @param <T>
	 * @param sql
	 * @param objectArray
	 * @param intArray
	 * @param classFullName
	 * @return
	 */
	public <T extends EntityBase> T getOneEntity(String sql, Object[] objectArray, int[] intArray, String classFullName) {
		T entity = null;;
		try {
			//Module person=(Module) getJdbcTemplate().queryForObject("select * from module where ids=?", new Object[]{moduleIds}, new int[]{java.sql.Types.BIGINT},new ModuleResultSetMap());
			entity = (T) getJdbcTemplate().queryForObject(sql, objectArray, intArray, (RowMapper) Class.forName(classFullName).newInstance());
		} catch (DataAccessException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return entity;
	}
	
	/**
	 * 查询多个对象
	 * @param <T>
	 * @param sql
	 * @param classFullName
	 * @return
	 */
	public <T extends EntityBase> List<T> getManyEntity(String sql, String classFullName) {
		List<T> list = null;
		try {
			//return (List<Module>)( getJdbcTemplate().query("select * from module", new ModuleResultSetMap()));
			list =  (List<T>)( getJdbcTemplate().query(sql, (RowMapper) Class.forName(classFullName).newInstance()));
		} catch (DataAccessException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return list;
	}

	
	
}
