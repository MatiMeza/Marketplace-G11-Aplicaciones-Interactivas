package com.uade.tpo.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uade.tpo.demo.entity.Joya;
import com.uade.tpo.demo.exceptions.JoyaDuplicateException;
import com.uade.tpo.demo.repository.JoyasRepository;


@Service
public class JoyasServiceImpl implements JoyasService {
    @Autowired
    private JoyasRepository joyasRepository;


    public List<Joya> getJoyas() {
        return joyasRepository.findAll();
    }

    public Optional<Joya> getJoyaById(Long id) {
        return joyasRepository.findById(id);
    }


     public Joya createJoya(String name, String description, double price) throws JoyaDuplicateException {
        List<Joya> Joyas = joyasRepository.findAll();
        if (Joyas.stream().anyMatch(
                joya -> joya.getName().equals(name)))
            throw new JoyaDuplicateException();
        return joyasRepository.save(new Joya(name, description, price));
    }

    
}
