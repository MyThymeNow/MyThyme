
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
                  <div class="card" style="width: 18rem"><!-- class="bg-transparent" -->
                    <div class="card-body" style="border: #333333 2px solid; background-image: linear-gradient(180deg, #f1b24a, #9DC88D)">
                      <h5 class="card-title" style="border: #164A41; text-align: center">${data.recipes[i].title}</h5>
                      <img className="centerImg" src="${data.recipes[i].image}" style="width: 8rem; length: 8rem; border: #9DC88D 2px solid">
                      <p class="card-text scroll">${data.recipes[i].summary}</p>
                      <a href="#" class="btn" style="color: white; background-color: #f1b24a">View recipe</a>
                   
                    </div>
                  </div>`;
    }
    // <div className="card" style="width: 18rem;"><!-- class="bg-transparent" -->
    //     <div className="card-body"
    //          style="border: #333333 2px solid; background-image: linear-gradient(180deg, #f1b24a, #9DC88D)">
    //         <h5 className="card-title" style="border: #164A41; text-align: center">Grandma's Secret Chocolate Chip
    //             Cookies</h5> <!-- ${data.recipes[i].title} -->
    //         <img className="centerImg"
    //              src="https://www.errenskitchen.com/wp-content/uploads/2019/02/chocolate-chip-cookies-3-500x375.jpg"
    //              style="width: 8rem; length: 8rem; border: #9DC88D 2px solid"> <!-- ${data.recipes[i].image} -->
    //             <p className="card-text scroll">
    //                 MyThyme is an app designed to make cost effective shopping seamless for everyone.
    //                 Users can select items they wish to buy, and will be given costs of those items at various
    //                 locations,
    //                 making deciding where to buy from simple. Users can also save and share their created grocery lists,
    //                 while also being provided recommended recipes based on items on their list. MyThyme was created by a
    //                 group of 4 developers, utilizing Java, JavaScript, MySQL, Spring Boot, HTML, CSS and several APIs.
    //
    //                 MyThyme is an app designed to make cost effective shopping seamless for everyone.
    //                 Users can select items they wish to buy, and will be given costs of those items at various
    //                 locations,
    //                 making deciding where to buy from simple. Users can also save and share their created grocery lists,
    //                 while also being provided recommended recipes based on items on their list. MyThyme was created by a
    //                 group of 4 developers, utilizing Java, JavaScript, MySQL, Spring Boot, HTML, CSS and several APIs.
    //             </p> <!-- ${data.recipes[i].summary} -->
    //             <a href="#" className="btn" style="color: white; background-color: #f1b24a">View
    //                 recipe</a> <!-- background-color: #f1b24a-->
    //     </div>
    // </div>
    $("#test-col").html(html);





    // $("#test-col").html("");
    // data.results.forEach(function (item, index) {
    //     if (index < 3) {
    //         console.log(item);
    //
    //         var breadTest = `
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





