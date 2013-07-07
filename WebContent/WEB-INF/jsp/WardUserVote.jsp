<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored ="false" %>
<%@page import="dao.Election"%>
<%@page import="dao.Candidate"%>
<%@page import="dao.User"%>
<div id="info"><div id="left_uni"><span class="username_label">id: </span><span id="username_value" class="username_value">${requestScope.user.id}</span></div><div id="right_uni"><span class = "lastlogin_label">lastlogin: </span><span class = "lastlogin_value">${requestScope.user.lastLogin}</span></div></div>
<c:if test="${not empty requestScope.candidates}">
<div class="main">
<s:form action="WardUserProxyVote.action">
	<input type="hidden" id="elecid" name="elecid" value="${requestScope.elecid }" />
	<input type="hidden" id="voterid" name="voterid" value="${requestScope.voterid }" />
	<tr>
		<th><label>Candidate</label></th>
		<th><label>Symbol</label></th>
		<th><label>Choice</label></th>
	</tr>
	<c:forEach var="candidate" items="${requestScope.candidates}">
		<tr>
		<td><span>${candidate.user}</span></td>
		<td><img class="votercard_image" src="./File?fileid=${candidate.symbol}" alt="${candidate.user}" /></td>
		<td><input type="radio" name="vote" id="${candidate.user}" value="${candidate.user}" /></td>
		</tr>
	</c:forEach>

	<s:submit cssClass="inbutton" value="vote" />
</s:form>
</div>
</c:if>
<c:if test="${empty requestScope.candidates}">
	<div>No contestants here.</div>
</c:if>