package com.gao.dao;

import java.util.List;

public interface Dao<E> {
	public E findById(Long id);
	public List<E> findAll();
	public int findResultCount();
	public List<E> findInterzone(int pageIndex);
	public E save(E arg);
	public E delete(E arg);
	public E update(E arg);
}
