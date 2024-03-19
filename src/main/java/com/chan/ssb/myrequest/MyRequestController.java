package com.chan.ssb.myrequest;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

@RestController
public class MyRequestController {

    @GetMapping("/hsr")
    public String hsr(HttpServletRequest request) {
        String result = "";
        result += "Request URI: " + request.getRequestURI() + "<br>";
        result += "Request URL: " + request.getRequestURL() + "<br>";
        result += "Request Method: " + request.getMethod() + "<br>";
        result += "Request Protocol: " + request.getProtocol() + "<br>";
        return result;
    }

    @GetMapping("/{id}")
    public String pathVariable(@PathVariable long id) {
        return id + " request received!";
    }
    @GetMapping("/{id}/path/{name}")
    public String pathVariables(@PathVariable long id, @PathVariable(value = "name") String str) {
        return id + " " + str + " request received!";
    }

    @GetMapping("/get-str")
    public String getString(@RequestParam String str) {
        return str;
    }

    @PostMapping("/post-strs")
    public String postStrings(@RequestParam String[] strs) {
        String result = "";
        for(String str : strs) {
            result += str + " ";
        }
        return result;
    }

    @GetMapping("/get-obj")
    public MyRequest getObj(@ModelAttribute MyRequest myRequest) {
        return myRequest;
    }

    @PostMapping("/post-objs")
    public MyRequest[] getObjsByRb(@RequestBody MyRequest[] myRequests) {
        return myRequests;
    }

}

