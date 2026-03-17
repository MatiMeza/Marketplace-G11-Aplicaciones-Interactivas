package com.uade.tpo.demo.service;

import java.util.ArrayList;
import java.util.Optional;

import com.uade.tpo.demo.entity.Joya;
import com.uade.tpo.demo.exceptions.JoyaDuplicateException;

public interface JoyasService {
    
    public ArrayList<Joya> getJoyas();

    public Joya createJoya(int newJoyaId, String name, String description, double price) throws JoyaDuplicateException;

    public Optional<Joya> getJoyaById(int id);
}
