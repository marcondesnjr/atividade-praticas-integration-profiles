/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifpb.pp.impl;

import br.com.caelum.stella.validation.CPFValidator;
import br.com.caelum.stella.validation.InvalidStateException;
import ifpb.pp.Validador;
import ifpb.pp.pessoa.CPF;

/**
 *
 * @author NandaPC
 */
public class ValidaCPF implements Validador<CPF>{

    @Override
    public boolean validar(CPF cpf) {
        if(cpf != null){
            if(cpf.valor() == null)
                return false;
            CPFValidator validator = new CPFValidator();
            try {
                validator.assertValid(cpf.valor());
                return true;
            } catch (InvalidStateException e) {
            }
            
            }
        return false;
    } 
    
}
