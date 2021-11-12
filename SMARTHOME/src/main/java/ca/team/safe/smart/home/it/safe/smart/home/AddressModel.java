package ca.team.safe.smart.home.it.safe.smart.home;

public class AddressModel {
    String streetAddress , city ,provinces, postalcode,country;

    public AddressModel(String streetAddress, String city,
                        String provinces, String postalcode, String country) {
        this.streetAddress = streetAddress;
        this.city = city;
        this.provinces = provinces;
        this.postalcode = postalcode;
        this.country = country;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvinces() {
        return provinces;
    }

    public void setProvinces(String provinces) {
        this.provinces = provinces;
    }

    public String getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
