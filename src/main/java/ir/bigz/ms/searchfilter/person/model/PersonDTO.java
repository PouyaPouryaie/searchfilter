package ir.bigz.ms.searchfilter.person.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.sql.Timestamp;

@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
@Getter
@Setter
public class PersonDTO {

    public Long id;
    public Timestamp creationDate;
    public Timestamp deletionDate;
    public boolean deleted;
    public String firstName;
    public String lastName;
    public Integer age;
    public String nationalCode;
    public String mobile;

    @JsonProperty("address")
    public AddressDTO addressDTO;

    public String addressOne;
    public String postalCode;

    public PersonDTO() {
    }

    public PersonDTO(Long id,
                     String firstName,
                     String lastName,
                     Integer age,
                     String nationalCode,
                     String mobile,
                     String addressOne,
                     String postalCode) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.nationalCode = nationalCode;
        this.mobile = mobile;
        this.addressOne = addressOne;
        this.postalCode = postalCode;
    }
}
