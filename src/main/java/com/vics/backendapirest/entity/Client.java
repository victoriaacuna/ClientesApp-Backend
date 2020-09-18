package com.vics.backendapirest.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name="client")
public class Client implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	@Size(min=4, max=20)
	@Column(nullable = false)
	private String name;
	
	@NotEmpty
	@Size(min=4, max=20)
	@Column(nullable = false)
	private String last_name;
	
	@NotEmpty
	@Email
	@Column(nullable = false, unique = true)
	private String email;
	
	
	@Temporal(TemporalType.DATE)
	private Date create_at;
	
	private String image;
	
	
	// Fetch es que se hace solo la carga cuando se llama al get.
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="region_id")
	// Si no se ignoran estos atributos relacionados al proxy dará error.
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@NotNull
	private Region region;
	
	@JsonIgnoreProperties(value={"client", "hibernateLazyInitializer", "handler"}, allowSetters = true)
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "client", cascade = CascadeType.ALL)
	private List<Bill> bills;
	

	//Esto se hará en el front.
//	@PrePersist
//	public void prePersist() {
//		this.create_at = new Date();
//	}
	
	
	
	public Long getId() {
		return id;
	}
	public Client() {
		this.bills = new ArrayList<>();
		
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getCreate_at() {
		return create_at;
	}
	public void setCreate_at(Date create_at) {
		this.create_at = create_at;
	}
	
	public List<Bill> getBills() {
		return bills;
	}
	public void setBills(List<Bill> bills) {
		this.bills = bills;
	}
	public Region getRegion() {
		return region;
	}
	public void setRegion(Region region) {
		this.region = region;
	}



	private static final long serialVersionUID = 1L;
	
}
