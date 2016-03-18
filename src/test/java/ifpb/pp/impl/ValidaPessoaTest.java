/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifpb.pp.impl;

import ifpb.pp.impl.ValidaPessoa;
import ifpb.pp.pessoa.CPF;
import ifpb.pp.pessoa.Endereco;
import ifpb.pp.pessoa.Pessoa;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.progress.MockingProgress;

/**
 *
 * @author José Marcondes do Nascimento Junior
 */

@RunWith(Parameterized.class)
public class ValidaPessoaTest {

    private Pessoa p;
    private boolean result;
    public ValidaPessoaTest(Pessoa p, boolean result) {
        this.p = p;
        this.result = result;
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Parameterized.Parameters
    public static Collection<Object[]> data(){
        Pessoa p1 = new Pessoa();
        p1.setCpf(new CPF("117.376.474-78"));
        p1.setEndereco(new Endereco("Rua", "Bairro"));
        p1.setFoto(new byte[1]);
        p1.setId(10);
        p1.setNome("João");
        boolean result1 = true;
        
        Pessoa p2 = new Pessoa();
        p2.setCpf(new CPF("243.484.770-60"));
        p2.setEndereco(new Endereco("Rua", "Bairro"));
        p2.setFoto(new byte[1]);
        p2.setId(10);
        p2.setNome("José");
        boolean result2 = true;
        
        Pessoa p3 = new Pessoa();
        p3.setCpf(new CPF("111111"));
        p3.setEndereco(new Endereco("Rua", "Bairro"));
        p3.setFoto(new byte[1]);
        p3.setId(10);
        p3.setNome("Maria");
        boolean result3 = false;
        
        Pessoa p4 = new Pessoa();
        p4.setCpf(new CPF("111.111.111-11"));
        p4.setEndereco(new Endereco("Rua", "Bairro"));
        p4.setFoto(new byte[1]);
        p4.setId(10);
        p4.setNome("Joana");
        boolean result4 = false;
        
        Pessoa p5 = new Pessoa();
        p5.setEndereco(new Endereco("Rua", "Bairro"));
        p5.setFoto(new byte[1]);
        p5.setId(10);
        p5.setNome("Joana");
        boolean result5 = false;
        
        Pessoa p6 = new Pessoa();
        p6.setCpf(new CPF("243.484.770-60"));
        p6.setFoto(new byte[1]);
        p6.setId(10);
        p6.setNome("Francisco");
        boolean result6 = false;
        
        Pessoa p7 = new Pessoa();
        p7.setCpf(new CPF());
        p7.setFoto(new byte[1]);
        p7.setId(10);
        p7.setNome("Francisco");
        boolean result7 = false;
        
        Pessoa p8 = new Pessoa();
        p8.setCpf(new CPF("243.484.770-60"));
        p8.setEndereco(new Endereco());
        p8.setFoto(new byte[1]);
        p8.setId(10);
        p8.setNome("Francisco");
        boolean result8 = true; //Discutivel
        
        Pessoa p9 = new Pessoa();
        p9.setCpf(new CPF("243.484.770-60"));
        p9.setEndereco(new Endereco("Rua", "Bairro"));
        p9.setId(10);
        p9.setNome("Joana");
        boolean result9 = false;
        
        Object[][] data = new Object[][] {
            {p1, result1},
            {p2, result2},
            {p3, result3},
            {p4, result4},
            {p5, result5},
            {p6, result6},
            {p7, result7},
            {p8, result8},
            {p9, result9},
        };
        return Arrays.asList(data);
    }

    @Test
    public void testValidar() {
                
        ValidaPessoa instance = new ValidaPessoa();
        assertEquals(result, instance.validar(p));
    }
    
}
