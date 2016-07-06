package jp.co.fujixerox.mysqlWeb.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jp.co.fujixerox.mysqlWeb.connect.MysqlConnect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("serial")
@WebServlet(name = "register",urlPatterns = "/register")
public class RegisterServlet  extends HttpServlet{
	
	MysqlConnect mc = new MysqlConnect();
	final static Logger LOG = LoggerFactory.getLogger(RegisterServlet.class);
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException{
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		try{
		String ID = request.getParameter("ID");
		String name = request.getParameter("name");
		String code = request.getParameter("code");
		
		out.println("Hello your register CompanyID is:" + ID);
		out.println("<br />your register CompanyName is:" + name);
		out.println("<br />your register CompanyCode is:" + code);
		out.println("<br />All of your CompanyInfo is below:");
		out.println("<ol>");
		boolean rst = mc.registerComMsg(ID, name, code);
		if(rst){
			out.println("<br /><h1>Register Successed !</h1>");
		} else {
			out.println("<br /><h1>Register Failed !</h1>");
		}
		
		ArrayList<String> rs = mc.showAllComMsg();
		out.println("<br /><h1>latest Company Table Data</h1>");
		out.println("<br />ID\tComPlainName\tComSecretName");
		for(int i =0;i<rs.size();i++){
			out.println(rs.get(i)+"<br />");
		}
		out.println("</ol>");
		out.flush();
		out.close();
		}catch(SQLException se){
			LOG.error("SQLException register failed", se);
			out.println("<center>register failed</center>");
			out.flush();
			out.close();
		} catch(ClassNotFoundException ce){
			LOG.error("ClassNotFoundException register failed", ce);
			out.println("<center>register failed</center>");
			out.flush();
			out.close();
		}
	}
}
