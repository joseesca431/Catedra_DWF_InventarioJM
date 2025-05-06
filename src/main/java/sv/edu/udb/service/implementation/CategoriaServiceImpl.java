package sv.edu.udb.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sv.edu.udb.controller.request.CategoriaRequest;
import sv.edu.udb.controller.response.CategoriaResponse;
import sv.edu.udb.model.Categoria;
import sv.edu.udb.repository.CategoriaRepository;
import sv.edu.udb.service.CategoriaService;
import sv.edu.udb.service.mapper.CategoriaMapper;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CategoriaServiceImpl implements CategoriaService {
    private final CategoriaRepository categoriaRepository;
    private final CategoriaMapper categoriaMapper;

    @Override
    public List<CategoriaResponse> findAll() {
        final List<Categoria> categorias = categoriaRepository.findAll();
        return categoriaMapper.toCategoriaResponseList(categorias);
    }

    @Override
    public CategoriaResponse findById(Long id) {
        final Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Categoría no encontrada con id: " + id));
        return categoriaMapper.toCategoriaResponse(categoria);
    }

    @Override
    public CategoriaResponse findByNombre(String nombre) {
        final Categoria categoria = categoriaRepository.findByNombre(nombre)
                .orElseThrow(() -> new NoSuchElementException("Categoría no encontrada con nombre: " + nombre));
        return categoriaMapper.toCategoriaResponse(categoria);
    }

    @Override
    public CategoriaResponse save(CategoriaRequest categoriaRequest) {
        if (categoriaRepository.existsByNombre(categoriaRequest.getNombre())) {
            throw new IllegalArgumentException("Ya existe una categoría con ese nombre");
        }
        final Categoria categoria = categoriaMapper.toCategoria(categoriaRequest);
        return categoriaMapper.toCategoriaResponse(categoriaRepository.save(categoria));
    }

    @Override
    public CategoriaResponse update(Long id, CategoriaRequest categoriaRequest) {
        final Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Categoría no encontrada con id: " + id));

        // Verificar que no exista otra categoría con el mismo nombre
        categoriaRepository.findByNombre(categoriaRequest.getNombre())
                .ifPresent(existingCategoria -> {
                    if (!existingCategoria.getId().equals(id)) {
                        throw new IllegalArgumentException("Ya existe otra categoría con ese nombre");
                    }
                });

        categoria.setNombre(categoriaRequest.getNombre());
        return categoriaMapper.toCategoriaResponse(categoriaRepository.save(categoria));
    }

    @Override
    public void delete(Long id) {
        final Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Categoría no encontrada con id: " + id));

        if (categoria.getProductos() != null && !categoria.getProductos().isEmpty()) {
            throw new IllegalStateException("No se puede eliminar la categoría porque tiene productos asociados");
        }

        categoriaRepository.delete(categoria);
    }
}
