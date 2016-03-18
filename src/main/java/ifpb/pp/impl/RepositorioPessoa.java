/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifpb.pp.impl;

import ifpb.pp.Repositorio;
import ifpb.pp.pessoa.Pessoa;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

/**
 *
 * @author NandaPC
 */
public class RepositorioPessoa implements Repositorio<Long, Pessoa> {
    private EntityManager entityManager = Persistence.createEntityManagerFactory("persistence").createEntityManager();

    @Override
    public boolean salvar(Pessoa pessoa) {
        entityManager.getTransaction().begin();
        entityManager.persist(pessoa);
        entityManager.getTransaction().commit();

        return true;
    }

    @Override
    public boolean remover(Pessoa pessoa) {
        entityManager.getTransaction().begin();
        entityManager.remove(pessoa);
        entityManager.getTransaction().commit();
        return true;
    }

    @Override
    public boolean remover(Long key) {
        entityManager.getTransaction().begin();
        entityManager.remove(this.localizar(key));
        return true;
    }

    @Override
    public Pessoa localizar(Long key) {
        Pessoa pessoa;
        pessoa = entityManager.find(Pessoa.class, key);
        return pessoa;
    }

    @Override
    public List<Pessoa> todos() {
        Query query = entityManager.createQuery("select p from Pessoa p");
        return query.getResultList();

    }

}
