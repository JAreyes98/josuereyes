package com.josuereyes.prueba.repo.dao;

import com.josuereyes.prueba.model.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteDAO {
    List<Cliente> obtenerTodos();

    Optional<Cliente> obtenerPorId(String id);

    Cliente nuevo(Cliente cliente);

    void actualizar(Cliente cliente);

    void eliminar(String id);
}
