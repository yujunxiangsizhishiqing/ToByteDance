package demoPackage;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class DT_JDBC {

    public static void main(String[] args) throws Exception {
        //查询其他数据库
        queryJdbc();
        //插入其他数据库
        //insertJdbc();
    }

    private static void queryJdbc() throws Exception{
        //DruidDataSource druidDataSource = new DruidDataSource();
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream("src\\main\\resources\\druid.properties");
        properties.load(fileInputStream);
        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);

        System.out.println(dataSource);
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            //String sql = "SELECT TOP 5 A.* from LACOM A";
            String sql = "SELECT * FROM ljapay WHERE 1=1 LIMIT 0,1";
            pstmt = connection.prepareStatement(sql);
            resultSet = pstmt.executeQuery();
            for (;resultSet.next();){
                System.out.println(resultSet.getString("MANAGECOM")+","+resultSet.getString("NAME"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            connection.close();
            pstmt.close();
            resultSet.close();
        }
    }

    private static void insertJdbc() throws Exception{
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream("src\\main\\resources\\druid2.properties");
        properties.load(fileInputStream);
        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);

        System.out.println(dataSource);
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        try {
            connection = dataSource.getConnection();
            String sql = "INSERT INTO CUX.CUX_GL_INTERFACE_YC_A VALUES ('testid', '1', '1', NULL, NULL, '1', 'wx', TO_DATE('2022-01-07 00:00:00', 'SYYYY-MM-DD HH24:MI:SS'), '1', TO_DATE('2022-01-11 00:00:00', 'SYYYY-MM-DD HH24:MI:SS'), '1', 'aaa', 'a', TO_DATE('2023-02-09 09:44:56', 'SYYYY-MM-DD HH24:MI:SS'), 'USER', '1', '1', '0', '3', '0', '0', '0', '0', '0', '0', '0', '0', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, TO_DATE('2023-02-09 09:44:56', 'SYYYY-MM-DD HH24:MI:SS'), '-1', '-1', TO_DATE('2023-02-09 09:44:56', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL)";
            pstmt = connection.prepareStatement(sql);
            resultSet = pstmt.executeQuery();
            for (;resultSet.next();){
                System.out.println("本次插入影响："+resultSet.getString("rows")+"行");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            connection.close();
            pstmt.close();
            resultSet.close();
        }
    }
}
