package com.br.projetoaplicado.Model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Entity
@Getter
@Setter
public class Usuario {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Column(unique=true)
    @Email
    @NotBlank
    @NotNull
    private String email;
    private String senha;


}
