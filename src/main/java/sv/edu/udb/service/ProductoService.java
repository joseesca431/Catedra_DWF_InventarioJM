package sv.edu.udb.service;

import sv.edu.udb.controller.request.ProductoRequest;
import sv.edu.udb.controller.response.ProductoResponse;
import java.util.List;

public interface ProductoService {
    List<ProductoResponse> findAll();
    ProductoResponse save(ProductoRequest productoRequest);
}
