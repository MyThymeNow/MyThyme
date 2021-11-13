//
// // let requestString = "https://api.spoonacular.com/recipes/findByIngredients?apiKey=" + spoonacularKey + "&ingredients=apples,+flour,+sugar&number=2";
//
// //Spoonacular
// // let requestString = "https://api.spoonacular.com/food/ingredients/search?apiKey=" + spoonacularKey + "&query=bread&number=30";
// let requestString = "https://api.spoonacular.com/recipes/random?apiKey=" + spoonacularKey + "&number=3";
// // let requestCategories = "https://api.spoonacular.com/food/ingredients/9266/information?apiKey=" + spoonacularKey + "&amount=1";
//
//Spoonacular
// let requestString = "https://api.spoonacular.com/food/ingredients/search?apiKey=" + spoonacularKey + "&query=bread&number=30";
// let requestString = "https://api.spoonacular.com/recipes/random?apiKey=" + spoonacularKey + "&number=3";
// let requestCategories = "https://api.spoonacular.com/food/ingredients/9266/information?apiKey=" + spoonacularKey + "&amount=1";

$.ajax({
    url: requestString,
    type: "GET"
}).done(function(data) {
    console.log(data);

    let html = "";
    for (let i = 0; i < 3; i++) {

        html += `
                  <div class="card" style="width: 18rem"><!-- class="bg-transparent" -->
                    <div class="card-body" style="background-color: #9DC88D">
                      <h5 class="card-title text-light font-weight-bold" style="border: #164A41; text-align: center">${data.recipes[i].title}</h5>
                      <img class="centerImg shadow bg-white rounded" src="${data.recipes[i].image}" style="width: 8rem; length: 8rem; border: #9DC88D 2px solid">
                      <br>
                      <p class="card-text scroll shadow p-3 mb-5 rounded" style="background-color: #ffffff; color: #164A41">${data.recipes[i].summary}</p>
                      <a href="#" class="btn centerImg shadow p-3 mb-2 mbr-2 rounded" style="color: white; background-color: #f1b24a">View recipe</a>

                    </div>
                  </div>`;

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
//     url: requestString,
//     type: "GET"
// }).done(function(data) {
//     console.log(data);
//
//     let html = "";
//     for (let i = 0; i < 3; i++) {
//
//         html += `
//                   <div class="card" style="width: 18rem"><!-- class="bg-transparent" -->
//                     <div class="card-body" style="border: #333333 2px solid; background-image: linear-gradient(180deg, #f1b24a, #9DC88D)">
//                       <h5 class="card-title" style="border: #164A41; text-align: center">${data.recipes[i].title}</h5>
//                       <img class="centerImg" src="${data.recipes[i].image}" style="width: 8rem; length: 8rem; border: #9DC88D 2px solid">
//                       <br>
//                       <p class="card-text scroll" style="border: #9DC88D 2px solid">${data.recipes[i].summary}</p>
//                       <a href="#" class="btn centerImg" style="color: white; background-color: #f1b24a">View recipe</a>
//
//                     </div>
//                   </div>`;
//     }
//     $("#test-col").html(html);
//
//
//
//
//
//     // $("#test-col").html("");
//     // data.results.forEach(function (item, index) {
//     //     if (index < 3) {
//     //         console.log(item);
//     //
//     //         var breadTest = `
//     //                 <div class="column" id="test-col">
//     //                     <div class="card" style="width: 18rem;">
//     //                     <ul class="list-group list-group-flush">
//     //
//     //                         <img src="https://spoonacular.com/cdn/ingredients_100x100/${item.image}">
//     //                         <li class="list-group-item">Name: ${item.name}</li>
//     //
//     //                     </ul>
//     //                 </div>`
//     //         $("#test-col").append(breadTest);
//     //     }
//     // })
// });
//
// // Cocktail DB
// // $.ajax({
// //     url: "https://www.thecocktaildb.com/api/json/v1/1/search.php?s=margarita",
// //     type: "GET"
// // }).done(function(data) {
// //     console.log(data);
// // });
//
// // Mapbox
//
//
//
//
//
