<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored ="false" %>
<%@page import="dao.Blog"%>
<%@page import="dao.User"%>
<c:if test="${not empty requestScope.blogs }">
<table class="table">
  <tr>
    <th>User</th>
    <th>Title</th>
    <th>Views</th>
  </tr>
  <c:forEach var="blog" items="${requestScope.blogs }">
			<tr>
			<td><span>${blog.user}</span></td>
			<td><span><a href="viewBlog.action?blog_id=${blog.blog_id}">${blog.title}</a></span></td>
			<td>${blog.views}</td>
			</tr>
	</c:forEach>
</table>
</c:if>
<c:if test="${ empty requestScope.blogs}">
	<div class="error" id="error">No blogs to display.</div>
</c:if>