package psybergate.phomolo.inv.ass.parser.domain;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import psybergate.phomolo.inv.ass.parser.AddressParser;

public class AddressParserTest {

	private String jsonAddress;
	private AddressParser<Address> addressParser;
	private String[] prettyPrints;

	@Before
	public void initialise() {
		jsonAddress = "addresses.json";
		addressParser = new AddressParser<Address>(jsonAddress);
		prettyPrints = new String[] {
				"Physical Address: {line1=Address 1, line2=Line 2} - City 1 - 1234 - South Africa",
				"Postal Address: null - City 2 - 2345 - Lebanon",
				"Business Address: {line1=Address 3, line2=} - City 3 - 3456 - South Africa",
				"Business Address: {line1=Address 4, line2=} - City 4 - 7890 - Mozambique" };
	}

	@Test
	public void prettyPrintAddressTest() {
		List<Address> addresses = addressParser.getAddresses();

		for (int i = 0; i < prettyPrints.length; i++) {
			Assert.assertEquals(prettyPrints[i], addresses.get(i).prettyPrint());
		}
	}

	@Test
	public void prettyPrintAllAddressesTest() {
		String allPrettyPrintAddresses = "";
		for (int i = 0; i < prettyPrints.length; i++) {
			allPrettyPrintAddresses += prettyPrints[i] + "\n";
		}
		Assert.assertEquals(allPrettyPrintAddresses, addressParser.prettyPrintAddresses());
	}

	@Test
	public void prettyPrintAddressesOFTypeTest() {
		AddressType postalType = new AddressType();
		postalType.setName("Postal Address");

		AddressType physicalType = new AddressType();
		physicalType.setName("Physical Address");

		AddressType bussinessType = new AddressType();
		bussinessType.setName("Business Address");

		String physicalTypes = prettyPrints[0] + "\n";
		String postalTypes = prettyPrints[1] + "\n";
		String businessTypes = prettyPrints[2] + "\n" + prettyPrints[3] + "\n";

		Assert.assertEquals(physicalTypes, addressParser.printAddressOfProvidedType(physicalType));
		Assert.assertEquals(postalTypes, addressParser.printAddressOfProvidedType(postalType));
		Assert.assertEquals(businessTypes, addressParser.printAddressOfProvidedType(bussinessType));
	}

}
