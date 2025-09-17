package taco.cloud.pojo;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Taco {

    private Long id;
    private Date createdAt = new Date();
    private String name;
    private List<Ingredient> ingredients;
}