package koreatech.cse.controller.kakao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.*;

@RestController
@RequestMapping("/kakao")
public class Kakao {
    @Value("${env.kakaoServiceKey}")
    private String kakaoServiceKey;

    @Transactional
    @RequestMapping(value = "/address/search", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    ResponseEntity<String> regionRegistry(@RequestParam(value = "location", required = true) String query, @RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "size", defaultValue = "10") Integer size) throws IOException {
//        String serviceKey_Decoder = URLDecoder.decode(kakaoServiceKey, "UTF-8");

        StringBuilder urlBuilder = new StringBuilder("https://dapi.kakao.com/v2/local/search/address.json"); /*URL*/

        try {
            urlBuilder.append("?" + URLEncoder.encode("query", "UTF-8") + "=" + URLEncoder.encode(query, "UTF-8"));
            urlBuilder.append("&" + URLEncoder.encode("page", "UTF-8") + "=" + page);
            urlBuilder.append("&" + URLEncoder.encode("size", "UTF-8") + "=" + size);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        URL url = new URL(urlBuilder.toString());

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        conn.setRequestProperty("Authorization", "KakaoAK " + kakaoServiceKey);

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

        return new ResponseEntity<String>(sb.toString(), HttpStatus.OK);
    }
}
