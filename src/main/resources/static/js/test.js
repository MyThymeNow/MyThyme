
// let requestString = "https://api.spoonacular.com/recipes/findByIngredients?apiKey=" + spoonacularKey + "&ingredients=apples,+flour,+sugar&number=2";

//Spoonacular
let searchFood = "https://api.spoonacular.com/food/ingredients/search?apiKey="+spoonacularKey+"&number=10&query=";
let requestString = "https://api.spoonacular.com/recipes/random?apiKey=" + spoonacularKey + "&number=3";
// let requestCategories = "https://api.spoonacular.com/food/ingredients/9266/information?apiKey=" + spoonacularKey + "&amount=1";


$.ajax({
    url: requestString,
    type: "GET"
}).done(function(data) {
    console.log(data);

    let html = "";
    for (let i = 0; i < 3; i++) {

        html += `
                  <div class="card bg-transparent" style="width: 18rem">
                    <div class="card-body">
                      <h5 class="card-title">${data.recipes[i].title}</h5>
                      <img src="${data.recipes[i].image}">
                      <p class="card-text">${data.recipes[i].summary}</p>
                      <a href="#" class="btn btn-primary">View recipe</a>
                   
                    </div>
                  </div>`;
    }
    $("#test-col").html(html);

});

// Search Food

$.ajax({
    url: searchFood,
    type: "GET"
}).done(function(data) {
    console.log(data);

});



// Cocktail DB
// $.ajax({
//     url: "https://www.thecocktaildb.com/api/json/v1/1/search.php?s=margarita",
//     type: "GET"
// }).done(function(data) {
//     console.log(data);
// });

// Mapbox





