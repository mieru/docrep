/**
 * $RCSfile: ValidatorComponent.java,v $
 * $Revision: 1.3 $
 * $Date: 2017/11/29 12:22:19 $
 * $Source: /CVS/java/mu/src/pl/com/altar/mu/springapp/component/ValidatorComponent.java,v $
 * $Author: grzesf $
 */
package docrep.component;

import java.util.Set;

import javax.validation.*;

import org.springframework.stereotype.Component;

@Component
public class ValidatorComponent {

  public <T> void valid(T validObject) throws Exception {
    Set<ConstraintViolation<T>> violations = createValidator().validate(validObject);
    StringBuilder bufor = new StringBuilder();
    boolean error = !violations.isEmpty();
    if (error) {
      for (ConstraintViolation<T> cv : violations) {
        bufor.append(cv.getPropertyPath()).append(": ").append(cv.getMessage()).append(System.lineSeparator());
      }
      throw new ValidationException(bufor.toString());
    }
  }
  
  private Validator createValidator() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    return factory.getValidator();
  }

}
