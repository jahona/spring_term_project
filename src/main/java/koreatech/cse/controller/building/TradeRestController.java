package koreatech.cse.controller.building;

import koreatech.cse.domain.building.trade.TradeItem;
import koreatech.cse.repository.TradeMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.inject.Inject;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/building")
public class TradeRestController {
    @Value("${env.serviceKey}")
    private String serviceKey;

    @Inject
    private TradeMapper tradeMapper;

    @RequestMapping("/trade")
    public @ResponseBody
    ResponseEntity<List<TradeItem>> restTest() throws IOException, ParserConfigurationException, XPathExpressionException, SAXException {
        RestTemplate restTemplate = new RestTemplate();

        String serviceKey_Decoder = URLDecoder.decode(serviceKey, "UTF-8");

        StringBuilder urlBuilder = new StringBuilder("http://openapi.molit.go.kr/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcNrgTrade"); /*URL*/

        try {
            urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=" + serviceKey_Decoder); /*Service Key*/
            urlBuilder.append("&" + URLEncoder.encode("LAWD_CD","UTF-8") + "=" + URLEncoder.encode("11110", "UTF-8")); /*각 지역별 행정표준코드관리시스템(www.code.go.kr)의 법정동코드 10자리 중 앞 5자리*/
            urlBuilder.append("&" + URLEncoder.encode("DEAL_YMD","UTF-8") + "=" + URLEncoder.encode("201512", "UTF-8")); /*실거래 자료의 계약년월(6자리)*/
            urlBuilder.append("&" + URLEncoder.encode("serviceKey","UTF-8") + "=" + URLEncoder.encode(serviceKey_Decoder, "UTF-8")); /*공공데이터포털에서 발급받은 인증키*/
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        URL url = new URL(urlBuilder.toString());

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());

        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }

        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }

        rd.close();
        conn.disconnect();

        System.out.println(sb.toString());

        InputSource is = new InputSource(new StringReader(sb.toString()));
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        DocumentBuilder builder;
        Document doc = null;

        builder = factory.newDocumentBuilder();
        doc = builder.parse(is);
        XPathFactory xPathFactory = XPathFactory.newInstance();
        XPath xpath = xPathFactory.newXPath();

        XPathExpression expr = xpath.compile("//items/item");
        NodeList nodeList = (NodeList)expr.evaluate(doc, XPathConstants.NODESET);

        List<TradeItem> list = new ArrayList<TradeItem>();

        for(int i=0 ; i<nodeList.getLength() ; i++) {
            NodeList child = nodeList.item(i).getChildNodes();
            TradeItem item = new TradeItem();

            for(int j=0 ; j<child.getLength(); j++) {
                Node node = child.item(j);
                String name = node.getNodeName();
//                System.out.println("현재 노드 이름 : " + node.getNodeName());

                if(name.equals("거래금액")) {
                    item.setDealAmount(node.getTextContent());
                } else if(name.equals("건물면적")) {
                    item.setBuildingArea(node.getTextContent());
                } else if(name.equals("건물주용도")) {
                    item.setBuildingUse(node.getTextContent());
                } else if(name.equals("건축년도")) {
                    item.setBuildYear(node.getTextContent());
                } else if(name.equals("구분")) {
                    item.setClassification(node.getTextContent());
                } else if(name.equals("년")) {
                    item.setDealYear(node.getTextContent());
                } else if(name.equals("대지면적")) {
                    item.setPlottage(node.getTextContent());
                } else if(name.equals("법정동")) {
                    item.setDong(node.getTextContent());
                } else if(name.equals("시군구")) {
                    item.setSigungu(node.getTextContent());
                } else if(name.equals("용도지역")) {
                    item.setLandUse(node.getTextContent());
                } else if(name.equals("월")) {
                    item.setDealMonth(node.getTextContent());
                } else if(name.equals("유형")) {
                    item.setBuildingType(node.getTextContent());
                } else if(name.equals("일")) {
                    item.setDealDay(node.getTextContent());
                } else if(name.equals("지역코드")) {
                    item.setRegionalCode(node.getTextContent());
                } else if(name.equals("층")) {
                    item.setFloor(node.getTextContent());
                }
            }

            System.out.println(item);
            list.add(item);
        }

        HttpHeaders headers = new HttpHeaders();

        return new ResponseEntity<List<TradeItem>>(list, headers, HttpStatus.CREATED);  // 201
    }
}
