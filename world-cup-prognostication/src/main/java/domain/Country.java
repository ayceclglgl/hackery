package domain;

public enum Country {
	ENGLAND("England"),
	GERMANY("Germany"),
	NETHERLANDS("Netherlands"),
	DENMARK("Denmark"),
	NORWAY("Norway"),
	SWEDEN("Sweden"),
	FRANCE("France"),
	BELGIUM("Belgium"),
	ICELAND("Iceland"),
	SPAIN("Spain"),
	FINLAND("FINLAND"),
	AUSTRIA("Austria"),
	ITALY("Italy"),
	SWITZERLAND("Switzerland"),
	NORTHERN_IRELAND("Northern Ireland"),
	PORTUGAL("Portugal");

	private final String name;

	Country(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
