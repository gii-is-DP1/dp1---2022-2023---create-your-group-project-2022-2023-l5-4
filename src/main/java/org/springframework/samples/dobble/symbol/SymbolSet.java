package org.springframework.samples.dobble.symbol;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.samples.dobble.model.NamedEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name="symbolsets")
public class SymbolSet extends NamedEntity{
    
    @NotNull
    private String path;

    @OneToMany(mappedBy = "symbolSet")
    @Size(min=57,max=57)
    private List<SymbolVariant> symbolVariants;

}
