var e = {
    imgList: [],
    getImgList: function(){
        $('#imglist-cont').find('img').each(function(_index,_element){
            e.imgList.push( $(this) );
        });
        e.handleImg();
    },
    handleImg: function(){
        for(var i = 0; i < e.imgList.length; i++) {
            if( parseInt(e.imgList[i].width()) > parseInt( e.imgList[i].height()) ){
                e.imgList[i].css({
                    'max-width': 'none',
                    'max-height': '100%'
                });
                var m = (parseInt(e.imgList[i].parent().width()) - parseInt(e.imgList[i].width()))/2;
                e.imgList[i].css({
                    'margin-left': m
                });
            }else{
                e.imgList[i].css({
                    'max-width': '100%',
                    'max-height' : 'none'
                });
                var m = (parseInt(e.imgList[i].parent().height()) - parseInt(e.imgList[i].height()))/2;
                e.imgList[i].css({
                    'margin-top': m
                });
            }
        };
    },
    init: function(){
        e.getImgList();
    }
}
e.init()