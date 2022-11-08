package com.br.projetoaplicado.Service;


import com.br.projetoaplicado.ExceptionHandler.ConstraintViolationException;
import com.br.projetoaplicado.ExceptionHandler.Dataintegrityviolationexception;
import com.br.projetoaplicado.ExceptionHandler.UserNotFoundException;
import com.br.projetoaplicado.Model.Usuario;
import com.br.projetoaplicado.Repository.DTO.AlterarSenhaDTO;
import com.br.projetoaplicado.Repository.DTO.CadastrarUsuarioDTO;
import com.br.projetoaplicado.Repository.DTO.EditarUsuarioDTO;
import com.br.projetoaplicado.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario cadastrarUsuario(CadastrarUsuarioDTO usuarioDTO){

        try{
        Usuario usuario = new Usuario();
        usuario.setNome(usuarioDTO.getNome());
        usuario.setSenha(usuarioDTO.getSenha());
        usuario.setEmail(usuarioDTO.getEmail());
        usuarioRepository.save(usuario);
        return usuario;
        } catch (DataIntegrityViolationException e) {
            throw new Dataintegrityviolationexception("Já possui um usuário com esse email cadastrado.");
        }
        catch (ConstraintViolationException e) {
            throw new ConstraintViolationException("Email não está no formato correto");
        }
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
            return usuario.orElse(null);
        }
        throw new UserNotFoundException();
    }

    public String excluirUsuario (Long id) {
        Optional<Usuario> shirt = this.usuarioRepository.findById(id);
        if (shirt.isPresent()) {
            usuarioRepository.deleteById(id);
            return "Usuário foi deletado com sucesso.";
        }
        throw new UserNotFoundException();
    }
    public Usuario alterarSenha(String email, AlterarSenhaDTO alterarSenhaDTO){
        Optional<Usuario> usuario = usuarioRepository.findByEmail(email);
        if(usuario.isPresent()) {
            usuario.get().setSenha(alterarSenhaDTO.getSenha());
            return usuario.orElse(null);
        }
        throw new UserNotFoundException();
    }

    public Usuario consultarPorId (Long id){
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        return usuario.orElseThrow(() -> new UserNotFoundException());
    }


}
