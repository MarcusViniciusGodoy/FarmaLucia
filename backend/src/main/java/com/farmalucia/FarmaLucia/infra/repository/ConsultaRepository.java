package com.farmalucia.FarmaLucia.infra.repository;

import com.farmalucia.FarmaLucia.infra.entity.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
}
