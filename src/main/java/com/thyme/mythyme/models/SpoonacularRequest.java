package com.thyme.mythyme.models;

import lombok.*;

import java.util.List;

//@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SpoonacularRequest {

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private List<SpoonacularIngredient> ingredients;

}
