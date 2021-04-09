package br.com.api.apicliente.service;

import br.com.api.apicliente.exception.NaoEncontradoException;
import br.com.api.apicliente.model.Cliente;
import br.com.api.apicliente.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {
  @Autowired
  private ClienteRepository clienteRepository;//injetando repotório dentro do serviço

  public Cliente getCliente(Long id) {
    try {
      return clienteRepository.findById(id).get();//retornar objeto opcional que pode conter um cliente, chama o get pra ver
    }catch (Exception e){
      throw new NaoEncontradoException("Não foi possivel verificar");
    }
  }

  public List<Cliente> getClientes(){//para ver todos os cliente não necessito de parâmetro
    return clienteRepository.findAll();//Retorna uma lista de clientes com o findAll
  }
  public Cliente salvarCliente(Cliente cliente){//objeto cliente sem retorno, pois é só salvar.
    clienteRepository.save(cliente);//salvando meu cliente
    return cliente;
  }
  public void deletarCliente(Long id){//retorna um boleano
    try {
      clienteRepository.delete(getCliente(id));
    }catch (Exception e){
      throw new NaoEncontradoException("Não foi possivel deletar");
    }

  }
  public Cliente putCliente(Long id, Cliente clienteValoresNovo){//tipo de identificação e tipo com novos valores
    Cliente cliente = getCliente(id);//retorno após visualizar
    cliente.setIdade(clienteValoresNovo.getIdade());//atualize meu cliente vendo a idade recebida
    cliente.setNome(clienteValoresNovo.getNome());//atualize meu cliente vendo  nome recebido
    return clienteRepository.saveAndFlush(cliente);
  }

  public Page<Cliente> getClientesPageble(int page, int size) {
    PageRequest pageRequest = PageRequest.of(page,size, Sort.Direction.ASC,"id");
    return clienteRepository.findAll(pageRequest);
  }

  public int somaIdadesMulheres(){
    List<Cliente> clienteList = getClientes();
    int somaIdade = 0;
    for (Cliente cliente:clienteList){
      if(cliente.getSexo().equals("F")){
        somaIdade += cliente.getIdade();
      }
    }
    return somaIdade;
  }

}
