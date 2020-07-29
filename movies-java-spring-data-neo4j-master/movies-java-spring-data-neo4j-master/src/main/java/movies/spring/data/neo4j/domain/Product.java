package movies.spring.data.neo4j.domain;

import java.util.ArrayList;
import java.util.List;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Mark Angrish
 */
@NodeEntity
public class Product {

	@Id
	@GeneratedValue
	private Long id;
	private double price;
	private String companyName;

	@JsonIgnoreProperties("stationary")
	@Relationship(type = "Search_FOR", direction = Relationship.INCOMING)
	private List<Role> roles;

	public Product() {
	}

	public Product(Long id, double price, String companyName) {
		this.id = id;
		this.price = price;
		this.companyName = companyName;
	}

	public Long getId() {
		return id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public double getPrice() {
		return price;
	}

	public List<Role> getRoles() {
		return roles;
	}
	
	public void addRole(Role role) {
		if (this.roles == null) {
			this.roles = new ArrayList<>();
		}
		this.roles.add(role);
	}
}