package movies.spring.data.neo4j.domain;

import java.util.ArrayList;
import java.util.List;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
public class Customer
{
	@Id
    @GeneratedValue
	private Long id;
	private String Name;
	private int born;

	@Relationship(type = "SEARCHED_FOR")
	private List<Stationary> stationaries = new ArrayList<>();

	public Customer() {
	}

	public Customer(String name, int born) {
		this.Name = name;
		this.born = born;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return Name;
	}

	public List<Stationary> getStationary() {
		return stationaries;
	}
}
