package psybergate.phomolo.inv.ass.parser.domain;

import java.time.LocalDateTime;
import java.util.Map;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import psybergate.phomolo.inv.ass.parser.serializer.ISOLocalDateTimeSerializer;

public class Address extends IdentifiableEntity implements PrettyPrintObject {

	private AddressType type;

	private String cityOrTown;

	@NotEmpty(message="Address lines must not be empty")
	private Map<String, @NotNull String> addressLineDetail;

	private ProvinceOrState provinceOrState;

	@NotNull(message="Country not included.")
	private Country country;

	@NotBlank
	@Pattern(regexp = "\\d{4}", message = "Postcode must be 4 numeric characters.")
	@JsonProperty(required = true)
	private String postalCode;

	private String suburbOrDistrict;

	@JsonDeserialize(using = ISOLocalDateTimeSerializer.class)
	private LocalDateTime lastUpdated;

	public AddressType getType() {
		return type;
	}

	public void setType(AddressType type) {
		this.type = type;
	}

	public String getCityOrTown() {
		return cityOrTown;
	}

	public void setCityOrTown(String cityOrTown) {
		this.cityOrTown = cityOrTown;
	}

	public Map<String, String> getAddressLineDetail() {
		return addressLineDetail;
	}

	public void setAddressLineDetail(Map<String, String> addressLineDetail) {
		this.addressLineDetail = addressLineDetail;
	}

	public ProvinceOrState getProvinceOrState() {
		return provinceOrState;
	}

	public void setProvinceOrState(ProvinceOrState provinceOrState) {
		this.provinceOrState = provinceOrState;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public LocalDateTime getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(LocalDateTime lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public String getSuburbOrDistrict() {
		return suburbOrDistrict;
	}

	public void setSuburbOrDistrict(String suburbOrDistrict) {
		this.suburbOrDistrict = suburbOrDistrict;
	}

	public String prettyPrint() {
		return getType().getName() + ": " + getAddressLineDetail() + " - " + getCityOrTown() + " - " + getPostalCode()
				+ " - " + getCountry().getName();
	}

}
