package ir.bigz.ms.searchfilter.person.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import ir.bigz.ms.searchfilter.common.model.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AttributeOverride(name = "id", column = @Column(name = "person_id"))
@Access(AccessType.FIELD)
public class Person extends BaseEntity<Long> {

    @NotNull(message = "firstname must be fill")
    protected String firstName;
    @NotNull(message = "lastname must be fill")
    protected String lastName;
    protected Integer age;
    @NotNull
    @Size(max = 10)
    protected String nationalCode;

    @Size(max = 11)
    protected String mobile;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    @JsonManagedReference
    protected Address address;

}
