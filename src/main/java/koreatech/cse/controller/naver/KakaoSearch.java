package koreatech.cse.controller.naver;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;

@RestController
@RequestMapping("/kakaos")
public class KakaoSearch {
    @Value("${env.kakaoServiceKey}")
    private String kakaoServiceKey;

    @Transactional
    @RequestMapping(value = "search", method = RequestMethod.GET,  produces = "application/json; charset=utf8")
    public @ResponseBody
    ResponseEntity<String> regionRegistry(@RequestParam(value = "query", required = true) String query) throws IOException {
//        String serviceKey_Decoder = URLDecoder.decode(kakaoServiceKey, "UTF-8");

        StringBuilder urlBuilder = new StringBuilder("https://dapi.kakao.com/v2/search/web"); /*URL*/

        try {
            urlBuilder.append("?" + URLEncoder.encode("query", "UTF-8") + "=" + URLEncoder.encode(query, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        URL url = new URL(urlBuilder.toString());

        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-type", "application/json");
        con.setRequestProperty("Content-type", "UTF-8");
        con.setRequestProperty("Authorization", "KakaoAK " + kakaoServiceKey);

//        System.out.println("Response code: " + conn.getResponseCode());

        int responseCode = con.getResponseCode();

        BufferedReader br;

        if (responseCode == 200) { 
            br = new BufferedReader(new InputStreamReader(con.getInputStream()));
        } else {  // 에러 발생
            br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
        }

        StringBuilder link = new StringBuilder();
        String line;
        while ((line =  br.readLine()) != null) {
            link.append(line);
        }

        br.close();
        con.disconnect();
        System.out.println(link.toString());

        return new ResponseEntity<String>(link.toString(), HttpStatus.OK);
    }

}
