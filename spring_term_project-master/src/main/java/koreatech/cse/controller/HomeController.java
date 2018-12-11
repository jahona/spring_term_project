package koreatech.cse.controller;

import koreatech.cse.domain.bithumb.BitHumb;
import koreatech.cse.domain.bithumb.Data;
import koreatech.cse.domain.bithumb.Datum;
import koreatech.cse.domain.bithumb.Ticker;
import koreatech.cse.domain.upbit.UpbitTicker;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class HomeController {
    @ModelAttribute("name")
    private String getName() {
        return "IamHomeControllerModelAttribute";
    }

    @RequestMapping
    public String home() {
        return "hello";
    }

    @RequestMapping("/jstlTest")
    public String emptyTest(Model model) {
        String a = null;
        String b = "";
        String c = "hello";
        List<String> d = new ArrayList<String>();
        List<String> e = new ArrayList<String>();
        e.add(a);
        e.add(b);

        model.addAttribute("a", a);
        model.addAttribute("b", b);
        model.addAttribute("c", c);
        model.addAttribute("d", d);
        model.addAttribute("e", e);

        List<String> stringArray = new ArrayList<String>();
        stringArray.add("one");
        stringArray.add("two");
        stringArray.add("three");
        model.addAttribute("stringArray", stringArray);

        Map<Integer, String> stringMap = new HashMap<Integer, String>();
        stringMap.put(1, "one");
        stringMap.put(2, "two");
        stringMap.put(3, "three");
        model.addAttribute("stringMap", stringMap);

        return "jstlTest";
    }

    @RequestMapping("/restTest")
    public String restTest(Model model) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<Ticker> bithumbResponseEntity = restTemplate.getForEntity("https://api.bithumb.com/public/ticker/BTC", Ticker.class);
            Ticker ticker = bithumbResponseEntity.getBody();

            model.addAttribute("bithumbs", ticker);

        } catch (HttpClientErrorException e) {
            System.out.println(e.getStatusCode() + ": " + e.getStatusText());
        }

        try {
            ResponseEntity<UpbitTicker[]> upbitResponseEntity = restTemplate.getForEntity("https://api.upbit.com/v1/ticker?markets=KRW-BTC", UpbitTicker[].class);
            UpbitTicker[] upbitTicker = upbitResponseEntity.getBody();

            model.addAttribute("upbits", upbitTicker[0]);

        } catch (HttpClientErrorException e) {
            System.out.println(e.getStatusCode() + ": " + e.getStatusText());
        }

        return "restTest";
    }
}
