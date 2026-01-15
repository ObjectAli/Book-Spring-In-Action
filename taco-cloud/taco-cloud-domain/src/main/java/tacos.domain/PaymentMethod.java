package tacos.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor(force=true, access=AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class PaymentMethod {

    @Id
    private String id;

    private final User user;
    private final String ccNumber;
    private final String ccCVV;
    private final String ccExpiration;

}