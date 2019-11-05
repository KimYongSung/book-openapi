package com.kys.openapi.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * React index.html 호출 컨트롤러
 */
@Controller
public class OpenApiWebController {

    /**
     * SPA 시작 페이지
     * @param model
     * @return
     */
    @GetMapping("/")
    public String index(Model model){
        return "index";
    }
}
