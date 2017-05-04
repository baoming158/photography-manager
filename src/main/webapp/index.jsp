<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8" %>
<%@include file="/common/taglibs.jsp" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta name="description" content="Neon Admin Panel"/>
    <meta name="author" content=""/>

    <title>摄像后台管理系统</title>


    <link rel="stylesheet"
          href="assets/js/jquery-ui/css/no-theme/jquery-ui-1.10.3.custom.min.css">
    <link rel="stylesheet"
          href="assets/css/font-icons/entypo/css/entypo.css">
    <!--<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Noto+Sans:400,700,400italic">-->
    <link rel="stylesheet" href="assets/css/bootstrap.css">
    <link rel="stylesheet" href="assets/css/neon-core.css">
    <link rel="stylesheet" href="assets/css/neon-theme.css">
    <link rel="stylesheet" href="assets/css/neon-forms.css">
    <link rel="stylesheet" href="assets/css/custom.css">
    <link rel="stylesheet" href="assets/css/ky.css">

    <script src="assets/js/jquery-1.11.0.min.js"></script>

    <!--[if lt IE 9]>
    <script src="assets/js/ie8-responsive-file-warning.js"></script><![endif]-->

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <%
        if (request.getSession().getAttribute("index") == null) {
            request.getSession().setAttribute("index", "showProduct");
        }
    %>
    <script>
    $(document).ready(function(){
    	$(".sidebar-collapse").click(function(){
        	setTimeout(function() {
        							$(".sidebar-collapse").css({left:"3px"});
        						}, 100)
        	});
    	});
    function setCss(){
    	$(".sidebar-collapse").click(function(){
        	setTimeout(function() {
        							$(".sidebar-collapse").css({left:"3px"});
        						}, 100)
        	});
    }
    
    
    </script>
</head>
<body class="page-body  page-fade" data-url="http://neon.dev">

<div class="page-container">
    <!-- add class "sidebar-collapsed" to close sidebar by default, "chat-visible" to make chat appear always -->
    <div class="sidebar-menu">

        <header class="logo-env">
            <!-- logo -->
            <div class="logo">
                <a href="index.jsp"> <img src="assets/images/logo@2x.png"
                                          width="120" alt=""/>
                </a>
            </div>

            <div class="sidebar-collapse">
                <a href="#"  class="sidebar-collapse-icon with-animation" onclick="setCss()">
                    <!-- add class "with-animation" if you want sidebar to have animation during expanding/collapsing transition -->
                    <i class="entypo-menu"></i>
                </a>
            </div>

            <!-- open/close menu icon (do not remove if you want to enable menu on mobile devices) -->
            <div class="sidebar-mobile-menu visible-xs">
                <a href="#" class="with-animation"> <!-- add class "with-animation" to support animation -->
                    <i class="entypo-menu"></i>
                </a>
            </div>
        </header>
        <ul id="main-menu" class="">
            <s:auth value="label_manager">
                <li class="opened"><a href="#"
                                      action="${ctx}/showProduct"> <i
                        class="entypo-layout"></i> <span>产品管理</span>
                </a></li>
            </s:auth>
            <s:auth value="dot_manager">
            <li class="closed">
            	<a href="layout-api.html"> <i class="entypo-layout"></i> <span>打点管理</span>
           		</a>
                <ul>
                    <li class="active"><a href="#"
                                          action="${ctx}/showVideo"> <span>视频列表</span>
                    </a></li>
                </ul>
            </li>
            </s:auth>
        </ul>
    </div>
    <div class="main-content-1 main-content">

        <div class="row">

            <!-- Profile Info and Notifications -->
            <div class="col-md-6 col-sm-8  user-wrapper clearfix">
                <ul class="user-info pull-left pull-none-xsm">

                    <!-- Profile Info -->
                    <li class="profile-info dropdown pull-right">
                        <!-- add class "pull-right" if you want to place this from right -->

                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            ${sessionScope.user.name} </a>

                        <ul class="dropdown-menu">

                            <!-- Reverse Caret -->
                            <li class="caret"></li>

                            <!-- Profile sub-links -->
                            <li><a href="${ctx}/clearLogin"> <i class="entypo-user"></i>
                                退出
                            </a></li>

                            <!--<li>
                    <a href="mailbox.html">
                        <i class="entypo-mail"></i>
                        Inbox
                    </a>
                </li>

                <li>
                    <a href="extra-calendar.html">
                        <i class="entypo-calendar"></i>
                        Calendar
                    </a>
                </li>

                <li>
                    <a href="#">
                        <i class="entypo-clipboard"></i>
                        Tasks
                    </a>
                </li>-->
                        </ul>
                    </li>

                </ul>

            </div>

        </div>

        <hr/>
        <div class="row">
            <iframe id="cards_iframe" src="${ctx}/${index }" height="78%"
                    width="100%" border="none" style="border: none; height: 800px;"></iframe>
        </div>
        <!-- Footer -->
        <footer class="main">


            &copy; 2014 <strong>KUYUN</strong> TV<a class="ky-url"
                                                    href="http://www.kuyun.com" target="_blank">酷云互动</a>

        </footer>
    </div>


</div>


<!--<link rel="stylesheet" href="assets/js/jvectormap/jquery-jvectormap-1.2.2.css">
<link rel="stylesheet" href="assets/js/rickshaw/rickshaw.min.css">-->

<!-- Bottom Scripts -->
<script src="assets/js/gsap/main-gsap.js"></script>
<!--<script src="assets/js/jquery-ui/js/jquery-ui-1.10.3.minimal.min.js"></script>-->
<script src="assets/js/bootstrap.js"></script>
<script src="assets/js/joinable.js"></script>
<script src="assets/js/resizeable.js"></script>
<script src="assets/js/neon-api.js"></script>
<!--<script src="assets/js/jvectormap/jquery-jvectormap-1.2.2.min.js"></script>-->
<!--<script src="assets/js/jvectormap/jquery-jvectormap-europe-merc-en.js"></script>-->
<!--<script src="assets/js/jquery.sparkline.min.js"></script>-->
<!--<script src="assets/js/rickshaw/vendor/d3.v3.js"></script>-->
<!--<script src="assets/js/rickshaw/rickshaw.min.js"></script>-->
<!--<script src="assets/js/raphael-min.js"></script>-->
<!--<script src="assets/js/morris.min.js"></script>-->
<script src="assets/js/toastr.js"></script>
<!--<script src="assets/js/neon-chat.js"></script>-->
<script src="assets/js/neon-custom.js"></script>
<script src="assets/js/neon-demo.js"></script>

</body>
</html>
<script type="text/javascript">
    var username = "${sessionScope.user.name}";
    var enabled = "${ws_enabled}";
    var wsUri = "${ws_server}";
    function initSource() {
        if (!username || enabled == 'false' || !wsUri) {
            return false;
        }
        wsUri = "${ws_server}?user_id=" + username
        return true;
    }
    var output;
    var isConnect = 2;
    function init() {
        testWebSocket();
        setInterval(checkConnect, 5000);
    }
    function checkConnect() {
        if (isConnect == 2) {
            testWebSocket()
        }
    }

    function testWebSocket() {
        if (!initSource()) {
            return;
        }
        websocket = new WebSocket(wsUri);
        websocket.onopen = function (evt) {
            isConnect = 1;
            onOpen(evt)
        };
        websocket.onclose = function (evt) {
            isConnect = 2;
            onClose(evt)
        };
        websocket.onmessage = function (evt) {
            onMessage(evt)
        };
        websocket.onerror = function (evt) {
            onError(evt)
        };

    }

    function onOpen(evt) {
        doSend("${sessionScope.user.name} login");
    }

    function onClose(evt) {
        isConnect = 2;
    }

    function onMessage(evt) {
        var data = evt.data;
        toastr.info(data);
    }

    function onError(evt) {
        websocket.close();
    }

    function doSend(message) {
        websocket.send(message);
    }

    window.addEventListener("load", init, false);
</script>