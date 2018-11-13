<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>REST 테스트</title>
    <style>
        table {
            width: 100%;
            border: 1px solid #444444;
            table-layout: fixed;
        }
        th, td {
            border: 1px solid #444444;
        }
    </style>
</head>
<body>
<%--forEach (Array, List, Set..)--%>

    <div>Bithumb</div>
    <div>
        <table>
            <thead>
            <tr>
                <th>상태</th>
                <th>시작거래금액</th>
                <th>마지막거래금액</th>
                <th>최저가</th>
                <th>최고가</th>
                <th>평균가</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>${bithumbs.status}</td>
                <td>${bithumbs.data.openingPrice}</td>
                <td>${bithumbs.data.closingPrice}</td>
                <td>${bithumbs.data.minPrice}</td>
                <td>${bithumbs.data.maxPrice}</td>
                <td>${bithumbs.data.averagePrice}</td>
            </tr>
            </tbody>
        </table>
    </div>

    <div>Upbit</div>
    <div>
        <table>
            <thead>
            <tr>
                <th>상태</th>
                <th>시작거래금액</th>
                <th>마지막거래금액</th>
                <th>최저가</th>
                <th>최고가</th>
                <th>평균가</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>${upbits.change}</td>
                <td>${upbits.openingPrice}</td>
                <td>${upbits.tradePrice}</td>
                <td>${upbits.highPrice}</td>
                <td>${upbits.lowPrice}</td>
                <td>${(upbits.highPrice + upbits.lowPrice) / 2.0}</td>
            </tr>
            </tbody>
        </table>
    </div>


</body>
</html>
