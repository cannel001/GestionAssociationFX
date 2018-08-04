package com.objis.gestassociation.service;

import java.util.List;

public interface IService<T,PK> {
	
	public Boolean create(T t);
	public T readOne(PK pk);
	public Boolean update(T t);
	public Boolean delete(PK pk);
	public List<T> readAll();

}
