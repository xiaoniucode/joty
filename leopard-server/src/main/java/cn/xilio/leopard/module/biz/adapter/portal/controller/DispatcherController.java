package cn.xilio.leopard.module.biz.adapter.portal.controller;


import cn.xilio.leopard.module.biz.service.DispatcherService;
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

    @GetMapping(value = "/{code:[a-zA-Z0-9]{6}}", name = "JUMP TO")
    public String trigger(@PathVariable String code) {
        String longUrl = dispatcherService.getLongUrl(code);
        return "redirect:" + longUrl;
    }
}
