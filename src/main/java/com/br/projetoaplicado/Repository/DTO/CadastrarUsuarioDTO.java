package com.br.projetoaplicado.Repository.DTO;

import com.br.projetoaplicado.Model.Usuario;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.validation.constraints.Size;


@Data
@ToString()
public class CadastrarUsuarioDTO {


    private Long id;
    @Size(min = 4, max = 30, message = "Nome deve ter entre 3 e 30 caracteres")
    @Column(nullable = false)
    private String nome;
    @Size(min = 8, max = 20, message = "A senha deve ter entre 8 e 20 caracteres")
    private String senha;
    @Size(min = 10, max = 100, message = "Email deve ter entre 10 e 100 caracteres ")
    @Column(unique = true, nullable = false)
    private String email;
    @Column(unique = true)
    private String token;

    public Usuario toUser() {
        return new Usuario(
        );
    }
}
