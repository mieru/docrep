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
