function parseContents(text) {
	var str = "";
	
	str = text.replace(/</gi, "&lt;");
	str = str.replace(/>/gi, "&gt;");
	str = str.replace(/\r\n/gi, "<br />");
	
	return str;
}

function listReply(nowBlock, nowPage, code, url) {
	$.ajax({
		type: 'POST'
 		, dataType: 'JSON'  
 		, url: url
 		, data: { "nowBlock" : nowBlock, "nowPage" : nowPage, "code" : code, "url" : url }
 		, success: function(data) {
 			var list = eval(data.result.list);
 			var paging = data.result.paging;
 			var loginid = data.result.loginid;
 			var html = "<li>" + data.result.msgList + "</li>";
 			
 			if(list.length < 1) {
 				html = html + "<li class='center'>" + data.result.msgEmpty + "</li>";
 			} else {
 				html = html + "<li class='row'><table>";
 				
 				for(var i=0; i<list.length; i++) {
 					html = html + "<tr><td>" + list[i].name + "</td><td>" + parseContents(list[i].contents) + "</td><td>" + list[i].regdate + "</td></tr>";
 				}
 				
 				html = html + "</table></li>";
 				html = html + "<li class='center'>" + paging + "</li>";
 			}
 			
 			$("#replyList").html(html);
		}
		, error: function(data, status, err) {
			console.log(data);
			alert(err);
		}
	});
}

function replyProc(param, url, listUrl) {
	$.ajax({
		type: 'POST'
 		, dataType: 'JSON' 
 		, url: url
 		, data: param
 		, success: function(data) {
 			listReply(0, 0, data.result.boardid, listUrl);
		}
		, error: function(data, status, err) {
			alert(err);
		}
	});
}
