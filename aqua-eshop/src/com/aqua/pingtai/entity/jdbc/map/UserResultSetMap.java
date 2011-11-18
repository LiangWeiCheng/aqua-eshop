package com.aqua.pingtai.entity.jdbc.map;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.aqua.pingtai.entity.bean.authority.User;

import org.springframework.jdbc.core.RowMapper;


public class UserResultSetMap implements RowMapper {
	
	/**
	 * JDBC查询封装
	 */
	public Object mapRow(ResultSet rs, int index) throws SQLException {
		User module=new User();
		module.setIds(rs.getLong("ids"));
		module.setUserName(rs.getString("username"));
		return module;
	}

}
