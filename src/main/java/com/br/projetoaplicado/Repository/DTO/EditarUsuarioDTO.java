package com.br.projetoaplicado.Repository.DTO;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
public class EditarUsuarioDTO {

    private Long id;
    @NotNull
    @NotBlank
    private String nome;
    @NotNull
    @NotBlank
    private String senha;
    private String email;

}
