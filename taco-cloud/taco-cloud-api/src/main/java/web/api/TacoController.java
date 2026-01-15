package web.api;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tacos.data.TacoRepository;
import tacos.domain.Taco;
import tacos.domain.TacoOrder;

import java.util.Optional;

@RestController//вместо @Controller, чтобы сообщить Spring, что возвращаемые значения всех методов-обработчиков в контроллере
// должны включаться непосредственно в тело ответа
@RequestMapping(path = "/api/tacos", produces = "application/json")
@CrossOrigin(origins="http://tacocloud:8080")
public class TacoController {

    private final TacoRepository tacoRepository;

    public TacoController(TacoRepository repository) {
        this.tacoRepository = repository;
    }

    @GetMapping(params = "recent")
    public Iterable<Taco> getRecentTacos() {
        PageRequest page = PageRequest.of(
                0, 12, Sort.by("createdAt").descending());
        return tacoRepository.findAll(page).getContent();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Taco> getTacoById(@PathVariable("id") Long id) {
        Optional<Taco> optTaco = tacoRepository.findById(id);
        return optTaco
                .map(taco -> new ResponseEntity<>(taco, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @PostMapping(consumes="application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Taco createTaco(@RequestBody Taco taco) {
        return tacoRepository.save(taco);
    }

    //Пример обновления
    @PutMapping(path="/{id}", consumes="application/json")
    public Taco updateTaco(
            @PathVariable("orderId") Long orderId,
            @RequestBody Taco order) {
        order.setId(orderId);
        return tacoRepository.save(order);
    }

    //Пример частичного обновления
    @PatchMapping(path="/{id}", consumes="application/json")
    public ResponseEntity<Object> patchOrder(
            @PathVariable("orderId") Long orderId,
            @RequestBody Taco patch) {
        Optional<Taco> tacoOptional = tacoRepository.findById(orderId);
        if (tacoOptional.isPresent()) {
            Taco taco = tacoOptional.get();
            if (patch.getName() != null) {
                taco.setName(patch.getName());
            }
            if (patch.getIngredients() != null) {
                taco.setIngredients(patch.getIngredients());
            }
            return new ResponseEntity<>(tacoRepository.save(taco), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable("id") Long id) {
        try {
            tacoRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
        }
    }

}
