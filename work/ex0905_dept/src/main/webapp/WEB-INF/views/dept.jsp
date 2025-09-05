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
        <h1>부서목록</h1>
    </header>
    <article>
        <table>
            <caption>부서목록 테이블</caption>
            <thead>
            <tr>
                <td colspan="4">
                    <form action="search" method="post">
                        <select name="type">
                            <option value="0">부서코드</option>
                            <option value="1">부서명</option>
                            <option value="2">도시코드</option>
                        </select>
                        <input type="text" name="value"/>
                        <button type="button" onclick="exe(this.form)">검색</button>
                    </form>
                </td>
            </tr>
            <tr>
                <th>부서코드</th>
                <th>부서명</th>
                <th>도시코드</th>
            </tr>
            </thead>
            <tbody>
            <c:if test="${ar ne null}">
                <c:forEach var="vo" items="${ar}">
                    <tr>
                        <td>${vo.deptno}</td>
                        <td>${vo.dname}</td>
                        <td>${vo.loc_code}</td>
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
