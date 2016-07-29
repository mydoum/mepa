/**
 * Created by Nea on 28/07/2016.
 */
var initIndex = 0;
var loadNumber = 25;

$(document).ready(function () {
    var win = $(window);
    var doc = $(document);

    console.log("Inside Outside");
    // Each time the user scrolls
    win.scroll(function () {
        // Vertical end reached?
        if (doc.height() - win.height() == win.scrollTop() && loadNumber < infiniteScrollerElements.length) {
            // New row
            console.log("Inside Infinite Begin");
            console.log("Init Index : " + initIndex);
            console.log("Load Number : " + loadNumber);

            for (var i = initIndex; i < loadNumber; ++i) {
                var table = document.getElementById("infiniteInvestorsList");
                var row = table.insertRow(i);
                var cell1 = row.insertCell(0);
                var cell2 = row.insertCell(1);

                cell1.innerHTML = infiniteScrollerElements[i][0];
                cell2.innerHTML = infiniteScrollerElements[i][1];
            }
            initIndex = loadNumber;
            loadNumber =
                loadNumber + 15 < infiniteScrollerElements.length ?
                loadNumber + 15 : loadNumber + (infiniteScrollerElements.length - loadNumber);
            console.log("Inside Infinite End");
            console.log("Init Index : " + initIndex);
            console.log("Load Number : " + loadNumber);
        }
    });
});