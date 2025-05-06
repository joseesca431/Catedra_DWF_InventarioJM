package sv.edu.udb.service;

import sv.edu.udb.controller.request.ProductoRequest;
import sv.edu.udb.controller.response.ProductoResponse;
import java.util.List;

public interface ProductoService {
    List<ProductoResponse> findAll();
    ProductoResponse findById(Long id);
    List<ProductoResponse> findByCategoria(String categoria);
    List<ProductoResponse> findByMarca(String marca);
    ProductoResponse save(ProductoRequest productoRequest);
    ProductoResponse update(Long id, ProductoRequest productoRequest);
    void delete(Long id);
}
