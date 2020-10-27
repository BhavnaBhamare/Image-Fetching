package com.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.model.BLManager;
import com.pojo.Demo;

/**
 * Servlet implementation class SaveDemo
 */
@WebServlet("/SaveDemo")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 100, // 10MB
maxRequestSize = 1024 * 1024 * 500)
public class SaveDemo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String SAV_DIR = "D:/Bhavna/ProjectAll/imgdemo/WebContent/imgdata";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SaveDemo() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	Demo d = new Demo();
	BLManager b = new BLManager();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		String name = request.getParameter("name");
		Part img = request.getPart("img");
		try {
			String Image1 = extractFileName(img);
			d.setImg(Image1);
			img.write(SAV_DIR + File.separator + Image1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		d.setName(name);
		b.saveImg(d);
		String m = "Employee Added successfully";
		HttpSession session = request.getSession();
		session.setAttribute("empadd", m);

		response.sendRedirect("Index.jsp");

	}

	private String extractFileName(Part img) {
		String contentDisp = img.getHeader("content-disposition");
		String[] items = contentDisp.split(";");
		for (String s : items) {
			if (s.trim().startsWith("filename")) {
				return s.substring(s.indexOf("=") + 2, s.length() - 1);
			}
		}
		return "";

	}

}
