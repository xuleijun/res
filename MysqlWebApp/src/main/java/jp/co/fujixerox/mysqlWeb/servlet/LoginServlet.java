package jp.co.fujixerox.mysqlWeb.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.fujixerox.mysqlWeb.connect.MysqlConnect;

@SuppressWarnings("serial")
@WebServlet(name = "userlogin", urlPatterns = "/userlogin")
public class LoginServlet extends HttpServlet {

	MysqlConnect mc = new MysqlConnect();
	final static Logger LOG = LoggerFactory.getLogger(LoginServlet.class);
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		try {
			String identity = request.getParameter("identity");
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			
			boolean flag = mc.userExists(identity,username,password);

			HttpSession hs = request.getSession();
			
			if (flag) {
				hs.setAttribute("loginError", "");
				hs.setAttribute("loginName", username);
				out.println("Login Successed!");
				response.sendRedirect("convertRegister.jsp");
			} else {
				hs.setAttribute("loginError", "username or password was wrong");
				out.println("username or password was wrong");
			}
			out.flush();
			out.close();
		} catch (SQLException se) {
			LOG.error("SQLException userlogin failed", se);
			out.println("<center>userlogin failed</center>");
			out.flush();
			out.close();
		} catch (ClassNotFoundException ce) {
			LOG.error("ClassNotFoundException userlogin failed", ce);
			out.println("<center>userlogin failed</center>");
			out.flush();
			out.close();
		}
	}
}
