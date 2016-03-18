/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifpb.pp.impl;

import ifpb.pp.impl.RepositorioPessoa;
import ifpb.pp.pessoa.CPF;
import ifpb.pp.pessoa.Endereco;
import ifpb.pp.pessoa.Pessoa;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author José Marcondes do Nascimento Junior
 */
import pp.dbunitconf.DBUnitHelper;

public class RepositorioPessoaTest {

    public RepositorioPessoaTest() {
    }

    @Before
    public void setUp() {
        new DBUnitHelper().cleanInsert("/Endereco.xml");
        new DBUnitHelper().cleanInsert("/Pessoa.xml");
    }

    @After
    public void tearDown() {
        new DBUnitHelper().deleteAll("/Pessoa.xml");
        new DBUnitHelper().deleteAll("/Endereco.xml");

    }

    @Test
    public void testSalvar() {
        Pessoa pessoa = new Pessoa();
        pessoa.setId(10);
        pessoa.setCpf(new CPF("180.806.443-73"));
        pessoa.setEndereco(new Endereco("Rua1", "Bairro1"));
        pessoa.setFoto(new byte[10]);

        RepositorioPessoa repository = new RepositorioPessoa();
        assertTrue(repository.salvar(pessoa));
        assertEquals(pessoa, repository.localizar(pessoa.getId()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSalvarNulo() {
        Pessoa pessoa = null;
        RepositorioPessoa repository = new RepositorioPessoa();
        repository.salvar(pessoa);
    }

    @Test(expected = javax.persistence.RollbackException.class)
    public void testSalvarInvalidPrimary() {
        Pessoa pessoa = new Pessoa();
        pessoa.setId(1);
        pessoa.setCpf(new CPF("180.806.443-73"));
        pessoa.setEndereco(new Endereco("Rua1", "Bairro1"));
        pessoa.setFoto(new byte[10]);

        RepositorioPessoa repository = new RepositorioPessoa();
        assertTrue(repository.salvar(pessoa));
        assertEquals(pessoa, repository.localizar(pessoa.getId()));

    }

    @Test
    public void testRemoverPessoa() {
        RepositorioPessoa rep = new RepositorioPessoa();
        Pessoa p = rep.localizar(new Long(1));
        assertNotNull(p);
        assertTrue(rep.remover(p));
        assertNull(rep.localizar(p.getId()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoverPessoaNula() {
        RepositorioPessoa rep = new RepositorioPessoa();
        Pessoa p = rep.localizar(new Long(99));
        assertNull(p);
        rep.remover(p);
    }

    @Test
    public void testRemoverLong() {
        Long id = new Long(1);
        RepositorioPessoa rep = new RepositorioPessoa();
        assertNotNull(rep.localizar(id));
        assertTrue(rep.remover(id));
        assertNull(rep.localizar(id));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoverLongNulo() {
        Long id = new Long(99);
        RepositorioPessoa rep = new RepositorioPessoa();
        assertNull(rep.localizar(id));
        rep.remover(id);
    }

    @Test
    public void testLocalizar() {
        Pessoa p = new RepositorioPessoa().localizar(new Long(1));
        assertNotNull(p);
        Pessoa real = new Pessoa();
//        real.setId(1);
//        real.setNome("nome1");
//        real.setFoto("12345".getBytes());
//        real.setEndereco(new Endereco("rua", "bairro"));
//        real.setCpf(new CPF("180.806.443-73"));
        assertArrayEquals("12345".getBytes(), p.getFoto());
        assertEquals(1, p.getId());
        assertEquals(new CPF("180.806.443-73"), p.getCpf());
        Endereco end = new Endereco("rua", "bairro");
        end.setId(1);
        assertEquals(end, p.getEndereco());
        assertEquals("nome1", p.getNome());
    }

    @Test
    public void testLocalizarInexistente() {
        Pessoa p = new RepositorioPessoa().localizar(new Long(333));
        assertNull(p);
    }

    @Test
    public void testTodos() {
        RepositorioPessoa rep = new RepositorioPessoa();
        List<Pessoa> list = rep.todos();
        assertEquals(list.size(), 4);
    }

    @Test
    public void testTodosVazio() {
        new DBUnitHelper().deleteAll("/Pessoa.xml");
        RepositorioPessoa rep = new RepositorioPessoa();
        List<Pessoa> list = rep.todos();
        assertEquals(list.size(), 0);
    }

}
