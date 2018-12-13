package koreatech.cse.controller;

import koreatech.cse.domain.bithumb.BitHumb;
import koreatech.cse.domain.bithumb.Data;
import koreatech.cse.domain.bithumb.Datum;
import koreatech.cse.domain.bithumb.Ticker;
import koreatech.cse.domain.upbit.UpbitTicker;
import org.springframework.beans.factory.annotation.Value;
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
    @Value("${env.kakaoServiceKey}")
    private String kakaoServiceKey;

    @RequestMapping
    public String home() {
        return "hello";
    }

    @RequestMapping("/map")
    public String map(Model model) {
        model.addAttribute("appKey", kakaoServiceKey);

        return "map";
    }
}
