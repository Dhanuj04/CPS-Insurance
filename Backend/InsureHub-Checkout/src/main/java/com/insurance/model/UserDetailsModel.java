package com.insurance.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class UserDetailsModel
{
    @Id
    @GeneratedValue
    private Long userDetailsId;
    private String userName;
    private Integer age;
    private Boolean isTobaccoConsumer;
    private Boolean doesUserDrink;
    private String nomineeName;
    private Integer nomineeAge;
    private String nomineeRelation;

}
