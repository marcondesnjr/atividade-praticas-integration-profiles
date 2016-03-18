/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifpb.pp.impl;

import ifpb.pp.impl.ValidaCPF;
import ifpb.pp.pessoa.CPF;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 *
 * @author José Marcondes do Nascimento Junior
 */
@RunWith(Parameterized.class)
public class ValidaCPFTest {

    private CPF cpf;
    private boolean result;
    
    public ValidaCPFTest(CPF c, boolean result) {
        this.cpf = c;
        this.result = result;
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {

        Object[][] data = new Object[][]{
            {new CPF("117.376.474-78"), true},
            {new CPF("180.806.443-73"), true},
            {new CPF("772.422.544-34"), true},
            {new CPF("841.462.592-40"), true},
            {new CPF("871.815.842-00"), true},
            {new CPF("302.832.673-64"), true},
            {new CPF("539.113.271-10"), true},
            {new CPF("169.562.824-10"), true},
            {new CPF("107.343.322-62"), true},
            {new CPF("11111"), false},
            {new CPF("111.111.111-11"), false},
            {new CPF("123.236.120-54"), false},
            {new CPF("naoconsigolernada"), false},
            {new CPF(""), false},
            {new CPF(null), false}
        };
        return Arrays.asList(data);
    }

    @Test
    public void testValidarValido() {
        ValidaCPF instance = new ValidaCPF();
        System.out.println(cpf.valor() + result);
        assertEquals(result, instance.validar(cpf));
    }

}
