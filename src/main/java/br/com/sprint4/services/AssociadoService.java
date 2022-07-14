package br.com.sprint4.services;

import br.com.sprint4.repositories.AssociadoRepository;
import org.springframework.stereotype.Service;

@Service
public class AssociadoService {

    final AssociadoRepository associadoRepository;

    public AssociadoService(AssociadoRepository associadoRepository) {
        this.associadoRepository = associadoRepository;
    }
}
