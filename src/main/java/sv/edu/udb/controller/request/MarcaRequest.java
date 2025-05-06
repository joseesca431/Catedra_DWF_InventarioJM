package sv.edu.udb.controller.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class MarcaRequest {
    @NotBlank(message = "El nombre de la marca es obligatorio")
    private String nombre;
}