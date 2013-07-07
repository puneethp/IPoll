<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored ="false" %>
<%@page import="dao.Blog"%>
<%@page import="dao.User"%>
<c:if test="${not empty blogs }">
	<table class="table">
	<tr>
		<th>Title</th>
		<th>By</th>
		<th>Views</th>
	</tr>
	<c:forEach var="blog" items="${blogs }">
		<tr>
			<td><a href="<s:url action='viewBlog.action' />?blog_id=${blog.blog_id}">${blog.title }</a></td>
			<td>${blog.user }</td>
			<td>${blog.views }</td>
		</tr>
	</c:forEach>
	</table>
</c:if>