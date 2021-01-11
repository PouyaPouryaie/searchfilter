package ir.bigz.ms.searchfilter.person.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;
import java.sql.Timestamp;

@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class AddressDTO {

    @JsonProperty("id")
    protected Long id;
    @JsonProperty("creationDate")
    protected Timestamp creationDate;
    @JsonProperty("deletionDate")
    protected Timestamp deletionDate;
    @JsonProperty("deleted")
    protected boolean deleted;
    @JsonProperty("addressOne")
    protected String addressOne;
    @JsonProperty("addressTwo")
    protected String addressTwo;
    @JsonProperty("postalCode")
    protected String postalCode;
    @JsonProperty("person")
    protected PersonDTO personDTO;
}
