
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
//                   <div class="card ml-3" style="width: 18rem"><!-- class="bg-transparent" -->
//                   <div class="card ml-4" style="width: 18rem"><!-- class="bg-transparent" -->
//                     <div class="card-body" style="display: flex; flex-direction: column; background-color: #f1b24a"><!--9DC88D light green-->
//                       <h5 class="card-title text-light font-weight-bold" style="display: flex; flex-direction: row; border: #164A41; text-align: center">${data.recipes[i].title}</h5>
//                       <img class="centerImg shadow bg-white rounded" src="${data.recipes[i].image}" style="display: flex; flex-direction: row; width: 8rem; length: 8rem; border: #164A41 2px solid">
//                       <br>
//                       <p class="card-text scroll shadow p-3 mb-5 rounded" style="display: flex; flex-direction: row; background-color: #ffffff; color: #000">${data.recipes[i].summary}</p>
//                       <a href="#" class="btn centerImg shadow p-3 mb-2 mbr-2 rounded" style="width: 80%; color: white; background-color: #164A41">View recipe</a>
//
//                     </div>
//                   </div>`;
//
//     }
//     $("#test-col").html(html);
//
//
//
//
//
//
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
//
// });
//
// // Cocktail DB
// // $.ajax({
// //     url: requestString,
// //     type: "GET"
// // }).done(function(data) {
// //     console.log(data);
// //
// //     let html = "";
// //     for (let i = 0; i < 3; i++) {
// //
// //         html += `
// //                   <div class="card" style="width: 18rem"><!-- class="bg-transparent" -->
// //                     <div class="card-body" style="border: #333333 2px solid; background-image: linear-gradient(180deg, #f1b24a, #9DC88D)">
// //                       <h5 class="card-title" style="border: #164A41; text-align: center">${data.recipes[i].title}</h5>
// //                       <img class="centerImg" src="${data.recipes[i].image}" style="width: 8rem; length: 8rem; border: #9DC88D 2px solid">
// //                       <br>
// //                       <p class="card-text scroll" style="border: #9DC88D 2px solid">${data.recipes[i].summary}</p>
// //                       <a href="#" class="btn centerImg" style="color: white; background-color: #f1b24a">View recipe</a>
// //
// //                     </div>
// //                   </div>`;
// //     }
// //     $("#test-col").html(html);
// //
// //
// //
// //
// //
// //     // $("#test-col").html("");
// //     // data.results.forEach(function (item, index) {
// //     //     if (index < 3) {
// //     //         console.log(item);
// //     //
// //     //         var breadTest = `
// //     //                 <div class="column" id="test-col">
// //     //                     <div class="card" style="width: 18rem;">
// //     //                     <ul class="list-group list-group-flush">
// //     //
// //     //                         <img src="https://spoonacular.com/cdn/ingredients_100x100/${item.image}">
// //     //                         <li class="list-group-item">Name: ${item.name}</li>
// //     //
// //     //                     </ul>
// //     //                 </div>`
// //     //         $("#test-col").append(breadTest);
// //     //     }
// //     // })
// // });
// //
// // // Cocktail DB
// // // $.ajax({
// // //     url: "https://www.thecocktaildb.com/api/json/v1/1/search.php?s=margarita",
// // //     type: "GET"
// // // }).done(function(data) {
// // //     console.log(data);
// // // });
// //
// // // Mapbox
// //
// //
// //
// //
// //