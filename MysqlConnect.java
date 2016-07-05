import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;

class MysqlConnect {
	public static void main(String[] args) throws Exception {
		
		System.out.println(getComPlainName("BBB"));
		/*
		Connection conn = null;
		String sql;
		String url = "jdbc:mysql://localhost:3306/test?user=root&useUnicode=true&characterEncoding=UTF8";
		try {
			Class.forName("com.mysql.jdbc.Driver");

			System.out.println("upload mysql successed");
			conn = DriverManager.getConnection(url);
			Statement stmt = conn.createStatement();
			sql = "create table company(ID char(20),ComPlainName varchar(20),ComSecretName varchar(20),primary key(ID))";
			int result = stmt.executeUpdate(sql);
			if (result != -1) {
				sql = "insert into company(ID,ComPlainName,ComSecretName) values('1','AAACompany','AAA')";
				result = stmt.executeUpdate(sql);
				sql = "insert into company(ID,ComPlainName,ComSecretName) values('2','BBBCompany','BBB')";
				result = stmt.executeUpdate(sql);
				sql = "insert into company(ID,ComPlainName,ComSecretName) values('3','CCCCompany','CCC')";
				result = stmt.executeUpdate(sql);
				sql = "insert into company(ID,ComPlainName,ComSecretName) values('4','DDDCompany','DDD')";
				result = stmt.executeUpdate(sql);
				sql = "insert into company(ID,ComPlainName,ComSecretName) values('5','EEECompany','EEE')";
				result = stmt.executeUpdate(sql);
				sql = "insert into company(ID,ComPlainName,ComSecretName) values('6','FFFCompany','FFF')";
				result = stmt.executeUpdate(sql);
				sql = "insert into company(ID,ComPlainName,ComSecretName) values('7','GGGCompany','GGG')";
				result = stmt.executeUpdate(sql);
				sql = "insert into company(ID,ComPlainName,ComSecretName) values('8','HHHCompany','HHH')";
				result = stmt.executeUpdate(sql);
				sql = "select * from company ";
				ResultSet rs = stmt.executeQuery(sql);
				System.out.println("ID\tComPlainName\tComSecretName");
				while (rs.next()) {
					System.out.println(rs.getString(1) + "\t" + rs.getString(2)+ "\t" + rs.getString(3));
					//System.out.println(rs.getString(1));
				}
				getComPlainName("AAA");
			}
		} catch (SQLException e) {
			System.out.println("MySQL failed");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
		*/
	}

	public static String getComPlainName(String ComSecretName) throws SQLException, ClassNotFoundException {
		Connection conn = null;
		String sql;
		String result=null;
		String url = "jdbc:mysql://localhost:3306/test?user=root&useUnicode=true&characterEncoding=UTF8";
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection(url);
		Statement stmt = conn.createStatement();

		sql ="select ComPlainName from company where ComSecretName ='" + ComSecretName + "'";
		ResultSet ComPlainName = stmt.executeQuery(sql);
		
		while (ComPlainName.next()) {
			result=ComPlainName.getString(1);
		}
		conn.close();
		return result;
	}

}
