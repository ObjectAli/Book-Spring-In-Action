package tacos.cassandra.data;

import org.springframework.data.repository.CrudRepository;
import tacos.cassandra.domain.TacoOrder;

import java.util.UUID;

public interface OrderRepository
        extends CrudRepository<TacoOrder, UUID> {
}