package org.myproject;

import com.jcabi.aspects.Cacheable;
import com.jcabi.jdbc.JdbcSession;
import com.jolbox.bonecp.BoneCPDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Created by amey on 2/7/16.
 */
public class DatabaseHelper {

    @Cacheable(forever = true)
    private static DataSource source() {
        BoneCPDataSource src = new BoneCPDataSource();
        src.setDriverClass("org.h2.Driver");
        src.setJdbcUrl("jdbc:h2:tcp://localhost/~/tmp/test");
        src.setUsername("sa");
        return src;
    }

    public static void insertRecord(String tableName, String columns, String values) {
        String sql = String.format("insert into %s%s values %s", tableName, columns, values);
        try {
            new JdbcSession(source()).sql(sql).execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateRecord(String tableName, String columns, String where) {
        String sql = String.format("update %s set %s where %s", tableName, columns, where);
        try {
            new JdbcSession(source()).sql(sql).execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteRecord(String tableName, String where) {
        String sql = String.format("delete from %s where %s", tableName, where);
        try {
            new JdbcSession(source()).sql(sql).execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
