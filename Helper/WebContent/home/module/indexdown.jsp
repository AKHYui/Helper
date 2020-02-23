<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String mpath = request.getContextPath();
String mbasePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+mpath+"/";
%>
            </div>
        </div>
    </div>
    <script src="<%=mbasePath %>assets/js/jquery.min.js"></script>
    <script src="<%=mbasePath %>assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="<%=mbasePath %>assets/js/Sidebar-Menu.js"></script>
</body>
</html>