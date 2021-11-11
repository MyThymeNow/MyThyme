
// console.log("This is the console");
//
// alert('This javascript works!');

$(document).ready(function() {
    $('input[type=checkbox][name=checkbox]').change(function() {
        if ($(this).is(':checked')) {
            alert(`${this.value} is checked`);
        }
        else {
            alert(`${this.value} is unchecked`);
        }
    });
});