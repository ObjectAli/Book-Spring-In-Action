package tacos.cassandra.domain;

import org.springframework.data.cassandra.core.mapping.UserDefinedType;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;


/**
 * UDT - User-Defined Type – определяемый пользователем тип
 * Пользовательский тип, используемый в Cassandra как аналог внешних ключей
 * Соответственно, столбец ingredients в таблице tacos будет хранить фактические данные из таблицы ingredients
 * */
@Data
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@UserDefinedType("ingredient")
public class IngredientUDT {

    private final String name;

    private final Ingredient.Type type;
}