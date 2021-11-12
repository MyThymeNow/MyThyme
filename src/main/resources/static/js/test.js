
// let requestString = "https://api.spoonacular.com/recipes/findByIngredients?apiKey=" + spoonacularKey + "&ingredients=apples,+flour,+sugar&number=2";

//Spoonacular
// let requestString = "https://api.spoonacular.com/food/ingredients/search?apiKey=" + spoonacularKey + "&query=bread&number=30";
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
                  <div className="card" style="width: 18rem;"><!-- class="bg-transparent" -->
        <div className="card-body" style="border: #333333 2px solid; background-image: linear-gradient(180deg, #f1b24a, #9DC88D)">
            <h5 className="card-title" style="border: #164A41; text-align: center">${data.recipes[i].title}</h5> 
            <img className="centerImg" src="${data.recipes[i].image}"
                 style="width: 8rem; length: 8rem; border: #9DC88D 2px solid">  
            <p className="card-text scroll">
                ${data.recipes[i].summary} </p> 
<!--            <a href="#" className="btn" style="color: white; background-color: #f1b24a">View recipe</a> &lt;!&ndash; background-color: #f1b24a&ndash;&gt;-->
        </div>
    </div>`
    }
    $("#test-col").html(html);






    //                 <div class="column" id="test-col">
    //                     <div class="card" style="width: 18rem;">
    //                     <ul class="list-group list-group-flush">
    //
    //                         <img src="https://spoonacular.com/cdn/ingredients_100x100/${item.image}">
    //                         <li class="list-group-item">Name: ${item.name}</li>
    //
    //                     </ul>
    //                 </div>`
    //         $("#test-col").append(breadTest);
    //     }
    // })
});

// Cocktail DB
// $.ajax({
//     url: "https://www.thecocktaildb.com/api/json/v1/1/search.php?s=margarita",
//     type: "GET"
// }).done(function(data) {
//     console.log(data);
// });

// Mapbox





