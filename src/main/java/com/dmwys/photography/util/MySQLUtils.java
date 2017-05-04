package com.dmwys.photography.util;

import org.apache.commons.dbcp2.*;
import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.*;
import java.util.*;

public class MySQLUtils {
	private static final Logger logger = LoggerFactory.getLogger(MySQLUtils.class);

	private static String DATABASE_RESOURCENAME = "resourceName";

	private static String DATABASE_KYTV = "db.photo";

	private static Map<String, DataSource> dataSourceMap = new HashMap<>();

	static {
		try {
			InputStream inputStream = MySQLUtils.class.getResourceAsStream("/configure.properties");
			Properties properties = new Properties();
			properties.load(inputStream);
			String resourceNames = properties.getProperty(DATABASE_RESOURCENAME);
			for(String rn : resourceNames.split(",")){
				dataSourceMap.put(rn,setupDataSource(
						properties.getProperty(rn+".url"),
						properties.getProperty(rn+".user"),
						properties.getProperty(rn+".password")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private MySQLUtils() {
	}

	public static Connection getConnection() {
		Connection con = null;
		try {
			con = dataSourceMap.get(DATABASE_KYTV).getConnection();
		} catch (Exception e) {
			logger.error("MySQL获取数据库连接失败：", e);
		}
		return con;
	}

	private static DataSource setupDataSource(String connectUri, String uname, String passwd) {
		//
		// First, we'll create a ConnectionFactory that the
		// pool will use to create Connections.
		// We'll use the DriverManagerConnectionFactory,
		// using the connect string passed in the command line
		// arguments.
		//
		ConnectionFactory connectionFactory = new DriverManagerConnectionFactory(connectUri, uname, passwd);

		//
		// Next we'll create the PoolableConnectionFactory, which wraps
		// the "real" Connections created by the ConnectionFactory with
		// the classes that implement the pooling functionality.
		//
		PoolableConnectionFactory poolableConnectionFactory = new PoolableConnectionFactory(connectionFactory, null);

		//
		// Now we'll need a ObjectPool that serves as the
		// actual pool of connections.
		//
		// We'll use a GenericObjectPool instance, although
		// any ObjectPool implementation will suffice.
		//
		ObjectPool<PoolableConnection> connectionPool = new GenericObjectPool<>(poolableConnectionFactory);

		// Set the factory's pool property to the owning pool
		poolableConnectionFactory.setPool(connectionPool);

		//
		// Finally, we create the PoolingDriver itself,
		// passing in the object pool we created.
		//
		PoolingDataSource<PoolableConnection> dataSource = new PoolingDataSource<>(connectionPool);

		return dataSource;
	}

	public static void close(Statement st) {
		close(null, st, null);
	}

	public static void close(Statement st, ResultSet rs) {
		close(null, st, rs);
	}

	public static void close(Connection con, Statement st, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
				rs = null;
			}
			if (st != null) {
				st.close();
				st = null;
			}
			if (con != null) {
				con.close();
				con = null;
			}
		} catch (SQLException e) {
			logger.error("数据库关闭失败", e);
		}
	}

	/**
	 * 根据SQL 查询数据
	 * @param sql
	 * @return List<Map<String, Double>>
	 */
	public static List<Map<String, Object>> query(String sql){
		List<Map<String, Object>> resultList = new ArrayList<>();
		try(
				Connection dbConnection = MySQLUtils.getConnection()
		){
			ResultSet rs = dbConnection.createStatement().executeQuery(sql);
			ResultSetMetaData md = rs.getMetaData();
			while(rs.next()){
				Map<String, Object> resultMap = new HashMap<>();
				int columnCount = md.getColumnCount();
				for(int i = 1; i < columnCount; i++){
					resultMap.put(md.getColumnName(i), rs.getObject(i));
				}
				resultList.add(resultMap);
			}
		}catch (Exception e){
			e.printStackTrace();
			logger.error("query ERROR : ", e);
		}
		return resultList;
	}

	public static void close(Connection con, Statement st) {
		close(con, st, null);
	}

	public static void close(Connection con) {
		close(con, null, null);
	}

	public static void main(String[] args) throws Exception {
		String sql = "select * from production";
	    List<Map<String,Object>> list = query(sql);
	    if(list !=null){
	    	for(Map<String,Object> map:list){
	    		String bb = map.get("context").toString();
				System.out.println(bb);
			}
		}
	}

}
