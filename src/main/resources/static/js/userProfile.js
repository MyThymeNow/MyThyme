// "use strict"
// let requestString = "https://api.spoonacular.com/recipes/random?apiKey=" + spoonacularKey + "&number=3";
// // // // let requestCategories = "https://api.spoonacular.com/food/ingredients/9266/information?apiKey=" + spoonacularKey + "&amount=1";
// //
// //
// $.ajax({
//     url: requestString,
//     type: "GET"
// }).done(function(data) {
//     console.log(data);
//
//     let html = `
//             <div class="col">
//         <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
//             <ol class="carousel-indicators">
//                 <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
//                 <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
//                 <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
//             </ol>
//             <div class="carousel-inner">
//                 <div class="carousel-item active">
//                     <img src="${data.recipes[i].image} class="d-block w-100" alt="...">
//                 </div>
//                 <div class="carousel-item">
//                     <img src="${data.recipes[i].image}" class="d-block w-100" alt="...">
//                 </div>
//                 <div class="carousel-item">
//                     <img src="${data.recipes[i].image}" class="d-block w-100" alt="...">
//                 </div>
//             </div>
//             <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
//                 <span class="carousel-control-prev-icon" aria-hidden="true"></span>
//                 <span class="sr-only">Previous</span>
//             </a>
//             <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
//                 <span class="carousel-control-next-icon" aria-hidden="true"></span>
//                 <span class="sr-only">Next</span>
//             </a>
//         </div>
//     </div>`;
//
//     // for (let i = 0; i < 3; i++) {
//     //
//     //     html += `
//     //               <div class="card ml-3" style="width: 18rem"><!-- class="bg-transparent" -->
//     //               <div class="card ml-4" style="width: 18rem"><!-- class="bg-transparent" -->
//     //                 <div class="card-body" style="display: flex; flex-direction: column; background-color: #f1b24a"><!--9DC88D light green-->
//     //                   <h5 class="card-title text-light font-weight-bold" style="display: flex; flex-direction: row; border: #164A41; text-align: center">${data.recipes[i].title}</h5>
//     //                   <img class="centerImg shadow bg-white rounded" src="${data.recipes[i].image}" style="display: flex; flex-direction: row; width: 8rem; length: 8rem; border: #164A41 2px solid">
//     //                   <br>
//     //                   <p class="card-text scroll shadow p-3 mb-5 rounded" style="display: flex; flex-direction: row; background-color: #ffffff; color: #000">${data.recipes[i].summary}</p>
//     //                   <a href="#" class="btn centerImg shadow p-3 mb-2 mbr-2 rounded" style="width: 80%; color: white; background-color: #164A41">View recipe</a>
//     //
//     //                 </div>
//     //               </div>`;
//     //
//     // }
//     $("#profile-col").html(html);
// });