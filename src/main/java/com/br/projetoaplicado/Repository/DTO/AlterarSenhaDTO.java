package com.br.projetoaplicado.Repository.DTO;

import lombok.Data;
import javax.validation.constraints.Size;


@Data
public class AlterarSenhaDTO {

    private Long id;
    private String email;
    @Size(min = 8, max = 20, message = "A senha deve ter entre 8 e 20 caracteres")
    private String senha;
}
