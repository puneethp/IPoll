<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="dao.User"%>
<%@ page isELIgnored ="false" %>
<c:if test="${not empty requestScope.user}">
<div class="main">
	<h1>Welcome to I-Poll</h1>
					<p>
					An interactive online Polling solution. there is lot more
					than just voting online.
					</p>
					<ul>
						<li>Blog.</li>
						<li>Interact with the would be politicians.</li>
						<li>Voice your opinion.</li>
					</ul>
					
</div>
</c:if>