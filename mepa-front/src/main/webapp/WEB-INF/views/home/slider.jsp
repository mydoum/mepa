<!DOCTYPE html>
<html lang="en">
<head>
    <title>Bootstrap Example</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    <style>
        .carousel-inner > .item > img,
        .carousel-inner > .item > a > img {
            margin: auto;
        }
    </style>
</head>
<body>

<div class="container">
    <div id="myCarousel" class="carousel slide">
        <!-- Indicators -->
        <ol class="carousel-indicators">
            <li class="item1 active"></li>
            <li class="item2"></li>
            <li class="item3"></li>
            <li class="item4"></li>
        </ol>

        <!-- Wrapper for slides -->
        <div class="carousel-inner" role="listbox">

            <div class="item active">
                <img src="<c:url value="/img/slider/investment_2.jpg"/>" alt="investment 2" width="1170" height="500">
                <div class="carousel-caption">
                    <h3>Des projets innovants</h3>
                    <p>Ajoutez de la valeur à votre patrimoine</p>
                </div>
            </div>

            <div class="item">
                <img src="<c:url value="/img/slider/investment_1.jpg"/>" alt="investment 1" width="1170" height="500">
                <div class="carousel-caption">
                    <h3>Des projets ambitieux</h3>
                    <p>Des équipes prometeuses vous proposent les projets de demain</p>
                </div>
            </div>

            <div class="item">
                <img src="<c:url value="/img/slider/investment_2.jpg"/>" alt="investment 2" width="1170" height="500">
                <div class="carousel-caption">
                    <h3>Des projets innovants</h3>
                    <p>Ajoutez de la valeur à votre patrimoine</p>
                </div>
            </div>

            <div class="item">
                <img src="<c:url value="/img/slider/investment_3.jpg"/>" alt="investment 3" width="1170" height="500">
                <div class="carousel-caption">
                    <h3>Ciblez votre ROI</h3>
                    <p>Choisissez l'investissement qui vous convient</p>
                </div>
            </div>

        </div>

        <!-- Left and right controls -->
        <a class="left carousel-control" href="#myCarousel" role="button">
            <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
        </a>
        <a class="right carousel-control" href="#myCarousel" role="button">
            <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
        </a>
    </div>
</div>

<script>
    $(document).ready(function () {
        // Activate Carousel
        $("#myCarousel").carousel();

        // Enable Carousel Indicators
        $(".item1").click(function () {
            $("#myCarousel").carousel(0);
        });
        $(".item2").click(function () {
            $("#myCarousel").carousel(1);
        });
        $(".item3").click(function () {
            $("#myCarousel").carousel(2);
        });
        $(".item4").click(function () {
            $("#myCarousel").carousel(3);
        });

        // Enable Carousel Controls
        $(".left").click(function () {
            $("#myCarousel").carousel("prev");
        });
        $(".right").click(function () {
            $("#myCarousel").carousel("next");
        });
    });
</script>

</body>
</html>
