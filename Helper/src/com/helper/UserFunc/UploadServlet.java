/*
 * 负责上传主题图片
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
     
    // 上传文件存储目录
    private static final String UPLOAD_DIRECTORY = "upload/image";
 
    // 上传配置
    private static final int MEMORY_THRESHOLD   = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB
 
    /**
     * 上传数据及保存文件
     */
    protected void doPost(HttpServletRequest request,
        HttpServletResponse response) throws ServletException, IOException {
    	
    	HttpSession session = request.getSession();
    	String username = (String) session.getAttribute("username");
    	String arttitle = (String) session.getAttribute("arttitle");
    	String artaddr = (String) session.getAttribute("artaddr");
    	String arttext = (String) session.getAttribute("arttext");
    	
        // 检测是否为多媒体上传
        if (!ServletFileUpload.isMultipartContent(request)) {
            // 如果不是则停止
            PrintWriter writer = response.getWriter();
            writer.println("Error: 表单必须包含 enctype=multipart/form-data");
            writer.flush();
            return;
        }
 
        
        response.setContentType("text/html;charset=UTF-8");	
        String path = request.getContextPath();
    	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path
    			+ "/";
        
        DiskFileItemFactory factory = new DiskFileItemFactory();  // 配置上传参数
        
        factory.setSizeThreshold(MEMORY_THRESHOLD);  // 设置内存临界值
        
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));  // 设置临时存储目录
 
        ServletFileUpload upload = new ServletFileUpload(factory);
         
        upload.setFileSizeMax(MAX_FILE_SIZE);  // 设置最大文件上传值
         
        upload.setSizeMax(MAX_REQUEST_SIZE);  // 设置最大请求值
        
        upload.setHeaderEncoding("UTF-8");   // 中文处理

        // 构造临时路径来存储上传的文件 这个路径相对当前应用的目录

        String uploadPath = getServletContext().getRealPath("/") + File.separator + UPLOAD_DIRECTORY;
       
         
        // 如果目录不存在则创建
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
 
        try {
            // 解析请求的内容提取文件数据
            @SuppressWarnings("unchecked")
            List<FileItem> formItems = upload.parseRequest(request);
 
            if (formItems != null && formItems.size() > 0) {
                // 迭代表单数据
                for (FileItem item : formItems) {
                    // 处理不在表单中的字段
                    if (!item.isFormField()) {
                        String fileName = new File(item.getName()).getName();
                        String filePath = uploadPath + File.separator + fileName;
                        File storeFile = new File(filePath);
                        
                        String jpg = "jpg";
                        String png = "png";
                        String gif = "gif";
                        if(fileName.equals("")){
                        	System.out.println("上传失败，数据为空");
                        	response.getWriter().write("请先选择一个图片");
                        	response.setHeader("refresh", "3;url="+basePath+"MyArticleServlet");
                        }else{
                        String fileendv = String.valueOf(fileName);
                        String fileend = fileendv.substring(fileendv.length() -3,fileendv.length());
                        System.out.println(fileend);
                        if(fileend.equals(jpg)==true || fileend.equals(png)==true || fileend.equals(gif)==true){
                        
                        // 在控制台输出文件的上传路径
                        System.out.println(filePath);
                        System.out.println(username);
                        // 保存文件到硬盘
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
                        //跳转到SendArticleServlet
                        
                        String site = new String(basePath+"/SendArticleServlet");
                		response.setStatus(response.SC_MOVED_TEMPORARILY);
                		response.setHeader("Location", site);
                        }else{
                        	
                        	response.getWriter().write("Only support jpg,png,gif picture!!!");
                        	response.setHeader("refresh", "3;url="+basePath+"MyArticleServlet");
                        	System.out.println("不支持文件类型");
                        }
                        	
                        }
                    }
                }
            }
            
        } catch (Exception ex) {
            request.setAttribute("message",
                    "错误信息: " + ex.getMessage());
        }
        
        // 跳转到 message.jsp
        //getServletContext().getRequestDispatcher("/home/module/message.jsp").forward(
        //        request, response);
    }
        
}