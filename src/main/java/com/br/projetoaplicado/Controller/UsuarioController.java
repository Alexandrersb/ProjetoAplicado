package com.br.projetoaplicado.Controller;

import com.br.projetoaplicado.ExceptionHandler.ConstraintViolationException;
import com.br.projetoaplicado.ExceptionHandler.Dataintegrityviolationexception;
import com.br.projetoaplicado.Model.Usuario;
import com.br.projetoaplicado.Repository.DTO.AlterarSenhaDTO;
import com.br.projetoaplicado.Repository.DTO.CadastrarUsuarioDTO;
import com.br.projetoaplicado.Repository.DTO.EditarUsuarioDTO;
import com.br.projetoaplicado.Repository.UsuarioRepository;
import com.br.projetoaplicado.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    private final PasswordEncoder encoder;

    public UsuarioController(UsuarioRepository usuarioRepository, UsuarioService usuarioService, PasswordEncoder encoder) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioService = usuarioService;
        this.encoder = encoder;
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Usuario> addUsuario (@Valid @RequestBody CadastrarUsuarioDTO usuarioDTO) throws Exception {
        usuarioDTO.setSenha(encoder.encode(usuarioDTO.getSenha()));
        return new ResponseEntity<>(usuarioService.cadastrarUsuario(usuarioDTO), HttpStatus.CREATED);
    }

    @GetMapping("/consultar")
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        return new ResponseEntity<>(usuarioService.listarUsuarios(), HttpStatus.OK);
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<Usuario> editarUsuario(@PathVariable("id") Long id,@Valid @RequestBody EditarUsuarioDTO editarUsuarioDTO) throws Exception{
        try{
            editarUsuarioDTO.setSenha(encoder.encode(editarUsuarioDTO.getSenha()));
            return new ResponseEntity<>(usuarioService.editarUsuario(id, editarUsuarioDTO), HttpStatus.OK); }
        catch (DataIntegrityViolationException e) {
            throw new Dataintegrityviolationexception("Já possui um usuário com esse email cadastrado.");
        }
        catch (ConstraintViolationException e) {
            throw new ConstraintViolationException("Email não está no formato correto");
        }
    }
    @GetMapping("/consultarPorNome")
        public ResponseEntity<List<Usuario>> listarPorNome(@RequestParam("nome") String nome){
        return new ResponseEntity<>(usuarioRepository.findByNomeIgnoreCaseContains(nome), HttpStatus.OK);
    }
    @GetMapping("/consultarPorEmail")
    public ResponseEntity<List<Usuario>> listarPorEmail(@RequestParam("email") String email){
        return new ResponseEntity<>(usuarioRepository.findByEmailIgnoreCaseContains(email), HttpStatus.OK);
    }

    @GetMapping("/consultar/{id}")
    public ResponseEntity<Usuario> listarPorId (@PathVariable("id") Long id){
        return new ResponseEntity<>(usuarioService.consultarPorId(id), HttpStatus.OK);
    }

    @PatchMapping("/alterarSenha/{email}")
    public ResponseEntity<Usuario> alterarSenha(@PathVariable("email") String email, @Valid @RequestBody AlterarSenhaDTO alterarSenhaDTO) {
        alterarSenhaDTO.setSenha(encoder.encode(alterarSenhaDTO.getSenha()));
        return new ResponseEntity<>(usuarioService.alterarSenha(email, alterarSenhaDTO), HttpStatus.OK);
    }

    @DeleteMapping("/excluir/{id}")
    public String excluirUsuario(@PathVariable ("id") Long id){
        return usuarioService.excluirUsuario(id);
    }


}
