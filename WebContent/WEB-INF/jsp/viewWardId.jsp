<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored ="false" %> 
<%@ page import="dao.WardID" %>
<div class="main">
	<c:if test="${not empty ward}">
		<h1>${ward.wardId}</h1>
		<dl>
				<dt><label>State: </label></dt>
				<dd>${ward.state }</dd>
				
				<dt><label>City: </label></dt>
				<dd>${ward.city }</dd>
			
				<dt><label>WardNo: </label></dt>
				<dd>${ward.wardNo }</dd>
		</dl>
	</c:if>
</div>