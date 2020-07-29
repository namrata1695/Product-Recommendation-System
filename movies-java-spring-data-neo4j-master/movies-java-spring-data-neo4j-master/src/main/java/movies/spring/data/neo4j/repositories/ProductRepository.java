package movies.spring.data.neo4j.repositories;

import java.util.Collection;

import movies.spring.data.neo4j.domain.Movie;
import movies.spring.data.neo4j.domain.Product;

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
public interface ProductRepository extends Neo4jRepository<Movie, Long> {

	Product findByTitle(@Param("title") String title);

	Collection<Product> findByTitleLike(@Param("title") String title);

    @Query("MATCH (m:Customer)<-[r:Search_FOR]-(a:Notebooks) RETURN m,r,a LIMIT $limit")
	Collection<Product> graph(@Param("limit") int limit);
}