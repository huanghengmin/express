<%@include file="/include/common.jsp" %>
<head>
    <%--<meta name="viewport" content="initial-scale=1.0, user-scalable=no"/>--%>
    <%--<meta http-equiv="Content-Type" content="text/html; charset=gb2312"/>--%>
    <title>快递实名</title>
   <%-- <style type="text/css">
        html {
            height: 100%
        }

        body {
            height: 100%;
            margin: 0px;
            padding: 0px
        }

        #container {
            height: 100%
        }
    </style>--%>

    <script type="text/javascript" src="${pageContext.request.contextPath}/js/express/express.js"></script>
    <%--<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=RVMhePvBNyNSxDubgI8ryYxg4pv1pipt"></script>--%>
</head>

<body>
<%--<div id="container"></div>
<script type="text/javascript">
    /*var map = new BMap.Map("container");
    map.centerAndZoom(new BMap.Point(116.331398, 39.897445), 11);
    map.enableScrollWheelZoom(true);

    map.clearOverlays();
    var new_point = new BMap.Point(120, 30);
    var marker = new BMap.Marker(new_point);  // 创建标注
    marker.addEventListener("click", attribute);
    map.addOverlay(marker);              // 将标注添加到地图中
    marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画

    var label = new BMap.Label("我是文字标注哦",{offset:new BMap.Size(20,-10)});
    marker.setLabel(label);

    map.panTo(new_point);

    var geoc = new BMap.Geocoder();

    function attribute() {
        var p = marker.getPosition();  //获取marker的位置
        geoc.getLocation(p, function (rs) {
            var addComp = rs.addressComponents;
            alert(addComp.province + ", " + addComp.city + ", " + addComp.district + ", " + addComp.street + ", " + addComp.streetNumber);
        });
    }*/


    // 百度地图API功能
    //GPS坐标
    var x = 116.32715863448607;
    var y = 39.990912172420714;
    var ggPoint = new BMap.Point(x,y);

    //地图初始化
    var bm = new BMap.Map("container");
    bm.centerAndZoom(ggPoint, 15);
    bm.addControl(new BMap.NavigationControl());
    var marker = null;
    //添加gps marker和label
//    var markergg = new BMap.Marker(ggPoint);
//    bm.addOverlay(markergg); //添加GPS marker
//    var labelgg = new BMap.Label("未转换的GPS坐标（错误）",{offset:new BMap.Size(20,-10)});
//    markergg.setLabel(labelgg); //添加GPS label

    //坐标转换完之后的回调函数
    translateCallback = function (data){
        if(data.status === 0) {
            marker= new BMap.Marker(data.points[0]);
            marker.addEventListener("click", attribute);
            bm.addOverlay(marker);
            marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
            var label = new BMap.Label("SEND LOCATION",{offset:new BMap.Size(20,-10)});
            marker.setLabel(label); //添加百度label
            bm.setCenter(data.points[0]);
        }
    }

    var geoc = new BMap.Geocoder();
    function attribute() {
        var p = marker.getPosition();  //获取marker的位置
        geoc.getLocation(p, function (rs) {
            var addComp = rs.addressComponents;
            alert(addComp.province + addComp.city +addComp.district + addComp.street +  addComp.streetNumber);
        });
    }

    setTimeout(function(){
        var convertor = new BMap.Convertor();
        var pointArr = [];
        pointArr.push(ggPoint);
        convertor.translate(pointArr, 1, 5, translateCallback)
    }, 1000);
</script>--%>
</body>