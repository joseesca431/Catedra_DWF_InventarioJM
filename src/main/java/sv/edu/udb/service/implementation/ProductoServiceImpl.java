package sv.edu.udb.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sv.edu.udb.controller.request.ProductoRequest;
import sv.edu.udb.controller.response.ProductoResponse;
import sv.edu.udb.model.Producto;
import sv.edu.udb.repository.ProductoRepository;
import sv.edu.udb.service.ProductoService;
import sv.edu.udb.service.mapper.ProductoMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements ProductoService {
    private final ProductoRepository productoRepository;
    private final ProductoMapper productoMapper;

    @Override
    public List<ProductoResponse> findAll(){
        final List<Producto> productos = productoRepository.findAll();
        return productoMapper.toProductoResponseList(productos);
    }

    @Override
    public ProductoResponse save(ProductoRequest productoRequest) {
        final Producto producto = productoMapper.toProducto(productoRequest);

        return productoMapper.toProductoResponse(productoRepository.save(producto));
    }

}
