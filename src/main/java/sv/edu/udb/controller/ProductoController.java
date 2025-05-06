package sv.edu.udb.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sv.edu.udb.controller.request.ProductoRequest;
import sv.edu.udb.controller.response.ProductoResponse;
import sv.edu.udb.service.ProductoService;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "producto")
public class ProductoController {
    private final ProductoService productoService;

    @GetMapping
    public List<ProductoResponse> getProductos() {
        return productoService.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProductoResponse> getProductoById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(productoService.findById(id));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<ProductoResponse>> getProductosByCategoria(@PathVariable String categoria) {
        try {
            return ResponseEntity.ok(productoService.findByCategoria(categoria));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/marca/{marca}")
    public ResponseEntity<List<ProductoResponse>> getProductosByMarca(@PathVariable String marca) {
        try {
            return ResponseEntity.ok(productoService.findByMarca(marca));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping
    public ResponseEntity<?> addProducto(@RequestBody @Valid ProductoRequest productoRequest) {
        try {
            return new ResponseEntity<>(productoService.save(productoRequest), HttpStatus.CREATED);
        } catch (NoSuchElementException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
