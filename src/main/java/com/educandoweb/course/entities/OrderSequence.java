package com.educandoweb.course.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_order_sequence")
public class OrderSequence {
	
	    @Id
	    private Integer ano; // suficiente para 2026, 2027, etc.
	    private Integer ultimaSequencia; // sequência incremental por ano
	    
		public Integer getAno() {
			return ano;
		}
		public void setAno(Integer ano) {
			this.ano = ano;
		}
		public Integer getUltimaSequencia() {
			return ultimaSequencia;
		}
		public void setUltimaSequencia(Integer ultimaSequencia) {
			this.ultimaSequencia = ultimaSequencia;
		}
	    
	    
}
