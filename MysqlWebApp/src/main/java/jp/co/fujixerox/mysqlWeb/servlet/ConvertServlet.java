package jp.co.fujixerox.mysqlWeb.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jp.co.fujixerox.mysqlWeb.connect.MysqlConnect;

@SuppressWarnings("serial")
@WebServlet(name = "convert", urlPatterns = "/convert")
public class ConvertServlet extends HttpServlet {

	MysqlConnect mc = new MysqlConnect();
	final static Logger LOG = LoggerFactory.getLogger(ConvertServlet.class);

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		try {
			String code = request.getParameter("code");
			String ComPlainName = mc.getComPlainName(code);

			out.println("Hello your CompanyCode is:" + code);
			if (!ComPlainName.isEmpty()) {
				out.println("<br />your CompanyName is:" + ComPlainName);
			} else {
				out.println("<br /> can't found your CompanyName");
			}
			out.flush();
			out.close();
		} catch (SQLException se) {
			LOG.error("SQLException convert failed", se);
			out.println("<center>convert failed</center>");
			out.flush();
			out.close();
		} catch (ClassNotFoundException ce) {
			LOG.error("ClassNotFoundException convert failed", ce);
			out.println("<center>convert failed</center>");
			out.flush();
			out.close();
		}
	}
}
