<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <meta charset="UTF-8">
    <style type="text/css"> 
   #bbs table {
       width:580px;
       margin-left:10px;
       border:1px solid black;
       border-collapse:collapse;
       font-size:14px;
       
   }
   
   #bbs table caption {
       font-size:20px;
       font-weight:bold;
       margin-bottom:10px;
   }
   
   #bbs table th,#bbs table td {
       text-align:center;
       border:1px solid black;
       padding:4px 10px;
   }
   
   .no {width:15%}
   .subject {width:30%}
   .writer {width:20%}
   .reg {width:20%}
   .hit {width:15%}
   .title{background:lightsteelblue}
   
   .odd {background:silver}
   
   /* paging */
   
   table tfoot ol.paging {
       list-style:none;
   }
   
   table tfoot ol.paging li {
       float:left;
       margin-right:8px;
   }
   
   table tfoot ol.paging li a {
       display:block;
       padding:3px 7px;
       border:1px solid #00B3DC;
       color:#2f313e;
       font-weight:bold;
   }
   
   table tfoot ol.paging li a:hover {
       background:#00B3DC;
       color:white;
       font-weight:bold;
   }
   
   .disable {
       padding:3px 7px;
       border:1px solid silver;
       color:silver;
   }
   
   .now {
      padding:3px 7px;
       border:1px solid #ff4aa5;
       background:#ff4aa5;
       color:white;
       font-weight:bold;
   }
      
</style>
</head>
<body>
<div id="bbs">
    <table>
        <caption>게시판</caption>
        <thead>
        <tr class="title">
            <th class="no">번호</th>
            <th class="subject">제목</th>
            <th class="writer">작성자</th>
            <th class="reg">작성일</th>
            <th class="hit">조회수</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="vo" items="${ar}" varStatus="vs">
            <tr>
                <td class="no">
                    ${page.totalCount-((page.nowPage-1)*page.numPerPage)-vs.index}
                </td>
                <td style="text-align: left;">
                    ${vo.subject}
                </td>
                <td>${vo.writer}</td>
                <td>${vo.write_date}</td>
                <td>${vo.hit}</td>
            </tr>
        </c:forEach>
        </tbody>
        <tfoot>
            <tr>
                <td colspan="4">
                    ${pageResult}
                </td>
                <td>
                    <button type="button"
                        onclick="location.href='/write?bname=${page.bname}&cPage=${page.nowPage}'">글쓰기</button>
                </td>
            </tr>
        </tfoot>
    </table>
</div>


</body>
</html>
