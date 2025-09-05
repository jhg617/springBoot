<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <meta charset="UTF-8">
    <style>
        table{
            width: 500px;
            border-collapse: collapse;
        }
        table th, table td{
            border: 1px solid black;
            padding: 5px;
        }
        table caption{
            text-indent: -9999px;
            height: 0;
        }
        .no-border{ border: none; }
    </style>
</head>
<body>
<div id="wrap">
    <header>
        <h1>사원목록</h1>
    </header>
    <article>
        <table>
            <caption>사원목록 테이블</caption>
            <thead>
            <tr>
                <td colspan="4">
                    <form action="search" method="post">
                        <select name="type">
                            <option value="0">사번</option>
                            <option value="1">이름</option>
                            <option value="2">직종</option>
                            <option value="3">부서</option>
                        </select>
                        <input type="text" name="value"/>
                        <button type="button" onclick="exe(this.form)">검색</button>
                    </form>
                </td>
            </tr>
            <tr>
                <th>사번</th>
                <th>이름</th>
                <th>직종</th>
                <th>부서</th>
            </tr>
            </thead>
            <tbody>
            <c:if test="${ar ne null}">
                <c:forEach var="vo" items="${ar}">
                    <tr>
                        <td>${vo.empno}</td>
                        <td>${vo.ename}</td>
                        <td>${vo.job}</td>
                        <td>${vo.deptno}</td>
                    </tr>
                </c:forEach>
            </c:if>
            </tbody>
        </table>
    </article>
</div>

<script>

    function exe(frm) {
        let v = frm.value.value; //name: value
        if(v.trim().length < 1){
            alert("검색어를 입력하세요");
            frm.value.focus();
            return;
        }
        frm.submit();
    }
</script>
</body>
</html>
