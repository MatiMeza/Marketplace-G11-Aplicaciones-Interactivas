package com.uade.tpo.demo.service;


import java.util.ArrayList;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.uade.tpo.demo.entity.Joya;
import com.uade.tpo.demo.exceptions.JoyaDuplicateException;
import com.uade.tpo.demo.repository.JoyasRepository;


@Service
public class JoyasServiceImpl implements JoyasService {
    private JoyasRepository joyasRepository;

    public JoyasServiceImpl(){
        joyasRepository = new JoyasRepository();
    }

    public ArrayList<Joya> getJoyas() {
        return joyasRepository.getJoyas();
    }


     public Joya createJoya(int newJoyaId, String name, String description, double price) throws JoyaDuplicateException {
        ArrayList<Joya> Joyas = joyasRepository.getJoyas();
        if (Joyas.stream().anyMatch(
                joya -> joya.getId() == newJoyaId && joya.getDescription().equals(description)))
            throw new JoyaDuplicateException();
        return joyasRepository.createJoya(newJoyaId, name, description, price);
    }

    public Optional<Joya> getJoyaById(int id) {
        return joyasRepository.getJoyaById(id);
    }
}
