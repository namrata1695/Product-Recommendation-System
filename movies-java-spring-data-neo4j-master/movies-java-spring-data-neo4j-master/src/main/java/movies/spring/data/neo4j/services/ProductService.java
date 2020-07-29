package movies.spring.data.neo4j.services;

import java.util.*;

import movies.spring.data.neo4j.domain.Movie;
import movies.spring.data.neo4j.domain.Product;
import movies.spring.data.neo4j.domain.Role;
import movies.spring.data.neo4j.repositories.MovieRepository;
import movies.spring.data.neo4j.repositories.ProductRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    private final static Logger LOG = LoggerFactory.getLogger(ProductService.class);

	private final ProductRepository productRepository;
	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	private Map<String, Object> toD3Format(Collection<Product> products) {
		List<Map<String, Object>> nodes = new ArrayList<>();
		List<Map<String, Object>> rels = new ArrayList<>();
		int i = 0;
		Iterator<Product> result = products.iterator();
		while (result.hasNext()) {
			Product product = result.next();
			nodes.add(map("title", product.getPrice(), "label", "stationary"));
			int target = i;
			i++;
			for (Role role : product.getRoles()) {
				Map<String, Object> actor = map("title", role.getPerson().getName(), "label", "actor");
				int source = nodes.indexOf(actor);
				if (source == -1) {
					nodes.add(actor);
					source = i++;
				}
				rels.add(map("source", source, "target", target));
			}
		}
		return map("nodes", nodes, "links", rels);
	}

	private Map<String, Object> map(String key1, Object value1, String key2, Object value2) {
		Map<String, Object> result = new HashMap<String, Object>(2);
		result.put(key1, value1);
		result.put(key2, value2);
		return result;
	}

    @Transactional(readOnly = true)
    public Product findByTitle(String title) {
    	Product result = productRepository.findByTitle(title);
        return result;
    }

    @Transactional(readOnly = true)
    public Collection<Product> findByTitleLike(String title) {
        Collection<Product> result = productRepository.findByTitleLike(title);
        return result;
    }

	@Transactional(readOnly = true)
	public Map<String, Object>  graph(int limit) {
		Collection<Product> result = productRepository.graph(limit);
		return toD3Format(result);
	}
}
