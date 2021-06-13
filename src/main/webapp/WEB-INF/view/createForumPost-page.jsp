<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html>
	<head>
		<title>Create Forum Post</title>
		
		<link type="text/css" rel="stylesheet" href="/css/materialize.css"/>
		<link type="text/css" rel="stylesheet" href="/css/custom.css"/>
		<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
	</head>
	<body class="bg-custom">
		<div>
			<div class="float-left color-red-variant1 margin-top--15">
				<h4>Hi, ${sessionUser.firstName}</h4>
			</div>
			<div >
				<form:form action="${pageContext.request.contextPath}/logout" method="post">
				    <input type="submit" class="btn btn-outline float-right margin-top-15" value="Logout"/>
				</form:form>
			</div>
		</div>
		<br><br><br>
	
		<form:form action="processCreateForumPost" method="post" modelAttribute="newPost">
		    <div class="center">
			  <div class="container">
				  <div class="row">
					  <div class="col m12">
						  <div class="card rgba-bg">
							  <div class="card-content">
							  <h3 class="text-color-mat-default">New Forum Post</h3>
							  	  <div class="input-field">
							  	  	<i class="material-icons prefix">title</i>
							        <form:input id="title" class="validate text-color-white" path="title"/>
								  	<label for="title">Title</label>
								  	<form:errors path="title" class="error"/>
							      </div>
							  	  <div class="input-field">
							  	  	<i class="material-icons prefix">format_align_left</i>
						           	<form:textarea id="content" class="materialize-textarea validate text-color-white" path="content"/>
						           	<label for="content">Content</label>
						           	<form:errors path="content" class="error"/>
							      </div>
							      <input type="submit" class="btn btn-outline" value="Post"/>
							  </div>
			  			  </div>
			  		  </div>
			  	  </div>
			  </div>
			</div>
		</form:form>
		
		<form:form action="forumPostList" method="get">
	        <input type="submit" class="btn btn-outline" value="Home"/>
	    </form:form>
		
		<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
		
 	 	<script>
	 	    M.textareaAutoResize($('#content'));
 	 	</script>
	</body>
</html>
