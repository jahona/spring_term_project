<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<% request.setCharacterEncoding("UTF-8"); %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>부동산을 정복하자</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <style>

    </style>
</head>
<body>

<div class="container-fluid">
    <div class="row">
        <div class="col-md-4">
            <div>
                <p>검색할 내용을 입력해주세요.</p>
                <form action="/kakaos/search" method="get">
                    <input type=text name=query SIZE=60 MAXLENGTH=50>
                    <button type="submit">검색</button>
                </form>
            </div>
        </div>
        <div class="col-md-8">
            <div>
                <div id="map" align="right" style="width:100%;height:500px;"></div>
                <div>
                    <p><em>지도를 클릭해주세요!</em></p>
                    <p id="result"></p>
                    <div class="input-group mb-3">
                        <input id="input-address-search" type="text" class="form-control" placeholder="Input Address" aria-label="Input Address" aria-describedby="basic-addon1">
                        <button id="btn-address-search" type="button" class="btn btn-success">Default</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=${appKey}"></script>
<script>
    window.onload = function() {
        $("#btn-address-search").click(searchAddress);

        var mapContainer = document.getElementById('map'), // 지도를 표시할 div
            mapOption = {
                center: new daum.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
                level: 3 // 지도의 확대 레벨
            };

        var map = new daum.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

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

        function searchAddress() {
            var full = location.protocol+'//'+location.hostname+(location.port ? ':'+location.port: '');
            var url = full + "/kakao/address/search";

            var input = $("#input-address-search");
            var inputVal = input.val();

            if(inputVal == '') {
                alert('empty string')
                return false;
            }

            url += "?location=" + inputVal;

            console.log(url);

            var xhr = new XMLHttpRequest();

            xhr.open("GET", url, true);
            xhr.setRequestHeader("Content-type", "application/json");
            xhr.send(null);
            xhr.addEventListener("load", function() {
                switch (xhr.status) {
                    case 200:
                        // 성공 처리
                        console.log('success')
                        // console.log(xhr.responseText)
                        // console.log(typeof xhr.responseText);
                        var obj = JSON.parse(xhr.responseText);
                        var documents = obj['documents']
                        console.log(documents);

                        setPositionKaKaoMap(documents);
                        break;
                    case 500:
                        // 예외 처리
                        console.log('fail')
                        console.log(xhr.responseText)
                        alert('server error');
                }
            });
        }

        function setPositionKaKaoMap(mapArrs) {
            if(mapArrs.length > 0) {
                setCenter(mapArrs[0]['y'], mapArrs[0]['x']);
            }

            var positions = [];

            for(var i = 0 ; i < mapArrs.length ; i++) {
                positions.push({
                    c: mapArrs[i]['address_name'],
                    latlng: new daum.maps.LatLng(mapArrs[i]['y'], mapArrs[i]['x']),
                    bCode: mapArrs[i]['address']['b_code']
                });
            }

            console.log(positions);

            // 마커 이미지의 이미지 주소입니다
            var imageSrc = "http://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";

            for (var i = 0; i < positions.length; i ++) {
                // 마커 이미지의 이미지 크기 입니다
                var imageSize = new daum.maps.Size(24, 35);

                // 마커 이미지를 생성합니다
                var markerImage = new daum.maps.MarkerImage(imageSrc, imageSize);

                // 마커를 생성합니다
                var marker = new daum.maps.Marker({
                    map: map, // 마커를 표시할 지도
                    position: positions[i].latlng, // 마커를 표시할 위치
                    // title : positions[i].t, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
                    image : markerImage // 마커 이미지,
                });

                // 마커에 표시할 인포윈도우를 생성합니다
                var infowindow = new daum.maps.InfoWindow({
                    content: positions[i].c // 인포윈도우에 표시할 내용
                });

                // 마커에 mouseover 이벤트와 mouseout 이벤트를 등록합니다
                // 이벤트 리스너로는 클로저를 만들어 등록합니다
                // for문에서 클로저를 만들어 주지 않으면 마지막 마커에만 이벤트가 등록됩니다
                daum.maps.event.addListener(marker, 'mouseover', makeOverListener(map, marker, infowindow));
                daum.maps.event.addListener(marker, 'mouseout', makeOutListener(infowindow));

                // 마커에 click 이벤트를 등록합니다
                daum.maps.event.addListener(marker, 'click', clickListener(positions[i].bCode));
            }
        }

        function setCenter(lat, lng) {
            console.log(lat);
            console.log(lng);
            // 이동할 위도 경도 위치를 생성합니다
            var moveLatLon = new daum.maps.LatLng(lat, lng);

            // 지도 중심을 이동 시킵니다
            map.setCenter(moveLatLon);
        }

        // 인포윈도우를 표시하는 클로저를 만드는 함수입니다
        function makeOverListener(map, marker, infowindow) {
            return function() {
                infowindow.open(map, marker);
            };
        }

        function clickListener(bCode) {
            return function() {
                var full = location.protocol+'//'+location.hostname+(location.port ? ':'+location.port: '');
                var url = full + "/building/trade/ym_record?LAWD_CD=" + bCode.substring(0, 5) + "&DEAL_YMD=201810";

                console.log(url);

                var xhr = new XMLHttpRequest();

                xhr.open("GET", url, true);
                xhr.setRequestHeader("Content-type", "application/json");
                xhr.send(null);
                xhr.addEventListener("load", function() {
                    switch (xhr.status) {
                        case 200:
                            // 성공 처리
                            console.log('success')
                            // console.log(xhr.responseText)
                            // console.log(typeof xhr.responseText);
                            var obj = JSON.parse(xhr.responseText);
                            console.log(obj)
                            break;
                        case 500:
                            // 예외 처리
                            console.log('fail')
                            console.log(xhr.responseText)
                            alert('server error');
                    }
                });
            }
        }

        // 인포윈도우를 닫는 클로저를 만드는 함수입니다
        function makeOutListener(infowindow) {
            return function() {
                infowindow.close();
            };
        }
    }
</script>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
        integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
        integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
        crossorigin="anonymous"></script>

</body>
</html>
