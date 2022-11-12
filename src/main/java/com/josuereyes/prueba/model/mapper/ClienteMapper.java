package com.josuereyes.prueba.model.mapper;

import com.josuereyes.prueba.model.Cliente;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClienteMapper implements RowMapper<Cliente> {
    @Override
    public Cliente mapRow(ResultSet rs, int rowNum) throws SQLException {

        Cliente cliente = new Cliente();
        cliente.setId(rs.getString("CustomerID"));
        cliente.setNombreCompanhia(rs.getString("CompanyName"));
        cliente.setNombreContacto(rs.getString("ContactName"));
        cliente.setTituloContacto(rs.getString("ContactTitle"));
        cliente.setDireccion(rs.getString("Address"));
        cliente.setCiudad(rs.getString("City"));
        return cliente;
    }
}
