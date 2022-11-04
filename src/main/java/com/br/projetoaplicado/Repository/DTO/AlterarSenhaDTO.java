package com.br.projetoaplicado.Repository.DTO;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
public class AlterarSenhaDTO {

    private Long id;
    @NotNull
    @NotBlank
    private String senha;
}
