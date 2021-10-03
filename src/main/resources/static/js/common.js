var setAddress = function(pref, city) {
	$('#pref').val(pref);
	$('#city').val(city);
}

var callbackFunction = function(data) {
	if (data.results == null) {
		alert('該当の住所が見つかりませんでした。');
		return;
	}
	var result = data.results[0];
	pref = result.address1;
	city = result.address2 + result.address3;

	setAddress(pref, city);
}

$(function() {
	
	// レイアウト調整	
	$('textarea.readonly').each(function(index, element) {
		$(element).height(0).innerHeight(element.scrollHeight);
	});
	$(document).on('input', 'textarea', function() {
		$(this).height(0).innerHeight(this.scrollHeight);
	})
	
	// CollapseのToggler設定
	$(document).on('click', '*[data-bs-toggle="collapse"]', function(e) {
		e.preventDefault();
		$(this).find('span').toggleClass('d-none');
	})

	// サイドナビゲーション
	$(document).on('click', '.sidenav-toggler, .cover', function() {
		$('.sidenav').toggleClass('visible');
		$('.cover').toggleClass('visible');
	});

	// ユーザー情報削除を押下した際にFromのAction属性を変更する
	$('#userDelete').click(function() {
	    if(!confirm('本当にこの投稿を削除しますか？')){
	        return false;
	    }else{
			$(this).parents('form').attr('action', $(this).data('action'));
			$(this).parents('form').submit();
	    }
	});

	// HTMLを読み込んだ際の処理
	$(document).ready(function() {
		// UserProfileの読み込み時
		if(window.location.href.match("UserProfile.html")){
			setProfileDefault();
		}
		// UserRegistrationの読み込み時
		if(window.location.href.match("UserRegistration.html")){
			isProfEdPermission();
		}
	});

	// デフォルトの画面表示を設定する。
	function setProfileDefault() {
		$(".personal-info").each( function(index, element) {
			 if(!$(element).text()){
				 $(element).text("未登録")
			 }
		 });
		if($(".pref").text() === "未登録"){
			$(".isShow-address").hide();
		}
		var PhoneNo = $(".PhoneNo");
		var phone = PhoneNo.eq(0).text();
		var mobile = PhoneNo.eq(1).text();
		if(phone === "未登録" && mobile === "未登録" ){
			$(".isShow-mobilePhoneNo").hide();
		} else {
			if(phone === "未登録"){
				$(".isShow-PhoneNo").hide();
				$(".isShow-mobile").hide();
			}
			if(mobile === "未登録"){
				$(".isShow-mobile").hide();
				$(".isShow-mobilePhoneNo").hide();
			}
		}
		if($("input[name = 'roles']").val() == "01"){
			console.log($('#loginUserId').val());	
			console.log($('#userId').val());	
			if($('#loginUserId').val() != $('#userId').val()){
	    		$('#editProfile').css('pointer-events', 'none');
	    		$('#editProfile').css('opacity', .65);
			}
		}
	}

	// デフォルトの画面表示を設定する。
	function isProfEdPermission() {
		switch($("#loginUserRoles").val()){
			case "01":
	    		$('select[name="roles"] option[value="02"]').prop('disabled', true);
	    		$('select[name="roles"] option[value="03"]').prop('disabled', true);
	    		$('#userDelete').prop('disabled', true);
				break;
			case "02":
	    		$('select[name="roles"] option[value="03"]').prop('disabled', true);
				break;
		}
	}


/** 
	$(document).ready(function() {
		var file = $('#thumbnail').attr('src');
		readerOnLoad(file);
	});
*/
	// 画像ファイルのサムネイル取得
	$(document).on('change', '#photo-file', function() {
		var file = $(this).prop('files')[0];
		// 画像以外は処理を停止
		if (!file.type.match('image.*')) {
			// クリア
			$(this).val('');
			alert('画像ファイルを選択してください。');
			return;
		}
		readerOnLoad(file);
		/*
		var file = $(this).prop('files')[0];
		// 画像以外は処理を停止
		if (!file.type.match('image.*')) {
			// クリア
			$(this).val('');
			alert('画像ファイルを選択してください。');
			return;
		}

		var reader = new FileReader();
		reader.onload = function() {
			$('#thumbnail').attr('src', reader.result);
		}
		reader.readAsDataURL(file);
		*/
	});

	// 画像表示
	function readerOnLoad(file) {
		var reader = new FileReader();
		reader.onload = function() {
			$('#thumbnail').attr('src', reader.result);
		}
		reader.readAsDataURL(file);
	}



	// ドラッグ＆ドロップイベントをハンドル 
	$("#thumbnail").on('dragenter', function(e) {
		e.stopPropagation();
		e.preventDefault();
	});
	$("#thumbnail").on('dragover', function(e) {
		e.stopPropagation();
		e.preventDefault();
	});
	$("#thumbnail").on('drop', function(e) {
		e.preventDefault();
		handleFiles(e.originalEvent.dataTransfer.files);
	});
	// ファイルがdivの外でドロップされブラウザで開かないよう、documentの「ドロップ」イベントを防ぐ。 
	$(document).on('dragenter', function(e) {
		e.stopPropagation();
		e.preventDefault();
	});
	$(document).on('dragover', function(e) {
		e.stopPropagation();
		e.preventDefault();
	});
	$(document).on('drop', function(e) {
		e.stopPropagation();
		e.preventDefault();
	});
	// ファイルがドロップされた時にファイルの中身を読み込む。 
	function handleFiles(files) {

		var file = $('#photo-file').prop('files')[0];
		// 画像以外は処理を停止
		if (!file.type.match('image.*')) {
			// クリア
			$(this).val('');
			alert('画像ファイルを選択してください。');
			return;
		}

		// 画像表示
		var reader = new FileReader();
		reader.onload = function() {
			$('#thumbnail').attr('src', reader.result);
		}
		reader.readAsDataURL(file);

	/*
		var file = files[0];

		// ファイルが画像が確認する
		if (!file.type.match('image.*')) {
			alert('画像を選択してください');
			return;
		}

		var img = document.createElement('img');  // <img>をつくります
		var reader = new FileReader();

		reader.onload = function() {  // 読み込みが完了したら
			img.src = reader.result;  // readAsDataURLの読み込み結果がresult
			$('#thumbnail').append(img);  // preview_filedに画像を表示
		}

		reader.readAsDataURL(file); // ファイル読み込みを非同期でバックグラウンドで開始
	*/
	}

	// 郵便番号検索
	$(document).on('click', '#search-address', function() {
		var baseURL = 'https://zipcloud.ibsnet.co.jp/api/search';
		var zip = $('#zipcode').val();

		// 郵便番号未入力チェック
		if (zip == null || zip.length != 7) {
			alert('郵便番号は7桁で入力してください。');
		}

		// Ajaxでzipcloudへアクセス
		$.ajax({
			type: 'GET',
			url: baseURL,
			dataType: 'jsonp',
			jsonp: callbackFunction,
			data: {
				zipcode: zip,
				callback: 'callbackFunction'
			}
		});
	});
	
});