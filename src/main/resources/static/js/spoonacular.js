"use strict"

$(document).ready(function () {

    // Random recipes on Landing page
    function getRandomRecipes() {
        $.ajax({
            url: `https://api.spoonacular.com/recipes/random?apiKey=${spoonacularKey}&number=3`,
            type: "GET"
        }).done(function (data) {
            console.log(data);
        })
    }

    getRandomRecipes();


    // Search
    // $("#searchButton").click(function (e) {
    //     e.preventDefault();
    //     let searchedItems = $("#search-input").val();
    //     $("#currentCity").html("Search results" + searchedItems);
    //     console.log(searchedItems);

    function searchRecipes() {
        $.ajax({
            url: `https://api.spoonacular.com/food/search?apiKey=${spoonacularKey}&query=apple&number=2`,
            type: "GET"
        }).done(function (data) {
            $("#search").html("Results" + data);
            console.log(data);
        })

    }
    searchRecipes();




})