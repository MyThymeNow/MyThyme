"use strict"

$(document).ready(function () {
//
//     // Random recipes on Landing page
//     // function getRandomRecipes() {
//     //     $.ajax({
//     //         url: `https://api.spoonacular.com/recipes/random?apiKey=${spoonacularKey}&number=3`,
//     //         type: "GET"
//     //     }).done(function (data) {
//     //         console.log(data);
//     //     })
//     // }
//     //
//     // getRandomRecipes();
//
//
//         // function searchProducts() {
//         //     $.ajax({
//         //         url: `https://api.spoonacular.com/food/products/search?apiKey=${spoonacularKey}&query=apple&number=20`,
//         //         type: "GET"
//         //     }).done(function (data) {
//         //         console.log(data);
//         //
//         //         let html = "";
//         //         for (let i = 0; i < 20; i++) {
//         //
//         //             html += `
//         //           <div class="card bg-transparent" style="width: 10rem">
//         //             <div class="card-body">
//         //               <h5 class="card-title">${data.products[i].title}</h5>
//         //               <img src="${data.products[i].image}">
//         //               <p class="card-text">${data.products[i].id}</p>
//         //               <a href="#" class="btn btn-primary">View Product</a>
//         //               <a href="#" class="btn btn-primary">Add to List</a>
//         //
//         //             </div>
//         //           </div>`;
//         //         }
//         //         $("#search_results").html(html);
//         //         })
//         // }
//         //         searchProducts();
//
//

    $("#search-button").click(function (e) {
        e.preventDefault();
        let q = $("#search-input").val();

        function searchRecipesByIngredients() {
            $.ajax({
                url: `https://api.spoonacular.com/recipes/complexSearch?apiKey=${spoonacularKey}&query=${q}&number=10&addRecipeInformation=true`,
                type: "GET"
            }).done(function (data) {
                console.log(data);

                let html = "";
                for (let i = 0; i < 5; i++) {

                    html += `
                  <div class="card bg-transparent" style="width: 25rem">
                    <div class="card-body">
                      <h5 class="card-title">${data.results[i].title}</h5>
                      <img src="${data.results[i].image}">
                      <p class="card-text">${data.results[i].summary}</p>
                      <a href="${data.results[i].sourceUrl}" class="btn btn-primary">View Recipe</a>
                      <a href="#" class="btn btn-primary">Add to List</a>

                    </div>
                  </div>`;
                }
                $("#search_results").html(html);
            })
        }

        searchRecipesByIngredients();
    })

    // $("#search-button").click(function (e) {
    //     e.preventDefault();
    //     let q = $("#search-input").val();
    //
    //     function searchSite(q) {
    //
    //         $.ajax({
    //             url: `https://api.spoonacular.com/food/search?query=${q}&apiKey=${spoonacularKey}&number=5`,
    //             type: "GET"
    //         }).done(function (data) {
    //             console.log(data);
    //
    //             let html = "";
    //             for (let i = 0; i < 6; i++) {
    //                 html += `
    //               <div class="card bg-transparent" style="width: 25rem">
    //                 <div class="card-body">
    //                 <h5 class="card-title">${data.searchResults[i].name}</h5>
    //
    //                  <a href="#" class="btn btn-primary">View More</a>
    //                 </div>
    //               </div>`;
    //             }
    //             $("#search_results").html(html);
    //
    //             })
    //     }
    //         searchSite(q);
    //     })


});
//
//
//     function produce() {
//         $.ajax({
//             url: `https://api.spoonacular.com/food/ingredients/search?apiKey=${spoonacularKey}&query=produce&number=20`,
//             type: "GET"
//         }).done(function (data) {
//             console.log(data);
//
//             let html = "";
//             for (let i = 0; i < 10; i++) {
//
//                     html += `
//                   <div class="card bg-transparent" style="width: 10rem">
//
//                     <div class="card-body">
//
//                       <h5 class="card-title">${data.results[i].name}</h5>
//                       <img src="https://spoonacular.com/cdn/ingredients_100x100/${data.results[i].image}">
//                       <p class="card-text">${data[i].results[i].aisle}</p>
//                       <a href="#" class="btn btn-primary">Add to List</a>
//
//                     </div>
//                   </div>`;
//                 }
//             $("#search_results").html(html);
//         })
//     }
//     produce();
// })
//
//
//             // function getPriceEstimate() {
//             //     $.ajax({
//             //         url:
//             //     })
//             // }
//
//
