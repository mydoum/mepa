/**
 * Created by Nea on 28/07/2016.
 */
var initIndex = 0;
var loadNumber = 25;

$(document).ready(function () {
    console.log("Inside Outside");
    // Each time the user scrolls
    $(window).scroll(function () {
        // Vertical end reached?
        console.log("Ratio window : " + ($(window).scrollTop() / $(window).height() * 100));
        console.log("Ratio document: " + ($(window).scrollTop() / $(document).height()* 100));

        if ($(window).scrollTop() / $(window).height() > 0.5 && $(window).scrollTop() / $(document).height() > 0.5
            && loadNumber < infiniteScrollerElements.length) {
            // New row
            console.log("I am executed");
            for (var i = initIndex; i < loadNumber; ++i) {
                var table = document.getElementById("infiniteInvestorsList");
                var row = table.insertRow(-1);
                var cell1 = row.insertCell(0);
                var cell2 = row.insertCell(1);

                cell1.innerHTML = infiniteScrollerElements[i][0];
                cell2.innerHTML = infiniteScrollerElements[i][1];
            }
            initIndex = loadNumber;
            loadNumber =
                loadNumber + 15 < infiniteScrollerElements.length ?
                loadNumber + 15 : loadNumber + (infiniteScrollerElements.length - loadNumber);
        }
    });
});