<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored ="false" %> 
<script type="text/javascript" src="./js/jHtmlArea-0.7.5.js"></script>
<link rel="Stylesheet" type="text/css" href="./bootstrap/css/jHtmlArea.css" />
<div>
<s:form action="commitPage.action" method="post">
<input type="hidden" id="page" value="${page}" name="page" />
<tr><td colspan="2"><h1>${page}</h1></td></tr>
<tr>
<td colspan="2">
<textarea name="content_page" id="content_page" style="width:450px; height:400px">${contents}</textarea></td>
</tr>
<tr><td></td><td><s:submit cssClass="btn btn-primary" value="Change" /></td></tr>
</s:form>

<script type="text/javascript">
// You can do this to perform a global override of any of the "default" options
// jHtmlArea.fn.defaultOptions.css = "jHtmlArea.Editor.css";

$(function() {
    $("textarea").htmlarea(); // Initialize jHtmlArea's with all default values
});
</script>

</div>