
// let requestString = "https://api.spoonacular.com/recipes/findByIngredients?apiKey=" + spoonacularKey + "&ingredients=apples,+flour,+sugar&number=2";

//Spoonacular
let requestString = "https://api.spoonacular.com/food/ingredients/search?apiKey=" + spoonacularKey + "&query=bread&number=30";

$.ajax({
    url: requestString,
    type: "GET"
}).done(function(data) {
    console.log(data);

    $("#test-col").html("");
    data.results.forEach(function (item, index) {
        if (index < 5) {
            console.log(item);

            var breadTest = `
                    <div class="column" id="test-col">
                        <div class="card" style="width: 18rem;">
                        <ul class="list-group list-group-flush">

                            <img src="https://spoonacular.com/cdn/ingredients_100x100/${item.image}">
                            <li class="list-group-item">Name: ${item.name}</li>
                            <li class="list-group-item">Id: ${item.id}</li>
                        </ul>
                    </div>`
            $("#test-col").append(breadTest);
        }
    })
});

// Cocktail DB
$.ajax({
    url: "https://www.thecocktaildb.com/api/json/v1/1/search.php?s=margarita",
    type: "GET"
}).done(function(data) {
    console.log(data);
});

// Mapbox





