package movies.spring.data.neo4j.repositories;

import java.util.Collection;

import movies.spring.data.neo4j.domain.Product;
import movies.spring.data.neo4j.domain.Stationary;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * @author Michael Hunger
 * @author Mark Angrish
 * @author Michael J. Simons
 */
@RepositoryRestResource(collectionResourceRel = "products", path = "products")
public interface ProductRepository extends Neo4jRepository<Stationary, Long> {
/*
	Stationary findByTitle(@Param("title") String title);

	Collection<Stationary> findByTitleLike(@Param("title") String title);

    @Query("MATCH (m:Customer)<-[r:Search_FOR]-(a:Notebooks) RETURN m,r,a LIMIT $limit")
	Collection<Stationary> graph(@Param("limit") int limit);
    */
    @Query("MATCH (m:Stationary) RETURN m LIMIT $limit")
	Collection<Stationary> allStationaries(@Param("limit") int limit);
   
    @Query("CALL {MATCH (n:Stationary)<-[r:SEARCHED_FOR]-(a:Customer) WHERE n.CompanyName = $productName RETURN a} MATCH (a)-[:SEARCHED_FOR]->(c:Stationary) RETURN c")
	Collection<Stationary> getPeopleAlsoSearchedFor(@Param("productName") String productName);
    
    
    
}