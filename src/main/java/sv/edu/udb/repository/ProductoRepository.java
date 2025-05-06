package sv.edu.udb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sv.edu.udb.model.Categoria;
import sv.edu.udb.model.Marca;
import sv.edu.udb.model.Producto;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    List<Producto> findByCategoria(Categoria categoria);
    List<Producto> findByMarca(Marca marca);
    List<Producto> findByNombreContainingIgnoreCase(String nombre);
    List<Producto> findByDisponible(boolean disponible);
}
