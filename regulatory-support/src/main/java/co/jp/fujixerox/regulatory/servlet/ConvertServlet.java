package co.jp.fujixerox.regulatory.servlet;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import co.jp.fujixerox.regulatory.util.DocuWorksUtil;
import co.jp.fujixerox.regulatory.util.JacobUtil;
import co.jp.fujixerox.regulatory.util.Pdf2HtmlUtil;
import co.jp.fujixerox.regulatory.util.SystemUtil;

@WebServlet(name = "ConvertServlet", urlPatterns = "/ConvertServlet")
@MultipartConfig
public class ConvertServlet extends HttpServlet {
	private static final Logger logger = LogManager.getLogger(ConvertServlet.class.getName());
	private static final long serialVersionUID = 1L;
	private Properties props = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ConvertServlet() {
		try {
			props = new Properties();
			InputStream in = this.getClass().getClassLoader().getResourceAsStream("system.properties");
			props.load(in);
		} catch (Exception e) {
			throw new ExceptionInInitializerError(e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		PrintWriter out = response.getWriter();
		try {
			String convertType = request.getParameter("changefile");
			// アップロードパス
			String pdfUploadPath = props.getProperty("upload_pdf");
			String wordUploadPath = props.getProperty("upload_word");
			String dcuworksUploadPath = props.getProperty("upload_dcuworks");
			String excelUploadPath = props.getProperty("upload_excel");
			// アップロードファイルを取得する。
			Collection<Part> parts = request.getParts();
			for (Part part : parts) {
				// ヘッダの情報を得ます：form-data; name="file"; filename="snmp4j--api.zip"
				String header = part.getHeader("content-disposition");
				// ファイル名を取得する。
				String fileName = getFileName(header);
				if (StringUtils.isEmpty(fileName)) {
					continue;
				} else {
					// pdf2htmlEX ファイルパス
					String exeFilePath = props.getProperty("toolpath");
					// DocuWorks2Pdf ツールパス
					String batPath = props.getProperty("file2pdfBat");
					// printto.exeパス
					String printtoPath = props.getProperty("printtoPath");
					// pdfファイルパス
					String pdfFilePath = pdfUploadPath + File.separator + fileName;
					// wordファイルパス
					String wordFilePath = wordUploadPath + File.separator + fileName;
					// dcuworksファイルパス
					String dcuworksFilePath = dcuworksUploadPath + File.separator + fileName;
					// excelファイルパス
					String excelFilePath = excelUploadPath + File.separator + fileName;
					// システム日時
					String sysDate = SystemUtil.getSystemDate();
					// HTMLファイル名
					String htmlFileName = fileName.substring(0, fileName.lastIndexOf("."));
					boolean flg = false;
					if ("pdf2html".equals(convertType)) {
						SystemUtil.isExist(pdfUploadPath, pdfFilePath);
						// ファイルを書きます
						part.write(pdfFilePath);

						// PDFファイル変換
						String outputPath = props.getProperty("pdf2htmloutput") + File.separator + sysDate;
						flg = Pdf2HtmlUtil.convertToHtmlEX(exeFilePath, pdfFilePath, outputPath, htmlFileName);
						
						if (flg) {
							out.println("<center>" + fileName + "ファイルの変換に成功しました。</center><br/>");
						} else {
							out.println("<center>" + fileName + "ファイルの変換に失敗しました。</center><br/>");
						}
					} else if ("word2html".equals(convertType)) {
						SystemUtil.isExist(wordUploadPath, wordFilePath);
						// ファイルを書きます
						part.write(wordFilePath);

						// Wordファイル変換
						String outputPath = props.getProperty("word2htmloutput");
						flg = JacobUtil.wordToHtml(wordFilePath, outputPath, htmlFileName, batPath, printtoPath,
								exeFilePath);
						
						if (flg) {
							out.println("<center>" + fileName + "ファイルの変換に成功しました。</center><br/>");
						} else {
							out.println("<center>" + fileName + "ファイルの変換に失敗しました。</center><br/>");
						}
					} else if("dcuworks2html".equals(convertType)) {
						SystemUtil.isExist(dcuworksUploadPath, dcuworksFilePath);
						// ファイルを書きます
						part.write(dcuworksFilePath);
						
						// pdf 出力パス
						String outputPath = props.getProperty("docuWorks2pdfOutput");
						
						// dcuworksファイル変換
						flg = DocuWorksUtil.print(dcuworksFilePath, batPath, printtoPath, outputPath, exeFilePath);
						
						if (flg) {
							out.println("<center>" + fileName + "ファイルの変換に成功しました。</center><br/>");
						} else {
							out.println("<center>" + fileName + "ファイルの変換に失敗しました。</center><br/>");
						}
					} else {
						SystemUtil.isExist(excelUploadPath, excelFilePath);
						// ファイルを書きます
						part.write(excelFilePath);

						// Excelファイル変換
						String outputPath = props.getProperty("excel2htmloutput") + File.separator + sysDate;
						SystemUtil.isExist(outputPath, null);
						flg = JacobUtil.excelToHtml(excelFilePath,
								outputPath + File.separator + htmlFileName + ".html");
						
						if (flg) {
							out.println("<center>" + fileName + "ファイルの変換に成功しました。</center><br/>");
						} else {
							out.println("<center>" + fileName + "ファイルの変換に失敗しました。</center><br/>");
						}
					}
				}
			}

			out.flush();
			out.close();
		} catch (Exception e) {
			logger.error("ファイルのアップロードに失敗しました。", e);
			out.println("<center>ファイルのアップロードに失敗しました。</center>");
			out.flush();
			out.close();
		}
	}

	/**
	 * ファイル名を取得する。 ヘッダフォーマット：firefoxとgoogle：form-data; name="file";
	 * filename="snmp4j--api.zip" IE：form-data; name="file";
	 * filename="E:\snmp4j--api.zip"
	 * 
	 * @param header
	 *            ヘッダ
	 * @return ファイル名
	 */
	public String getFileName(String header) {
		/**
		 * String[] tempArr1 = header.split(";");
		 * firefoxとgoogle：：tempArr1={form-data,name="file",filename=
		 * "snmp4j--api.zip"}
		 * IE：tempArr1={form-data,name="file",filename="E:\snmp4j--api.zip"}
		 */
		String[] tempArr1 = header.split(";");
		/**
		 * firefoxとgoogle：tempArr2={filename,"snmp4j--api.zip"}
		 * IE：tempArr2={filename,"E:\snmp4j--api.zip"}
		 */
		if (tempArr1.length != 3) {
			return null;
		} else {
			String[] tempArr2 = tempArr1[2].split("=");
			String fileName = tempArr2[1].substring(tempArr2[1].lastIndexOf("\\") + 1).replaceAll("\"", "");
			return fileName;
		}
	}

}
