package com.uade.tpo.demo.service;

import java.util.List;
import java.util.Optional;

import com.uade.tpo.demo.entity.Joya;
import com.uade.tpo.demo.exceptions.JoyaDuplicateException;

public interface JoyasService {
    
    public List<Joya> getJoyas();

    public Optional<Joya> getJoyaById(Long id);

    public Joya createJoya(String name, String description, double price) throws JoyaDuplicateException;

    
}
