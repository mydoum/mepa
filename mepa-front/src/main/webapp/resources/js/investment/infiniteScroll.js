/**
 * Created by Nea on 28/07/2016.
 */

$(document).ready(function() {
    var win = $(window);
    var doc = $(document);

    // Each time the user scrolls
    /*win.scroll(function() {
        // Vertical end reached?
        if (doc.height() - win.height() == win.scrollTop()) {
            // New row
            var tr = $('<tr />').append($('<th />')).appendTo($('#spreadsheet'));

            // Current number of columns to create
            var n_cols = $('#spreadsheet tr:first-child th').length;
            for (var i = 0; i < n_cols; ++i)
                tr.append($('<td />'));
        }
    });*/
});