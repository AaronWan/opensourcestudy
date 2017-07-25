import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

@Getter
@Setter
@AllArgsConstructor
public class Address {

    @NotEmpty(message = "would be nice if we had one",groups = GroupA.class,payload = Severity.Info.class)
    private String zipCode;

    @NotEmpty(message = "the city is mandatory",groups = GroupB.class,payload = Severity.Error.class)
    String city;
}