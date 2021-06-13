<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html>
	<head>
		<title>Forum Post Comments</title>
		
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
	
		<div class="container word-wrap-break-word">
		  <div class="row">
			  <div class="col m12">
				  <div class="card rgba-bg-variant1">
					  <div class="card-content">
					  	<div class="center">
						  <div class="info-message display-block max-width-800">
						  		<h3>${forumPostDetails.title}</h3>
						  </div>
						</div>
					    <br>
				        <h5>${forumPostDetails.content}</h5>
				        <p>
			        	<span class="success-message display-block width-31-percent margin-left-600">
			        		Created By: <span class="font-300">${forumPostDetails.creatorName}</span><br>
			        		User Id: <span class="font-300">${forumPostDetails.creatorId}</span><br>
					  		Created On: <span class="font-300">${forumPostDetails.createdOn}</span>
			        	</span>
					  </div>
				  </div>
			  </div>
		  </div>
		</div>
		
		<div class="container">
		  <div class="row">
			  <div class="col m12">
			      <input id="scroll_bottom" type="submit" class="btn btn-outline" value="Scroll bottom"/>
			  </div>
		  </div>
		</div>
		
		<div class="container">
		  <div class="row">
			  <div class="col m3">
				  <div class="card rgba-bg-variant3">
					  <div class="card-content">
					  <c:choose>
					  	<c:when test="${commentCount == 1}">
					  		<h5>${commentCount} Comment</h5>
					  	</c:when>
				      	<c:otherwise>
					  		<h5>${commentCount} Comments</h5>
					  	</c:otherwise>
					  </c:choose>
				      </div>
			      </div>
		  	  </div>
		  </div>
		</div>
		
		<c:forEach items="${commentMap}" var="comment" varStatus="vs">
			<c:if test="${commentMap != ''}">
				<p>
				<div class="container">
				  <div class="row">
					  <div class="col m12">
						  <div class="card rgba-bg-variant2">
							  <div class="card-content">
								<h5 class="text-color-white">${comment.value[0]}</h5>
								<span class="success-message display-block width-31-percent margin-left-600">
					        		Created By: <span class="font-300">${comment.value[2]}</span><br>
					        		User Id: <span class="font-300">${comment.value[1]}</span><br>
							  		Created On: <span class="font-300">${comment.value[3]}</span>
					        	</span>
							  </div>
						  </div>
					  </div>
				  </div>
				</div>
			</c:if>
		</c:forEach>
		
		<p>
		<form:form id="submit_comment_form" action="${pageContext.request.contextPath}/user/submitPostComment#submit_comment_form" method="post" modelAttribute="postComments">
		    <div class="center">
			  <div class="container">
				  <div class="row">
					  <div class="col m12">
						  <div class="card rgba-bg-variant3">
							  <div class="card-content">
							  	  <div class="input-field">
							  	  	<i class="material-icons prefix">insert_comment</i>
						           	<form:textarea class="materialize-textarea text-color-white" id="comment" path="comment"/>
						           	<form:errors path="comment" class="error"/>
						           	<label for="comment">Leave a comment...</label>
							      </div>
							      <form:hidden path="titleId" value="${forumPostDetails.titleId}"/>
							      
							      <input type="submit" class="btn btn-outline" value="Post"/>
							  </div>
			  			  </div>
			  		  </div>
			  	  </div>
			  </div>
			</div>
		</form:form>
		
		<div class="container">
		  <div class="row">
			  <div class="col m12">
			      <input id="scroll_top" type="submit" class="btn btn-outline" value="Scroll top"/>
			  </div>
		  </div>
		</div>
		
		<form:form action="${pageContext.request.contextPath}/user/forumPostList" method="get">
	        <input type="submit" class="btn btn-outline" value="Home"/>
	    </form:form>
		
		<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
		
 	 	<script>
	 	    M.textareaAutoResize($('#comment'));
 	 	</script>
 	 	<script>
	 	 	$('#scroll_bottom').click(function () {
	 	 	    $('html, body').animate({scrollTop:$(document).height()}, 'slow');
	 	 	    return false;
	 	 	});
	
	 	 	$('#scroll_top').click(function () {
	 	 	    $('html, body').animate({scrollTop:0}, 'slow');
	 	 	    return false;
	 	 	});
 	 	</script>
	</body>
</html>