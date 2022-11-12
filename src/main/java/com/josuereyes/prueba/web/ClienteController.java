package com.josuereyes.prueba.web;

import com.josuereyes.prueba.model.Cliente;
import com.josuereyes.prueba.service.ClienteService;
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

    @GetMapping("/")
    public ResponseEntity<List<Cliente>> all() {
        return ResponseEntity.ok(service.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> byId(@PathVariable String id) {
        return service.obtenerPorId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/new")
    public ResponseEntity<Cliente> nuevo(@RequestBody Cliente cliente) {
        service.nuevo(cliente);
        return ResponseEntity.ok(cliente);
    }

    @PutMapping("/update")
    public ResponseEntity<Cliente> update(@RequestBody Cliente cliente) {
        service.actualizar(cliente);
        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> update(@PathVariable String id) {
        service.eliminar(id);
        Optional<Cliente> cl = service.obtenerPorId(id);
        return cl.map(f -> ResponseEntity.ok("ELIMINADO")).orElse(ResponseEntity.badRequest().body("NO SE ELIMINO"));
    }
}
