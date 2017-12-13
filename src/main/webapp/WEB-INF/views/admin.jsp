<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<%@ include file="common/link.jsp"%>
<title>${title}|管理メニュー</title>
</head>
<body>
	<%@ include file="common/top-menu.jsp"%>

	<div id="contents">

		<div id="user-account-info-area">
			<h2 id="user-list-info-header">ユーザ情報</h2>
			<p>
				ユーザ名：
				<sec:authentication property="principal.username" />
			</p>
		</div>

		<div id="user-account-change-area">
			<h2 id="user-account-change-header">ユーザアカウント変更</h2>
			<form:form action="${contextPath}/admin" method="post"
				modelAttribute="form" enctype="multipart/form-data">

				<form:label path="newUsername">新しいユーザ名</form:label>
				<form:input path="newUsername" />

				<form:label path="newPassword">新しいパスワード</form:label>
				<form:password path="newPassword" />

				<form:label path="newPasswordConfirmation">新しいパスワード(確認用)</form:label>
				<form:password path="newPasswordConfirmation" />

				<form:label path="password">現在のパスワード</form:label>
				<form:password path="password" />

				<form:button>送信</form:button>
			</form:form>
		</div>

		<div id="developer-menu-area">
			<h2 id="developer-menu-header">管理メニュー</h2>
			<ul>
				<li><a href="${contextPath}/admin/initialize">テーブル初期化</a></li>
			</ul>
		</div>

	</div>

</body>
</html>
