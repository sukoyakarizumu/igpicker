// ログアウト時の処理
function logout() {
	var ret = confirm("ログアウトします。よろしいですか？");
	if (ret == true) {
		$('#logout-form').submit();
	}
	else {
		return false;
	}
}

// 画像削除時の処理
function deleteImage() {
	var classList = $(this).attr('class').split(' ');
	var imageId = classList[1].slice(6);
	var csrfToken = classList[2];

	var ret = confirm("画像#" + imageId + "を削除します。よろしいですか？");
	if (ret == true) {
		var url = $(this).attr('href');
		$.ajax({
			url : url,
			type : 'DELETE',
			headers : {
				"X-CSRF-TOKEN" : csrfToken,
			},
			success : function(result) {
				location.reload();
			},
			error : function() {
				alert("画像の削除に失敗しました。");
			}
		});
	}
	return false;
}

$(function() {

	// ログアウトのリンクを押下した場合のイベン追加ト
	$('#logout-link').click(logout);

	// 削除のリンクを押下した場合のイベント追加
	$(".delete-link").each(function() {
		$(this).click(deleteImage);
	});
});
