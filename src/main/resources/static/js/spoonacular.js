"use strict"

let recipeDetails = null;



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

    // Search recipes - works
    function searchRecipes(q) {
        $.ajax({
            url: `https://api.spoonacular.com/recipes/complexSearch?apiKey=${spoonacularKey}&query=${q}&number=10&addRecipeInformation=true`,
            type: "GET"
        }).done(function (data) {
            console.log(data);

            let html = "";
            for (let i = 0; i < 5; i++) {

                html += `
                  <div class="card bg-transparent" style="width: 25rem">
                    <div class="card-body" style="display: flex; flex-direction: column; background-color: #f1b24a">
                      <h5 class="card-title">${data.results[i].title}</h5>
                      <img src="${data.results[i].image}">
                      <p class="card-text">${data.results[i].summary}</p>
                      <a href="${data.results[i].sourceUrl}" class="btn" style="border-radius: 4px; background-color: #164A41; color: white;">View Recipe</a>
                         
                          <a href="#" id="listButton" class="btn" data-id="${data.results[i].id}" style="border-radius: 4px; background-color: #164A41; color: white;">Add to List</a>
                         

                    </div>
                  </div>`;
            }
            $("#search_results").html(html);
        })
    }

    $("#search-button").click(function (e) {
        e.preventDefault();
        let q = $("#search-input").val();


        searchRecipes(q);
    })
    searchRecipes($("#search-input").val());


//
//
//     // Beverages
//     function beverages() {
//         $.ajax({
//             url: `https://api.spoonacular.com/recipes/complexSearch?type=beverage&apiKey=${spoonacularKey}&addRecipeInformation=true&fillIngredients=true`,
//             type: "GET"
//         }).done(function (data) {
//             console.log(data);
//
//             let html = "";
//             for (let i = 0; i < 10; i++) {
//
//                     html += `
//                   <div class="card bg-transparent ml-4" style="width: 18rem">
//                     <div class="card-body" style="display: flex; flex-direction: column; background-color: #f1b24a">
//                       <h5 class="card-title">${data.results[i].title}</h5>
//                       <img src="${data.results[i].image}">
//                       <a href="${data.results[i].sourceUrl}" class="btn" style="border-radius: 4px; background-color: #164A41; color: white;">View Recipe</a>
//                       <div sec:authorize="isAuthenticated()">
//                       <a href="#" id="bevButton" class="btn" data-id="${data.results[i].id}" style="border-radius: 4px; background-color: #164A41; color: white;">Add to List</a>
//                      </div>
//                     </div>
//                   </div>`;
//                 }
//             $("#beverage_results").html(html);
//             $("#bevButton").click(function (e) {
//                 e.preventDefault();
//                 const recipeId = $(this).attr("data-id")
//                 console.log(recipeId);
//                 const recipe = recipeDetails.results.find(result => result.id == recipeId)
//                 console.log(recipe)
//
//
//                 const recipeData = {
//                     name: recipe.title,
//                     ingredients: recipe.extendedIngredients.map(ingredient => ({name: ingredient.name, quantity: ingredient.amount, notes: ingredient.originalString}))
//                 }
//
//
//                 function saveRecipe(data) {
//                     let token = $("meta[name='_csrf']").attr("content");
//                     let header = $("meta[name='_csrf_header']").attr("content");
//
//                     console.log($("meta[name='_csrf']").attr("content"), token)
//
//                     $(document).ajaxSend(function (e, xhr, options) {
//                         xhr.setRequestHeader(header, token);
//                     });
//
//
//                     $.post({
//                         contentType: "application/json",
//                         url: "/saveIngredients",
//                         data: JSON.stringify(data),
//                         timeout: 600000,
//                         success: function (data) {
//                             console.log(data);
//
//                         }
//
//                     })
//                     // Make call to api using the recipe ID
//                 }
//
//                 saveRecipe(recipeData);
//             })
//
//         })
//
//     }
//     beverages();
//
//
// // Main course
    function mainCourse() {
        $.ajax({
            url: `https://api.spoonacular.com/recipes/complexSearch?type=main course&apiKey=${spoonacularKey}&addRecipeInformation=true&fillIngredients=true`,
            type: "GET"
        }).done(function (data) {
            recipeDetails = data
            console.log(data);


            let html = "";
            for (let i = 0; i < 10; i++) {

                html += `
                  <div class="card bg-transparent ml-4" style="width: 18rem">
                    <div class="card-body" style="display: flex; flex-direction: column; background-color: #f1b24a">
                      <h5 class="card-title">${data.results[i].title}</h5>
                      <img src="${data.results[i].image}">
                      <a href="${data.results[i].sourceUrl}" class="btn" style="border-radius: 4px; background-color: #164A41; color: white;">View Recipe</a>

                      <a href="#" id="listButton" class="btn" data-id="${data.results[i].id}" style="border-radius: 4px; background-color: #164A41; color: white;">Add to List</a>
      
                    </div>
                  </div>`;
            }
            $("#maincourse_results").html(html);

            $("#listButton").click(function (e) {
                e.preventDefault();
                const recipeId = $(this).attr("data-id")
                console.log(recipeId);
                const recipe = recipeDetails.results.find(result => result.id == recipeId)
                console.log(recipe)


                const recipeData = {
                    name: recipe.title,
                    ingredients: recipe.extendedIngredients.map(ingredient => ({
                        name: ingredient.name,
                        quantity: ingredient.amount,
                        notes: ingredient.originalString
                    }))
                }


                function saveRecipe(data) {
                    let token = $("meta[name='_csrf']").attr("content");
                    let header = $("meta[name='_csrf_header']").attr("content");

                    console.log($("meta[name='_csrf']").attr("content"), token)

                    $(document).ajaxSend(function (e, xhr, options) {
                        xhr.setRequestHeader(header, token);
                    });


                    $.post({
                        contentType: "application/json",
                        url: "/saveIngredients",
                        data: JSON.stringify(data),
                        timeout: 600000,
                        success: function (data) {
                            console.log(data);

                        }

                    })
                    // Make call to api using the recipe ID
                }

                saveRecipe(recipeData);
            })

        })

    }

    mainCourse();






     // Sides
    function sides() {
        $.ajax({
            url: `https://api.spoonacular.com/recipes/complexSearch?type=side dish&apiKey=${spoonacularKey}&addRecipeInformation=true&fillIngredients=true`,
            type: "GET"
        }).done(function (data) {
            console.log(data);

            let html = "";
            for (let i = 0; i < 10; i++) {

                html += `
                  <div class="card bg-transparent ml-4" style="width: 18rem">
                    <div class="card-body" style="display: flex; flex-direction: column; background-color: #f1b24a">
                      <h5 class="card-title">${data.results[i].title}</h5>
                      <img src="${data.results[i].image}">
                      <a href="${data.results[i].sourceUrl}" class="btn" style="border-radius: 4px; background-color: #164A41; color: white;">View Recipe</a>
                      <div sec:authorize="isAuthenticated()">
                      <a href="#" class="btn" data-id="${data.results[i].id}" style="border-radius: 4px; background-color: #164A41; color: white;">Add to List</a>
                     </div>
                    </div>
                  </div>`;
            }
            $("#side_results").html(html);
        })
    }
    sides();
//
//
//     // Desserts
    function dessert() {
        $.ajax({
            url: `https://api.spoonacular.com/recipes/complexSearch?type=dessert&apiKey=${spoonacularKey}&addRecipeInformation=true&fillIngredients=true`,
            type: "GET"
        }).done(function (data) {
            console.log(data);

            let html = "";
            for (let i = 0; i < 10; i++) {

                html += `
                  <div class="card bg-transparent ml-4" style="width: 18rem">
                    <div class="card-body" style="display: flex; flex-direction: column; background-color: #f1b24a">
                      <h5 class="card-title">${data.results[i].title}</h5>
                      <img src="${data.results[i].image}">
                      <a href="${data.results[i].sourceUrl}" class="btn" style="border-radius: 4px; background-color: #164A41; color: white;">View Recipe</a>
                      <div sec:authorize="isAuthenticated()">
                      <a href="#" class="btn" data-id="${data.results[i].id}" style="border-radius: 4px; background-color: #164A41; color: white;">Add to List</a>
                     </div>
                    </div>
                  </div>`;
            }
            $("#dessert_results").html(html);
        })
    }
    dessert();
//
//
//     // Bread
    function bread() {
        $.ajax({
            url: `https://api.spoonacular.com/recipes/complexSearch?type=bread&apiKey=${spoonacularKey}&addRecipeInformation=true&fillIngredients=true`,
            type: "GET"
        }).done(function (data) {
            console.log(data);

            let html = "";
            for (let i = 0; i < 10; i++) {

                html += `
                  <div class="card bg-transparent ml-4" style="width: 18rem">
                    <div class="card-body" style="display: flex; flex-direction: column; background-color: #f1b24a">
                      <h5 class="card-title">${data.results[i].title}</h5>
                      <img src="${data.results[i].image}">
                      <a href="${data.results[i].sourceUrl}" class="btn" style="border-radius: 4px; background-color: #164A41; color: white;">View Recipe</a>

                      <a href="#" class="btn" data-id="${data.results[i].id}" style="border-radius: 4px; background-color: #164A41; color: white;">Add to List</a>

                    </div>
                  </div>`;
            }
            $("#bread_results").html(html);
        })
    }
    bread();
//
//
//
//     // Appetizers
    function appetizers() {
        $.ajax({
            url: `https://api.spoonacular.com/recipes/complexSearch?type=appetizer&apiKey=${spoonacularKey}&addRecipeInformation=true&fillIngredients=true`,
            type: "GET"
        }).done(function (data) {
            console.log(data);

            let html = "";
            for (let i = 0; i < 10; i++) {

                html += `
                  <div class="card bg-transparent ml-4" style="width: 18rem">
                    <div class="card-body" style="display: flex; flex-direction: column; background-color: #f1b24a">
                      <h5 class="card-title">${data.results[i].title}</h5>
                      <img src="${data.results[i].image}">
                      <a href="${data.results[i].sourceUrl}" class="btn" style="border-radius: 4px; background-color: #164A41; color: white;">View Recipe</a>
                      <a href="#" class="btn" data-id="${data.results[i].id}" style="border-radius: 4px; background-color: #164A41; color: white;">Add to List</a>
                     </div>
                    </div>
                  </div>`;
            }
            $("#app_results").html(html);
        })
    }
    appetizers();
});
