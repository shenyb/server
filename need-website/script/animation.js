// JavaScript Document
$( document ).ready( function(){
	
	// $('.slogan').animate({'margin-left':'-89px'});
	$('.slogan').addClass('on');
	
	
	window.onscroll = function(){
		var st = $(document).scrollTop();
		var t1 = $( 'header').height();
		var t2 = $( '.indexmain1').height();
		var t3 = $( '.indexmain2').height();
		var t4 = $( '.indexmain3').height();
		var t5 = $( '.indexmain4').height();
		var t6 = $( '.indexmain5').height();
		//console.log( $(document).scrollTop() );
		if( st > t1 + t2/2 && st < t1 + t2 + t3/2 ){
			// $( '.indexmain2con li' ).fadeIn( "slow" );
			// console.log( t1 + t2 + t3 + t4/2 );
			$('.n').addClass('on');
			$('.qrcode').addClass('on');
		} 
		if (  st > t1 + t2 + t3/2 && st < t1 + t2 + t3 + t4/2 ){
			//console.log( '执行动画' );
			$('.lal').find('img').eq(0).animate({'margin-left':0,'opacity':1},'slow',function(){
				$('.lal').find('img').eq(1).animate({'margin-left':0,'opacity':1},'slow',function(){
					$('.lal').find('img').eq(2).animate({'margin-left':0,'opacity':1},'slow',function(){
						$('.phone2').animate({'margin-left':200,'opacity':1},'slow');
					});
				});
			});
		}
		if (  st > t1 + t2 + t3 + t4/2 && st < t1 + t2 + t3 + t4 + t5/2 ){
			$( '.phone3-2' ).animate({'margin-right':0,'opacity':'1'},'slow',function(){
				$('.ryq').find('img').eq(0).animate({'margin-top':0,'opacity':1},'slow',function(){
					$('.ryq').find('img').eq(1).animate({'margin-top':0,'opacity':1},'slow',function(){
						$('.ryq').find('img').eq(2).animate({'margin-top':0,'opacity':1},'slow');
					});
				});
			});
		}
		if (  st > t1 + t2 + t3 + t4 + t5/2 && st < t1 + t2 + t3 + t4 + t5 + t6/2 ){
			$('.qqj').find('p').eq(0).animate({width:'427px',opacity:'1'},'slow',function(){
				$('.qqj').find('p').eq(1).animate({width:'427px',opacity:'1'},'slow',function(){
					$('.qqj').find('p').eq(2).animate({width:'445px',opacity:'1'},'slow',function(){
						$( '.phone4' ).animate({'margin-left':'200px',opacity:'1'},'slow');
					});
				});
			});
		}
		
	}

}) 
