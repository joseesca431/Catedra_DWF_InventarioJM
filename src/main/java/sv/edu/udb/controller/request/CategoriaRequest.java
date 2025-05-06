package sv.edu.udb.controller.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaRequest {
    @NotBlank(message = "El nombre de la categoría es obligatorio")
    private String nombre;
}