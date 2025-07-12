package cn.xilio.joty.adapter.portal.controller;


import cn.xilio.joty.core.limit.RateLimit;
import cn.xilio.joty.service.DispatcherService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
    public String trigger(@PathVariable String code, HttpServletRequest request, HttpServletResponse response) {
        String longUrl = dispatcherService.getLongUrl(code,request,response);
        return "redirect:" + longUrl;
    }
}
