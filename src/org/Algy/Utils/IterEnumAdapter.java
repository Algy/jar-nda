package org.Algy.Utils;

import java.util.Enumeration;
import java.util.Iterator;

public class IterEnumAdapter<E> implements Iterator<E>,
		Enumeration<E> {
	
	public IterEnumAdapter(Iterator<E> iter) {
		super();
		this.iter = iter;
	}
	public IterEnumAdapter(Enumeration<E> enumer) {
		super();
		this.enumer = enumer;
	}


	Iterator<E> iter;
	Enumeration<E> enumer;
	
	@Override
	public boolean hasMoreElements() {
		return (iter != null)?iter.hasNext() : enumer.hasMoreElements();
	}

	@Override
	public E nextElement() {
		return (iter != null)?iter.next() : enumer.nextElement();
	}

	@Override
	public boolean hasNext() {
		return (iter != null)?iter.hasNext() : enumer.hasMoreElements();
	}

	@Override
	public E next() {
		return (iter != null)?iter.next() : enumer.nextElement();
	}

	@Override
	public void remove() {
		if(iter != null)
			iter.remove();
		else
			throw new UnsupportedOperationException("java.util.Enumeration does not support remove() operation");
	}

}
