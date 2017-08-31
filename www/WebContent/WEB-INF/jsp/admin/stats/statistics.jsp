<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><spring:message code="contents.stats.admin.title" /></title>
<!--[if lte IE 8]>
<script src="${pageContext.request.contextPath}/js/excanvas.min.js"></script>
<![endif]-->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/chart/Chart.min.js"></script>
<script type="text/javascript" charset="UTF-8">
<!--
var myChart;

$(function() {
	var ctx = $("#myChart");
	
	$.ajax({
		type: 'POST'
 		, dataType: 'JSON'  
 		, url: "statsAjax.do"
 		, data: {}
 		, success: function(data) {
 			var list = eval(data.result.list);
 			var labels = [];
 			var values = [];
 			var length = list.length;
 			var backColor = [];
 			var borderColor = [];
 			
 			for(var i=0; i<length; i++) {
 				labels.push(list[i].name);
 				values.push(list[i].value);
 				backColor.push("rgba(255, 255, 255, 0.5)");
 				borderColor.push("rgba(255, 255, 255, 1)");
 			}
 			
 			myChart = new Chart(ctx, {
 			    type: 'bar',
 			    data: {
 			        labels: labels,
 			        datasets: [{
 			            data: values,
 			            backgroundColor: backColor,
 			            borderColor: borderColor,
 			            borderWidth: 1
 			        }]
 			    },
 			    options: {
 			    	title: {
 			            display: true,
 			            text: '개시판 통계'
 			        },
 			        legend: {
 			        	display: false
 			        },
 			        scales: {
 			        	xAxes: [{
 			            	gridLines: {
 			            		offsetGridLines: true
 			            	}
						}],
 			            yAxes: [{
 			                ticks: {
 			                    beginAtZero:true
 			                },
							gridLines: {
								offsetGridLines: true
			            	}
 			            }]
 			        }
 			    }
 			});
		}
		, error: function(data, status, err) {
			alert(err);
		}
	});
});
//-->
</script>
</head>
<body>
	<h1><spring:message code="contents.stats.admin.title" /></h1>
	<div style="width:400px; height:400px">
		<canvas id="myChart"></canvas>
	</div>
	
</body>
</html>