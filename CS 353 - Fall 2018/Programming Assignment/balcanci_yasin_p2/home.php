<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title>Welcome</title>
    <script
            src="https://code.jquery.com/jquery-3.2.1.min.js"
            integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
            crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link href="style.css" rel="stylesheet">
    <script src="welcomeClickHandler.js"></script>
</head>

<body>
<div class="container" style="margin-top:80px;">
    <nav class="navbar navbar-inverse bg-primary navbar-fixed-top">
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                        aria-expanded="false" aria-controls="navbar">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="home.php">Summer Internship Application</a>
            </div>
            <div id="navbar" class="navbar-collapse collapse">
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="logout.php">Log Out</a></li>
                </ul>
            </div>
        </div>
    </nav>
    <div class="panel container-fluid">
        <div class="col-md-12 text-right">
            <a type="button" class="btn btn-primary btn-sm" id="btn-back" href="<?php echo $_SERVER['HTTP_REFERER'] ?>"><span
                        class="glyphicon glyphicon-chevron-left"></span>Go Back</a>
        </div>
        <h3 class="page-header" style="font-weight: bold;">Applied Internships</h3>
        <table class="table">
            <thead>
            <tr>
                <th>Company ID</th>
                <th>Company Name</th>
                <th>Quota</th>
                <th>Cancel</th>
            </tr>
            </thead>
			<?php
			include 'connect.php';
			if(empty($_SESSION['sid'])){
				header("Location: http://dijkstra.cs.bilkent.edu.tr/~ybalcanci/index.php");
				die();
			}

			$sql = "SELECT DISTINCT apply.cid,company.cname, company.quota FROM (student,apply),company WHERE student.sid = apply.sid and apply.cid = company.cid and student.sid = \"" . $_SESSION['sid'] . "\";";
			$result = mysqli_query($conn, $sql);
			while($row = mysqli_fetch_assoc($result)) {
				echo "<tbody>
			<tr>
				<td>" . $row["cid"] . "</td>
				<td>" . $row["cname"] . "</td>
				<td>" . $row["quota"] . "</td>
				<td>
					<form method=\"post\" action=\"unapply.php\">
					<button type=\"submit\" class=\"btn btn-default btn-sm btn-danger btn-cancel\" name=\"" . $row["cid"] . "\">
						<span class=\"glyphicon glyphicon glyphicon-remove\"></span>
					</button>
					</form>
				</td>
			</tr>
			</tbody>";
			}
			?>
        </table>
        <div class="col-md-12 text-center" style="padding-top: 10px">
            <form method="post" action="companies.php">
			<button type="submit" class="btn btn-primary" <?php if (mysqli_num_rows($result) >= 3){ ?> disabled <?php   } ?> id="btn-apply">Apply for new internship</button>
			</form>
		</div>
    </div>
</div>

</body>
</html>
