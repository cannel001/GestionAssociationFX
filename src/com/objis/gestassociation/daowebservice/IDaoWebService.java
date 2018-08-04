package com.objis.gestassociation.daowebservice;

import java.util.List;

/**
 * Interface IDaoWebService
 *
 * @author Seka Cannel Ulrich Evrard
 *
 * @param <T>
 * @param <PK>
 */
public interface IDaoWebService<T, PK> {

    public Boolean create(T t);

    public T readOne(PK pk);

    public Boolean update(T t);

    public Boolean delete(PK pk);

    public List<T> readAll();

}
