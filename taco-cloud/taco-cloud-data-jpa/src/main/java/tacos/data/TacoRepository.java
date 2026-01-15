package tacos.data;

import org.springframework.data.repository.PagingAndSortingRepository;
import tacos.domain.Taco;

//@Repository
public interface TacoRepository extends PagingAndSortingRepository<Taco, Long> {
}