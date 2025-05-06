package sv.edu.udb.controller.request;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true) //Ignora propiedades desconocidas
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductoRequest {

    @NotNull
    private String nombre;
    private String descripcion;
    @NotNull
    @Min(value = 0)
    private BigDecimal precio;
    @NotNull
    @Min(value = 0)
    private Integer stock;
    @NotNull
    private String categoria;
    @NotNull
    private String marca;
    private Boolean disponible;


}
