package com.need.common.mybatisTypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

public class DateLongTypeHandler implements TypeHandler<Long> {

    @Override
    public void setParameter(PreparedStatement ps, int i, Long parameter, JdbcType jdbcType) throws SQLException {
        // TODO Auto-generated method stub
        if (parameter == null) {
            ps.setNull(i, Types.VARCHAR);
        } else {
            java.sql.Date date = new java.sql.Date(parameter);
            ps.setDate(i, date);
        }
    }

    @Override
    public Long getResult(ResultSet rs, String columnName) throws SQLException {
        // TODO Auto-generated method stub
        Timestamp columnValue = rs.getTimestamp(columnName);
        if (columnValue == null) {
            return null;
        }
        return columnValue.getTime();
    }

    @Override
    public Long getResult(ResultSet rs, int columnIndex) throws SQLException {
        // TODO Auto-generated method stub
        Timestamp columnValue = rs.getTimestamp(columnIndex);
        if (columnValue == null) {
            return null;
        }
        return columnValue.getTime();
    }

    @Override
    public Long getResult(CallableStatement cs, int columnIndex) throws SQLException {
        // TODO Auto-generated method stub
        Timestamp columnValue = cs.getTimestamp(columnIndex);
        if (columnValue == null) {
            return null;
        }
        return columnValue.getTime();
    }

}
