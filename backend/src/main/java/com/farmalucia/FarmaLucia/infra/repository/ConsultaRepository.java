package com.farmalucia.FarmaLucia.infra.repository;

import com.farmalucia.FarmaLucia.infra.entity.Consulta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    Page<Consulta> findAllByOrderByData(Pageable paginacao);
}
