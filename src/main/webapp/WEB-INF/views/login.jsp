<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<%@ include file="common/link.jsp"%>
<title>${title}|ログイン</title>
</head>
<body>
	<%@ include file="common/top-menu.jsp"%>

	<div id="contents">


		<div id="login-form">

			<form:form action="${contextPath}/login" method="post">

				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />

				<div id="login-input-fields">
					<input type="text" id="username" name="username"
						placeholder="ログインID" /> <input type="password" id="password"
						name="password" placeholder="パスワード" />
				</div>

				<c:if test="${! empty error}">
					<div id="login-message">ユーザー名もしくはパスワードが無効です</div>
				</c:if>

				<input type="submit" value="ログイン" />

			</form:form>
		</div>
	</div>

</body>
</html>
