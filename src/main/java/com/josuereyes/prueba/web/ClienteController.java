package com.josuereyes.prueba.web;

import com.josuereyes.prueba.model.Cliente;
import com.josuereyes.prueba.service.ClienteService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customer")
public class ClienteController {

    private final ClienteService service;

    @Autowired
    public ClienteController(ClienteService service) {
        this.service = service;
    }

    @ApiOperation("Retorna todos los registros de clientes")
    @GetMapping("/")
    public ResponseEntity<List<Cliente>> all() {
        return ResponseEntity.ok(service.obtenerTodos());
    }

    @ApiOperation("Retorna el cliente con id")
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> byId(@ApiParam("Identificador del cliente, Tipo <b>Integer<b/>") @PathVariable String id) {
        return service.obtenerPorId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }


    @ApiOperation("Registra un nuevo cliente que posee el formato: ")
    @PostMapping("/new")
    public ResponseEntity<Cliente> nuevo(@ApiParam("Cliente a ser guardado") @RequestBody Cliente cliente) {
        service.nuevo(cliente);
        return ResponseEntity.ok(cliente);
    }

    @ApiOperation("Actualiza un cliente que posee el formato: ")
    @PutMapping("/update")
    public ResponseEntity<Cliente> update(@ApiParam("Cliente a ser actualizado") @RequestBody Cliente cliente) {
        service.actualizar(cliente);
        return ResponseEntity.ok(cliente);
    }


    @ApiOperation("Elimina un cliente con id")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> update(@ApiParam("Id de Cliente a ser eliminado") @PathVariable String id) {
        service.eliminar(id);
        Optional<Cliente> cl = service.obtenerPorId(id);
        return cl.map(f -> ResponseEntity.ok("ELIMINADO")).orElse(ResponseEntity.badRequest().body("NO SE ELIMINO"));
    }
}
