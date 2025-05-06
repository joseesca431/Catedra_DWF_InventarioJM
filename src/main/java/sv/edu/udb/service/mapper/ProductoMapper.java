package sv.edu.udb.service.mapper;

import org.mapstruct.Mapper;
import sv.edu.udb.controller.request.ProductoRequest;
import sv.edu.udb.controller.response.ProductoResponse;
import sv.edu.udb.model.Producto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductoMapper {
    Producto toProducto(ProductoRequest productoRequest);
    ProductoResponse toProductoResponse(Producto producto);
    List<ProductoResponse> toProductoResponseList(List<Producto> productos);

}
