package com.oppc.util;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class ToHtml extends HttpServlet{
	  public void service(HttpServletRequest request, HttpServletResponse response)
		      throws ServletException, IOException {
		     String url = "";
		     String name = "";

		     ServletContext sc = getServletContext();

		     String htmName = request.getParameter("htmName");// 要生成的htm文件名
		     String jspUrl = request.getParameter("jspUrl");//访问的jsp路径
		     String htmUrl = request.getParameter("htmUrl");//存放生成htm文件的路径

		     // 则你访问这个servlet时加参数.如http://localhost/test/toHtml?file_name=index

		     //url = "/" + file_name + ".jsp";// 你要生成的页面的文件名。 我的扩展名为jsf .
		    
		     name = sc.getRealPath(htmUrl)+"\\"+ htmName + ".htm";
		     //System.out.println(name);
		     //name = ConfConstants.CONTEXT_PATH+"\\"+ file_name + ".htm";// 这是生成的html文件名,如index.htm.文件名字与源文件名相同。扩展名为htm

		     //ConfConstants.CONTEXT_PATH为你的应用的上下文路径。

		     RequestDispatcher rd = sc.getRequestDispatcher(jspUrl);

		     final ByteArrayOutputStream os = new ByteArrayOutputStream();

		     final ServletOutputStream stream = new ServletOutputStream() {
		      public void write(byte[] data, int offset, int length) {
		       os.write(data, offset, length);
		      }

		      public void write(int b) throws IOException {
		       os.write(b);
		      }
		     };

		     final PrintWriter pw = new PrintWriter(new OutputStreamWriter(os));

		     HttpServletResponse rep = new HttpServletResponseWrapper(response) {
		      public ServletOutputStream getOutputStream() {
		       return stream;
		      }

		      public PrintWriter getWriter() {
		       return pw;
		      }
		     };
		     rd.include(request, rep);
		     pw.flush();
		     FileOutputStream fos = new FileOutputStream(name); // 把jsp输出的内容写到xxx.htm
		     os.writeTo(fos);
		     fos.close();
		     System.out.println("页面已经成功生成");
		     //PrintWriter out = response.getWriter();
		     //out.print("<p align=center><font size=3 color=red>页面已经成功生成！single<br>http://www.agilejava.org/space/? 233</font></p>");
		    } 
}
