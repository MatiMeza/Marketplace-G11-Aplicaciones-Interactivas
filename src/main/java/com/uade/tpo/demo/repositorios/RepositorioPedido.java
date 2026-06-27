package com.uade.tpo.demo.repositorios;

import com.uade.tpo.demo.entidades.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RepositorioPedido extends JpaRepository<Pedido, Long> {
    List<Pedido> findByEmailUsuarioOrderByFechaPedidoDesc(String email);
    List<Pedido> findAllByOrderByFechaPedidoDesc();
}