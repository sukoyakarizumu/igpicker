<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<%@ include file="common/link.jsp"%>
<title>${title}</title>
</head>
<body>
	<%@ include file="common/top-menu.jsp"%>

	<div id="contents">
		<div id="image-upload-area">
			<h2 id="image-upload-header">画像アップロード</h2>
			<form:form action="${contextPath}/upload" method="post"
				modelAttribute="form" enctype="multipart/form-data">

				<form:label path="title">タイトル</form:label>
				<form:input path="title" />

				<form:label path="comment">コメント</form:label>
				<form:textarea path="comment" rows="5"/>

				<form:label path="file">ファイル選択</form:label>
				<form:input path="file" type="file" />

				<form:button>送信</form:button>
			</form:form>
		</div>
	</div>

</body>
</html>