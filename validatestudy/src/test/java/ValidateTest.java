import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import java.util.Set;

/**
 * Created by Aaron on 25/07/2017.
 */
public class ValidateTest {
    @Test
    public void testValidate(){
        Address address = new Address("", "");
        Set<ConstraintViolation<Address>> set = Validation.buildDefaultValidatorFactory().getValidator().validate(address,GroupA.class,GroupB.class);
        set.forEach(item->{
            System.out.println(item.getMessage());
        });
        System.out.println("----------");
        address = new Address("", "");
        set = Validation.buildDefaultValidatorFactory().getValidator().validate(address,GroupB.class,GroupA.class);
        set.forEach(item->{
            System.out.println(item.getConstraintDescriptor().getPayload());
        });

        System.out.println("----------");
        address = new Address("", "");
        set = Validation.buildDefaultValidatorFactory().getValidator().validateProperty(address,"zipCode",GroupB.class,GroupA.class);
        set.forEach(item->{
            System.out.println(item.getMessage());
        });


        System.out.println("----------");
    }
}
