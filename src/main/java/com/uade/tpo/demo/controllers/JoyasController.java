package com.uade.tpo.demo.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uade.tpo.demo.entity.Joya;
import com.uade.tpo.demo.entity.dto.CategoryRequest;
import com.uade.tpo.demo.entity.dto.JoyaRequest;
import com.uade.tpo.demo.exceptions.JoyaDuplicateException;
import com.uade.tpo.demo.service.JoyasService;

import java.net.URI;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;





@RestController
@RequestMapping("joyas")
public class JoyasController {
    private JoyasService joyasService;

    public JoyasController() {
        joyasService = new JoyasService();
    }

    @GetMapping
    public ResponseEntity<ArrayList<Joya>> getMethodName() {
        return ResponseEntity.ok(joyasService.getJoyas());
    }

    @PostMapping
    public ResponseEntity<Object> createJoya(@RequestBody JoyaRequest joyaRequest) throws JoyaDuplicateException {
        Joya result = joyasService.createJoya(joyaRequest.getId(), joyaRequest.getName(), joyaRequest.getDescription(),
                joyaRequest.getPrice());
        return ResponseEntity.created(URI.create("/joyas/" + result.getId())).body(result);
    }
    
    @GetMapping("/{joyaId}")
    public ResponseEntity<Object> getJoyaById(@PathVariable int joyaId) {
        Optional<Joya> result = joyasService.getJoyaById(joyaId);
        if (result.isPresent())
            return ResponseEntity.ok(result.get());

        return ResponseEntity.noContent().build();
    }
    
    

}
