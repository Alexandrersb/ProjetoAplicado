package com.br.projetoaplicado.Repository;

import com.br.projetoaplicado.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository <Usuario, Long> {

    List<Usuario> findAll();

    List<Usuario> findByNomeIgnoreCaseContains(String nome);

    List<Usuario> findByEmailIgnoreCaseContains(String email);

    Optional<Usuario> findByEmail(String email);

    Optional<Usuario> findById(Long id);


}
