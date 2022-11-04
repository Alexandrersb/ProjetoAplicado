package com.br.projetoaplicado.Repository.DTO;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
public class CadastrarUsuarioDTO {


    private Long id;
    @NotNull
    @NotBlank
    private String nome;
    @NotNull
    @NotBlank
    private String senha;
    @Column(unique=true)
    @NotNull
    @NotBlank
    @Email
    private String email;
}
