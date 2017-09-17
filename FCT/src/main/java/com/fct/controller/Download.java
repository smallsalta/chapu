package com.fct.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * http://www.codejava.net/frameworks/spring/spring-mvc-sample-application-for-
 * downloading-files
 */
@Controller
public class Download {
	/**
	 * Size of a byte buffer to read/write file
	 */
	private static final int BUFFER_SIZE = 4096;

	/**
	 * Method for handling file download request from client
	 */
	@RequestMapping("download.do")
	public void doDownload(HttpServletRequest request, HttpServletResponse response, String filePath) 
	throws IOException 
	{
		
		ServletContext context		= request.getSession().getServletContext();
		String appPath 				= context.getRealPath("");
		
		String fullPath 			= appPath + "/" + filePath;
		File downloadFile 			= new File(fullPath);
		FileInputStream inputStream = new FileInputStream(downloadFile);

		LoggerFactory.getLogger( Download.class ).info(fullPath);

		String mimeType 	= context.getMimeType(fullPath);
		mimeType			= mimeType == null ? "application/octet-stream" : mimeType;
		String headerKey	= "Content-Disposition";
		String headerValue 	= String.format("attachment; filename=\"%s\"", downloadFile.getName());
		
		response.setContentType(mimeType);
		response.setContentLength((int) downloadFile.length());
		response.setHeader(headerKey, headerValue);

		OutputStream outStream 	= response.getOutputStream();
		byte[] buffer			= new byte[BUFFER_SIZE];
		int bytesRead 			= -1;

		while( (bytesRead = inputStream.read(buffer)) != -1 ) 
		{
			outStream.write(buffer, 0, bytesRead);
		}

		inputStream.close();
		outStream.close();
		downloadFile.delete();
	}
}