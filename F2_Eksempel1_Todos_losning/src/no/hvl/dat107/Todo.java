package no.hvl.dat107;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (schema="forelesning2", name="todo")
public class Todo {
	
	@Id 
	private int id;
	private String tekst = "";
	
	public Todo() {} //Trengs for JPA
	
	public Todo(int id, String tekst) {
		this.id = id;
		setTekst(tekst);
	}

	@Override
	public String toString() {
		return "Todo [id=" + id + ", tekst=" + tekst + "]";
	}

	public void setTekst(String tekst) {
		if (tekst != null) {
			this.tekst = tekst;
		}
	}
}
