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