package com.kys.openapi.keyword.controller;

import com.kys.openapi.app.result.DataResponse;
import com.kys.openapi.app.result.PageResponse;
import com.kys.openapi.keyword.domain.KeyWordHistory;
import com.kys.openapi.keyword.dto.KeyWordCallInfo;
import com.kys.openapi.keyword.dto.KeyWordDTO;
import com.kys.openapi.keyword.service.KeyWordService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/keyWord")
public class KeyWordController {

    private KeyWordService keyWordService;

    @GetMapping(value = "/top10")
    public ResponseEntity<DataResponse<List<KeyWordCallInfo>>> getKeyWordByTop10() {
        return ResponseEntity.ok(keyWordService.getKeyWordByTop10());
    }

    @GetMapping(value = "/history")
    public ResponseEntity<PageResponse<KeyWordHistory>> getKeyWordHistory(@Valid KeyWordDTO dto, Principal principal) {
        return ResponseEntity.ok(keyWordService.getKeyWordHistory(dto, principal));
    }
}
