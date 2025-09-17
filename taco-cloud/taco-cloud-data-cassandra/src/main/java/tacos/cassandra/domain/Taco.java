package tacos.cassandra.domain;

import com.datastax.oss.driver.api.core.uuid.Uuids;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.rest.core.annotation.RestResource;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@RestResource(rel = "tacos", path = "tacos")
@Document
@Table("tacos") // Хранить в таблице "tacos"
public class Taco {

    @PrimaryKeyColumn(type= PrimaryKeyType.PARTITIONED) //Определение ключа раздела
    private UUID id = Uuids.timeBased();

    @NotNull
    @Size(min = 5, message = "Name must be at least 5 characters long")
    private String name;

    /**
     * Ключи кластеризации используются для определения порядка хранения записей в разделе
     * в данном случае в порядке убывания (DESCENDING)
     * */
    @PrimaryKeyColumn(type=PrimaryKeyType.CLUSTERED, ordering= Ordering.DESCENDING) //Определение ключа кластеризации
    private Date createdAt = new Date();

    /**
     * Так как в Cassandra данные сильно денормализованы, то в ingredients не будут содержаться ключи на записи в таблице
     * ingredients, а будут храниться дубли полных данных
     * */
    @Size(min=1, message="You must choose at least 1 ingredient")
    @Column("ingredients") //Отображает список в столбец "ingredients"
    private List<IngredientUDT> ingredients = new ArrayList<>();

    public void addIngredient(Ingredient ingredient) {
        this.ingredients.add(TacoUDRUtils.toIngredientUDT(ingredient));
    }
}