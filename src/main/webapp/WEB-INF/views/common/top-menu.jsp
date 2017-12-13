<div id="top-menu">
	<h1>${title}</h1>
	<sec:authorize access="isAuthenticated()">
		<ul id="main-menu">
			<li><a href="${contextPath}/index">ホーム</a></li>
			<li><a href="${contextPath}/upload">画像アップロード</a></li>
			<sec:authorize access="hasRole('ADMIN')">
				<li><a href="${contextPath}/admin">管理</a></li>
			</sec:authorize>
		</ul>
		<ul id="account-menu">
			<li><a id="logout-link" href="#">ログアウト</a></li>
		</ul>
		<div id="login-user">
			ログイン中：
			<sec:authentication property="principal.username" />
		</div>
		<form id="logout-form" action="${contextPath}/logout" method="POST">
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
		</form>
	</sec:authorize>
</div>