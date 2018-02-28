/**
 * 
 */
package br.com.camara.consumer;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.camara.model.Planeta;

/**
 * @author renan
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Swapi {

	private Integer count;
	private Object next;
	private Object previus;
	
	private List<Planeta> results = new ArrayList<Planeta>();

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Object getNext() {
		return next;
	}

	public void setNext(Object next) {
		this.next = next;
	}

	public Object getPrevius() {
		return previus;
	}

	public void setPrevius(Object previus) {
		this.previus = previus;
	}

	public List<Planeta> getResults() {
		return results;
	}

	public void setResults(List<Planeta> results) {
		this.results = results;
	}
}
