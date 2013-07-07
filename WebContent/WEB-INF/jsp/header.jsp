<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<a class="brand" href="http://localhost:8080/IPoll/">IPoll</a>
<li><a href="<s:url action='home.action' />?page=Home" alt="Home">Home</a></li>
<li><a href="<s:url action='news.action' />?page=News" alt="News">News</a></li>
<c:if test="${empty sessionScope.user}">
	<li><a href="<s:url action='login.action' />" alt="Login">Login</a></li>
</c:if>
<c:if test="${not empty sessionScope.user}">
<li><a href="<s:url action='viewResults.action' />" alt="View Results">Results</a></li>
</c:if>
<li><a href="<s:url action='help.action' />?page=Help" alt="Help">Help</a></li>
