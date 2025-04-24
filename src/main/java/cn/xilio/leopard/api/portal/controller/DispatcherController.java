package cn.xilio.leopard.api.portal.controller;

import cn.xilio.leopard.domain.dispatcher.service.DispatcherService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping(value = "/", name = "Transponder")
public class DispatcherController {
    @Autowired
    private DispatcherService dispatcherService;

    @GetMapping(value = "/{code:[a-zA-Z0-9]{6}}", name = "JUMP TO")
    public String trigger(@PathVariable String code) {

        return "redirect:" + "https://www.baidu.com";
    }
}
