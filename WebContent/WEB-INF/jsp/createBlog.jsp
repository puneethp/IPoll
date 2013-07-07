<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>

<script type="text/javascript" src="./js/jHtmlArea-0.7.5.js"></script>
<link rel="Stylesheet" type="text/css" href="./bootstrap/css/jHtmlArea.css" />
<div>
	<s:form action="createBlog.action" method="post">
		<s:textfield cssClass="textBox" size="10" label="Title " name="title"
			id="title" value="value" />
		<tr>
			<td colspan="2"><textarea name="content_blog" id="content_blog"
					style="width: 450px; height: 400px"></textarea></td>
		</tr>
		<tr>
			<td></td>
			<td><s:submit cssClass="btn btn-primary" value="blogit" /></td>
		</tr>
	</s:form>
<script type="text/javascript">
        // You can do this to perform a global override of any of the "default" options
        // jHtmlArea.fn.defaultOptions.css = "jHtmlArea.Editor.css";

        $(function() {
            $("textarea").htmlarea(); // Initialize jHtmlArea's with all default values
        });
    </script>

</div>