package psybergate.phomolo.inv.ass.parser.validator;

import java.util.Collection;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import psybergate.phomolo.inv.ass.parser.domain.IdentifiableEntity;

public class DomainValidator<T extends IdentifiableEntity> {

	private Validator validator;

	public DomainValidator() {
		this.validator = Validation.buildDefaultValidatorFactory().getValidator();
	}

	public Set<ConstraintViolation<T>> violations(T t) {
		return validator.validate(t);
	}

	public String domainViolationsPrint(T t) {
		String invalidValidationFields = "";
		Set<ConstraintViolation<T>> validate = validator.validate(t);
		if (validate.size() > 0) {
			invalidValidationFields = t.getClass().getSimpleName() + " - id - " + t.getId() + "\n";
			for (ConstraintViolation<T> constraintViolation : validate) {
				invalidValidationFields += "\t" + constraintViolation.getPropertyPath() + ": "
						+ constraintViolation.getMessage() + "\n";
			} 
		}
		return invalidValidationFields;
	}
	
	public String domainViolationsPrint(Collection<T> domainObjects){
		String violationsResults = "";
		for (T domainObject : domainObjects) {
			violationsResults += domainViolationsPrint(domainObject);
		}
		return violationsResults;
	}
}
