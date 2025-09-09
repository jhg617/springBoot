<%@ page import="com.sist.ex0908_bbs.vo.BbsVO" %>
<%@ page import="com.sist.ex0908_bbs.vo.CommVO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Insert title here</title>
  <link rel="stylesheet" href="https://code.jquery.com/ui/1.14.1/themes/base/jquery-ui.css">
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

    #bbs table th {
      text-align:center;
      border:1px solid black;
      padding:4px 10px;
    }

    #bbs table td {
      text-align:left;
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

    .hide{ display: none}

  </style>

</head>
<body>
<%--<%
  Object obj = request.getAttribute("vo");
  if(obj != null){
    BbsVO vo = (BbsVO) obj;
%>--%> <%--위의 스크립트릿안에 내용을 아래 JSTL 문법으로 바꿈--%>
<c:if test="${requestScope.vo ne null}"> <%--ne은 '!='와 같다--%>
  <c:set var="vo" value="${requestScope.vo}"/>
<div id="bbs">
  <form method="post" >
    <table summary="게시판 글쓰기">
      <caption>게시판 글쓰기</caption>
      <tbody>
      <tr>
        <th>제목:</th>
        <td>${vo.subject}</td>
      </tr>
      <c:if test="${vo.file_name ne null and vo.file_name.length() > 4}"> <%--첨부파일이 있을경우에만 보여주기--%>
      <tr>
        <th>첨부파일:</th>
        <td><a href="javascript:down('${vo.file_name}')">
          ${vo.file_name}
        </a></td>
      </tr>
      </c:if>
      <tr>
        <th>이름:</th>
        <td>${vo.writer}</td>
      </tr>
      <tr>
        <th>내용:</th>
        <td>${vo.content}</td>
      </tr>

      <tr>
        <td colspan="2">
          <input type="button" value="수정" onclick="goEdit()"/>
          <input type="button" value="삭제" onclick="goDel()"/> <%--삭제 버튼 지정--%>
          <%--<input type="button" value="목록" onclick="javascript:location.href='Controller?type=list&cPage=${param.cPage}'"/>--%>
          <input type="button" value="목록" onclick="goList()"/>
        </td>
      </tr>
      </tbody>
    </table>
  </form>
  <form method="post" action="bbs/addComm">
    이름:<input type="text" name="writer"/><br/>
    내용:<textarea rows="4" cols="55" name="content"></textarea><br/>
    비밀번호:<input type="password" name="pwd"/><br/>

    <input type="hidden" name="b_idx" value="${vo.b_idx}">
    <input type="hidden" name="cPage" value="${param.cPage}"/>
    <input type="hidden" name="type" value="commadd"/>
    <input type="submit" value="저장하기"/>
  </form>

  <form name="ff" method="post">
    <input type="hidden" name="bname" value="${bname}"/> <%--mv.addObject에 bname 등록해줘서 param 없이 바로 사용 가능--%>
    <input type="hidden" name="f_name"/>
    <input type="hidden" name="b_idx" value="${vo.b_idx}"/>
    <input type="hidden" name="cPage" value="${param.cPage}"/> <%--BbsControl에서 mv.addObject 등록안해줘서 param 붙여줘야함--%>
  </form>

  <%-- 삭제 시 보여주는 팝업창--%>
  <div id="del_dialog" title="삭제">
    <form action="/bbs/del" method="post">
      <%-- 비밀번호 표현등 할 수 있음 --%>
      <p>정말로 삭제하시겠습니까?</p>
      <input type="hidden" name="b_idx" value="${vo.b_idx}"/>
      <input type="hidden" name="cPage" value="${param.cPage}"/>
      <button type="button" onclick="del(this.form)">삭제</button>
    </form>
  </div>

  댓글들<hr/>
  <c:forEach var="cvo" items="${vo.c_list}">
  <div>
    이름:${cvo.writer}&nbsp;&nbsp;
    날짜:${cvo.write_date} <br/>
    내용:${cvo.content}
  </div>
  <hr/> <%--밑줄--%>
  </c:forEach>

</div>
</c:if>

<%-- 표현할 vo객체가 존재하지 않는다면 원래 있던 목록 페이지로 이동한다.--%>
<c:if test="${requestScope.vo eq null}"> <%--eq는 '==' 와 같다--%>
  <c:redirect url="/list"> <%--********************************--%>
    <c:param name="cPage" value="${param.cPage}"/>
    <c:param name="bname" value="${bname}"/>
  </c:redirect>
</c:if>

<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/ui/1.14.1/jquery-ui.js"></script>

<script>
  $(function () {
    let option = {
      modal: true,
      autoOpen: false, //호출되는 즉시 대화상자 표시(기본값: true)
      resizable: false,
    };

    $("#del_dialog").dialog(option);
  });

  function goList() {
    document.ff.action = "/list";
    document.ff.submit();
  }
  function goDel() {
    /*document.ff.action = "Controller";
    document.ff.type.value = "del"
    document.ff.submit();*/
    $("#del_dialog").dialog("open");
  }
  function del(frm) {
    frm.submit();
  }
  function goEdit() {
    //ff를 찾아야한다!
    document.ff.action = "/edit";
    document.ff.submit();
  }
  function down(fname) {
    document.ff.action = "/download"; //controller에 Mapping으로 이동!
    document.ff.f_name.value = fname;
    document.ff.submit();
  }
</script>
</body>
</html>













