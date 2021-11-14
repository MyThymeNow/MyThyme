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

    // Search recipes - works

    // $("#search-button").click(function (e) {
    //     e.preventDefault();
    //     let q = $("#search-input").val();
    //
    //     function searchRecipes() {
    //         $.ajax({
    //             url: `https://api.spoonacular.com/recipes/complexSearch?apiKey=${spoonacularKey}&query=${q}&number=10&addRecipeInformation=true`,
    //             type: "GET"
    //         }).done(function (data) {
    //             console.log(data);
    //
    //             let html = "";
    //             for (let i = 0; i < 5; i++) {
    //
    //                 html += `
    //               <div class="card bg-transparent" style="width: 25rem">
    //                 <div class="card-body">
    //                   <h5 class="card-title">${data.results[i].title}</h5>
    //                   <img src="${data.results[i].image}">
    //                   <p class="card-text">${data.results[i].summary}</p>
    //                   <a href="${data.results[i].sourceUrl}" class="btn btn-primary">View Recipe</a>
    //                   <a href="#" class="btn btn-primary">Add to List</a>
    //
    //                 </div>
    //               </div>`;
    //             }
    //             $("#search_results").html(html);
    //         })
    //     }
    //     searchRecipes();
    // })


    // Beverages
    function beverages() {
        $.ajax({
            url: `https://api.spoonacular.com/recipes/complexSearch?type=beverage&apiKey=${spoonacularKey}&addRecipeInformation=true`,
            type: "GET"
        }).done(function (data) {
            console.log(data);

            let html = "";
            for (let i = 0; i < 10; i++) {

                    html += `
                  <div class="card bg-transparent" style="width: 10rem">
                    <div class="card-body">
                      <h5 class="card-title">${data.results[i].title}</h5>
                      <img src="${data.results[i].image}">
                      <a href="${data.results[i].sourceUrl}" class="btn btn-primary">View Recipe</a>
                      <a href="#" class="btn btn-primary">Add to List</a>
                    </div>
                  </div>`;
                }
            $("#beverage_results").html(html);
        })
    }
    beverages();


// Main course
    function mainCourse() {
        $.ajax({
            url: `https://api.spoonacular.com/recipes/complexSearch?type=main course&apiKey=${spoonacularKey}&addRecipeInformation=true`,
            type: "GET"
        }).done(function (data) {
            console.log(data);

            let html = "";
            for (let i = 0; i < 10; i++) {

                html += `
                  <div class="card bg-transparent" style="width: 10rem">
                    <div class="card-body">
                      <h5 class="card-title">${data.results[i].title}</h5>
                      <img src="${data.results[i].image}">
                      <a href="${data.results[i].sourceUrl}" class="btn btn-primary">View Recipe</a>
                      <a href="#" class="btn btn-primary">Add to List</a>
                    </div>
                  </div>`;
            }
            $("#maincourse_results").html(html);
        })
    }
    mainCourse();


    // Sides
    function sides() {
        $.ajax({
            url: `https://api.spoonacular.com/recipes/complexSearch?type=side dish&apiKey=${spoonacularKey}&addRecipeInformation=true`,
            type: "GET"
        }).done(function (data) {
            console.log(data);

            let html = "";
            for (let i = 0; i < 10; i++) {

                html += `
                  <div class="card bg-transparent" style="width: 10rem">
                    <div class="card-body">
                      <h5 class="card-title">${data.results[i].title}</h5>
                      <img src="${data.results[i].image}">
                      <a href="${data.results[i].sourceUrl}" class="btn btn-primary">View Recipe</a>
                      <a href="#" class="btn btn-primary">Add to List</a>
                    </div>
                  </div>`;
            }
            $("#side_results").html(html);
        })
    }
    sides();


    // Desserts
    function dessert() {
        $.ajax({
            url: `https://api.spoonacular.com/recipes/complexSearch?type=dessert&apiKey=${spoonacularKey}&addRecipeInformation=true`,
            type: "GET"
        }).done(function (data) {
            console.log(data);

            let html = "";
            for (let i = 0; i < 10; i++) {

                html += `
                  <div class="card bg-transparent" style="width: 10rem">
                    <div class="card-body">
                      <h5 class="card-title">${data.results[i].title}</h5>
                      <img src="${data.results[i].image}">
                      <a href="${data.results[i].sourceUrl}" class="btn btn-primary">View Recipe</a>
                      <a href="#" class="btn btn-primary">Add to List</a>
                    </div>
                  </div>`;
            }
            $("#dessert_results").html(html);
        })
    }
    dessert();


    // Bread
    function bread() {
        $.ajax({
            url: `https://api.spoonacular.com/recipes/complexSearch?type=bread&apiKey=${spoonacularKey}&addRecipeInformation=true`,
            type: "GET"
        }).done(function (data) {
            console.log(data);

            let html = "";
            for (let i = 0; i < 10; i++) {

                html += `
                  <div class="card bg-transparent" style="width: 10rem">
                    <div class="card-body">
                      <h5 class="card-title">${data.results[i].title}</h5>
                      <img src="${data.results[i].image}">
                      <a href="${data.results[i].sourceUrl}" class="btn btn-primary">View Recipe</a>
                      <a href="#" class="btn btn-primary">Add to List</a>
                    </div>
                  </div>`;
            }
            $("#bread_results").html(html);
        })
    }
    bread();



    // Appetizers
    function appetizers() {
        $.ajax({
            url: `https://api.spoonacular.com/recipes/complexSearch?type=appetizer&apiKey=${spoonacularKey}&addRecipeInformation=true`,
            type: "GET"
        }).done(function (data) {
            console.log(data);

            let html = "";
            for (let i = 0; i < 10; i++) {

                html += `
                  <div class="card bg-transparent" style="width: 10rem">
                    <div class="card-body">
                      <h5 class="card-title">${data.results[i].title}</h5>
                      <img src="${data.results[i].image}">
                      <a href="${data.results[i].sourceUrl}" class="btn btn-primary">View Recipe</a>
                      <a href="#" class="btn btn-primary">Add to List</a>
                    </div>
                  </div>`;
            }
            $("#app_results").html(html);
        })
    }
    appetizers();
});
