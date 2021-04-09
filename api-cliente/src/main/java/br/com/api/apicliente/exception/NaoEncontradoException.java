package br.com.api.apicliente.exception;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NaoEncontradoException extends RuntimeException {
    private String mensagem;
}
