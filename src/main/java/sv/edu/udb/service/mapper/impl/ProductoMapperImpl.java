package sv.edu.udb.service.mapper.impl;

import org.springframework.beans.factory.annotation.Autowired;
import sv.edu.udb.controller.request.ProductoRequest;
import sv.edu.udb.controller.response.ProductoResponse;
import sv.edu.udb.model.Categoria;
import sv.edu.udb.model.Marca;
import sv.edu.udb.model.Producto;
import sv.edu.udb.repository.CategoriaRepository;
import sv.edu.udb.repository.MarcaRepository;
import sv.edu.udb.service.mapper.ProductoMapper;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class ProductoMapperImpl implements ProductoMapper {
    private CategoriaRepository categoriaRepository;

    private MarcaRepository marcaRepository;

    @Override
    public Producto toProducto(ProductoRequest productoRequest) {
        if (productoRequest == null) {
            return null;
        }

        // Buscar la categoría por nombre
        Categoria categoria = categoriaRepository.findByNombre(productoRequest.getCategoria())
                .orElseThrow(() -> new NoSuchElementException("Categoría no encontrada: " + productoRequest.getCategoria()));

        // Buscar la marca por nombre
        Marca marca = marcaRepository.findByNombre(productoRequest.getMarca())
                .orElseThrow(() -> new NoSuchElementException("Marca no encontrada: " + productoRequest.getMarca()));

        Producto producto = Producto.builder()
                .nombre(productoRequest.getNombre())
                .descripcion(productoRequest.getDescripcion())
                .precio(productoRequest.getPrecio())
                .stock(productoRequest.getStock())
                .categoria(categoria)
                .marca(marca)
                .build();

        // Si disponible no es nulo, asignarlo
        if (productoRequest.getDisponible() != null) {
            producto.setDisponible(productoRequest.getDisponible());
        } else {
            // Valor por defecto
            producto.setDisponible(true);
        }

        return producto;
    }

    @Override
    public ProductoResponse toProductoResponse(Producto producto) {
        if (producto == null) {
            return null;
        }

        return ProductoResponse.builder()
                .id(producto.getId())
                .nombre(producto.getNombre())
                .descripcion(producto.getDescripcion())
                .precio(producto.getPrecio())
                .stock(producto.getStock())
                .categoria(producto.getCategoria() != null ? producto.getCategoria().getNombre() : null)
                .marca(producto.getMarca() != null ? producto.getMarca().getNombre() : null)
                .disponible(producto.getDisponible())
                .build();
    }

    @Override
    public List<ProductoResponse> toProductoResponseList(List<Producto> productos) {
        if (productos == null) {
            return null;
        }

        return productos.stream()
                .map(this::toProductoResponse)
                .collect(Collectors.toList());
    }
}
