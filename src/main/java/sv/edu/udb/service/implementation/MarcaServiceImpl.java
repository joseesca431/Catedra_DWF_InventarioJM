package sv.edu.udb.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sv.edu.udb.controller.request.MarcaRequest;
import sv.edu.udb.controller.response.MarcaResponse;
import sv.edu.udb.model.Marca;
import sv.edu.udb.repository.MarcaRepository;
import sv.edu.udb.service.MarcaService;
import sv.edu.udb.service.mapper.MarcaMapper;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class MarcaServiceImpl implements MarcaService {
    private final MarcaRepository marcaRepository;
    private final MarcaMapper marcaMapper;

    @Override
    public List<MarcaResponse> findAll() {
        final List<Marca> marcas = marcaRepository.findAll();
        return marcaMapper.toMarcaResponseList(marcas);
    }

    @Override
    public MarcaResponse findById(Long id) {
        final Marca marca = marcaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Marca no encontrada con id: " + id));
        return marcaMapper.toMarcaResponse(marca);
    }

    @Override
    public MarcaResponse findByNombre(String nombre) {
        final Marca marca = marcaRepository.findByNombre(nombre)
                .orElseThrow(() -> new NoSuchElementException("Marca no encontrada con nombre: " + nombre));
        return marcaMapper.toMarcaResponse(marca);
    }

    @Override
    public MarcaResponse save(MarcaRequest marcaRequest) {
        if (marcaRepository.existsByNombre(marcaRequest.getNombre())) {
            throw new IllegalArgumentException("Ya existe una marca con ese nombre");
        }
        final Marca marca = marcaMapper.toMarca(marcaRequest);
        return marcaMapper.toMarcaResponse(marcaRepository.save(marca));
    }

    @Override
    public MarcaResponse update(Long id, MarcaRequest marcaRequest) {
        final Marca marca = marcaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Marca no encontrada con id: " + id));

        // Verificar que no exista otra marca con el mismo nombre
        marcaRepository.findByNombre(marcaRequest.getNombre())
                .ifPresent(existingMarca -> {
                    if (!existingMarca.getId().equals(id)) {
                        throw new IllegalArgumentException("Ya existe otra marca con ese nombre");
                    }
                });

        marca.setNombre(marcaRequest.getNombre());
        return marcaMapper.toMarcaResponse(marcaRepository.save(marca));
    }

    @Override
    public void delete(Long id) {
        final Marca marca = marcaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Marca no encontrada con id: " + id));

        if (marca.getProductos() != null && !marca.getProductos().isEmpty()) {
            throw new IllegalStateException("No se puede eliminar la marca porque tiene productos asociados");
        }

        marcaRepository.delete(marca);
    }
}
