<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page session="true"%>
<c:set var="loginId" value="${sessionScope.id}"/>
<c:set var="loginOutLink" value="${loginId=='' ? '/login/login' : '/login/logout'}"/>
<c:set var="loginOut" value="${loginId=='비회원' ? '로그인' : '로그아웃'}"/>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<c:url value='/css/boardList.css'/>">
    <script src="https://code.jquery.com/jquery-1.11.3.js"></script>
    <title>HOME</title>
</head>
<body>
<div id="wrap">
    <header id="header"><!--header/start-->
        <nav class="nav"><!--nav/start-->
            <h1 class="main-logo">
                <a class="main-logo-img" href="<c:url value="/"/>"></a>
            </h1><!--.main-logo-->
            <div class="nav-list">
                <div class="nav-git">
                    <a class="nav-git-adr" href="https://github.com/LeeSangKuk/mySpace.git">https://github.com/LeeSangKuk/mySpace.git</a>
                </div><!--.nav-git-->
                <div class="nav-info">
                    <ul class="nav-info-list">
                        <li class="nav-user">
                            접속된 계정 :
                            <span class="nav-user-name">${loginId}</span>
                        </li>
                        <li class="nav-user-login"><a href="<c:url value='${loginOutLink}'/>">${loginOut}</a></li>
                        <li class="nav-user-join"><a href="<c:url value='/login/join'/>">회원가입</a></li>
                    </ul><!--.nav-info-list-->
                </div><!--.nav-info-->
            </div><!--nav-list-->
        </nav><!--nav/end-->
    </header><!--header/end-->

    <!-- =======================================header============================================= -->
    <div class="board-container">
        <div class="search-container">
            <form action="#" class="search-form" method="get">
                <select class="search-option" name="option">
                    <option>제목+내용</option>
                    <option>제목만</option>
                    <option>작성자</option>
                </select>
                <input type="text" name="keyword" class="search-input" type="text" value="" placeholder="검색어를 입력해주세요">
                <input type="submit" class="search-button" value="검색">
            </form>
            <button id="writeBtn" class="btn-write">글쓰기</button>
        </div>
        <table>
            <tr>
                <th class="no">번호</th>
                <th class="title">제목</th>
                <th class="writer">이름</th>
                <th class="regdate">등록일</th>
                <th class="viewcnt">조회수</th>
            </tr>
            <c:forEach var="boardDTO" items="${list}">
                <tr>
                <td class="no">${boardDTO.bno}</td>
                <td class="title">${boardDTO.title}</td>
                <td class="writer">${boardDTO.writer}</td>
                <td class="regdate"><fmt:formatDate value="${boardDTO.reg_date}" pattern="yyyy-MM-dd" type="date"/></td>
                <td class="viewcnt">${boardDTO.view_cnt}</td>
                </tr>
            </c:forEach>
        </table>
        <div></div>
        </section><!--.main-join/end-->
    <footer><!--footer/start-->
    </footer><!--footer/end-->
</div><!--#wrap-->
</body>
</html>
