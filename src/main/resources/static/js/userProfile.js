"use strict"
let requestString = "https://api.spoonacular.com/recipes/random?apiKey=" + spoonacularKey + "&number=3";
// // // let requestCategories = "https://api.spoonacular.com/food/ingredients/9266/information?apiKey=" + spoonacularKey + "&amount=1";
//
$.ajax({
    url: requestString,
    type: "GET"
}).done(function(data) {
    console.log(data);

    // let html = `            <div class="col">
    //             <div id="myThymeCarouselIndicators" class="carousel slide" data-ride="carousel">
    //                 <ol class="carousel-indicators">
    //                     <li data-target="#myThymeCarouselIndicators" data-slide-to="0" class="active"></li>
    //                     <li data-target="#myThymeCarouselIndicators" data-slide-to="1"></li>
    //                     <li data-target="#myThymeCarouselIndicators" data-slide-to="2"></li>
    //                 </ol>
    //                 <div class="carousel-inner">`;

    // let mid = "";
    for (let i = 0; i < 3; i++) {
        let mid = "";
        if (i == 0){
            mid+=`<div class="carousel-item active">`;
        } else {
                mid+=`<div class="carousel-item">`;
            }
        mid +=
            `
                <a href="${data.recipes[i].sourceUrl}"><img
                    src="${data.recipes[i].image}"
                    class="d-block w-80" alt="..."></a>
                <div class="carousel-caption d-none d-md-block">
                    <h2 style="color: #ffffff; align-self: center"><strong>Try Something New</strong></h2>
<!--                    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>-->
                </div>
            `;
        $("#profile-col").append(mid).css('border','3px solid #' );
    }

    // $("#profile-col").html(mid);

    // $("#profile-col").html(html).append(xyz);
    // $("#profile-col").append(html)
    // $(html).append("#profile-col").append(xhtml)
    // $("#profile-col").before(html) + $("profile-col").append(mid) + $("profile-col").after(end);
    // $("#profile-col").html(html).append(mid,end);
    // $('*').css('border', '1px solid #F00'); TODO EXAMPLE TO STYLE
    // $(".carousel").html(html).append(mid).after(end);
    // $(".carousel").append(html,mid,end);
});