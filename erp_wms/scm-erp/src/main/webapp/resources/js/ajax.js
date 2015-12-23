/**
 * ajax 公共请求方法 , 需要jquery
 * 
 * params:
 * url: 拼接好的地址（带参数！）
 * method： 请求方法 1 get ， 2 post 其他get
 * data: 参数（json格式）
 * callback ： 回掉函数
 * msg ： 错误提示信息
 */


	var to_ajax = function(url,method, data, callback, errmsg) {
		//alert(url);
		$.ajax({
			type : method,
			url : url,
			data: data,
			cache : false,
			dataType : 'json',
			success : function(data) {
				callback.call(this,data);
			},
			error : function() {
				alert(errmsg);
			}
		});

	}

