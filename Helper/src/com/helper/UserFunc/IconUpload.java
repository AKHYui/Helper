/*
 * �������ͷ��
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

/*
 * ������ϴ�ͷ�����
 * ���ϴ�������ͼƬ������
 */

@WebServlet("/IconUpload")
public class IconUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// �ϴ��ļ��洢Ŀ¼
    private static final String UPLOAD_DIRECTORY = "upload/icon";
    
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
    	
        // ����Ƿ�Ϊ��ý���ϴ�
        if (!ServletFileUpload.isMultipartContent(request)) {
            // ���������ֹͣ
            PrintWriter writer = response.getWriter();
            writer.println("Error: ��������� enctype=multipart/form-data");
            writer.flush();
            return;
        }
        
        // �����ϴ�����
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // �����ڴ��ٽ�ֵ - �����󽫲�����ʱ�ļ����洢����ʱĿ¼��
        factory.setSizeThreshold(MEMORY_THRESHOLD);
        // ������ʱ�洢Ŀ¼
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
 
        ServletFileUpload upload = new ServletFileUpload(factory);
         
        // ��������ļ��ϴ�ֵ
        upload.setFileSizeMax(MAX_FILE_SIZE);
         
        // �����������ֵ (�����ļ��ͱ�����)
        upload.setSizeMax(MAX_REQUEST_SIZE);
        
        // ���Ĵ���
        upload.setHeaderEncoding("UTF-8"); 

        // ������ʱ·�����洢�ϴ����ļ�
        // ���·����Ե�ǰӦ�õ�Ŀ¼
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
                        String fileendv = String.valueOf(fileName);
                        String fileend = fileendv.substring(fileendv.length() -3,fileendv.length());
                        System.out.println(fileend);
                        if(fileend.equals(jpg)==true || fileend.equals(png)==true){
                        
                        // �ڿ���̨����ļ����ϴ�·��
                        System.out.println(filePath);
                        System.out.println(username);
                        // �����ļ���Ӳ��
                        item.write(storeFile);
                        
                        String iconName = fileName;
                        
                        request.setAttribute("iconName",
                                "/Helper/upload/icon/"+iconName);
                        session.setAttribute("iconName", iconName);
                        //��ת��SendArticleServlet
                        String path = request.getContextPath();
                    	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path
                    			+ "/";
                    	String site = new String(basePath+"SendIconServlet");
                		response.setStatus(response.SC_MOVED_TEMPORARILY);
                		response.setHeader("Location", site);
                        }else if(fileend.equals(gif)==true){
                        	// �ڿ���̨����ļ����ϴ�·��
                            System.out.println(filePath);
                            System.out.println(username);
                            // �����ļ���Ӳ��
                            item.write(storeFile);
                            
                            String iconName = fileName;
                            
                            request.setAttribute("iconName",
                                    "/Helper/upload/icon/"+iconName);
                            session.setAttribute("iconName", iconName);
                            
                            //��ת��SendArticleServlet
                            String path = request.getContextPath();
                        	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path
                        			+ "/";
                        	String site = new String(basePath+"SendIconServlet");
                    		response.setStatus(response.SC_MOVED_TEMPORARILY);
                    		response.setHeader("Location", site);
                        }else{
                        	String path = request.getContextPath();
                        	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path
                        			+ "/";
                        	response.getWriter().write("Only support jpg,png,gif picture!!!");
                        	response.setHeader("refresh", "3;url="+basePath+"UserSettingServlet");
                        	System.out.println("��֧���ļ�����");
                        }
                        	
                        }
                    }
                }
            
        } catch (Exception ex) {
            request.setAttribute("message",
                    "������Ϣ: " + ex.getMessage());
        }
    }

}
