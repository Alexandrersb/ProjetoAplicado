package com.br.projetoaplicado.Repository.DTO;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
public class AlterarSenhaDTO {

    private Long id;
    private String email;
    @NotNull
    @NotBlank
    private String senha;
}
