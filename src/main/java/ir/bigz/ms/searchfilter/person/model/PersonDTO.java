package ir.bigz.ms.searchfilter.person.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
@Getter
@Setter
public class PersonDTO {

    protected String firstName;
    protected String lastName;
    protected Integer age;
    protected String nationalCode;
    protected String mobile;

    @JsonProperty("address")
    protected AddressDTO addressDTO;
    protected Long id;
    protected Timestamp creationDate;
    protected Timestamp deletionDate;
    protected boolean deleted;

    public PersonDTO() {
    }
}
