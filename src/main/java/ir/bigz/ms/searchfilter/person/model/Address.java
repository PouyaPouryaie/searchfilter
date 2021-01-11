package ir.bigz.ms.searchfilter.person.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import ir.bigz.ms.searchfilter.common.model.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@AttributeOverride(name = "id", column = @Column(name = "address_id"))
public class Address  extends BaseEntity<Long> {

    protected String addressOne;
    protected String addressTwo;
    protected String postalCode;

    @OneToOne(mappedBy = "address", fetch = FetchType.LAZY)
    @JsonBackReference
    protected Person person;
}
