<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="dao.User"%>
<%@ page isELIgnored ="false" %>
<c:if test="${not empty sessionScope.user}">
<ul class="nav pull-right">
<li class="dropdown">
	<a href="#" class="dropdown-toggle" data-toggle="dropdown">${sessionScope.user.id}
	<b class="caret"></b></a>
	<ul class="dropdown-menu">
		<li><a href="<s:url action='logout.action' />" alt="logout">Logout</a></li>
		<li class="nav-header">Last Login</li>
		<li><span style="padding: 5px;">${sessionScope.user.lastLogin}</span></li>
	</ul>
</li>
</ul>
</c:if>