package com.uade.tpo.demo.repository;

import java.util.ArrayList;
import java.util.Optional;

import com.uade.tpo.demo.entity.Joya;

public class JoyasRepository {
    private ArrayList<Joya> joyas;

    public JoyasRepository() {
        joyas = new ArrayList<Joya>();
        joyas.add(Joya.builder().id(1).name("Anillo de oro").description("Anillo de oro macizo con diamantes").price(500.0).build());
        joyas.add(Joya.builder().id(2).name("Collar de plata").description("Collar de plata con un colgante de zafiro").price(300.0).build());
        joyas.add(Joya.builder().id(3).name("Pulsera de diamantes").description("Pulsera de diamantes").price(2000.0).build());
    }

    public ArrayList<Joya> getJoyas() {
        return this.joyas;
    }

    public Joya createJoya(int newJoyaId, String name, String description, double price) {
        Joya newJoya = Joya.builder()
                .id(newJoyaId)
                .name(name)
                .description(description)
                .price(price).build();
        this.joyas.add(newJoya);
        return newJoya;
    }

    public Optional<Joya> getJoyaById(int id) {
        return this.joyas.stream().filter(j -> j.getId() == id).findFirst();
    }
    
}
