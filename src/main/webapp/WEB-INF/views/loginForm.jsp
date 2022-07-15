<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false"%>
<c:set var="loginId" value="${pageContext.request.getSession(false)==null ? '비회원' : pageContext.request.session.getAttribute('id')}"/>
<c:set var="loginOut" value="${loginId=='비회원' ? '로그인' : '로그아웃'}"/>
<c:set var="loginOutLink" value="${loginId=='' ? '/login/login' : '/login/logout'}"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<c:url value='/css/loginForm.css'/>">
    <script src="https://code.jquery.com/jquery-1.11.3.js"></script>
    <title>LOGIN</title>
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

    <section class="section1"><!--section1/start-->
        <form action="<c:url value="/login/login"/>" method="post" onsubmit="return formCheck(this);">
            <div class="login-form"><!--login-form/start-->
                <h1 class="login-title">LOGIN</h1>
                <div id="msg" class="msg">
                    <c:if test="${not empty param.msg}">
                        <i>${param.msg}</i>
                    </c:if>
                </div><!--.msg-->
                <div class="login-input">
                    <input type="text" name="id" value="${cookie.id.value}" placeholder="아이디" autofocus>
                    <input type="password" name="pw" placeholder="비밀번호">
                    <input type="hidden" name="toURL" value="${param.toURL}">
                </div><!--.login-input-->
                <button class="login-btn">로그인</button>
                <div class="login-others">
                    <label><input type="checkbox" name="rememberId" value="on" ${empty cookie.id.value ? "":"checked"}> [아이디 기억]</label>
                    <a class="login-other-margin" href="#">[ID/PW찾기]</a>
                    <a href="<c:url value='/login/join'/>">[회원가입]</a>
                </div><!--.login-others-->
            </div><!--.login-form/end-->
        </form><!--/form-->
    </section><!--section1/end-->
    <footer><!--footer/start-->
    </footer><!--footer/end-->
</div><!--#wrap-->
<script>
    let msg = "${msg}";
    if(msg=="JOIN_OK") alert("회원가입에 성공했습니다!");
    if(msg=="JOIN_ERR") alert("회원가입에 실패했습니다 다시 시도해주세요.");
</script>
<script>
    function formCheck(frm) {
        let msg ='';
        if(frm.id.value.length==0 || frm.pw.value.length==0) {
            setMessage('로그인 정보에 공백이 있습니다!', frm.id);
            return false;
        }
        return true;
    }
    // 메세지를 저장하는 함수
    function setMessage(msg, element){
        document.getElementById("msg").innerHTML = ` ${'${msg}'}`;
        if(element) {
            element.select();
        }
    }
</script>
</body>
</html>