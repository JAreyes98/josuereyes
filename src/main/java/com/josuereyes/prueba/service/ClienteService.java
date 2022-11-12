package com.josuereyes.prueba.service;

import com.josuereyes.prueba.model.Cliente;
import com.josuereyes.prueba.model.mapper.ClienteMapper;
import com.josuereyes.prueba.repo.dao.ClienteDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteDAO clienteDAO;

    @Autowired
    public ClienteService(ClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }

    public List<Cliente> obtenerTodos() {
        return clienteDAO.obtenerTodos();
    }

    public Optional<Cliente> obtenerPorId(String id) {
        return clienteDAO.obtenerPorId(id);
    }

    public Cliente nuevo(Cliente cliente) {
        return clienteDAO.nuevo(cliente);
    }

    public void actualizar(Cliente cliente) {
        clienteDAO.actualizar(cliente);
    }

    public void eliminar(String id) {
        clienteDAO.eliminar(id);
    }
}
