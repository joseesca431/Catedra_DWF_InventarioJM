package sv.edu.udb.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sv.edu.udb.controller.request.ProductoRequest;
import sv.edu.udb.controller.response.ProductoResponse;
import sv.edu.udb.model.Categoria;
import sv.edu.udb.model.Marca;
import sv.edu.udb.model.Producto;
import sv.edu.udb.repository.CategoriaRepository;
import sv.edu.udb.repository.MarcaRepository;
import sv.edu.udb.repository.ProductoRepository;
import sv.edu.udb.service.ProductoService;
import sv.edu.udb.service.mapper.ProductoMapper;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements ProductoService {
    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;
    private final MarcaRepository marcaRepository;
    private final ProductoMapper productoMapper;

    @Override
    public List<ProductoResponse> findAll(){
        final List<Producto> productos = productoRepository.findAll();
        return productoMapper.toProductoResponseList(productos);
    }
    @Override
    public ProductoResponse findById(Long id) {
        final Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Producto no encontrado con id: " + id));
        return productoMapper.toProductoResponse(producto);
    }
    @Override
    public List<ProductoResponse> findByCategoria(String categoria) {
        final Categoria categoriaEntity = categoriaRepository.findByNombre(categoria)
                .orElseThrow(() -> new NoSuchElementException("Categoría no encontrada: " + categoria));

        final List<Producto> productos = productoRepository.findByCategoria(categoriaEntity);
        return productoMapper.toProductoResponseList(productos);
    }
    @Override
    public List<ProductoResponse> findByMarca(String marca) {
        final Marca marcaEntity = marcaRepository.findByNombre(marca)
                .orElseThrow(() -> new NoSuchElementException("Marca no encontrada: " + marca));

        final List<Producto> productos = productoRepository.findByMarca(marcaEntity);
        return productoMapper.toProductoResponseList(productos);
    }


    @Override
    public ProductoResponse save(ProductoRequest productoRequest) {
        // La validación de categoría y marca se hace en el mapper
        final Producto producto = productoMapper.toProducto(productoRequest);
        return productoMapper.toProductoResponse(productoRepository.save(producto));
    }
    @Override
    public ProductoResponse update(Long id, ProductoRequest productoRequest) {
        final Producto productoExistente = productoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Producto no encontrado con id: " + id));

        // Obtener categoria y marca
        final Categoria categoria = categoriaRepository.findByNombre(productoRequest.getCategoria())
                .orElseThrow(() -> new NoSuchElementException("Categoría no encontrada: " + productoRequest.getCategoria()));

        final Marca marca = marcaRepository.findByNombre(productoRequest.getMarca())
                .orElseThrow(() -> new NoSuchElementException("Marca no encontrada: " + productoRequest.getMarca()));

        // Actualizar el producto existente
        productoExistente.setNombre(productoRequest.getNombre());
        productoExistente.setDescripcion(productoRequest.getDescripcion());
        productoExistente.setPrecio(productoRequest.getPrecio());
        productoExistente.setStock(productoRequest.getStock());
        productoExistente.setCategoria(categoria);
        productoExistente.setMarca(marca);

        if (productoRequest.getDisponible() != null) {
            productoExistente.setDisponible(productoRequest.getDisponible());
        }

        return productoMapper.toProductoResponse(productoRepository.save(productoExistente));
    }
    @Override
    public void delete(Long id) {
        final Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Producto no encontrado con id: " + id));

        productoRepository.delete(producto);
    }

}
