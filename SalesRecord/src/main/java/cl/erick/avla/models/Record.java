package cl.erick.avla.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Entity
@Table(name="historial")
public class Record {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	public String movimiento;
	
	@Column(updatable = false)
	private Date createdAt;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="usuario_id")
    private User user;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="producto_id")
    private Product product;
	
	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}
	
	// Constructors
	
	public Record() {
	}
	
	// Getters and Setters

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMovimiento() {
		return movimiento;
	}

	public void setMovimiento(String movimiento) {
		this.movimiento = movimiento;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Product getProducto() {
		return product;
	}

	public void setProducto(Product producto) {
		this.product = producto;
	}
	
	
}
