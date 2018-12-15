<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<% request.setCharacterEncoding("UTF-8"); %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>클릭 이벤트 등록하기</title>
</head>
<body>

<table width ="100%">
    <tr>
        <td width="28%">
            <p>검색할 내용을 입력해주세요.</p>
            <form action="/kakaos/search" method="get">
                <input type=text name=query SIZE=60 MAXLENGTH=50>
                <button type="submit">검색</button>
            </form>
        </td>
        <td>
            <div id="map" align="right" style="width:100%;height:500px;"></div>
            <p><em>지도를 클릭해주세요!</em></p>
            <p id="result"></p>

            <form action="/kakao/address/search" method="get">
                <input type=text name=location SIZE=60 MAXLENGTH=50>
                <button type="submit">Submit</button>
            </form>

            <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=${appKey}"></script>
            <script>
                var mapContainer = document.getElementById('map'), // 지도를 표시할 div
                    mapOption = {
                        center: new daum.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
                        level: 3 // 지도의 확대 레벨
                    };

                var map = new daum.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

                // 지도에 클릭 이벤트를 등록합니다
                // 지도를 클릭하면 마지막 파라미터로 넘어온 함수를 호출합니다
                daum.maps.event.addListener(map, 'click', function (mouseEvent) {

                    // 클릭한 위도, 경도 정보를 가져옵니다
                    var latlng = mouseEvent.latLng;

                    var message = '클릭한 위치의 위도는 ' + latlng.getLat() + ' 이고, ';
                    message += '경도는 ' + latlng.getLng() + ' 입니다';

                    var resultDiv = document.getElementById('result');
                    resultDiv.innerHTML = message;
                });
                function relayout() {

                    // 지도를 표시하는 div 크기를 변경한 이후 지도가 정상적으로 표출되지 않을 수도 있습니다
                    // 크기를 변경한 이후에는 반드시  map.relayout 함수를 호출해야 합니다
                    // window의 resize 이벤트에 의한 크기변경은 map.relayout 함수가 자동으로 호출됩니다
                    map.relayout();
                }
            </script>
        </td>
    </tr>
</table>
</body>
</html>
