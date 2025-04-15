package com.example.pruebahack.functions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/funcion")
public class FuncionController {
    @Autowired
    FuncionRepository funcionRepository;

    @PostMapping
    ResponseEntity<Funcion> addFuncion(@RequestBody Funcion funcion) {
        return ResponseEntity.ok(funcionRepository.save(funcion));
    }

    @GetMapping("/{id}")
    ResponseEntity<Funcion> getFuncion(@PathVariable Long id) {
        return ResponseEntity.ok(funcionRepository.findById(id).get());
    }

    @PutMapping("/{id}")
    ResponseEntity<Funcion> updateFuncion(@PathVariable Long id, @RequestBody Funcion funcionActualizada) {
        Optional<Funcion> funcionExistente = funcionRepository.findById(id);
        if (funcionExistente.isPresent()) {
            Funcion funcion = funcionExistente.get();
            funcion.setNombre(funcionActualizada.getNombre());
            funcion.setDuracion(funcionActualizada.getDuracion());
            funcion.setPrecio(funcionActualizada.getPrecio());
            funcion.setStock(funcionActualizada.getStock());
            funcion.setFecha(funcionActualizada.getFecha());
            return ResponseEntity.ok(funcionRepository.save(funcion));
        }
        return ResponseEntity.notFound().build();
    }

}
