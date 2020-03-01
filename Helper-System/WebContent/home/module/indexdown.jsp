<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String npath = request.getContextPath();
String nbasePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+npath+"/";
%>
            </div>
            <footer class="bg-white sticky-footer">
                <div class="container my-auto">
                    <div class="text-center my-auto copyright"></div>
                </div>
            </footer>
        </div><a class="border rounded d-inline scroll-to-top" href="#page-top"><i class="fas fa-angle-up"></i></a></div>
    <script src="<%=nbasePath %>assets/js/jquery.min.js"></script>
    <script src="<%=nbasePath %>assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.4.1/jquery.easing.js"></script>
    <script src="<%=nbasePath %>assets/js/theme.js"></script>
</body>

</html>