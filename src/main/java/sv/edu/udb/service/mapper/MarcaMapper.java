// MarcaMapper.java
package sv.edu.udb.service.mapper;

import org.mapstruct.Mapper;
import sv.edu.udb.controller.request.MarcaRequest;
import sv.edu.udb.controller.response.MarcaResponse;
import sv.edu.udb.model.Marca;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MarcaMapper {
    Marca toMarca(MarcaRequest marcaRequest);
    MarcaResponse toMarcaResponse(Marca marca);
    List<MarcaResponse> toMarcaResponseList(List<Marca> marcas);
}
