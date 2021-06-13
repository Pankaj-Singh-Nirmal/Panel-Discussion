<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>

<html>
	<head>
		<title>Forum Post List</title>
		
		<link type="text/css" rel="stylesheet" href="/css/materialize.css"/>
		<link type="text/css" rel="stylesheet" href="/css/custom.css"/>
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
	
		<form:form action="createForumPost" method="get">
			<input type="submit" class="btn btn-outline margin-left-5" value="Create New Post"/>
		</form:form>
		<p>
		
		<c:if test="${postSuccess != null}">
			<div class="center"><span class="success-message font-size-20">${postSuccess}</span></div>
		</c:if>
		
		<p>
		<div align="center">
			<div>
				<table class="table table-bordered">
					<thead>
						<tr>
							<td>Sl no</td>
							<td>Title</td>
							<td>Comments</td>
							<td>Creator</td>
							<td>Created On</td>
							<td>Select</td>
						</tr>
					</thead>
		
					<c:forEach items="${postList}" var="post" varStatus="vs">
						<tr>
							<td>${vs.index+1}</td>
							<td>${post.title}</td>
							<td>${post.commentCount}</td>
							<td>
								${post.creatorName}
								<div class="user_id">
									<p>User Id: ${post.creatorId}</p>
								</div>
							</td>
							<td>${post.createdOn}</td>
							<td>
								<form:form action="selectForumPost/${post.titleId}" method="get">
									<input type="submit" class="btn btn-outline" value="Select"/>
								</form:form>
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</body>
</html>