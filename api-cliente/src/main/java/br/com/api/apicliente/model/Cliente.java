package br.com.api.apicliente.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter// permite visualizar
@Setter//permite alterar valores dos atributos
@NoArgsConstructor//construtor vazio
@AllArgsConstructor//construtor com todas as exigências para criação
@Entity //informa que esse objeto deve virar uma tabela no banco de dados (padrão JPA)
@Table(name = "clientes") //informa que é uma tabela e permite configurar a tabela
public class Cliente {
    @Id//anotação especifica para chave primária e também já entende que é uma coluna
    @GeneratedValue(strategy = GenerationType.AUTO)//adicionando valor automático a cada novo objeto
    private Long id; // chave primária

    @Column//anotação de identificação para cria coluna no banco de dados
    private String nome;//vai criar uma coluna nome

    @Column//anotação de identificação para cria coluna no banco de dados
    private int idade;//vai criar uma coluna idade

    @Column//anotação de identificação para cria coluna no banco de dados
    private String cor;

    @Column//anotação de identificação para cria coluna no banco de dados
    private String sexo;

    @Column//anotação de identificação para cria coluna no banco de dados
    private double altura;

    @Column//anotação de identificação para cria coluna no banco de dados
    private String pais;

    @Column//anotação de identificação para cria coluna no banco de dados
    private String estado;

    @Column//anotação de identificação para cria coluna no banco de dados
    private String municipio;

    @Column//anotação de identificação para cria coluna no banco de dados
    private String bairro;

    @Column//anotação de identificação para cria coluna no banco de dados
    private String rua;

}
