package com.farmalucia.FarmaLucia.infra.repository;

import com.farmalucia.FarmaLucia.infra.entity.Atendente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AtendenteRepository extends JpaRepository<Atendente, Long> {

   @Query("""
            SELECT
                CASE WHEN COUNT(a) > 0 THEN TRUE ELSE FALSE END
            FROM
                Atendente a
            WHERE (a.email = :email) AND (:id IS NULL OR a.id <> :id)
            """)
    boolean isJaCadastrado(String email, Long id);
}
