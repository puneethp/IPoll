<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored ="false" %>
<%@page import="dao.Election"%>
<%@page import="dao.Candidate"%>
<%@page import="dao.User"%>
<c:if test="${not empty requestScope.candidates}">
<div class="span12">
<h1>Vote</h1>
<s:form action="myVote.action" cssClass="table">
	<input type="hidden" id="elecid" name="elecid" value="${requestScope.elecid }" />
	<tr>
		<th><label>Candidate</label></th>
		<th><label>Symbol</label></th>
		<th><label>Choice</label></th>
	</tr>
	<c:forEach var="candidate" items="${requestScope.candidates}">
		<tr>
		<td><span>${candidate.user}</span></td>
		<td><img class="img-polaroid" src="./File?fileid=${candidate.symbol}" alt="${candidate.user}" /></td>
		<td><input type="radio" name="vote" id="${candidate.user}" value="${candidate.user}" /></td>
		</tr>
	</c:forEach>

	<s:submit cssClass="btn btn-primary" value="vote" />
</s:form>
</div>
</c:if>
<c:if test="${empty requestScope.candidates}">
	<div>No contestants here.</div>
</c:if>