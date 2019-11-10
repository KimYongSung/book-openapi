package com.kys.openapi.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * web 호출 컨트롤러
 */
@Controller
public class OpenApiWebController {

    /**
     * @param model
     * @return
     */
    @GetMapping(value="/")
    public String login(Model model) {
        return "login";
    }

    @GetMapping(value = "/web/join")
    public String join(Model model) {
        return "join";
    }

    @GetMapping(value = "/web/index")
    public String index(Model model){
        return "index";
    }

    @GetMapping(value = "/web/logout")
    public String logout(Model model) {

        return "login";
    }
}
