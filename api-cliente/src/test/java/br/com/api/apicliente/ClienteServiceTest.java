package br.com.api.apicliente;


import br.com.api.apicliente.model.Cliente;
import br.com.api.apicliente.repository.ClienteRepository;
import br.com.api.apicliente.service.ClienteService;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClienteServiceTest {

    @InjectMocks
    private ClienteService clienteService;

    @Mock
    private ClienteRepository clienteRepository;

    private EasyRandom easyRandom = new EasyRandom();

    @Test
    public void deveVerificarSeOMetodoRetornaCliente(){
        Cliente cliente = new Cliente();
        cliente.setNome("Moises");
        Optional<Cliente> optionalCliente = Optional.of(cliente);
        when(clienteRepository.findById(Mockito.eq(1L))).thenReturn(optionalCliente);
        Cliente clienteRetornado = clienteService.getCliente(1L);
        Assertions.assertEquals("Moises",clienteRetornado.getNome() );
    }

    @Test
    public void testarSeRetornaUmaLista(){
        Cliente cliente = new Cliente();
        cliente.setSexo("Masculino");
        List<Cliente> listaTesteClientes = new ArrayList<>();
        listaTesteClientes.add(new Cliente());
        listaTesteClientes.add(cliente);
        listaTesteClientes.add(new Cliente());
        when(clienteRepository.findAll()).thenReturn(listaTesteClientes);
        List<Cliente> clientes = clienteService.getClientes();
        Assertions.assertEquals("3",String.valueOf(clientes.size()));
        Assertions.assertEquals("Masculino",clientes.get(1).getSexo());
    }

    @Test
    public void testaValorDaSomaDasIdadesDasMulheres(){
        List<Cliente> listaTeste = new ArrayList<>();
        int somaDasIdadesTest = criarClientesRetornaSomaIdade(listaTeste);
        when(clienteService.getClientes()).thenReturn(listaTeste);
        int somaIdadesMulheresTeste = clienteService.somaIdadesMulheres();
        Assertions.assertEquals(somaDasIdadesTest, somaIdadesMulheresTeste );
    }

    private int criarClientesRetornaSomaIdade(List<Cliente> listaTeste){
        int somaDasIdadesTest = 0;
        for (int i = 0; i < 10; i++) {
            Cliente cliente = new Cliente();
            cliente.setIdade(i+10);
            if(isPar(i)){
                cliente.setSexo("M");
            }else {
                cliente.setSexo("F");
                somaDasIdadesTest += cliente.getIdade();
            }
            listaTeste.add(cliente);
        }
        return somaDasIdadesTest;
    }

    private boolean isPar(int i) {
        return i % 2 == 0;
    }
}
