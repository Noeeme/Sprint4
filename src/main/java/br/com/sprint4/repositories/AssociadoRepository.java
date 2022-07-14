package br.com.sprint4.repositories;

import br.com.sprint4.models.AssociadoModelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssociadoRepository extends JpaRepository<AssociadoModelo, Long> {
}
