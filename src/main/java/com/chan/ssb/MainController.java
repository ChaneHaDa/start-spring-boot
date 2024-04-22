package com.chan.ssb;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

    @GetMapping("/")
    @ResponseBody
    public String main(OAuth2AuthenticationToken token) {
        System.out.println(token.getPrincipal().getAttributes());
        return "Hello!";
    }

}
