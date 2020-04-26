/*
 * �����ϴ�����ͼƬ
 */
package com.helper.UserFunc;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
 
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
 

/**
 * Servlet implementation class UploadServlet
 */
@WebServlet("/UploadServlet")
public class UploadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
     
    // �ϴ��ļ��洢Ŀ¼
    private static final String UPLOAD_DIRECTORY = "upload/image";
 
    // �ϴ�����
    private static final int MEMORY_THRESHOLD   = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB
 
    /**
     * �ϴ����ݼ������ļ�
     */
    protected void doPost(HttpServletRequest request,
        HttpServletResponse response) throws ServletException, IOException {
    	
    	HttpSession session = request.getSession();
    	String username = (String) session.getAttribute("username");
    	String arttitle = (String) session.getAttribute("arttitle");
    	String artaddr = (String) session.getAttribute("artaddr");
    	String arttext = (String) session.getAttribute("arttext");
    	
        // ����Ƿ�Ϊ��ý���ϴ�
        if (!ServletFileUpload.isMultipartContent(request)) {
            // ���������ֹͣ
            PrintWriter writer = response.getWriter();
            writer.println("Error: ��������� enctype=multipart/form-data");
            writer.flush();
            return;
        }
 
        
        response.setContentType("text/html;charset=UTF-8");	
        String path = request.getContextPath();
    	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path
    			+ "/";
        
        DiskFileItemFactory factory = new DiskFileItemFactory();  // �����ϴ�����
        
        factory.setSizeThreshold(MEMORY_THRESHOLD);  // �����ڴ��ٽ�ֵ
        
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));  // ������ʱ�洢Ŀ¼
 
        ServletFileUpload upload = new ServletFileUpload(factory);
         
        upload.setFileSizeMax(MAX_FILE_SIZE);  // ��������ļ��ϴ�ֵ
         
        upload.setSizeMax(MAX_REQUEST_SIZE);  // �����������ֵ
        
        upload.setHeaderEncoding("UTF-8");   // ���Ĵ���

        // ������ʱ·�����洢�ϴ����ļ� ���·����Ե�ǰӦ�õ�Ŀ¼

        String uploadPath = getServletContext().getRealPath("/") + File.separator + UPLOAD_DIRECTORY;
       
         
        // ���Ŀ¼�������򴴽�
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
 
        try {
            // ���������������ȡ�ļ�����
            @SuppressWarnings("unchecked")
            List<FileItem> formItems = upload.parseRequest(request);
 
            if (formItems != null && formItems.size() > 0) {
                // ����������
                for (FileItem item : formItems) {
                    // �����ڱ��е��ֶ�
                    if (!item.isFormField()) {
                        String fileName = new File(item.getName()).getName();
                        String filePath = uploadPath + File.separator + fileName;
                        File storeFile = new File(filePath);
                        
                        String jpg = "jpg";
                        String png = "png";
                        String gif = "gif";
                        if(fileName.equals("")){
                        	System.out.println("�ϴ�ʧ�ܣ�����Ϊ��");
                        	response.getWriter().write("����ѡ��һ��ͼƬ");
                        	response.setHeader("refresh", "3;url="+basePath+"MyArticleServlet");
                        }else{
                        String fileendv = String.valueOf(fileName);
                        String fileend = fileendv.substring(fileendv.length() -3,fileendv.length());
                        System.out.println(fileend);
                        if(fileend.equals(jpg)==true || fileend.equals(png)==true || fileend.equals(gif)==true){
                        
                        // �ڿ���̨����ļ����ϴ�·��
                        System.out.println(filePath);
                        System.out.println(username);
                        // �����ļ���Ӳ��
                        item.write(storeFile);
                        request.setAttribute("title",
                            arttitle);
                        request.setAttribute("addr",
                                artaddr);
                        request.setAttribute("text",
                                arttext);
                        request.setAttribute("filename",
                                "/Helper/upload/image/"+fileName);
                        session.setAttribute("filename", fileName);
                        //��ת��SendArticleServlet
                        
                        String site = new String(basePath+"/SendArticleServlet");
                		response.setStatus(response.SC_MOVED_TEMPORARILY);
                		response.setHeader("Location", site);
                        }else{
                        	
                        	response.getWriter().write("Only support jpg,png,gif picture!!!");
                        	response.setHeader("refresh", "3;url="+basePath+"MyArticleServlet");
                        	System.out.println("��֧���ļ�����");
                        }
                        	
                        }
                    }
                }
            }
            
        } catch (Exception ex) {
            request.setAttribute("message",
                    "������Ϣ: " + ex.getMessage());
        }
        
        // ��ת�� message.jsp
        //getServletContext().getRequestDispatcher("/home/module/message.jsp").forward(
        //        request, response);
    }
        
}