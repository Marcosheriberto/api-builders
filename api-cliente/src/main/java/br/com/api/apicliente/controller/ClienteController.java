package br.com.api.apicliente.controller;

import br.com.api.apicliente.model.Cliente;
import br.com.api.apicliente.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import springfox.documentation.annotations.Cacheable;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@RestController//informa que é um controlador
@RequestMapping(name = "/api")//permite a criação do padrão do endpoint para está classe

public class ClienteController {

    @Autowired //pede ao Spring injetar uma instância se necessário
    private ClienteService clienteservice;//injetando o serviço

    @GetMapping(value = "/cliente/{id}")//tipo de verbo HTTP
    @CacheEvict(value = "cliente-single", key ="#cliente.nome")
    public Cliente getCliente(@PathVariable Long id){
        return clienteservice.getCliente(id);
    }

    @GetMapping(value = "/clientes")//ver todos os cliente
    public ResponseEntity<List<Cliente>> getClientes(){//recebe uma lista de clientes
        List<Cliente> clienteList= clienteservice.getClientes();//direcionando para receber do serviço
        if(clienteList.isEmpty()){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(clienteList);
        }
    }
    @GetMapping(value = "/clientes/pageble")//ver todos os cliente
    public ResponseEntity<Page<Cliente>> getClientesPageBle(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size
    ){//recebe uma lista de clientes
       try{
           Page<Cliente> clientes = clienteservice.getClientesPageble(page, size);
           if(clientes.isEmpty()){
               return ResponseEntity.noContent().build();
           }else{
               return ResponseEntity.ok(clientes);
           }
       }catch (Exception e){
           return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }
    @PostMapping(value = "/cliente/salvar") // Preciso saber como faz
    public ResponseEntity<Cliente> createClient(@RequestBody Cliente cliente){
        try {
            if(Objects.nonNull(cliente)) {
                Cliente clienteSalvo = clienteservice.salvarCliente(cliente);//verificar tipo de retorno
                URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                        .path(clienteSalvo.getId().toString()).build().toUri();
                return ResponseEntity.created(uri).body(null);
            }else {
                return ResponseEntity.badRequest().body(null);
            }
        }catch(Exception e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
        }
    }

    @DeleteMapping(value = "/cliente/{id}")//recebe a id para achar e depois deleta
    public ResponseEntity<Cliente> deletarCliente(@PathVariable Long id){
        Cliente cliente = clienteservice.getCliente(id);
        if(Objects.nonNull(cliente)){
            clienteservice.deletarCliente(id);//retorna cliente
            return ResponseEntity.ok(cliente);
        }else{
            return ResponseEntity.badRequest().body(null);//retorna
        }
    }

    @PutMapping(path = "/cliente/{id}", produces = { "application/json" })// Preciso saber como faz
    public ResponseEntity<Cliente> alterarCliente(@PathVariable("id") Long id, @RequestBody Cliente clienteNovo){
        try{
            Cliente cliente = clienteservice.getCliente(id);
            if ( Objects.nonNull(cliente)){
                Cliente clienteAtualizado = clienteservice.putCliente(id,clienteNovo) ;
                return ResponseEntity.ok(clienteAtualizado);
            }else{
                return ResponseEntity.badRequest().body(null);
            }
        } catch(Exception exececao){
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
        }
    }
}




