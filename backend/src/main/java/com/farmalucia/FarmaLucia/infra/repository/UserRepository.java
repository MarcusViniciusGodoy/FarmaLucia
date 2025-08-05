package com.farmalucia.FarmaLucia.infra.repository;

import com.farmalucia.FarmaLucia.infra.entity.User;
import com.farmalucia.FarmaLucia.infra.projections.UserDetailsProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(nativeQuery = true, value = """
			SELECT usuario.email AS username, usuario.senha, role.id AS roleId, role.authority
			FROM user
			INNER JOIN user_role ON user.id = user_role.user_id
			INNER JOIN role ON role.id = user_role.role_id
			WHERE user.email = :email
		""")
    List<UserDetailsProjection> searchUserAndRolesByEmail(String email);


    Optional<User> findByEmail(String email);
}
