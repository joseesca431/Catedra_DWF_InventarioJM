package sv.edu.udb.service;

import sv.edu.udb.controller.request.CategoriaRequest;
import sv.edu.udb.controller.response.CategoriaResponse;

import java.util.List;

public interface CategoriaService {
    List<CategoriaResponse> findAll();
    CategoriaResponse findById(Long id);
    CategoriaResponse findByNombre(String nombre);
    CategoriaResponse save(CategoriaRequest categoriaRequest);
    CategoriaResponse update(Long id, CategoriaRequest categoriaRequest);
    void delete(Long id);
}
