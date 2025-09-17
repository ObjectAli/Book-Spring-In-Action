package tacos.cassandra.data;

import org.springframework.data.repository.CrudRepository;
import tacos.cassandra.domain.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {
}