package cn.xilio.joty.adapter.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error")
public class ErrorController {

    @RequestMapping("/107")
    public String error404() {
        return "error/1007";
    }
}
