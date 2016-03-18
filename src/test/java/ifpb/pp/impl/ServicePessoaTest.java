/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifpb.pp.impl;

import ifpb.pp.Repositorio;
import ifpb.pp.ServiceException;
import ifpb.pp.Validador;
import ifpb.pp.ValidadorException;
import ifpb.pp.pessoa.CPF;
import ifpb.pp.pessoa.Endereco;
import ifpb.pp.pessoa.Pessoa;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mockito;

/**
 *
 * @author José Marcondes do Nascimento Junior
 */
public class ServicePessoaTest {

    private Repositorio repositorioPessoa;
    private Validador<Pessoa> validador;

    public ServicePessoaTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testSalvarValida() {
        repositorioPessoa = Mockito.mock(RepositorioPessoa.class);
        validador = Mockito.mock(ValidaPessoa.class);

        ServicePessoa servicePessoa = new ServicePessoa(validador, repositorioPessoa);
        CPF cpf = new CPF("180.806.443-73");
        Endereco endereco = new Endereco("Rua", "Bairo");
        Pessoa pessoa = new Pessoa(1l, "Fernanda", new byte[4], cpf, endereco);

        Mockito.when(repositorioPessoa.salvar(pessoa)).thenReturn(Boolean.TRUE);
        Mockito.when(validador.validar(pessoa)).thenReturn(Boolean.TRUE);

        Assert.assertTrue(servicePessoa.salvar(pessoa));
    }

    @Test(expected = ServiceException.class)
    public void testSalvarInvalida() {
        repositorioPessoa = Mockito.mock(RepositorioPessoa.class);
        validador = Mockito.mock(ValidaPessoa.class);

        ServicePessoa servicePessoa = new ServicePessoa(validador, repositorioPessoa);
        CPF cpf = new CPF("111111111");
        Endereco endereco = new Endereco("Rua", "Bairo");
        Pessoa pessoa = new Pessoa(1l, "Fernanda", new byte[4], cpf, endereco);

        Mockito.when(repositorioPessoa.salvar(pessoa)).thenReturn(Boolean.TRUE);
        Mockito.when(validador.validar(pessoa)).thenReturn(Boolean.FALSE);

        Assert.assertFalse(servicePessoa.salvar(pessoa));
        Mockito.verify(repositorioPessoa, Mockito.never()).salvar(pessoa);
    }

    @Test(expected = ValidadorException.class)
    public void testSalvarPessoaNula() {

        repositorioPessoa = Mockito.mock(RepositorioPessoa.class);
        validador = Mockito.mock(Validador.class);

        ServicePessoa servicePessoa = new ServicePessoa(validador, repositorioPessoa);

        Mockito.when(repositorioPessoa.salvar(null)).thenReturn(Boolean.FALSE);
        Mockito.when(validador.validar(null)).thenThrow(new ValidadorException());

        Assert.assertFalse(servicePessoa.salvar(null));
        Mockito.verify(repositorioPessoa, Mockito.never()).salvar(null);
    }

    @Test
    public void testRemover() {
        repositorioPessoa = Mockito.mock(RepositorioPessoa.class);
        validador = Mockito.mock(Validador.class);

        ServicePessoa servicePessoa = new ServicePessoa(validador, repositorioPessoa);
        CPF cpf = new CPF("09913628458");
        Endereco endereco = new Endereco("Rua", "Bairo");
        Pessoa pessoa = new Pessoa(1l, "Fernanda Alves", new byte[4], cpf, endereco);

        Mockito.when(repositorioPessoa.remover(pessoa)).thenReturn(Boolean.TRUE);
        Mockito.when(validador.validar(pessoa)).thenReturn(Boolean.TRUE);

        Assert.assertTrue(servicePessoa.remover(pessoa));
    }

    @Test
    public void testLocalizar() {
        repositorioPessoa = Mockito.mock(RepositorioPessoa.class);
        validador = Mockito.mock(Validador.class);

        ServicePessoa servicePessoa = new ServicePessoa(validador, repositorioPessoa);
        CPF cpf = new CPF("09913628458");
        Endereco endereco = new Endereco("Rua", "Bairo");
        Pessoa pessoa = new Pessoa(1l, "Fernanda", new byte[4], cpf, endereco);

        Mockito.when(repositorioPessoa.localizar(1l)).thenReturn(pessoa);

        Assert.assertNotNull(servicePessoa.localizar(1l));
    }

    @Test
    public void TestLocalizaPessoaInexistente() {

        repositorioPessoa = Mockito.mock(RepositorioPessoa.class);
        validador = Mockito.mock(Validador.class);
        ServicePessoa servicePessoa = new ServicePessoa(validador, repositorioPessoa);
        Mockito.when(repositorioPessoa.localizar(2l)).thenReturn(null);

        Assert.assertNull(servicePessoa.localizar(2l));
    }

    @Test
    public void testTodos() {
        repositorioPessoa = Mockito.mock(RepositorioPessoa.class);
        validador = Mockito.mock(Validador.class);

        List<Pessoa> listaPessoas = new ArrayList<Pessoa>();
        CPF cpf = new CPF("09913628458");
        Endereco endereco = new Endereco("Rua", "Bairo");
        Pessoa pessoa = new Pessoa(1l, "Fernanda", new byte[4], cpf, endereco);
        listaPessoas.add(pessoa);

        ServicePessoa servicePessoa = new ServicePessoa(validador, repositorioPessoa);

        Mockito.when(repositorioPessoa.todos()).thenReturn(listaPessoas);

        Assert.assertNotNull(servicePessoa.todos());
    }

}
