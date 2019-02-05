package psybergate.phomolo.inv.ass.parser;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import psybergate.phomolo.inv.ass.parser.domain.Address;
import psybergate.phomolo.inv.ass.parser.domain.AddressType;

public class AddressParser<A extends Address> {
	private String fileLocation;

	private List<A> addresses;

	public AddressParser(String fileLocation) {
		this.fileLocation = fileLocation;
	}

	public AddressParser() {
		this("addresses.json");
	}

	public String prettyPrint(A t) {
		return t.prettyPrint();
	}

	public String prettyPrintAddresses() {
		return prettyPrintAddresses(getAddresses());
	}
	
	private String prettyPrintAddresses(Collection<A> addresses) {
		String prettyPrint = "";
		for (A t : addresses) {
			prettyPrint += prettyPrint(t) + "\n";
		}
		return prettyPrint;
	}

	public String getFileLocation() {
		return fileLocation;
	}

	@SuppressWarnings("unchecked")
	public List<A> getAddresses() {
		if (addresses == null) {
			ObjectMapper mapper = new ObjectMapper();
			ClassLoader classLoader = getClass().getClassLoader();
			File jsonFile = new File(classLoader.getResource(getFileLocation()).getFile());
			try {
				addresses = (List<A>) Arrays.asList(mapper.readValue(jsonFile, Address[].class));
				return addresses;
			} catch (JsonParseException e) {
				throw new RuntimeException(e);
			} catch (JsonMappingException e) {
				throw new RuntimeException(e);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		} else {
			return addresses;
		}
	}

	private Set<A> addressesOfProvidedType(AddressType type) {
		Set<A> addresses = new HashSet<A>();

		for (A t : getAddresses()) {
			if (t.getType().getName().trim().equals(type.getName())) {
				addresses.add(t);
			}
		}

		return addresses;
	}

	public String printAddressOfProvidedType(AddressType type){
		Set<A> addressesOfProvidedType = addressesOfProvidedType(type);
		return prettyPrintAddresses(addressesOfProvidedType);
	}
}
