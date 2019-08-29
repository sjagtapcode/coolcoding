package com.coolcode;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet("/Fileinput")
public class Fileinput extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String inputfolder;
	String outputfolder;
	
    public Fileinput() {
    	super();
        inputfolder="C:/Users/HP/Desktop/javajar/input/";
        outputfolder="C:/Users/HP/Desktop/javajar/output/";
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(ServletFileUpload.isMultipartContent(request)){
            try {
            	String name="";
                List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
               	for(FileItem item : multiparts){
                    if(!item.isFormField()){
                    	
                        name = new File(item.getName()).getName();
                        item.write( new File(inputfolder + name));
                    }
                }
               	Compile compiler=new Compile();
               	compiler.setFileInputAddress(name);
               	compiler.setFileOutputAddress(name);
               	compiler.compiledOutput();
               //File uploaded successfully
               request.setAttribute("message", compiler.getFileOutputAddress());
            } catch (Exception ex) {
               request.setAttribute("message", "File Upload Failed due to " + ex);
            }
        }
		request.getRequestDispatcher("/result.jsp").forward(request,response);
	}
}
