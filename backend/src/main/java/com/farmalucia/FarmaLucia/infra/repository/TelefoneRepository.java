package com.farmalucia.FarmaLucia.infra.repository;

import com.farmalucia.FarmaLucia.infra.entity.Telefone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TelefoneRepository extends JpaRepository<Telefone, Long> {
}
