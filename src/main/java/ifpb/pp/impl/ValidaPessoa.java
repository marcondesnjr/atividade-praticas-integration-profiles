/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifpb.pp.impl;

import ifpb.pp.Validador;
import ifpb.pp.ValidadorException;
import ifpb.pp.pessoa.CPF;
import ifpb.pp.pessoa.Pessoa;

/**
 *
 * @author NandaPC
 */
public class ValidaPessoa implements Validador<Pessoa> {

    @Override
    public boolean validar(Pessoa pessoa) {
        if (pessoa == null) {
            throw new ValidadorException();
        }
        
        ValidaCPF validaCPF = new ValidaCPF();
        if (validaCPF.validar(pessoa.getCpf())) {
            if (pessoa.getFoto() != null && pessoa.getEndereco() != null) {
                return true;
            }
        }
        return false;

    }

}
