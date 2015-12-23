(function(){

    // 页面加载完成时表单显示动画
    var formAnimation = function(){
        var tempArr = new Array();
        $('#login-form').find('.row').each(function(index, element){
            tempArr.push($(this));
        });
        var i = 0;
        var handleClass = function(){
            tempArr[i].removeClass('preroll');
            i++;
            if( i > 2 ){
                clearInterval( rehandleInterval );
            }
        }
        var rehandleInterval = setInterval(handleClass, 200);
    }

    // 输入框聚焦时 label 移动的动画
    // 默认页面打开时判断输入框值状态 执行label状态 兼容 Chrome
    var bopsAutoPostionLabel = function( _id ){
        var e = {
            _element: {},
            _targetId: "",
            _forInput: {},
            _opts: {},
            labelMoveOut: function(){
                if( this._opts.style ){
                    this._element.addClass( this._opts.style);
                }else{
                    this._element.css({'left': '-90px'});
                }
            },
            labelMoveIn : function(){
                if( this._opts.style ){
                    this._element.removeClass( this._opts.style);
                }else{
                    this._element.css({'left': '11px'})
                }
            },
            handleChromeSavePassword : function(){
                if( navigator.userAgent.toLowerCase().indexOf("chrome") >= 0){
                    var _interval = window.setInterval(function () {
                        var autofills = $('input:-webkit-autofill');
                        if (autofills.length > 0) {
                            window.clearInterval(_interval);
                            autofills.each(function() {
                                var id = $(this).attr('id');
                                if( this._targetId == id ){
                                    this.labelMoveOut();
                                }
                            });
                        }
                    }, 20);
                }
            },
            handleVal : function(){
                if( this._forInput.val() ){
                    this.labelMoveOut();
                    return;
                }
                this.labelMoveIn();
            },
            addEvent: function(){
                this._forInput.on('input propertychange', function(){
                    e.handleVal();
                });
            },
            init: function( _id ) {
                this._element = $('#'+_id);
                this._targetId = this._element.attr('for');
                this._forInput = $( '#' + this._targetId );
                this._opts.style = "login-form-label-left";

                this.addEvent();
                this.handleVal();
                this.handleChromeSavePassword();
            }
        };
        e.init( _id );
        return e;
    }

    $(document).ready( function(){
        formAnimation();
        var usernameAutoPosition = new bopsAutoPostionLabel('username-label');
        var passwordAutoPosition = new bopsAutoPostionLabel('password-label');
    });

})();