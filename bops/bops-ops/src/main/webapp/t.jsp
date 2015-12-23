<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>


    <body>
         <div>
        <input type="button" onclick="to_ajax('http://localhost:8080/opassign/201510121544149e833376f1c','get',{a:1},say,'error!!!');">
        </div>
      
        
        <script type="text/javascript">
        function say(data){
        	alert('in');
        	alert(JSON.stringify(data));
        }
        
        </script>
        
        <!-- 在 body的最底部引入js文件且一定保持 jquery 在 bootstrap 之前引入 -->
        <script src="resources/js/jquery/jquery-2.1.4.min.js"></script>
        <script src="resources/js/bootstrap/bootstrap.min.js"></script>
         <script src="resources/js/ajax.js"></script>
    </body>
</html>