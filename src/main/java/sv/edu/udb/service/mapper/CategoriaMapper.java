package sv.edu.udb.service.mapper;

import org.mapstruct.Mapper;
import sv.edu.udb.controller.request.CategoriaRequest;
import sv.edu.udb.controller.response.CategoriaResponse;
import sv.edu.udb.model.Categoria;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {
    Categoria toCategoria(CategoriaRequest categoriaRequest);
    CategoriaResponse toCategoriaResponse(Categoria categoria);
    List<CategoriaResponse> toCategoriaResponseList(List<Categoria> categorias);
}