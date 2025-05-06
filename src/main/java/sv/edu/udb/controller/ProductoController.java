package sv.edu.udb.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sv.edu.udb.controller.request.ProductoRequest;
import sv.edu.udb.controller.response.ProductoResponse;
import sv.edu.udb.service.ProductoService;

import java.util.List;
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "producto")
public class ProductoController {
    private final ProductoService productoService;

    @GetMapping
    public List<ProductoResponse> getProductos() {
        return productoService.findAll();
    }

    @PostMapping
    public ProductoResponse addProducto(@RequestBody @Valid ProductoRequest productoRequest) {
        return productoService.save(productoRequest);
    }
}
