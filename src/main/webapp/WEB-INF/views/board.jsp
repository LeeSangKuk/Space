<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page session="true"%>
<c:set var="loginId" value="${sessionScope.id}"/>
<c:set var="loginOutLink" value="${loginId=='' ? '/login/login' : '/login/logout'}"/>
<c:set var="loginOut" value="${loginId=='' ? '로그인' : '로그아웃'}"/>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<c:url value='/css/board.css'/>">
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
    <section class="container"><!--.container/start-->
        <h2 class="writing-header">게시판 ${mode=="new" ? "글쓰기" : "읽기"}</h2>
        <form id="form" class="frm" method="post">
            <input type="hidden" name="bno" value="${boardDTO.bno}">
            <input name="title" type="text" value="<c:out value='${boardDTO.title}'/>" placeholder="  제목을 입력해 주세요." ${mode=="new" ? "" : "readonly='readonly'"}>
            <textarea name="content" rows="20" placeholder=" 내용을 입력해 주세요." ${mode=="new" ? "" : "readonly='readonly'"}><c:out value="${boardDTO.content}"/></textarea>
            <c:if test="${mode eq 'new'}">
                <button type="button" id="writeBtn" class="btn btn-write">등록</button>
            </c:if>
            <c:if test="${mode ne 'new'}">
                <button type="button" id="writeNewBtn" class="btn btn-write">글쓰기</button>
            </c:if>
            <c:if test="${boardDTO.writer eq loginId}">
                <button type="button" id="modifyBtn" class="btn btn-modify">수정</button>
                <button type="button" id="removeBtn" class="btn btn-remove">삭제</button>
            </c:if>
            <button type="button" id="listBtn" class="btn btn-list">목록</button>
        </form>
    <div></div>
    </section><!--.container/end-->
    <footer><!--footer/start-->
    </footer><!--footer/end-->
</div><!--#wrap-->
<script>
    // 메인 스크립트
    $(document).ready(function(){
        // #writeBtn 버튼을 눌렀을 때-----------------------------
        $("#writeBtn").on("click", function(){
            let form = $("#form");
            form.attr("action", "<c:url value='/board/write'/>");
            form.attr("method", "post");
            if(formCheck())
                form.submit();
        });
        // #listBtn 버튼을 눌렀을 때-------------------------------
        $("#listBtn").on("click", function(){
            location.href="<c:url value='/board/list${sc.queryString}'/>";
        });
        // modifyBtn 버튼을 눌렀을 때------------------------------
        $("#modifyBtn").on("click", function(){
            let form = $("#form");
            let isReadonly = $("input[name=title]").attr('readonly');
            // 1. 읽기 상태이면, 수정 상태로 변경
            if(isReadonly=='readonly'){
                $(".writing-header").html("게시판 수정");
                $("input[name=title]").attr('readonly', false);
                $("textarea").attr('readonly', false);
                $("#writeNewBtn").hide();
                $("#modifyBtn").html("변경");
                return;
            }

            // 2. 수정 상태이면, 수정된 내용을 서버로 전송
            form.attr("action", "<c:url value='/board/modify${sc.queryString}'/>");
            form.attr("method", "post");

            // 3. 게시판 공백 다시 학인 후 제출
            if(formCheck())
                form.submit();
        })
        // 3. removeBtn 버튼을 눌렀을 때------------------------------
        $("#removeBtn").on("click", function(){
            if(!confirm("정말로 삭제하시겠습니까?")) return;
            let form = $("#form");
            form.attr("action", "<c:url value='/board/remove${sc.queryString}'/>");
            form.attr("method", "post");
            form.submit();
        });
    })

    // 게시판 박스 공백 체크
    let formCheck = function() {
        let form = document.getElementById("form");
        if(form.title.value=="") {
            alert("제목을 입력해 주세요.");
            form.title.focus();
            return false;
        }
        if(form.content.value=="") {
            alert("내용을 입력해 주세요.");
            form.content.focus();
            return false;
        }
        return true;
    }
</script>
</body>
</html>