package com.thyme.mythyme.models;

import lombok.*;

//@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SpoonacularIngredient {

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private Double quantity;

    @Getter
    @Setter
    private String notes;

}
