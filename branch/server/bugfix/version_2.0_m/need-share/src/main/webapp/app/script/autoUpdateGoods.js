(function(){
    var e = {
        goodsList: null,
        postData: null,
        setValue: function( _responseList ){
        	for(var i = 0; i < _responseList.length; i++) {
        		var thisDom = e.goodsList[_responseList[i].id];
        		thisDom.find("[data-goods-img]").attr("src","http://7xkdkd.com2.z0.glb.qiniucdn.com/"+ goodsData.goodsTopPics[0] );
        		thisDom.find("[data-goods-name]").text( goodsData.goodsName );
        		thisDom.find("[data-goods-desc]").text( goodsData.brief );
        		thisDom.find("[data-goods-oriprice]").text( goodsData.onsalePrice );
        		thisDom.find("[data-goods-price]").text( "¥"+goodsData.discountPrice );
        	};
        },
        getGoodsDom: function(){
        	$("[data-goods-id]").each(function( index, element ){
        		var thisDom = $(this);
        		var idadtime = thisDom.attr("[data-goods-id]");
        		var id = idadtime.split("-")[0];
        		e.postData.push( idadtime );
        		e.goodsList[id] = thisDom;
        	});
        },
        checkGoodsInfo: function(){
        	if( e.goodsidList.length > 0 ){
        		$.post("/checkGoodsInfo", { 
        			goodsIdAndUpdateTime: e.goodsidList 
        		}, function( response ){
        		  	if( response.code == 200 ){
        		  		if( response.data.list > 0 ){
        		  			e.setValue( response.data.list );
        		  		}
        		  	}
        		})
        	}
        },
        init: function() {
        	e.getGoodsDom();
        	e.checkGoodsInfo();
        }
    };
    e.init()
})();