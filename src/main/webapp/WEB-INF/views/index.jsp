<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<%@ include file="common/link.jsp"%>
<link rel="stylesheet"
	href="<c:url value="/resources/css/lib/lightbox.css" />">
<title>${title}</title>
</head>
<body>
	<%@ include file="common/top-menu.jsp"%>

	<div id="contents">
		<div id="image-list-area">
			<h2 id="image-list-header">画像一覧</h2>

			<ul class="image-list">
				<c:forEach var="i" items="${list}" varStatus="status">
					<li class="image-item">
						<div class="image-description">
							<h3 class="image-title">#${i.id} ${i.title}</h3>
							<p class="image-comment">${i.comment}</p>
						</div>
						<div class="image-thumbnail">
							<a href="${contextPath}/api/v1/images/${i.id}/file"
								data-lightbox="image-index" data-title="${i.title}">
								<img src="${i.thumbnail}">
							</a>
						</div>
						<div class="image-operation">
							<a class="delete-link image-${i.id} ${_csrf.token}" href="${contextPath}/api/v1/images/${i.id}">
								<img src="${imagePath}/delete.png" width="16" height="16">
							</a>
						</div>
					</li>
				</c:forEach>
			</ul>
		</div>
	</div>

	<script src="<c:url value="/resources/js/lib/lightbox.js" />"></script>
</body>
</html>