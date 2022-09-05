package test.testShop.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Movie")
@Getter @Setter
public class Movie extends Item {

    private String director;
    private String actor;
}
