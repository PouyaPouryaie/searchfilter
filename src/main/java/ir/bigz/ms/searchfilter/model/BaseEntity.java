package ir.bigz.ms.searchfilter.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.sql.Timestamp;

@MappedSuperclass
public abstract class BaseEntity<T extends Number> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected T id;
    protected Timestamp creationDate = new Timestamp(System.currentTimeMillis());
    protected Timestamp deletionDate;
    protected boolean deleted = false;

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted){
        if(deleted){
            this.deletionDate = new Timestamp(System.currentTimeMillis());
        }
        this.deleted = deleted;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public Timestamp getDeletionDate() {
        return deletionDate;
    }

    public void setDeletionDate(Timestamp deletionDate) {
        this.deletionDate = deletionDate;
    }

    public T getId() {
        return id;
    }

    public void setId(T id) {
        this.id = id;
    }
}
