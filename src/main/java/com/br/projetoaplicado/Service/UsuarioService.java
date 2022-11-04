package com.br.projetoaplicado.Service;


import com.br.projetoaplicado.ExceptionHandler.UserNotFoundException;
import com.br.projetoaplicado.Model.Usuario;
import com.br.projetoaplicado.Repository.DTO.AlterarSenhaDTO;
import com.br.projetoaplicado.Repository.DTO.CadastrarUsuarioDTO;
import com.br.projetoaplicado.Repository.DTO.EditarUsuarioDTO;
import com.br.projetoaplicado.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public String cadastrarUsuario(CadastrarUsuarioDTO usuarioDTO){

        Usuario usuario = new Usuario();
        usuario.setNome(usuarioDTO.getNome());
        usuario.setSenha(usuarioDTO.getSenha());
        usuario.setEmail(usuarioDTO.getEmail());
        usuarioRepository.save(usuario);
        return "Usuário cadastrado com sucesso";
    }

    public List<Usuario> listarUsuarios (){
        Optional<List<Usuario>> usuario = Optional.of(usuarioRepository.findAll());
        return usuario.orElse(null);
    }

    public Usuario editarUsuario(Long id, EditarUsuarioDTO editarUsuarioDTO) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (usuario.isPresent()) {
            usuario.get().setNome(editarUsuarioDTO.getNome());
            usuario.get().setEmail(editarUsuarioDTO.getEmail());
            usuario.get().setSenha(editarUsuarioDTO.getSenha());
            usuarioRepository.save(usuario.get());
        }
        return usuario.orElseThrow(() -> new UserNotFoundException());
    }

    public String excluirUsuario (Long id) {
        Optional<Usuario> shirt = this.usuarioRepository.findById(id);
        if (shirt.isPresent()) {
            usuarioRepository.deleteById(id);
            return "Usuário foi deletado com sucesso.";
        }
        throw new UserNotFoundException();
    }
    public Usuario alterarSenha(Long id, AlterarSenhaDTO alterarSenhaDTO){
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if(usuario.isPresent()) {
            usuario.get().setSenha(alterarSenhaDTO.getSenha());
        }
        return usuario.orElseThrow(() -> new UserNotFoundException());
    }

    public Usuario consultarPorId (Long id){
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        return usuario.orElseThrow(() -> new UserNotFoundException());
    }


}
