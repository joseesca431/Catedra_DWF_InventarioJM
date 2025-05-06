// MarcaService.java
package sv.edu.udb.service;

import sv.edu.udb.controller.request.MarcaRequest;
import sv.edu.udb.controller.response.MarcaResponse;

import java.util.List;

public interface MarcaService {
    List<MarcaResponse> findAll();
    MarcaResponse findById(Long id);
    MarcaResponse findByNombre(String nombre);
    MarcaResponse save(MarcaRequest marcaRequest);
    MarcaResponse update(Long id, MarcaRequest marcaRequest);
    void delete(Long id);
}
