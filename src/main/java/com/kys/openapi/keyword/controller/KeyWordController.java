package com.kys.openapi.keyword.controller;

import com.kys.openapi.app.result.Response;
import com.kys.openapi.keyword.dto.KeyWordDTO;
import com.kys.openapi.keyword.service.KeyWordService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/keyWord")
public class KeyWordController {

    private KeyWordService keyWordService;

    @GetMapping(value = "/top10")
    public ResponseEntity<Response> getKeyWordByTop10() {
        return ResponseEntity.ok(keyWordService.getKeyWordByTop10());
    }

    @GetMapping(value = "/history")
    public ResponseEntity<Response> getKeyWordHistory(@Valid KeyWordDTO dto, Principal principal) {
        return ResponseEntity.ok(keyWordService.getKeyWordHistory(dto, principal));
    }
}
