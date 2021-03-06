package ro.teamnet.bootstrap.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

/**
 * A dictionary.
 */
@Entity
@Table(name = "T_DICTIONARY")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Dictionary implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "code", nullable = false, unique = true)
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "has_i18n")
    private Boolean hasI18n;

    @ManyToOne
    private DictionaryCollection collection;

    @ManyToOne
    @JoinColumn(name = "value_type_id", nullable = true)
    private DictionaryElement valueType;

    @OneToMany(mappedBy = "dictionary")
    Collection<DictionaryElement> dictionaryElements;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getHasI18n() {
        return hasI18n;
    }

    public void setHasI18n(Boolean hasI18n) {
        this.hasI18n = hasI18n;
    }

    public DictionaryCollection getCollection() {
        return collection;
    }

    public void setCollection(DictionaryCollection collection) {
        this.collection = collection;
    }

    public DictionaryElement getValueType() {
        return valueType;
    }

    public void setValueType(DictionaryElement valueType) {
        this.valueType = valueType;
    }

    public Collection<DictionaryElement> getDictionaryElements() {
        return dictionaryElements;
    }

    public void setDictionaryElements(Collection<DictionaryElement> dictionaryElements) {
        this.dictionaryElements = dictionaryElements;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Dictionary that = (Dictionary) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Dictionary{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", hasI18n=" + hasI18n +
                ", collection=" + collection +
                ", valueType=" + valueType +
                ", dictionaryElements=" + dictionaryElements +
                '}';
    }
}
