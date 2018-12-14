package koreatech.cse.controller.building;

import koreatech.cse.domain.building.trade.TradeItem;
import koreatech.cse.domain.building.trade.TradeYMRecord;
import koreatech.cse.repository.TradeItemMapper;
import koreatech.cse.repository.TradeYMRecordMapper;
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
import java.util.*;

@RestController
@RequestMapping("/building")
public class TradeRestController {
    @Value("${env.serviceKey}")
    private String serviceKey;

    @Inject
    private TradeItemMapper tradeItemMapper;

    @Inject
    private TradeYMRecordMapper tradeYMRecordMapper;

    @RequestMapping(value = "/trade/ym_record", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    ResponseEntity<Map<String, Object>> getMaxOrMinTradeRecord(@RequestParam(value = "LAWD_CD", required = true) String lawdCD, @RequestParam(value = "DEAL_YMD", required = true) String dealYMD) throws IOException, ParserConfigurationException, XPathExpressionException, SAXException {
        // id와 regional_code를 가진 데이터가 디비 있는지 검사
        String year = dealYMD.substring(0, 4);
        String month = dealYMD.substring(4, 6);

        int iyear = Integer.parseInt(year);
        int imonth = Integer.parseInt(month);

        List<TradeYMRecord> tradeYMRecords = new ArrayList<>();

        // 해당 region에 대해 1년치 데이터가 적재되어있는지 db에서 비교 후 없으면 적재한다.
        imonth++;
        for(int k=0 ; k<=12 ; k++) {
            imonth--;

            if(imonth == 0) {
                imonth = 12;
                iyear--;
            }

            String newDealYMD = Integer.toString(iyear);

            if(Integer.toString(imonth).length() == 1) {
                newDealYMD += "0";
            }

            newDealYMD += Integer.toString(imonth);
            System.out.println(newDealYMD);

            TradeYMRecord tradeYMRecord = tradeYMRecordMapper.findOne(newDealYMD, lawdCD);

            // 디비 적재한다.
            if(tradeYMRecord == null) {
                List<TradeItem> items = new ArrayList<TradeItem>();

                String serviceKey_Decoder = URLDecoder.decode(serviceKey, "UTF-8");

                StringBuilder urlBuilder = new StringBuilder("http://openapi.molit.go.kr/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcNrgTrade"); /*URL*/

                try {
                    urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=" + serviceKey_Decoder); /*Service Key*/
                    urlBuilder.append("&" + URLEncoder.encode("LAWD_CD", "UTF-8") + "=" + URLEncoder.encode(lawdCD, "UTF-8")); /*각 지역별 행정표준코드관리시스템(www.code.go.kr)의 법정동코드 10자리 중 앞 5자리*/
                    urlBuilder.append("&" + URLEncoder.encode("DEAL_YMD", "UTF-8") + "=" + URLEncoder.encode(newDealYMD, "UTF-8")); /*실거래 자료의 계약년월(6자리)*/
                    urlBuilder.append("&" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + URLEncoder.encode(serviceKey_Decoder, "UTF-8")); /*공공데이터포털에서 발급받은 인증키*/
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                URL url = new URL(urlBuilder.toString());

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Content-type", "application/json");
//        System.out.println("Response code: " + conn.getResponseCode());

                BufferedReader rd;
                if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
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

//        System.out.println(sb.toString());

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
                NodeList nodeList = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);

                for (int i = 0; i < nodeList.getLength(); i++) {
                    NodeList child = nodeList.item(i).getChildNodes();
                    TradeItem item = new TradeItem();

                    for (int j = 0; j < child.getLength(); j++) {
                        Node node = child.item(j);
                        String name = node.getNodeName();
//                System.out.println("현재 노드 이름 : " + node.getNodeName());

                        if (name.equals("거래금액")) {
                            String dealAmount = node.getTextContent();
                            dealAmount = dealAmount.replace(",", "");
                            int amount = Integer.parseInt(dealAmount);
                            item.setDealAmount(amount);
                        } else if (name.equals("건물면적")) {
                            item.setBuildingArea(node.getTextContent());
                        } else if (name.equals("건물주용도")) {
                            item.setBuildingUse(node.getTextContent());
                        } else if (name.equals("건축년도")) {
                            item.setBuildYear(node.getTextContent());
                        } else if (name.equals("구분")) {
                            item.setClassification(node.getTextContent());
                        } else if (name.equals("년")) {
                            item.setDealYear(node.getTextContent());
                        } else if (name.equals("대지면적")) {
                            item.setPlottage(node.getTextContent());
                        } else if (name.equals("법정동")) {
                            item.setDong(node.getTextContent());
                        } else if (name.equals("시군구")) {
                            item.setSigungu(node.getTextContent());
                        } else if (name.equals("용도지역")) {
                            item.setLandUse(node.getTextContent());
                        } else if (name.equals("월")) {
                            item.setDealMonth(node.getTextContent());
                        } else if (name.equals("유형")) {
                            item.setBuildingType(node.getTextContent());
                        } else if (name.equals("일")) {
                            item.setDealDay(node.getTextContent());
                        } else if (name.equals("지역코드")) {
                            item.setRegionalCode(node.getTextContent());
                        } else if (name.equals("층")) {
                            item.setFloor(node.getTextContent());
                        }
                    }

                    items.add(item);
                    tradeItemMapper.insert(item);
                }

                // TradeYMRecord 생성
                tradeYMRecord = new TradeYMRecord();

                TradeItem minTrade = items
                        .stream()
                        .min(Comparator.comparing(TradeItem::getDealAmount))
                        .orElseThrow(NoSuchElementException::new);

                TradeItem maxTrade = items
                        .stream()
                        .max(Comparator.comparing(TradeItem::getDealAmount))
                        .orElseThrow(NoSuchElementException::new);

                int sum = items
                        .stream()
                        .mapToInt(TradeItem::getDealAmount).sum();

                int tradeCount = items.size();

                tradeYMRecord.setMinimumDeal(minTrade.getDealAmount());
                tradeYMRecord.setMinimumTradeId(minTrade.getId());
                tradeYMRecord.setMaximumDeal(maxTrade.getDealAmount());
                tradeYMRecord.setMaximumTradeId(maxTrade.getId());
                tradeYMRecord.setTradeCount(tradeCount);
                tradeYMRecord.setAverageDeal(sum / tradeCount);
                tradeYMRecord.setRegionalCode(lawdCD);
                tradeYMRecord.setDealYM(newDealYMD);

                tradeYMRecordMapper.insert(tradeYMRecord);
                tradeYMRecords.add(tradeYMRecord);
            } else {
                tradeYMRecords = tradeYMRecordMapper.findListByTerm(dealYMD, lawdCD, 12);
            }
        }

        HttpHeaders headers = new HttpHeaders();

        Map<String, Object> map = new HashMap<String, Object>();

        map.put("year_ym", dealYMD);
        map.put("regional_code", lawdCD);
        map.put("trade_record", tradeYMRecords);
//        map.put("max_trade", maxTrade);
//        map.put("min_trade", minTrade);

        return new ResponseEntity<Map<String, Object>>(map, headers, HttpStatus.CREATED);  // 201
    }
}
