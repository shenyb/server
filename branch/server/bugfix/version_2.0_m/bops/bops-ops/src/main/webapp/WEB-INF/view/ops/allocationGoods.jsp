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
        <link rel="stylesheet" href="/resources/css/font-awesome.min.css" >
        <link rel="stylesheet" href="/resources/css/layout.css" >
        <link rel="stylesheet" href="/resources/css/ops.css" >
        <link rel="stylesheet" href="/resources/css/sidebar.css" >
        <link rel="stylesheet" href="/resources/css/content-header.css" >
        <link rel="stylesheet" href="/resources/css/datapage.css" >
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
                        <%@ include file="/resources/html/content_header.jsp" %>
                    </div>
                    <div class="layout-right-bottom" >
                        <!-- 
                            这部分写各自页面对应的内容
                            注意锁紧格式
                            范围 start -
                         -->
		<!--头部 -->
		 <div class="data-page-wrap">
             <div style="margin-left:2%;" >
				<div>
					<div>
						当前专题已分配商品:
						<a onclick="addXinhuanGoods('${xinhuanOpsId}')" class="btn btn-info">保存</a>
					</div>
					<!-- ngIf: assignList.length > 0 -->
					<div ng-if="assignList.length &gt; 0" class="clear ng-scope" id="imgDiv">
						
						<!-- ngRepeat: data in assignList -->
						<c:forEach items="${allocationlist}" var="goods"> 
						<div style="float:left;border: 1px solid #f0f0f0;margin-right: 10px" ng-repeat="data in assignList" class="ng-scope" id="img${goods.goodsId }">
							<input type="hidden" name="id" value="${goods.id }" id="xinhuanId">
							<input type="hidden" name="xinhuanGoodsId" value="${goods.goodsId }" id="xinhuanGoodsId">
							<img style="width:100px;" src="${picDomain }${goods.scenePicKey}">
							<div style="width:100px;" class="ng-binding">${goods.goodsName }</div>
							<div>
								<button onclick="deleteImg('${goods.goodsId }')" style="cursor:pointer">删除</button>
							</div>
						</div>
					</c:forEach>
					</div>
					<!-- end ngIf: assignList.length > 0 -->
				</div>
				<div style="margin: 14px 0;" class="bops-pub-param-tips-line"></div>
				<div class="bops-pub-param-seach" style="margin-top: 17%;">
					<input type="text" style="vertical-align: 1px;
						   		  width: 200px;
						   		  padding-left: 18px;
						   		  display:inline-block;
						   		  height:32px" id="goodsCode" placeholder="请输入商品编码进行查询" class="ng-pristine ng-untouched ng-valid">
					<input type="text" style="vertical-align: 1px;
						   		  width: 200px;
						   		  padding-left: 18px;
						   		  display:inline-block;
						   		  height:32px" id="goodsName" placeholder="请输入商品名称进行查询" class="ng-pristine ng-untouched ng-valid">
					<a style="vertical-align:middle;margin-top:-2px;" class="bops-pub-btn" id="search-btn">
						<span id="search-btn-text" class="btn btn-info">搜索</span>
					</a>
				</div>
				   <!--商品列表 -->
				  <div  id="goodsList" style="display: none;">
				      <div class="wrapper">
                          <div class="row">
                             <div class="col-sm-12">
                                  <section class="panel">
				  
					<div class="dataTables_wrapper form-inline dt-bootstrap no-footer" 
                                                     id="DataTables_Table_0_wrapper">
                                                    <div class="tbl-head">
                                                        
                                                    </div>     
                                            <table id="goodsTable" aria-describedby="DataTables_Table_0_info" 
                                                           role="grid" id="DataTables_Table_0" 
                                                           class="table convert-data-table data-table dataTable no-footer">
                                                        <thead>
                                                            <tr role="row">
                                                            <th aria-label="OrderDate : activate to sort column descending" 
                                                                    aria-sort="ascending" style="width: 25px;" 
                                                                    colspan="1" 
                                                                    rowspan="1" 
                                                                    aria-controls="DataTables_Table_0" 
                                                                    tabindex="0" 
                                                                    class="sorting_asc">
                                                                    
                                                                </th>
                                                                <th aria-label="OrderDate : activate to sort column descending" 
                                                                    aria-sort="ascending" style="width: 184px;" 
                                                                    colspan="1" 
                                                                    rowspan="1" 
                                                                    aria-controls="DataTables_Table_0" 
                                                                    tabindex="0" 
                                                                    class="sorting_asc">
                                                                    商品主图
                                                                </th>
                                                                <th aria-label="Region : activate to sort column ascending" 
                                                                    style="width: 135px;" 
                                                                    colspan="1" 
                                                                    rowspan="1" 
                                                                    aria-controls="DataTables_Table_0" 
                                                                    tabindex="0" 
                                                                    class="sorting">
                                                                    商品编号
                                                                </th>
                                                                <th aria-label="Rep : activate to sort column ascending" 
                                                                    style="width: 181px;" 
                                                                    colspan="1" 
                                                                    rowspan="1" 
                                                                    aria-controls="DataTables_Table_0" 
                                                                    tabindex="0" 
                                                                    class="sorting">
                                                                    商品名称
                                                                </th>
                                                                <th aria-label="Item : activate to sort column ascending" 
                                                                    style="width: 133px;" 
                                                                    colspan="1" 
                                                                    rowspan="1" 
                                                                    aria-controls="DataTables_Table_0" 
                                                                    tabindex="0" 
                                                                    class="sorting">
                                                                    商品简介
                                                                </th>
                                                                           <th aria-label="Rep : activate to sort column ascending" 
                                                                    style="width: 181px;" 
                                                                    colspan="1" 
                                                                    rowspan="1" 
                                                                    aria-controls="DataTables_Table_0" 
                                                                    tabindex="0" 
                                                                    class="sorting">
                                                                    商品原价
                                                                </th>
                                                                <th aria-label="Item : activate to sort column ascending" 
                                                                    style="width: 133px;" 
                                                                    colspan="1" 
                                                                    rowspan="1" 
                                                                    aria-controls="DataTables_Table_0" 
                                                                    tabindex="0" 
                                                                    class="sorting">
                                                                    商品折扣价
                                                                </th>
                                                            </tr>
                                                        </thead>
                                                        <tbody>         
                                                              
                </tbody>
            </table>            
                                
                  </div>
                  </section>
                  </div>
                  </div>
                  </div>
				</div>
				</div>
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
        <!-- 侧面导航栏组件 js -->
        <script src="/resources/js/sidebar.js"></script>
        <script src="/resources/js/jquery.pagination.js"></script>
        <script type="text/javascript">
           $('#light-pagination').pagination({
        		pages: "${page.pageCount}",
        		cssStyle: 'light-theme',
        		currentPage: "${page.page}",
				onPageClick: function(pageNumber, event) {
					window.location.href='page?page='+pageNumber;
				}
        	});
          
          //搜索商品
         $('#search-btn').click(function(){
        	 $('#goodsList').show();
        	 var auditStatus="SUCCESS";
        	 var goodsStatus="ONLINE"
        	 var goodsCode=$('#goodsCode').val();
        	 var goodsName=$('#goodsName').val();
        	 $("#goodsTable tbody").children('tr').remove(); 
        	 $.ajax({
    		     type: 'GET',
    		     url: '/goods',
    		     data: {'auditStatus':auditStatus,'goodsStatus':goodsStatus,'goodsCode':goodsCode,'goodsName':goodsName},
    		     dataType: 'json',
    		    success : function(data) {
    		    	var picDomain=data.data.picDomain;
    		    	//alert(imUrl);
                   if(JSON.stringify(data.code)==200){
                	    $.each(data.data.list, function(i,goods){ 
                	    var imgUrl=	picDomain+goods.scenePicKey;
                		var $table = $("#goodsTable");
                   	    var $tr="<tr ng-repeat='data in goodsList' class='ng-scope'>"+
                   	    "<label for='"+goods.goodsId+"'>"+
   						"<td style='vertical-align:middle'>"+
   						"<input type='checkbox'  id='"+goods.goodsId+"' onclick=addGoods(this.id,this.checked)></td>"+
   					"<td style='vertical-align:middle'>"+
   						"<img style='width:25px;' src='"+imgUrl+"'></td></label>"+
   					"<td style='vertical-align:middle' class='ng-binding'>"+goods.goodsCode+"</td>"+	
   					"<td style='vertical-align:middle' class='ng-binding'>"+goods.goodsName+"</td>"+
   					"<td style='vertical-align:middle'>"+
   						"<p style='width:190px;text-overflow:ellipsis;overflow:hidden;white-space:nowrap;' class='ng-binding'>"+goods.brief+"</p></td>"
   					+"<td style='vertical-align:middle' class='ng-binding'>"+goods.onsalePrice+"</td>"+
   					+"<td style='vertical-align:middle' class='ng-binding'>"+goods.discountPrice+"</td></tr>";
                        $table.append($tr);     
                       });    
                	    
                   }else{ 
                	   alert(JSON.stringify(data.msg));
                   }
   			     },
   			    error : function() {
   				    alert("系统异常");
   			}

    		   }); 
        	 
        	 
        	 
         })  
        //把商品添加到DIV  
         function addGoods(goodsId,checked){ 
        	  if(checked==true){
        		  if($("#img"+goodsId).length>0){
        			  alert("此商品已添加过");
        			  $("#"+goodsId).prop('checked',false);
        			  return false;	  
        		  }
        	 $.ajax({
    		     type: 'GET',
    		     url: '/goods/'+goodsId,
    		     dataType: 'json',
    		    success : function(data) {
                   if(JSON.stringify(data.code)==200){
                	   var imgurl="${picDomain }"+data.data.bopsGoods.scenePicKey;
                	   var goodsName=data.data.bopsGoods.goodsName;
                	   var $imgHtml="<div style='float:left;border: 1px solid #f0f0f0;margin-right: 10px' class='ng-scope' id='img"+goodsId+"'>"
              				+"<input type='hidden' id='xinhuanId' value='' name='id'><input type='hidden' id='xinhuanGoodsId' value='"+goodsId+"' name='xinhuanGoodsId'>"
              				+"<img src='"+imgurl+"' style='width:100px;'>"
              				+"<div class='ng-binding' style='width:100px;'>"+goodsName+"</div>"
              				+"<div><button style='cursor:pointer' onclick=deleteImg('"+goodsId+"')>删除</button></div></div>" 
              				var $imgDiv=$('#imgDiv');
              				//alert($imgHtml);
                      	   $imgDiv.append($imgHtml); 
                   }else{ 
                	   alert(JSON.stringify(data.msg));
                   }
   			     },
   			    error : function() {
   				    alert("系统异常");
   			}

    		   });
        	   
        	  }else{
        		  
        		  $("#img"+goodsId).remove();  
        	  }	
          
          } 
        
         function deleteImg(goodsId){
        	 //alert("#img"+goodsId);
        	 $("#img"+goodsId).remove(); 
        	 $("#"+goodsId).prop('checked',false);
        	 
         } 
        
        //给新欢运营位添加商品 
        function addXinhuanGoods(opsId){
        	 
         //var goodsIds=[];
         var obj = $("input[name='xinhuanGoodsId']");
         //alert(obj);
           var goodsIds = new Array();
          for(var i=0;i<obj.length;i++){
        	  goodsIds[i]= obj[i].value;
          } 
          var goodsIds = goodsIds.join(",");
    	 $.ajax({
		     type: 'POST',
		     url: '/opsXinhuanGoods',
		     data:{"opsId":opsId,"goodsIds":goodsIds},
		     dataType: 'json',
		    success : function(data) {
               if(JSON.stringify(data.code)==200){
            	  alert('添加成功');
            	  location.reload()
               }else{ 
            	   alert(JSON.stringify(data.msg));
               }
			     },
			    error : function() {
				    alert("系统异常");
			}

		   }); 	
        }	  
        	  
         </script>
    </body>
</html>