"use strict"
let requestString = "https://api.spoonacular.com/recipes/random?apiKey=" + spoonacularKey + "&number=3";
// // // let requestCategories = "https://api.spoonacular.com/food/ingredients/9266/information?apiKey=" + spoonacularKey + "&amount=1";
//
$.ajax({
    url: requestString,
    type: "GET"
}).done(function(data) {
    console.log(data);

    let html = `            <div class="col">
                <div id="myThymeCarouselIndicators" class="carousel slide" data-ride="carousel">
                    <ol class="carousel-indicators">
                        <li data-target="#myThymeCarouselIndicators" data-slide-to="0" class="active"></li>
                        <li data-target="#myThymeCarouselIndicators" data-slide-to="1"></li>
                        <li data-target="#myThymeCarouselIndicators" data-slide-to="2"></li>
                    </ol>
                    <div class="carousel-inner">`;

    let xyz = ``;
    for (let i = 0; i < 3; i++) {

        xyz += `
                        <div class="carousel-item active">
                            <img src="${data.recipes[i].image}" class="d-block w-100" alt="recipe image">
                        </div>`;

    }
    xyz += `
</div>
<a class="carousel-control-prev" href="#myThymeCarouselIndicators" role="button" data-slide="prev">
<span class="carousel-control-prev-icon" aria-hidden="true"></span>
<span class="sr-only">Previous</span>
</a>
<a class="carousel-control-next" href="#myThymeCarouselIndicators" role="button" data-slide="next">
<span class="carousel-control-next-icon" aria-hidden="true"></span>
<span class="sr-only">Next</span>
</a>
</div>
</div>
`
 $("#profile-col").html(html);
    // $("#profile-col").append(html)
    // $("#profile-col").append(html)
    // $(html).append("#profile-col").append(xhtml)
});