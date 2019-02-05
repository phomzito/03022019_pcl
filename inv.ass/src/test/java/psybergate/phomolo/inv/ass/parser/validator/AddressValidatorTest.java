package psybergate.phomolo.inv.ass.parser.validator;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.junit.Assert;
import org.junit.Test;

import psybergate.phomolo.inv.ass.parser.AddressParser;
import psybergate.phomolo.inv.ass.parser.domain.Address;
import psybergate.phomolo.inv.ass.parser.domain.Country;

public class AddressValidatorTest {

	@Test
	public void testInvalidAddressPostalCode() {
		DomainValidator<Address> addressValidator = new DomainValidator<>();

		String name = "South Africa";
		String code = "ZA";
		String postalCode = "123";
		String value = "Address1";
		Address address = new Address();

		Country country = new Country();
		country.setName(name);
		country.setCode(code);

		address.setCountry(country);
		address.setPostalCode(postalCode);

		HashMap<String, String> addressLineDetail = new HashMap<>();
		addressLineDetail.put("line1", value);

		address.setAddressLineDetail(addressLineDetail);

		Set<ConstraintViolation<Address>> violations = addressValidator.violations(address);

		Assert.assertEquals(1, violations.size());
		Assert.assertEquals(violations.iterator().next().getMessage(), "Postcode must be 4 numeric characters.");
	}

	@Test
	public void InvalidAddressCountryTest() {
		DomainValidator<Address> addressValidator = new DomainValidator<>();

		String postalCode = "1234";
		String value = "Address1";
		Address address = new Address();

		Country country = null;

		address.setCountry(country);
		address.setPostalCode(postalCode);

		HashMap<String, String> addressLineDetail = new HashMap<>();
		addressLineDetail.put("line1", value);

		address.setAddressLineDetail(addressLineDetail);

		Set<ConstraintViolation<Address>> violations = addressValidator.violations(address);

		Assert.assertEquals(1, violations.size());
		Assert.assertEquals(violations.iterator().next().getMessage(), "Country not included.");
	}

	@Test
	public void jsonValidationTest() {
		String jsonAddress = "addresses.json";
		AddressParser<Address> addressParser = new AddressParser<Address>(jsonAddress);

		List<Address> addresses = addressParser.getAddresses();

		DomainValidator<Address> addressValidator = new DomainValidator<>();

		Set<ConstraintViolation<Address>> violations = addressValidator.violations(addresses.get(0));
		Assert.assertEquals(0, violations.size());

		violations = addressValidator.violations(addresses.get(1));
		Assert.assertEquals(1, violations.size());

		ConstraintViolation<Address> violation = violations.iterator().next();

		Assert.assertEquals("Address lines must not be empty", violation.getMessage());

		violations = addressValidator.violations(addresses.get(2));
		Assert.assertEquals(0, violations.size());

		violations = addressValidator.violations(addresses.get(3));
		Assert.assertEquals(0, violations.size());
	}

	@Test
	public void jsonValidationViolationResultsTest() {
		String jsonAddress = "addresses.json";
		AddressParser<Address> addressParser = new AddressParser<Address>(jsonAddress);

		List<Address> addresses = addressParser.getAddresses();

		DomainValidator<Address> addressValidator = new DomainValidator<>();

		String domainViolationsPrint = addressValidator.domainViolationsPrint(addresses);
		Assert.assertEquals("Address - id - 2\n\taddressLineDetail: Address lines must not be empty\n",
				domainViolationsPrint);
	}
}
