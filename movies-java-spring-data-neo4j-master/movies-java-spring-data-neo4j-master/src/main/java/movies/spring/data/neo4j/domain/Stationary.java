package movies.spring.data.neo4j.domain;

import java.util.ArrayList;
import java.util.List;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Namrata Ajmeri
 */
@NodeEntity
public class Stationary 
{
	@Id
	@GeneratedValue
	private Long id;
	private String CompanyName;
	private String Price;

	@JsonIgnoreProperties("stationaries")
	@Relationship(type = "SEARCHED_FOR", direction = Relationship.INCOMING)
	private List<Rec> recs;

	public Stationary() {
	}

	public Stationary(String CompanyName, String Price) {
		this.Price = Price;
		this.CompanyName = CompanyName;
	}

	public Long getId() {
		return id;
	}
	public String getPrice()
	{
		return Price;
	}
	public String getCompanyName() {
		return CompanyName;
	}

	public List<Rec> getRecs() {
		return recs;
	}

}
