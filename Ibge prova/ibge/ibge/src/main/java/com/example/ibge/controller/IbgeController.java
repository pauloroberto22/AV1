package com.example.ibge.controller;

import com.example.ibge.model.IbgeEntity;
import com.example.ibge.service.IbgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/noticias")
public class IbgeController {
    @Autowired
    private IbgeService service;

    @GetMapping
    public String consultarCompleto(@PathVariable int completo){
        return service.consultarCompleto(completo);
    }

    @GetMapping("/noticiaereleases")
    public String consultarModelos(@PathVariable int completo, int noticias) {
        return service.consultarNoticias(completo, noticias);
    }

    @GetMapping("/release/")
    public String consultarReleases(@PathVariable int completo, int releases) {
        return service.consultarReleases(completo, releases);
    }


    @PostMapping
    public IbgeEntity inserir(@RequestBody IbgeEntity user ){return service.inserir(user); }

}

