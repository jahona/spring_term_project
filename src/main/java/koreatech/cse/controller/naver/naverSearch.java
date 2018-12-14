package koreatech.cse.controller.naver;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

@RestController
@RequestMapping("/naver")
public class naverSearch {

    @Value("${env.naverClientId}")
    private String clientId; //애플리케이션 클라이언트 아이디값";

    @Value("${env.naverClientSecret}")
    private String clientSecret; //애플리케이션 클라이언트 시크릿값";

    @Transactional
    @RequestMapping(value = "/search", method = RequestMethod.GET, produces = "application/json")

    public @ResponseBody
    ResponseEntity<String> regionRegistry(@RequestParam(value = "query", required = true) String query) throws IOException {

        StringBuffer response = new StringBuffer();
        try {
            String text = URLEncoder.encode(query, "UTF-8");

            StringBuilder apiURL = new StringBuilder("https://openapi.naver.com/v1/search/news?query=" + text); // json 결과
            //String apiURL = "https://openapi.naver.com/v1/search/blog.xml?query="+ text; // xml 결과

            URL url = new URL(apiURL.toString());
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            con.setRequestMethod("GET");
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);

            int responseCode = con.getResponseCode();

            BufferedReader br;

            if (responseCode == 200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }

            String inputLine;

            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }

            br.close();

            System.out.println(response.toString());

        } catch (Exception e) {
            System.out.println(e);
        }

        return new ResponseEntity<String>(response.toString(), HttpStatus.OK);
    }
}
