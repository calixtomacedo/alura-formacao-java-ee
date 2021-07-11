package br.com.cmdev.javaeeparteiii.utils.servlet;

import java.io.File;
import java.io.IOException;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.cmdev.javaeeparteiii.utils.infra.FileSaver;

@WebServlet("/file/*")
public class FileServlet extends HttpServlet {

	private static final long serialVersionUID = -6058584860945929190L;

	private static final String BARRA = File.separator;
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getRequestURI().split("/file")[1];
		
		Path source = Paths.get(FileSaver.SERVER_PATH.concat(BARRA).concat(path));
		
		FileNameMap fileNameMap = URLConnection.getFileNameMap();
		String contentType = fileNameMap.getContentTypeFor("file:"+source);
		
		response.reset();
		response.setContentType(contentType);
		response.setHeader("Content-Lenhth", String.valueOf(Files.size(source)));
		response.setHeader("Content-Disposition", "filename=\""+source.getFileName().toString()+"\"");
		
		FileSaver.transfer(source, response.getOutputStream());
	}
	
}
