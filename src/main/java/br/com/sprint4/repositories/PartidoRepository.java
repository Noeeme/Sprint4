package br.com.sprint4.repositories;

import br.com.sprint4.models.PartidoModelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface PartidoRepository extends JpaRepository<PartidoModelo, Long> {

}
