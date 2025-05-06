package sv.edu.udb.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sv.edu.udb.controller.request.MarcaRequest;
import sv.edu.udb.controller.response.MarcaResponse;
import sv.edu.udb.service.MarcaService;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "marca")
public class MarcaController {
    private final MarcaService marcaService;

    @GetMapping
    public List<MarcaResponse> getMarcas() {
        return marcaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MarcaResponse> getMarcaById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(marcaService.findById(id));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<MarcaResponse> getMarcaByNombre(@PathVariable String nombre) {
        try {
            return ResponseEntity.ok(marcaService.findByNombre(nombre));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> addMarca(@RequestBody @Valid MarcaRequest marcaRequest) {
        try {
            return new ResponseEntity<>(marcaService.save(marcaRequest), HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateMarca(@PathVariable Long id, @RequestBody @Valid MarcaRequest marcaRequest) {
        try {
            return ResponseEntity.ok(marcaService.update(id, marcaRequest));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMarca(@PathVariable Long id) {
        try {
            marcaService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
