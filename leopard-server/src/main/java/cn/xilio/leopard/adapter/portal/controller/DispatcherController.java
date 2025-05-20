package cn.xilio.leopard.adapter.portal.controller;


import cn.xilio.leopard.core.limit.RateLimit;
import cn.xilio.leopard.service.DispatcherService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/", name = "Transponder")
public class DispatcherController {
    @Autowired
    private DispatcherService dispatcherService;
    @RateLimit(rate = 1000, messageKey = "1100")
    @GetMapping(value = "/{code:[a-zA-Z0-9]{6}}", name = "JUMP TO")
    public String trigger(@PathVariable String code, HttpServletRequest request) {
        String longUrl = dispatcherService.getLongUrl(code,request);
        return "redirect:" + longUrl;
    }
}
