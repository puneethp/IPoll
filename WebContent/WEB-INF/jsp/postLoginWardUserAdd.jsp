<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="dao.User" isELIgnored ="false" %>
<script language="javascript" type="text/javascript">
var isValid = false;
var isValid2 = false;
function hasFilledfields()
{
	if(document.getElementById('user').value.length!=0&&document.getElementById('password').value.length!=0&&
			document.getElementById('conPassword').value.length!=0&&document.getElementById('email').value
			.length!=0&&document.getElementById('language').value.length!=0)
	{
		var temp = document.getElementById("error");
		temp.innerHTML = "";
		return true;
	}
	var temp = document.getElementById("error");
	temp.innerHTML = "<p>Error: Empty Fields.</p>";
	return false;
}
function passTest()
{
	if(document.getElementById('password').value==document.getElementById('conPassword').value)
	{
		var temp = document.getElementById("passError");
		temp.innerHTML = "";
		return true;
	}
	var temp = document.getElementById("passError");
	temp.innerHTML = "<p>Error: Password dont match.</p>";
	return false;
}
function verifyForm()
{
	verify();
	verify_ward();
	return isValid&&isValid2;
}
function verify()
{
	var xmlhttp;
	var txt,xx,x,i;
	if (window.XMLHttpRequest)
	  {// code for IE7+, Firefox, Chrome, Opera, Safari
	  xmlhttp=new XMLHttpRequest();
	  }
	else
	  {// code for IE6, IE5
	  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	  }
	xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    {
		  x=xmlhttp.responseXML.documentElement.getElementsByTagName("msg");
		  if(x.length==1)
			{
			  
				xx=x[0].getElementsByTagName("Query");
				var temp = xx[0].firstChild.nodeValue;
				if(temp=="UserExist")
				{
					
					xx=x[0].getElementsByTagName("value");
					if(xx[0].firstChild.nodeValue=="false")
					{
						var temp = document.getElementById("idError");
						temp.innerHTML="<p>Error: User Should Exists try a different id.</p>";
						isValid = false;
					}
					else if(xx[0].firstChild.nodeValue=="true"){
						var temp = document.getElementById("idError");
						temp.innerHTML = "";
						isValid = true;
					}
					else
					{
						var temp = document.getElementById("idError");
						temp.innerHTML = "<p>Error: Internal Error please try later.</p>";
						isValid = false;
					}
				}
			}
	    }
	  }
	var pval = "user=" + document.getElementById("user").value;
	xmlhttp.open("GET","./UserExist?"+pval,true);
	xmlhttp.send();
}
function verify_ward()
{
	var xmlhttp;
	var txt,xx,x,i;
	if(window.XMLHttpRequest)
	{
	  xmlhttp=new XMLHttpRequest();
	}
	else
	  {// code for IE6, IE5
	  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	  }
	xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
	    {
		  x=xmlhttp.responseXML.documentElement.getElementsByTagName("msg");
		  if(x.length==1)
			{
			  
				xx=x[0].getElementsByTagName("Query");
				var temp = xx[0].firstChild.nodeValue;
				if(temp=="wardvalid")
				{
					
					xx=x[0].getElementsByTagName("value");
					if(xx[0].firstChild.nodeValue=="false")
					{
						var temp = document.getElementById("idError");
						temp.innerHTML = "<p>Error: Ward Doesnt Exists try a different id.</p>";
						isValid2 = false;
					}
					else if(xx[0].firstChild.nodeValue=="true"){
						var temp = document.getElementById("idError");
						temp.innerHTML="";
						isValid2 = true;
					}
					else
					{
						var temp = document.getElementById("idError");
						temp.innerHTML = "<p>Error: Internal Error please try later.</p>";
						isValid2 = false;
					}
				}
			}
	    }
	  }
	var pval = "wardid=" + document.getElementById("wardid").value;
	xmlhttp.open("GET","./WardIdExist?"+pval,true);
	xmlhttp.send();
}
</script>

<c:if test="${not empty requestScope.msg}">
<label class="error">${requestScope.msg}</label>
</c:if>
<form onsubmit="return verifyForm()" action="addWardUser.action">
		<fieldset>
			<legend>Add Ward User</legend>
		<label class="text-error" id="idError" ></label>
		<label>User</label>
		<input type="text"  onblur="verify()" id="user" name="user"/>
		<label>WardId</label>
		<input type="text" onblur="verify_ward()"  id="wardid" name="wardid" /><br/>
		<input type="submit" class="btn btn-primary" />
		</fieldset>
</form>