package com.educandoweb.course.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_shipment")
public class Shipment implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String trackingCode;
	private Instant postedAt;
	private String shipmentImageUrl;
	private String notes;
	
	@ManyToOne
	@JoinColumn(name = "carrier_id")
	private Carrier carrier;
	
	@JsonBackReference
	@OneToOne
	@JoinColumn(name = "order_id")
	private Order order;
	
	public Shipment(Long id, Carrier carrier, String trackingCode, Instant postedAt, String shipmentImageUrl,
			String notes) {
		super();
		this.id = id;
		this.carrier = carrier;
		this.trackingCode = trackingCode;
		this.postedAt = postedAt;
		this.shipmentImageUrl = shipmentImageUrl;
		this.notes = notes;
	}
	
	public Shipment() {
		super();
		
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Carrier getCarrier() {
		return carrier;
	}

	public void setCarrier(Carrier carrier) {
		this.carrier = carrier;
	}

	public String getTrackingCode() {
		return trackingCode;
	}

	public void setTrackingCode(String trackingCode) {
		this.trackingCode = trackingCode;
	}

	public Instant getPostedAt() {
		return postedAt;
	}

	public void setPostedAt(Instant postedAt) {
		this.postedAt = postedAt;
	}

	public String getShipmentImageUrl() {
		return shipmentImageUrl;
	}

	public void setShipmentImageUrl(String shipmentImageUrl) {
		this.shipmentImageUrl = shipmentImageUrl;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	
	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, trackingCode);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Shipment other = (Shipment) obj;
		return Objects.equals(id, other.id) && Objects.equals(trackingCode, other.trackingCode);
	}

	
}
