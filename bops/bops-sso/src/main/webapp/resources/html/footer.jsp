<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

 <!-- 在 body的最底部引入js文件且一定保持 jquery 在 bootstrap 之前引入 -->
        <script src="<%=basePath %>/resources/js/jquery/jquery-2.1.4.min.js"></script>
        <script src="<%=basePath %>/resources/js/bootstrap/bootstrap.min.js"></script>
        <!-- 侧面导航栏组件 js -->
        <script src="<%=basePath %>/resources/js/sidebar.js"></script>
        <script src="<%=basePath %>/resources/js/jquery.pagination.js"></script>
        <script type="text/javascript">
           $('#light-pagination').pagination({
        		pages: "4",
        		cssStyle: 'light-theme',
        		currentPage: "1",
				onPageClick: function(pageNumber, event) {
					window.location.href='opassign?page='+pageNumber;
				}
        	});
         </script>
    </body>
</html>