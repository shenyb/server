package com.need.common.mybatisTypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

public class TrueOrFalseEnumTypeHandler implements TypeHandler<String>{

	private String isTrueOrFalse(String columnValue){
		if("NEED".equals(columnValue)){
			return String.valueOf(Boolean.TRUE).toUpperCase();
		}
		else{
			return String.valueOf(Boolean.FALSE).toUpperCase();
		}
	}

	@Override
	public void setParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType) throws SQLException {
		// TODO Auto-generated method stub
		if (parameter == null)
			ps.setNull(i, Types.VARCHAR);
		else {
			if(String.valueOf(Boolean.FALSE).toUpperCase().equals(parameter))
			{
				ps.setString(i, "CANCEL");
			}
			else{
				ps.setString(i, "NEED");
			}
		}
	}

	@Override
	public String getResult(ResultSet rs, String columnName) throws SQLException {
		// TODO Auto-generated method stub
		if(rs.wasNull()){
			return String.valueOf(Boolean.FALSE).toUpperCase();
		}
		String columnValue = rs.getString(columnName);
		return isTrueOrFalse(columnValue);
	}

	@Override
	public String getResult(ResultSet rs, int columnIndex) throws SQLException {
		// TODO Auto-generated method stub
		if(rs.wasNull()){
			return String.valueOf(Boolean.FALSE).toUpperCase();
		}
		String columnValue = rs.getString(columnIndex);
		return isTrueOrFalse(columnValue);
	}

	@Override
	public String getResult(CallableStatement cs, int columnIndex) throws SQLException {
		// TODO Auto-generated method stub
		if(cs.wasNull()){
			return String.valueOf(Boolean.FALSE).toUpperCase();
		}
		String columnValue = cs.getString(columnIndex);
		return isTrueOrFalse(columnValue);
	}

}
