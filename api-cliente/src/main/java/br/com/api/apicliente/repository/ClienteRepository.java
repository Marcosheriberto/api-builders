package br.com.api.apicliente.repository;

import br.com.api.apicliente.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository//define que é um repositório
public interface ClienteRepository extends JpaRepository<Cliente, Long> {//Herança: interface ClienteRepository é filha de JpaRepositóri
}
