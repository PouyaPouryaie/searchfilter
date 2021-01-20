package ir.bigz.ms.searchfilter.person.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import ir.bigz.ms.searchfilter.common.annotation.NationalCode;
import ir.bigz.ms.searchfilter.common.model.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AttributeOverride(name = "id", column = @Column(name = "person_id"))
@Access(AccessType.FIELD)
public class Person extends BaseEntity<Long> {

    @NotEmpty(message = "مقدار نام به درستی قرار داده نشده است")
    protected String firstName;
    @NotEmpty
    protected String lastName;
    protected Integer age;
    @NotNull
    @Size(min = 10)
    @NationalCode
    protected String nationalCode;

    @Pattern(regexp = "^(09|989)\\d{9}$")
    protected String mobile;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    @JsonManagedReference
    protected Address address;

}
