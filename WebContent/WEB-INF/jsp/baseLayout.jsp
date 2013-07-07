<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<html>
<head>
<!--   -->
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="./bootstrap/css/bootstrap.css" rel="stylesheet" />
<link href="./bootstrap/css/bootstrap-responsive.css" rel="stylesheet" />
</head>
<body>

	<script src="./bootstrap/js/jquery.min.js"></script>
	<script src="./bootstrap/js/bootstrap.min.js"></script>

	<div class="container-fluid">
		<div class="navbar navbar-inverse">
			<div class="navbar-inner">
				<ul class="nav">
					<tiles:insertAttribute name="headNavigation" />
				</ul>
				<tiles:insertAttribute name="postLoginHeader" />
				
			</div>
		</div>
		<div class="row-fluid">
			<div class="span2">
				<tiles:insertAttribute name="menu" />
			</div>
			<div class="span8">
				<tiles:insertAttribute name="content" />
			</div>
			<div class="span2">
				<tiles:insertAttribute name="right" />
			</div>
		</div>
	</div>
</body>
</html>