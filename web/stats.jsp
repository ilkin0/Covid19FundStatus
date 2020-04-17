<%@ page import="java.time.LocalDate" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>COVID19 FUND STATUS</title>

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">

</head>
<body>
<form action="status" method="get">
    <div class="jumbotron">
        <h2>Covid19-la mübarizədə ölkə üzrə edilən ümumi ianələr haqqənda statistik məlumatlar</h2>
        <h1 class="display-4">&#8380;<%=request.getAttribute("totalDonation")%>
        </h1>
        <p class="lead">Ümumi ianələrin cəmi</p>
        <hr class="my-4">
        <p><%=LocalDate.now()%> tarixi ilə Covid19 mübarizə fonduna edilən ümumi ianələrin cəmi</p>
    </div>

    <section class="stat" id="stats">
        <div class="content-box">
            <br><br>
            <div class="container">
                <div class="row text-center">

                    <div class="col-md-4">
                        <div class="stat-items">
                            <i class="fa fa-university fa-3x"></i>
                            <h2><span>&#8380;</span><span class="counter text-center">
                                <%=request.getAttribute("governmentDonation")%></span></h2>
                            <p>Dövlət ianəsi</p>
                        </div>
                    </div>

                    <div class="col-md-4">
                        <div class="stat-items">
                            <i class="fa fa-industry fa-3x"></i>
                            <h2><span>&#8380;</span><span class="counter text-center">
                                <%=request.getAttribute("legalEntities")%></span></h2>
                            <p>Hüquqi şəxslərin ianələri</p>
                        </div>
                    </div>

                    <div class="col-md-4">
                        <div class="stat-items">
                            <i class="fa fa-users fa-3x"></i>
                            <h2><span>&#8380;</span><span class="counter text-center">
                                <%=request.getAttribute("individualLocal")%></span></h2>
                            <p>Fiziki şəxlərin ianələri</p>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </section>

    <section class="content">
        <div class="container-fluid">
            <br><br>
            <div class="row">

                <div class="col-md-12">
                    <div class="card text-white bg-light">
                        <div class="card-header text-white bg-success">
                            <h3 class="card-title">İanələrin bağışlanması qrafikası - Pie Chart</h3>
                        </div>
                        <div class="card-body">
                            <canvas id="pieChart"
                                    style="min-height: 500px; height: 500px; max-height: 500px; max-width: 100%;"></canvas>
                        </div>
                    </div>
                </div>

                <div class="col-md-12">
                    <div class="card text-white bg-light">
                        <div class="card-header text-white bg-success">
                            <h3 class="card-title">İanələrin bağışlanması qrafikası - Donut Chart</h3>
                        </div>
                        <div class="card-body">
                            <canvas id="donutChart"
                                    style="min-height: 500px; height: 500px; max-height: 500px; max-width: 100%;"></canvas>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </section>


    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/waypoints/4.0.1/jquery.waypoints.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Counter-Up/1.0.0/jquery.counterup.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.3/Chart.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>

    <script>

        //Counter-up js
        $('.counter').counterUp({
            delay: 50,
            time: 1500
        });


        //creating variables from servlet for using in Chart.js
        let tDonation = <%=request.getAttribute("totalDonation")%>;
        let gDonation = <%=request.getAttribute("governmentDonation")%>;
        let leDonation = <%=request.getAttribute("legalEntities")%>;
        let inDonation = <%=request.getAttribute("individualLocal")%>;

        var Data = {
            labels: [
                'Dövlət ianəsi',
                'Hüquqi Şəxlər ianəsi',
                'Fiziki şəxslər ianəsi'
            ],
            datasets: [
                {
                    data: [gDonation, leDonation, inDonation],
                    backgroundColor: ['#f56954', '#00a65a', '#f39c12'],
                }
            ]
        }


        //Pie chart
        let pieChartCanvas = $('#pieChart').get(0).getContext('2d')
        let pieData = Data;
        let pieOptions = {
            maintainAspectRatio: false,
            responsive: true,
        }


        let pieChart = new Chart(pieChartCanvas, {
            type: 'pie',
            data: pieData,
            options: pieOptions
        })

        //Donut chart
        let donutChartCanvas = $('#donutChart').get(0).getContext('2d')
        let donutData = Data;

        let donutOptions = {
            maintainAspectRatio: false,
            responsive: true,
        }

        let donutChart = new Chart(donutChartCanvas, {
            type: 'doughnut',
            data: donutData,
            options: donutOptions
        })

    </script>
</form>
</body>
</html>
