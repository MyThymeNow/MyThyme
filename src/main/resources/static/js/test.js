//
// // let requestString = "https://api.spoonacular.com/recipes/findByIngredients?apiKey=" + spoonacularKey + "&ingredients=apples,+flour,+sugar&number=2";
//
// //Spoonacular
// // let requestString = "https://api.spoonacular.com/food/ingredients/search?apiKey=" + spoonacularKey + "&query=bread&number=30";
// let requestString = "https://api.spoonacular.com/recipes/random?apiKey=" + spoonacularKey + "&number=3";
// // let requestCategories = "https://api.spoonacular.com/food/ingredients/9266/information?apiKey=" + spoonacularKey + "&amount=1";
//
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
//                   <div class="card bg-transparent" style="width: 18rem">
//                     <div class="card-body">
//                       <h5 class="card-title">${data.recipes[i].title}</h5>
//                       <img src="${data.recipes[i].image}">
//                       <p class="card-text">${data.recipes[i].summary}</p>
//                       <a href="#" class="btn btn-primary">View recipe</a>
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
