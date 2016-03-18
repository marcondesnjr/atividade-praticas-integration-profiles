/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifpb.pp.impl;

import ifpb.pp.Repositorio;
import ifpb.pp.Service;
import ifpb.pp.ServiceException;
import ifpb.pp.Validador;
import ifpb.pp.pessoa.Pessoa;
import java.util.List;

/**
 *
 * @author NandaPC
 */
public class ServicePessoa implements Service<Long, Pessoa>{
    
    private Validador validaPessoa;
    private Repositorio repositorioPessoa;

    public ServicePessoa(Validador validador,Repositorio repositorio) {
        this.repositorioPessoa = repositorio;
        this.validaPessoa = validador;
    }

    @Override
    public boolean salvar(Pessoa pessoa) {
        if(validaPessoa.validar(pessoa)){
           return repositorioPessoa.salvar(pessoa);          
        }else
            throw new ServiceException();
    }

    @Override
    public boolean remover(Pessoa pessoa) {
        return repositorioPessoa.remover(pessoa);
        }

    @Override
    public Pessoa localizar(Long key) {
        return (Pessoa) repositorioPessoa.localizar(key);
        
    }

    @Override
    public List<Pessoa> todos() {
        return repositorioPessoa.todos();
    }
    
}
