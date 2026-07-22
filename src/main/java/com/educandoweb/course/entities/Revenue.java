package com.educandoweb.course.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_revenue")
public class Revenue implements Serializable{
		
		
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		private Instant moment;
		private String description;
		private Double amount;
				
		@ManyToOne
		@JoinColumn(name = "segment_id")
		private Segment segment;

		public Revenue(Long id, Instant moment, String description, Double amount, Segment segment) {
			super();
			this.id = id;
			this.moment = moment;
			this.description = description;
			this.amount = amount;
			this.segment = segment;
		}

		public Revenue() {
			super();
			// TODO Auto-generated constructor stub
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public Instant getMoment() {
			return moment;
		}

		public void setMoment(Instant moment) {
			this.moment = moment;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public Double getAmount() {
			return amount;
		}

		public void setAmount(Double amount) {
			this.amount = amount;
		}

		public Segment getSegment() {
			return segment;
		}

		public void setSegment(Segment segment) {
			this.segment = segment;
		}

		@Override
		public int hashCode() {
			return Objects.hash(id);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Revenue other = (Revenue) obj;
			return Objects.equals(id, other.id);
		}
		
		

}
