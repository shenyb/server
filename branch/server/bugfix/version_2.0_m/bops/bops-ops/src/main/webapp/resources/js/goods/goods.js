/**
 * 
 */
Array.prototype.indexOf = function(val) {
	for (var i = 0; i < this.length; i++) {
		if (this[i] == val)
			return i;
	}
	return -1;
};
Array.prototype.remove = function(val) {
	var index = this.indexOf(val);
	if (index > -1) {
		this.splice(index, 1);
	}
};
Array.prototype.toLeft = function(val) {
	var index = this.indexOf(val);
	if (index == 0)
		return;
	this.remove(val);
	this.splice(index - 1, 0, val);
}
Array.prototype.toRight = function(val) {
	var index = this.indexOf(val);
	if (index == this.length - 1)
		return;
	this.remove(val);
	this.splice(index + 1, 0, val);
}

var topPicLength = 0;
var detailPicLength = 0;
var topPicArray = new Array();
var detailPicArray = new Array();
var picAddress = $("#hiddenPicAddress").val();

$("#topPicKeysUpload")
		.fileupload(
				{
					// formData:{param1:"p1",param2:"p2"},//如果需要额外添加参数可以在这里添加
					done : function(e, result) {

						// done方法就是上传完毕的回调函数，其他回调函数可以自行查看api
						// 注意result要和jquery的ajax的data参数区分，这个对象包含了整个请求信息
						// 返回的数据在result.result中，假设我们服务器返回了一个json对象
						// alert(result);
						var fileName = JSON
								.stringify(result.result.data.fileNames[0]);
						fileName = fileName.substring(1, fileName.length - 1);
						$("#hiddenTopPicKeys").val(
								$("#hiddenTopPicKeys").val() + "," + fileName);
						if ($("#hiddenTopPicKeys").val().charAt(0) == ',') {
							$("#hiddenTopPicKeys").val(
									$("#hiddenTopPicKeys").val().substring(1));
						}
						console.log(JSON.stringify(result.result));
						// alert(JSON.stringify(result.result.data.fileNames[0]));
						// alert($("#hiddenTopPicKeys").val());
						topPicArray.push(fileName);
						$("#divTopPicKeys")
								.append(
										"<span id='topPicKey_"
												+ fileName
												+ "'><a href='javascript:void(0);' onclick=topPicToLeftOrRight('topPicKey_"
												+ fileName
												+ "','left')>&nbsp;←&nbsp;</a><a href='" 
												+ picAddress + fileName +  "' target='_blank'><img "
												+ " width='120px' src='" + picAddress
												+ fileName
												+ "''></a><a href='javascript:void(0);' onclick=topPicToLeftOrRight('topPicKey_"
												+ fileName
												+ "','right')>"
												+ "&nbsp;→&nbsp;</a><a href='javascript:void(0);' onclick=shanchu('topPicKey_"
												+ fileName
												+ "',"
												+ topPicLength
												+ ")>&nbsp;x&nbsp;</a>&nbsp;</span>");
						topPicLength++;
					}
				});

function shanchu(id, picIndex) {
	// alert(id + "\n" + picIndex);
	$("#" + id).remove();
	var hiddenTopPicKeys = $("#hiddenTopPicKeys").val();
	// alert(hiddenTopPicKeys);
	topPicLength--;
	var picKeyURL = id.split("_")[1];
	topPicArray.remove(picKeyURL);
	hiddenTopPicKeys = hiddenTopPicKeys.replace((picKeyURL + ","), '').replace(
			picKeyURL, '');
	if (hiddenTopPicKeys.charAt(hiddenTopPicKeys.length - 1) == ',') {
		hiddenTopPicKeys = hiddenTopPicKeys.substring(0,
				hiddenTopPicKeys.length - 1);
	}

	$("#hiddenTopPicKeys").val(hiddenTopPicKeys);
}
function topPicToLeftOrRight(topKey, type) {
	var topPicKey = topKey.split("_")[1];
	var index = topPicArray.indexOf(topPicKey);
	var span_this = $("#" + topKey);
	if ("left" == type) {
		if (index == 0)
			return;
		topPicArray.toLeft(topPicKey);
		var span_before = $("#" + topKey).prev();
		$("#" + topKey).remove();
		span_before.before(span_this);
	} else {
		if (index == topPicArray.length - 1)
			return;
		topPicArray.toRight(topPicKey);
		var span_after = $("#" + topKey).next();
		$("#" + topKey).remove();
		span_after.after(span_this);
	}
}

$("#scenePicKeysUpload")
		.fileupload(
				{
					// formData:{param1:"p1",param2:"p2"},//如果需要额外添加参数可以在这里添加
					done : function(e, result) {
						// done方法就是上传完毕的回调函数，其他回调函数可以自行查看api
						// 注意result要和jquery的ajax的data参数区分，这个对象包含了整个请求信息
						// 返回的数据在result.result中，假设我们服务器返回了一个json对象
						$("#divScenePicKeys").find("span").remove();
						var fileName = JSON
								.stringify(result.result.data.fileNames[0]);
						fileName = fileName.substring(1, fileName.length - 1);
						$("#hiddenScenePicKeys").val(fileName);
						console.log(JSON.stringify(result.result));
						$("#divScenePicKeys")
								.append(
										"<span id='span_scenePicKey'><a href='" 
												+ picAddress + fileName +  "' target='_blank'><img "
												+ " width='120px' src='" + picAddress
												+ fileName
												+ "''></a><a href='javascript:void(0);' onclick=shanchuScenePicKey('span_scenePicKey')>x</a>&nbsp;&nbsp;&nbsp;</span>");
					}
				});
$("#detailPicKeysUpload")
		.fileupload(
				{
					// formData:{param1:"p1",param2:"p2"},//如果需要额外添加参数可以在这里添加
					done : function(e, result) {

						// done方法就是上传完毕的回调函数，其他回调函数可以自行查看api
						// 注意result要和jquery的ajax的data参数区分，这个对象包含了整个请求信息
						// 返回的数据在result.result中，假设我们服务器返回了一个json对象
						var fileName = JSON
								.stringify(result.result.data.fileNames[0]);
						fileName = fileName.substring(1, fileName.length - 1);
						detailPicArray.push(fileName);
						$("#hiddenDetailPicKeys").val(
								$("#hiddenDetailPicKeys").val() + ","
										+ fileName);
						if ($("#hiddenDetailPicKeys").val().charAt(0) == ',') {
							$("#hiddenDetailPicKeys").val(
									$("#hiddenDetailPicKeys").val()
											.substring(1));
						}
						console.log(JSON.stringify(result.result));
						// alert(JSON.stringify(result.result.data.fileNames[0]));
						// alert($("#hiddenTopPicKeys").val());

						$("#divDetailPicKeys")
								.append(
										"<span id='detailPicKey_"
												+ fileName
												+ "'><a href='javascript:void(0);' onclick=detailPicToLeftOrRight('detailPicKey_"
												+ fileName
												+ "','left')>&nbsp;←&nbsp;</a><a href='" 
												+ picAddress + fileName +  "' target='_blank'><img "
												+ " width='120px' src='" + picAddress
												+ fileName
												+ "''></a><a href='javascript:void(0);' onclick=detailPicToLeftOrRight('detailPicKey_"
												+ fileName
												+ "','right')>&nbsp;→&nbsp;</a><a href='javascript:void(0);' onclick=shanchuDetailPic('detailPicKey_"
												+ fileName
												+ "',"
												+ detailPicLength
												+ ")>&nbsp;x&nbsp;</a>&nbsp;</span>");
						detailPicLength++;
					}
				});

function detailPicToLeftOrRight(topKey, type) {
	var topPicKey = topKey.split("_")[1];
	var index = detailPicArray.indexOf(topPicKey);
	var span_this = $("#" + topKey);
	if ("left" == type) {
		if (index == 0)
			return;
		detailPicArray.toLeft(topPicKey);
		var span_before = $("#" + topKey).prev();
		$("#" + topKey).remove();
		span_before.before(span_this);
	} else {
		if (index == detailPicArray.length - 1)
			return;
		detailPicArray.toRight(topPicKey);
		var span_after = $("#" + topKey).next();
		$("#" + topKey).remove();
		span_after.after(span_this);
	}
}

function shanchuDetailPic(id, picndex) {
	// alert(id + "\n" + picIndex);
	$("#" + id).remove();
	var picURL = id.split("_")[1];
	detailPicArray.remove(picURL);
	var hiddenDetailPicKeys = $("#hiddenDetailPicKeys").val();
	// alert(hiddenTopPicKeys);
	hiddenDetailPicKeys = hiddenDetailPicKeys.replace((picURL + ","),
			'').replace(picURL, '');
	if (hiddenDetailPicKeys.charAt(hiddenDetailPicKeys.length - 1) == ',') {
		hiddenDetailPicKeys = hiddenDetailPicKeys.substring(0,
				hiddenDetailPicKeys.length - 1);
	}
	/*
	 * var detailPicKeysString = new Array(); detailPicKeysString =
	 * hiddenDetailPicKeys.split(","); detailPicKeysString.splice(picIndex, 1);
	 * hiddenDetailPicKeys = detailPicKeysString.join(",");
	 */
	// alert(hiddenTopPicKeys);
	$("#hiddenDetailPicKeys").val(hiddenDetailPicKeys);
}
function shanchuScenePicKey(id) {
	// alert(id + "\n" + picIndex);
	$("#" + id).remove();
	$("#hiddenScenePicKeys").val('');
}

$("#a_submitGoodsEditSave").click(
		function() {
			/* alert($("#goodsEditProfile").serialize()); */
			// alert($("#goodsEditProfile").validate().form());
			
			if (!$("#goodsEditProfile").validate().form()) {
				return;
			}
			
			var hiddenTopPicKeys = "";
			topPicArray.remove('');
			for (i = 0; i < topPicArray.length; i++) {
				hiddenTopPicKeys = hiddenTopPicKeys + "," + topPicArray[i];
			}
			if (hiddenTopPicKeys.charAt(hiddenTopPicKeys.length - 1) == ',') {
				hiddenTopPicKeys = hiddenTopPicKeys.substring(0,
						hiddenTopPicKeys.length - 1);
			}
			if (hiddenTopPicKeys.charAt(0) == ',') {
				hiddenTopPicKeys = hiddenTopPicKeys.substring(1);
			}
			console.log(hiddenTopPicKeys);
			$("#hiddenTopPicKeys").val(hiddenTopPicKeys);
			
			var hiddenDetailPicKeys = "";
			detailPicArray.remove('');
			for (i = 0; i < detailPicArray.length; i++) {
				hiddenDetailPicKeys = hiddenDetailPicKeys + "," + detailPicArray[i];
			}
			if (hiddenDetailPicKeys.charAt(hiddenDetailPicKeys.length - 1) == ',') {
				hiddenDetailPicKeys = hiddenDetailPicKeys.substring(0,
						hiddenDetailPicKeys.length - 1);
			}
			if (hiddenDetailPicKeys.charAt(0) == ',') {
				hiddenDetailPicKeys = hiddenDetailPicKeys.substring(1);
			}
			console.log(hiddenDetailPicKeys);
			$("#hiddenDetailPicKeys").val(hiddenDetailPicKeys);
			
			$.ajax({
				type : "post",
				url : "/goods/addNewGoods",
				data : $("#goodsEditProfile").serialize(),
				cache : false,
				dataType : 'json',
				success : function(data) {
					if (data.code == 200) {
						window.location.href = "/goods/page?page=1";
					} else {
						alert("code: " + data.code + "\n" + data.msg);
					}
				}
			});
		});