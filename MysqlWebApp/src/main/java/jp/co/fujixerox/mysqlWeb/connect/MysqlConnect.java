package jp.co.fujixerox.mysqlWeb.connect;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;

public class MysqlConnect {

	String url = "jdbc:mysql://localhost:3306/test?user=root&useUnicode=true&characterEncoding=UTF8";
	/*
	public static void createComTab(String[] args) throws Exception {
		Connection conn = null;
		String sql;
		// MySQL的JDBC URL编写方式：jdbc:mysql://主机名称：连接端口/数据库的名称?参数=值
		// 避免中文乱码要指定useUnicode和characterEncoding
		// 执行数据库操作之前要在数据库管理系统上创建一个数据库，名字自己定，
		// 下面语句之前就要先创建javademo数据库
		// String url =
		// "jdbc:mysql://localhost:3306/test?user=root&password=jiangwei&useUnicode=true&characterEncoding=UTF8";
		String url = "jdbc:mysql://localhost:3306/test?user=root&useUnicode=true&characterEncoding=UTF8";
		try {
			// 之所以要使用下面这条语句，是因为要使用MySQL的驱动，所以我们要把它驱动起来，
			// 可以通过Class.forName把它加载进去，也可以通过初始化来驱动起来，下面三种形式都可以
			Class.forName("com.mysql.jdbc.Driver");// 动态加载mysql驱动
			// or:
			// com.mysql.jdbc.Driver driver = new com.mysql.jdbc.Driver();
			// or：
			// new com.mysql.jdbc.Driver();

			System.out.println("成功加载MySQL驱动程序");
			// 一个Connection代表一个数据库连接
			conn = DriverManager.getConnection(url);
			// Statement里面带有很多方法，比如executeUpdate可以实现插入，更新和删除等
			Statement stmt = conn.createStatement();
			sql = "create table company(ID char(20),ComPlainName varchar(20),ComSecretName varchar(20),primary key(ID))";
			int result = stmt.executeUpdate(sql);// executeUpdate语句会返回一个受影响的行数，如果返回-1就没有成功
			if (result != -1) {
				System.out.println("创建数据表成功");
				sql = "insert into company(ID,ComPlainName,ComSecretName) values('1','FUJI','AAA')";
				result = stmt.executeUpdate(sql);
				sql = "insert into company(ID,ComPlainName,ComSecretName) values('2','XEROX','BBB')";
				result = stmt.executeUpdate(sql);
				sql = "select * from company";
				ResultSet rs = stmt.executeQuery(sql);// executeQuery会返回结果的集合，否则返回空值
				System.out.println("ID\tComPlainName\tComSecretName");
				while (rs.next()) {
					System.out.println(rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3));// 入如果返回的是int类型可以用getInt()
				}
			}
		} catch (SQLException e) {
			System.out.println("MySQL操作错误");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
	}*/

	public String getComPlainName(String ComSecretName) throws SQLException, ClassNotFoundException {
		Connection conn = null;
		String sql;
		String result = null;
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection(url);
		Statement stmt = conn.createStatement();
		sql = "select ComPlainName from company where ComSecretName ='" + ComSecretName + "'";
		ResultSet ComPlainName = stmt.executeQuery(sql);
		while (ComPlainName.next()) {
			result = ComPlainName.getString(1);
		}
		conn.close();
		return result;
	}

	public boolean registerComMsg(String ID, String ComPlainName, String ComSecretName)
			throws SQLException, ClassNotFoundException {
		Connection conn = null;
		String sql;
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection(url);
		Statement stmt = conn.createStatement();
		sql = "select * from company where ID ='" + ID + "'";
		ResultSet idrs = stmt.executeQuery(sql);
		if (!idrs.next()) {
			sql = "insert into company(ID,ComPlainName,ComSecretName) values('" + ID + "','" + ComPlainName + "','"
					+ ComSecretName + "')";
			int rstLine = stmt.executeUpdate(sql);
			if (rstLine == -1) {
				System.out.println("insert Failed");
				return false;
			} else {
				System.out.println("insert Successed");
				return true;
			}
		} else {
			System.out.println("ID =" + ID + "record has been existed\n");
			return false;
		}
	}

	public ArrayList<String> showAllComMsg()
			throws SQLException, ClassNotFoundException {
		Connection conn = null;
		ArrayList<String> comMsgList = new ArrayList<String>();
		String sql;
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection(url);
		Statement stmt = conn.createStatement();
		
		sql = "select * from company";
		ResultSet rs = stmt.executeQuery(sql);
		System.out.println("ID\tComPlainName\tComSecretName");
		while (rs.next()) {
			System.out.println(rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3));// 入如果返回的是int类型可以用getInt()
			comMsgList.add(rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3));
		}
		conn.close();
		return comMsgList;
	}

	public boolean userExists(String identity,String username, String password)
			throws SQLException, ClassNotFoundException {
		boolean result = false;
		Connection conn = null;
		String sql;
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection(url);
		Statement stmt = conn.createStatement();
		sql = "select count(*) from user where identity='" + identity
				+ "' and username ='" + username + "'and password ='" + password + "'";
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			if (rs.getInt(1) == 1) {
				result = true;
			}
		}
		return result;
	}
	
}
