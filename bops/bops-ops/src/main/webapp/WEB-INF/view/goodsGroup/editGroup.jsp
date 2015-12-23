<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <!-- IE=edge告诉IE使用最新的引擎渲染网页 -->
        <!-- chrome=1则可以激活Chrome Frame , Chrome Frame可以让旧版IE浏览器使用Chrome的WebKit渲染引擎处理网页 -->
        <meta content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0" name="viewport">
        <!-- 强制让文档的宽度与设备的宽度保持1:1，并且文档最大的宽度比例是1.0，且不允许用户点击屏幕放大浏览 -->

        <meta content="yes" name="apple-mobile-web-app-capable" />
        <!-- iphone设备中的safari私有meta标签，它表示：允许全屏模式浏览 -->

        <meta content="black" name="apple-mobile-web-app-status-bar-style" />
        <!-- iphone的私有标签，它指定的iphone中safari顶端的状态条的样式 -->

        <meta content="telephone=no" name="format-detection" />
        <!-- 告诉设备忽略将页面中的数字识别为电话号码 -->

        <meta content="email=no" name="format-detection" />
        <!-- android设备忽略将页面中的email识别为电子邮箱 -->
        
        <link rel="shortcut icon" href="/resources/img/common/favicon.ico" type="image/x-icon" />

        <title>运营管理系统</title>
        
        <!-- 主体部分样式表 -->
        <link rel="stylesheet" href="/resources/css/bootstrap/bootstrap.min.css" >
        <link rel="stylesheet" href="/resources/css/bootstrap/button.css" >
        <link rel="stylesheet" href="/resources/css/font-awesome.min.css" >
        <link rel="stylesheet" href="/resources/css/layout.css" >
        <link rel="stylesheet" href="/resources/css/ops.css" >
        <link rel="stylesheet" href="/resources/css/ops-addkol.css" >
        <link rel="stylesheet" href="/resources/css/sidebar.css" >
        <link rel="stylesheet" href="/resources/css/content-header.css" >
       <!--  <link rel="stylesheet" href="/resources/css/datapage.css" > -->
       <link rel="stylesheet" href="/resources/css/profile-table.css">
       <link rel="stylesheet" href="/resources/css/modal.css" >
        <!-- 分页插件 css 样式 -->
        <link rel="stylesheet" href="/resources/css/pagination.css" />
        <!-- 此部分注释内容兼容IE低版本 H5相关 **不要删除**-->
        <!--[if lt IE 9]>
          <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
          <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
    </head>

    <body>
        <section class="layout-wrap">
            <div class="layout-left">
                <%@ include file="/resources/html/sidebar.jsp" %>
            </div>
            <div class="layout-right">
                <div class="layout-right-inner">
                    <div class="layout-right-top">
                        <%@ include file="/resources/html/content_header.html" %>
                    </div>
                    <div class="layout-right-bottom" >
                        <!-- 
                            这部分写各自页面对应的内容
                            注意锁紧格式
                            范围 start -
                         -->
                        <div class="form-page-wrap">
                            <div class="data-page-inner">
                                <div class="page-head">                                 
                                    <!--面包屑导航-->
                                    <div class="state-information">
                                        <ol class="breadcrumb m-b-less bg-less">
                                            <li><a href="#">主页</a></li>
                                            <li><a href="#">组合促销管理</a></li>
                                            <li class="active">编辑促销组合</li>
                                        </ol>
                                    </div>
                                </div>
                                 <div class="info">
					            <div class="info-top">
					                <span>新增促销组合</span>
					                <div class="back">
					                    <a href="javascript :;" onclick="javascript :history.back(-1);" class="button button-rounded button-tiny">返回</a>				                 			                   					                	
					                </div>
					            </div>
					            
				           		<form id="addExpertclassify"  name="myForm" action="/goodsGroup/editGroupGoods" method="POST" class="form-horizontal">
									<table class="table table-bordered table-hover table-condensed table-responsive">											
											<tr class="table-title">
									         	<td colspan="2">
									         	商品信息
									         	</td>												
											</tr>	
											<tr>
									        	<td>商品编码(补加商品)</td>
						                       <td colspan="">
						                       		<textarea name="goodsCodes" id="addGoodsCodes" class="form-control" required rows="5" cols="100" required></textarea>
						                       		<p>(请输入商品编码，以英文逗号分隔)</p>
						                       </td>						                                  
						                    </tr>
						                    <tr class="table-title">
									         	<td colspan="2">
									         	信息设置
									         	</td>												
											</tr>
						                    <tr>
									            <td><i class="fa fa-asterisk"></i>促销名称</td>
						                        <td colspan="3">
						                        <input type="text" name="groupName" id="addGroupName" class="form-control" value="${group.groupName}" required/>
                                      			<input type="hidden" name="groupBatch" id="editGroupBatch" class="form-control" value="${group.groupBatch}" required/>						                       
                                                </td> 						                                          
						                    </tr>
						                   
											<tr>
									            <td><i class="fa fa-asterisk"></i>促销说明</td>
						                        <td colspan="">
						                        <textarea name="groupBrief" id="introduct" required class="form-control" rows="3">${group.groupBrief }</textarea>
						                        </td> 						                                          
						                    </tr>
						                     <tr class="table-title">
									         	<td colspan="2">
									         	规则设置
									         	</td>												
											</tr>
										<tr>
											<td><i class="fa fa-asterisk"></i>规则设置</td>
											<td colspan="3">
											<div class="col-xs-1"> </div>
											<div class="col-xs-3">
											<input type="radio"  name="ruleType" value="DISCOUNT"  <c:if test="${group.ruleType=='DISCOUNT'}">checked='checked'</c:if> /> 折扣
		                                      &nbsp;&nbsp;
		                                      <input type="radio"  name="ruleType" value="FIXEDPRICE" <c:if test="${group.ruleType=='FIXEDPRICE'}">checked='checked'</c:if> /> 一口价
                                      		</div>
                                      	</td>
										</tr>
										<tr name="DISCOUNTTR">
		                                    <td></td>		                                   
		                                    <td>
		                                    <div class="col-xs-1 enough">
		                                      满
		                                    </div>
		                                       <div class="col-xs-2 small-group">
		                                      <input type="text" name="ruleOne" class="form-control" id="ruleOne" value="${group.ruleOne }"/><em class="cake">件</em>
		                                      </div>
		                                      
		                                      <div class="col-xs-2 small-group">
		                                      <input type="text" name="ruleOneDisc" class="form-control" id="ruleOneDisc" value="${group.ruleOneDisc }"/><em class="cake">折</em>
		                                      </div>
		                                      
		                                    </td>
		                                   
		                                  </tr>
		                                  <tr name="DISCOUNTTR">
		                                    <td></td>
		                                    <td>
		                                      <div class="col-xs-1 enough">
		                                      满
		                                    </div>
		                                      <div class="col-xs-2 small-group">
		                                      <input type="text" name="ruleTwo" class="form-control" id="ruleTwo" value="${group.ruleTwo }"/><em class="cake">件</em>
		                                      </div>
		                                      <div class="col-xs-2 small-group">
		                                      <input type="text" name="ruleTwoDisc" class="form-control" id="ruleTwoDisc" value="${group.ruleTwoDisc }"/><em class="cake">折</em>
		                                      </div>
		                                    </td>
		                                   
		                                  </tr>
		                                  <tr name="DISCOUNTTR">
		                                    <td></td>
		                                    <td>
		                                      <div class="col-xs-1 enough">
		                                      满
		                                    </div>
		                                      <div class="col-xs-2 small-group">
		                                      <input type="text" name="ruleThree" class="form-control" id="ruleThree" value="${group.ruleThree }"/><em class="cake">件</em>
		                                      </div>
		                                      <div class="col-xs-2 small-group">
		                                      <input type="text" name="ruleThreeDisc" class="form-control" id="ruleThreeDisc" value="${group.ruleThreeDisc }"/><em class="cake">折</em>
		                                      </div>
		                                    </td>		                                   										
					                   </tr>
					                    <tr id="FIXEDPRICEDIV" style="display: none;">
		                                    <td></td>
		                                    <td>
		                                      <div class="col-xs-1 enough">
		                                      满
		                                    </div>
		                                    <div class="col-xs-2 small-group">
		                                      <input type="text" name="ruleFixed" class="form-control" id="ruleFixed" value="${group.ruleFixed }"/>
		                                      <em class="cake">件</em>
		                                      </div>
		                                      <div class="col-xs-2 small-group">
		                                      <input type="text" name="ruleFixedPrice" class="form-control" id="ruleFixedPrice" value="${group.ruleFixedPrice }"/>
		                                      <em class="cake">元</em>
		                                      </div>
		                                    </td>		                                    
		                                </tr>
		                                 <tr>
											<td colspan="2">
												<button type="submit" class="button button-primary button-raised button-small pull-right" onclick="return toVaild()">提交审核</button>												
											</td>
										</tr>
									</table>																																												
								</form>
							 </div>  
                                
                                <%-- <!-- 2222222222 -->
                                <div class="wrapper">
                                    <div class="row">
                                        <div class="col-sm-12">
                                            <section class="panel">
                                                <header class="panel-heading ">
                                                   新增促销组合 
                                                    <span class="tools pull-right">
															<a href="javascript:void(0)" onclick="javascript :history.back(-1);" class="ops-pub-btn">
																<i class="fa fa-arrow-left"></i>
																<span>返回</span>
															</a>
                                                    </span>
                                                </header>
												<div class="bops-pub-wrap-inner">
													
													<div class="bops-pub-body">
														<div class="bops-pub-param-body">
															<form  id="addExpertclassify"  name="myForm" action="/goodsGroup/editGroupGoods" method="POST" >
															 <div class="bops-pub-param-header">
																	<span class="bops-pub-param-title">
																		商品信息
																	</span>
																</div>
																<table class="bops-pub-param-table">
																	<tbody>
																	<tr>
																		<th style="vertical-align: top;font-weight:normal;">商品编码 </br>(补加商品)</th>
																		<td>
																			<textarea name="goodsCodes" id="addGoodsCodes" rows="5" cols="100"></textarea>
																			</br><i>(请输入商品编码，以英文逗号分隔)</i>
																		</td>
																		<td style="vertical-align: top;"></td>
																	</tr>
																    </tbody>
																</table>
																<div class="bops-pub-param-header">
																	<span class="bops-pub-param-title">
																		信息设置
																	</span>
																</div>
																<table class="bops-pub-param-table">
																	<tbody>
																	<tr>
																		<th style="vertical-align: top;font-weight:normal;"><i style="color:red;">* </i> 促销名称</th>
																		<td>
																			<input type="text" name="groupName" id="addGroupName" value="${group.groupName}" required/>
																			<input type="hidden" name="groupBatch" id="editGroupBatch" value="${group.groupBatch}" required/>
																		</td>
																	</tr>
																	<!-- <tr>
																		<th style="vertical-align: top;font-weight:normal;"><i style="color:red;">* </i> 持续时间</th>
																		<td>
																		    <input type="text" id="startTimeString"
                                                                                name="startTimeString" placeholder="开始时间"
                                                                                value="" onfocus="WdatePicker({firstDayOfWeek:1,dateFmt:'yyyy-MM-dd HH:mm:ss'})" required:teue/>
                                                                            &nbsp;&nbsp;至&nbsp;&nbsp;
                                                                           <input type="text" id="endTimeString" name="endTimeString"
                                                                               placeholder="结束时间" value="" onfocus="WdatePicker({firstDayOfWeek:1,dateFmt:'yyyy-MM-dd HH:mm:ss'})"required:true/>
																		</td>
																	</tr> -->
																	<tr>
																		<th style="vertical-align: top;font-weight:normal;"><i style="color:red;">* </i> 促销说明</th>
																		<td>
																			<textarea name="groupBrief" id="introduct" required="" rows="3" cols="50" >${group.groupBrief }</textarea>
																		</td>
																	</tr>
																</tbody>
																</table>
																<div class="bops-pub-param-header">
																	<span class="bops-pub-param-title">
																		规则设置
																	</span>
																</div>
															 <table class="bops-pub-param-table">
																<tbody>
																	<tr>
																		<th style="vertical-align: top;font-weight:normal;"><i style="color:red;">* </i> 规则设置</th>
																		<td>
																			<input type="radio"  name="ruleType" value="DISCOUNT"  <c:if test="${group.ruleType=='DISCOUNT'}">checked='checked'</c:if> /> 折扣
																			&nbsp;&nbsp;
																			<input type="radio"  name="ruleType" value="FIXEDPRICE" <c:if test="${group.ruleType=='FIXEDPRICE'}">checked='checked'</c:if> /> 一口价
																		</td>
																	</tr>
																	
																	<tr name="DISCOUNTTR">
																		<th style="vertical-align: top;font-weight:normal;"></th>
																		<td>
																			满 &nbsp;&nbsp;<input type="text" name="ruleOne" id="ruleOne" value="${group.ruleOne }"/>&nbsp;&nbsp;件&nbsp;&nbsp;<input type="text" name="ruleOneDisc" id="ruleOneDisc" value="${group.ruleOneDisc }"/>&nbsp;&nbsp;折
																		</td>
																		<td style="vertical-align: top;"></td>
																	</tr>
																	<tr name="DISCOUNTTR">
																		<th style="vertical-align: top;font-weight:normal;"></th>
																		<td>
																			满 &nbsp;&nbsp;<input type="text" name="ruleTwo" id="ruleTwo" value="${group.ruleTwo }"/>&nbsp;&nbsp;件&nbsp;&nbsp;<input type="text" name="ruleTwoDisc" id="ruleTwoDisc" value="${group.ruleTwoDisc }"/>&nbsp;&nbsp;折
																		</td>
																		<td style="vertical-align: top;"></td>
																	</tr>
																	<tr name="DISCOUNTTR">
																		<th style="vertical-align: top;font-weight:normal;"></th>
																		<td>
																			满 &nbsp;&nbsp;<input type="text" name="ruleThree" id="ruleThree" value="${group.ruleThree }"/>&nbsp;&nbsp;件&nbsp;&nbsp;<input type="text" name="ruleThreeDisc" id="ruleThreeDisc" value="${group.ruleThreeDisc }"/>&nbsp;&nbsp;折
																		</td>
																		<td style="vertical-align: top;"></td>
																	</tr>
																
																	
																	 <tr id="FIXEDPRICEDIV" style="display: none;">
																		<th style="vertical-align: top;font-weight:normal;"></th>
																		<td>
																			满 &nbsp;&nbsp;<input type="text" name="ruleFixed" id="ruleFixed" value="${group.ruleFixed }"/>&nbsp;&nbsp;件&nbsp;&nbsp;<input type="text" name="ruleFixedPrice" id="ruleFixedPrice" value="${group.ruleFixedPrice }"/>&nbsp;&nbsp;元
																		</td>
																		<td style="vertical-align: top;"></td>
																	 </tr>
																	
																    </tbody>
																</table>
																
																<div class="bops-pub-param-tips-line" style="margin: 14px 0;"></div>
																<div class="bops-pub-param-btnlist">
																	<button class="bops-pub-btn bops-pub-param-btn"  style="margin-left: 3%;"  type="submit" onclick="return toVaild()">提交审核</button>
																</div>
															</form>
														</div>	
													</div>
												</div>
                                            </section>
                                        </div>
                                    </div>
                                </div>
                            	<!-- 222222222222 --> --%>
                            </div>
                        </div>
                        <!-- 
                            这部分写各自页面对应的内容
                            注意锁紧格式
                            范围 end -
                         -->
                    </div>
                </div>
            </div>
        </section>
        <!-- 在 body的最底部引入js文件且一定保持 jquery 在 bootstrap 之前引入 -->
        <script src="/resources/js/jquery/jquery-2.1.4.min.js"></script>
        <script src="/resources/js/bootstrap/bootstrap.min.js"></script>
        <script src="/resources/js/jquery/jquery.bootstrap.min.js"></script>
        <script src="/resources/js/jquery/jquery.validate.js"></script>
	    <script src="/resources/js/jquery/jquery-validate-message.js"></script>
        <!-- 侧面导航栏组件 js -->
        <script src="/resources/js/sidebar.js"></script>
        <script src="/resources/js/jquery.pagination.js"></script>
        <script src="/resources/js/My97DatePicker/WdatePicker.js"></script>
        <script type="text/javascript">
        $(document).ready(function(){  
            var ruleType= "${group.ruleType}";
        	if(ruleType=='DISCOUNT'){
        	 $("tr[name='DISCOUNTTR']").show();
       		 $('#FIXEDPRICEDIV').hide();
        		
        	} else{
        	 $("tr[name='DISCOUNTTR']").hide();
       		 $('#FIXEDPRICEDIV').show();
        		
        	}
        }); 
        
        $(":radio[name='ruleType']").click(function(){
        	 if($(this).val()=='DISCOUNT'){
        		 $("tr[name='DISCOUNTTR']").show();
        		 $('#FIXEDPRICEDIV').hide();
        	 }else{
        		 $("tr[name='DISCOUNTTR']").hide();
        		 $('#FIXEDPRICEDIV').show();
        		 
        	 }
        })
       
         //校验参数
    /*   		function validateData(){ 
  		    var beginValue= $('#startTimeString').val();
    	    var endValue= $('#endTimeString').val();
    	    if(beginValue!=''&&endValue!=''){
			var begin=new Array();
	        var end=new Array();
	        begin=beginValue.split("-");
	        end=endValue.split("-");
			var bday = new Array();
			bday = begin[2].split(" ");

			var eday = new Array();
			eday = end[2].split(" ");
						
			if(begin[0]>end[0]){
			alert("起始日期大于结束日期");
				return false;
			}
			else if(end[0]-begin[0]>0){
				if(bday[0]>eday[0]){
						return true;
				}
			}  			
			else if(begin[0]==end[0]){
				if(begin[1] > end[1]){
					alert("起始日期大于结束日期");
					return false;
				}				
				else if(end[1]-begin[1]>0){
					if(bday[0]>eday[0]){
						return true;
					}
				}
				else if(end[1]==begin[1]){
					if(bday[0] > eday[0]){
						alert("起始日期大于结束日期");
						return false;
					}
				}
       		}
			return true;
    	    }
			return true;
		} */
      //正整数
        function isPInt(str) {
            var g = /^[1-9]*[1-9][0-9]*$/;
            return g.test(str);
        }
      
       function isNumber(Str){
    	   var g = /^(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*))$/; 
    	   return g.test(Str);
       }
      
        //搜索表单提交验证
        function toVaild(){	
         var goodsCodes=$("#addGoodsCodes").val(); 
         //alert(ruleType);
         if(goodsCodes.indexOf("，")!=-1){
   		  alert("分隔符有误");
   		  return false;
   		  
   	  }
         if(goodsCodes!=null&&goodsCodes!=''){
         var flag = true;
   	        $.ajax({
				     type: 'POST',
				     url: '/goodsGroup/checkGoods',
				     data:{"goodsCodes":goodsCodes},
				     dataType: 'json',
				     async: false,
				    success : function(data) {
				    	if(data.code==200){
				    	}else{
				    		alert(data.msg);
				    		flag= false;
				    	}
					     },
					    error : function() {
						    alert("系统异常");
						    flag= false;
					}

		        });
        	if(!flag){
        		
        		return false;
        	}
         }
         var ruleType=$(":radio[name='ruleType']:checked").val();
         var ruleOne=$("#ruleOne").val();
         var ruleOne=$("#ruleOne").val();
         var ruleTwo=$("#ruleTwo").val();
         var ruleThree=$("#ruleThree").val();
         var ruleFixed=$("#ruleFixed").val();
         var ruleFixedPrice=$("#ruleFixedPrice").val();
         var ruleOneDisc =$("#ruleOneDisc").val();
         var ruleTwoDisc =$("#ruleTwoDisc").val();
         var ruleThreeDisc =$("#ruleThreeDisc").val();
         
           if(ruleType=='DISCOUNT'){
        	  if(ruleOne==null||ruleOne==''||ruleTwo==null||ruleTwo==''||ruleThree==null||ruleThree==''||ruleOneDisc==null||ruleOneDisc==''||ruleTwoDisc==''||ruleTwoDisc==null||ruleThreeDisc==null||ruleThreeDisc==''){
        		alert("折扣规则不能为空");
        		return false;
        	  }else if(!isPInt(ruleOne)||!isPInt(ruleTwo)||!isPInt(ruleThree)){
        		 alert("件数请输入整形");
        		 return false;  
        	  }else if(!isNumber(ruleOneDisc)||!isNumber(ruleTwoDisc)||!isNumber(ruleThreeDisc)){
        		 alert("折扣格式有误");
         		 return false;         		  
        	  }else if(ruleOne==ruleTwo||ruleOne==ruleThree||ruleTwo==ruleThree){
        		  alert("规则冲突，请检查");
          		  return false;
        	  }else if(ruleOneDisc==ruleTwoDisc||ruleOneDisc==ruleThreeDisc||ruleTwoDisc==ruleThreeDisc){
        		  alert("规则冲突，请检查");
          		  return false;  
        	  }else if(ruleOneDisc>=10||ruleThreeDisc>=10||ruleThreeDisc>=10){
        		  alert("规则冲突，请检查");
          		  return false;  
        	  }
        	  
        	  return true;
        	  
        	  
          }else if(ruleType=='FIXEDPRICE'){
        	  
        	 if(ruleFixed==null||ruleFixed==''||ruleFixedPrice==null||ruleFixedPrice==''){
        		 alert("一口价规则不能为空");
        		 return false; 
        	 } else if(!isPInt(ruleFixed)){
        		 alert("件数请输入整形");
        		 return false; 
        		 
        	 }else if(!isNumber(ruleFixedPrice)){
        		 alert("一口价格式有误");
         		 return false;
        		 
        	 }
        	 return true;
        	  
          }
         return true;
        
        }
          
         </script>
    </body>
</html>