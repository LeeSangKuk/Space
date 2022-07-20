<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false"%>
<c:set var="loginId" value="${pageContext.request.getSession(false)==null ? '비회원' : pageContext.request.session.getAttribute('id')}"/>
<c:set var="loginId" value="${pageContext.request.getSession(true)==null ? pageContext.request.session.getAttribute('id') : '비회원'}"/>
<c:set var="loginOut" value="${loginId=='비회원' ? '로그인' : '로그아웃'}"/>
<c:set var="loginOutLink" value="${loginId=='' ? '/login/login' : '/login/logout'}"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<c:url value='/css/register.css'/>">
    <script src="https://code.jquery.com/jquery-1.11.3.js"></script>
    <title>REGISTER</title>
</head>
    <body>
    <div id="wrap"><!--wrap/start-->
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
        <section class="main-join"><!--.main-join/start-->
            <form:form modelAttribute="userDTO" action="${pageContext.request.contextPath}/login/join" method="POST">
                <div class="main-lineout">
                    <h1 class="main-join-title"><a href="#">REGISTER</a></h1>
                    <label for="user-id">아이디 : <span id="msg" class="msg"><form:errors path="id"/> </span></label>
                    <input type="text" id="user-id" name="id" placeholder="8~16자리의 대소문자와 숫자 조합">
                    <label for="user-pw">비밀번호 : <span id="msg" class="msg"><form:errors path="pw"/> </span></label>
                    <input type="password" id="user-pw" name="pw" placeholder="8자리 이상 대소문자와 숫자, 특수문자 조합">
                    <label for="user-name">이름 : <span id="msg" class="msg"><form:errors path="name"/> </span></label>
                    <input type="text" id="user-name" name="name" placeholder="이상국">
                    <label for="user-email">이메일 : <span id="msg" class="msg"><form:errors path="email"/> </span></label>
                    <input type="email" id="user-email" name="email" placeholder="ksl0097@naver.com">
                    <label for="user-birth">생일 : <span id="msg" class="msg"><form:errors path="birth"/> </span></label>
                    <input type="date" id="user-birth" name="birth" placeholder="2022-07-12">
                    <button class="join-btn" type="submit">가입하기</button>
                </div><!--.main-lineout-->
            </form:form><!--/form-->
        </section><!--.main-join/end-->
    </div><!--#wrap/end-->
    <script>
        let msg = "${msg}";
        if(msg=="JOIN_ERR") alert("회원가입에 실패했습니다, 다시 시도해 주세요.");
    </script>
    </body>
</html>