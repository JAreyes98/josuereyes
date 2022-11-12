package com.josuereyes.prueba.repo.daoImpl;

import com.josuereyes.prueba.model.Cliente;
import com.josuereyes.prueba.model.mapper.ClienteMapper;
import com.josuereyes.prueba.repo.dao.ClienteDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class ClienteDAOImpl implements ClienteDAO {

    private final JdbcTemplate template;

    @Autowired
    public ClienteDAOImpl(JdbcTemplate template, DataSource dataSource) {
        this.template = template;
        template.setDataSource(dataSource);
    }

    @Override
    public List<Cliente> obtenerTodos() {
        return template.query("select * from Customers", new ClienteMapper());
    }

    @Override
    public Optional<Cliente> obtenerPorId(String id) {
        return template.query("select * from Customers where CustomerID=?"
                        , new ClienteMapper(), id)
                .stream()
                .findFirst();
    }

    @Override
    public Cliente nuevo(Cliente cliente) {
        SimpleJdbcInsert insert = new SimpleJdbcInsert(template).withTableName("Customers");
        Map<String, Object> map = new HashMap<>();
        map.put("CustomerID", cliente.getId());
        map.put("CompanyName", cliente.getNombreCompanhia());
        map.put("ContactName", cliente.getNombreContacto());
        map.put("ContactTitle", cliente.getTituloContacto());
        map.put("Address", cliente.getDireccion());
        map.put("City", cliente.getCiudad());
        insert.execute(map);
        return cliente;
    }

    @Override
    public void actualizar(Cliente cliente) {
        template.update("update Customers set " +
                "CompanyName=? " +
                ",ContactName=? " +
                ",ContactTitle=? " +
                ",Address=? " +
                ",City=? " +
                " WHERE CustomerID=?" , cliente.getNombreCompanhia()
                , cliente.getNombreContacto()
                , cliente.getTituloContacto()
                , cliente.getDireccion()
                , cliente.getCiudad()
                , cliente.getId()
        );
    }

    @Override
    public void eliminar(String id) {
        template.update("DELETE FROM Customers WHERE CustomerID=?", id);
    }
}
