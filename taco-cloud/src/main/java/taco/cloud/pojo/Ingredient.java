package taco.cloud.pojo;

import lombok.Data;

@Data
public class Ingredient {

    private final String id;
    private final String name;
    private final Type type;

    //Если IDEA будет ругаться, что нет конструктора, значит не установлен плагин Lombok
    public enum Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }

}
