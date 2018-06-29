package com.objis.gestassociation.dao;

import java.util.List;

/**
 * Interface IDao
 * @author Seka Cannel Ulrich Evrard
 *
 * @param <T>
 * @param <PK>
 */
public interface IDao<T,PK> {
	
	public Boolean create(T t);
	public T readOne(PK pk);
	public Boolean update(T t);
	public Boolean delete(PK pk);
	public List<T> readAll();

}
