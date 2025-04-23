package cn.xilio.leopard.api.portal.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/", name = "转发器")
public class DispatcherController {
    @GetMapping(value = "/{code}", name = "跳转")
    public String trigger(@PathVariable String code) {

        return "redirect:" + "originalUrl";
    }
}
