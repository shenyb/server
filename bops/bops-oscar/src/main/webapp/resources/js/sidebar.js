(function(){

	var $sidebar,$subnavList = [];

	$(document).ready( function(){

        $sidebar = $('#sidebar');

        $('#sidebar').find('.child-list').find('li').each(function( _index, _element ){
        	$subnavList.push( $(_element) );
        });

        var clearSubnavActive = function(){
        	for (var i = 0; i < $subnavList.length; i++) {
        		$subnavList[i].removeClass('active');
        	};
        }

        var clearNavActive = function(){
        	$sidebar.find('.menu-list').each(function(){
        		$(this).removeClass('nav-active');
        	});
        }

        $('#sidebar').find('.dashboard').on('click',function(){
        	clearNavActive();
        	$(this).addClass('active');
        });

        $sidebar.find('.menu-list').on('click',function(){
        	clearSubnavActive();
        	if( $(this).hasClass('nav-active') ){
        		$(this).removeClass('nav-active');
        	}else{
        		$(this).addClass('nav-active').siblings().removeClass('nav-active');
        	}
        });

        $('#sidebar').find('.child-list').find('li').on('click',function( e ){
        	e.stopPropagation();
        	clearSubnavActive();
        	$('#sidebar').find('.dashboard').removeClass('active');
        	$(this).addClass('active').siblings().removeClass('active');
        });
    });

})();