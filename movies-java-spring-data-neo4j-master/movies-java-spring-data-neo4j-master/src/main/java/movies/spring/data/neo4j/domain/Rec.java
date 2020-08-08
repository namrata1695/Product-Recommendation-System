package movies.spring.data.neo4j.domain;

import java.util.ArrayList;
import java.util.List;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

/**
 * @author Namrata Ajmeri
 */
@RelationshipEntity(type = "SEARCHED_FOR")
public class Rec {
	@Id
    @GeneratedValue
	private Long id;
	private List<String> recs = new ArrayList<>();

	@StartNode
	private Customer customer;

	@EndNode
	private Stationary stationary;

	public Rec() {
	}

	public Rec(Stationary stationary, Customer customer) {
		this.stationary = stationary;
		this.customer = customer;
	}

	public Long getId() {
	    return id;
	}

	public List<String> getRecs() {
	    return recs;
	}

	public Customer getCustomer() {
	    return customer;
	}

	public Stationary getStationary() {
	    return stationary;
	}

    public void addRoleName(String name) {
        if (this.recs == null) {
            this.recs = new ArrayList<>();
        }
        this.recs.add(name);
    }
}
