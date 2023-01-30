package com.aquiles.helpdesk.repositories;

import com.aquiles.helpdesk.domain.Chamado;
import org.springframework.boot.autoconfigure.web.servlet.JerseyApplicationPath;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChamadoRepository extends JpaRepository<Chamado, Integer> {
}
