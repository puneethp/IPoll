<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="main">
	<s:form action="WardUserReqVotePage.action" method="post">
		<s:textfield size="15" cssClass="textBox" id="voterid" name="voterid" label="VoterID " />
		<s:submit value="cast vote" cssClass="inbutton" />
	</s:form>
</div>