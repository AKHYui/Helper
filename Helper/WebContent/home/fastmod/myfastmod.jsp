<%@page import="org.apache.jasper.tagplugins.jstl.core.Out"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/home/module/indexup.jsp"%>

<%=session.getAttribute("fastmodkey") %>
<c:forEach items="${key_list}" var="usr" varStatus="idx">
<div class="row">
<div class="col-md-3">
<p class="text-center">发布人：${usr.user} </p>
</div>
<div class="col-md-6">
<p class="text-center">求助：${usr.text} &nbsp;<i class="fa fa-warning"></i>状态：${usr.status}</p>
</div>
<div class="col-md-3">
<p class="text-center"><button data-toggle="modal" data-target="#fastmod${usr.id}" type="button" class="btn btn-success"><i class="fa fa-coffee"></i>&nbsp;详 情</button></p>
</div>
</div>
</c:forEach>


<%@ include file="/home/module/indexdown.jsp"%>
