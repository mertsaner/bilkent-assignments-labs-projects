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
        <h3 class="page-header" style="font-weight: bold;">Companies to Apply</h3>
        <table class="table">
            <thead>
            <tr>
                <th>Company ID</th>
                <th>Company Name</th>
                <th>Quota</th>
            </tr>
            </thead>
			<?php
			include 'connect.php';
			if(empty($_SESSION['sid'])){
				header("Location: ./index.php");
				die();
			}

			$sql1 = "CREATE TEMPORARY TABLE IF NOT EXISTS sizes SELECT cid, count(sid) as cnt FROM apply group by cid;";
			$sql2 = "SELECT * FROM company
					WHERE cid NOT IN (
						SELECT DISTINCT apply.cid FROM (student,apply),company 
						WHERE student.sid = apply.sid and apply.cid = company.cid and student.sid = \"" . $_SESSION['sid'] . "\")
					AND cid NOT IN (
						SELECT DISTINCT company.cid FROM sizes,company 
						WHERE sizes.cid = company.cid AND cnt = quota);";
			$sql3 = "DROP TABLE IF EXISTS sizes;";
			mysqli_query($conn, $sql1);
			$result = mysqli_query($conn, $sql2);
			mysqli_query($conn, $sql3);
			while($row = mysqli_fetch_assoc($result)) {
				echo "<tbody>
			<tr>
				<td>" . $row["cid"] . "</td>
				<td>" . $row["cname"] . "</td>
				<td>" . $row["quota"] . "</td>
			</tr>
			</tbody>";
			}
			?>
        </table>
        <form class="form" action="apply.php" method='post' accept-charset='UTF-8'>
        <input type="text" id="cid" placeholder="cid" name="cid" style="margin-top:3px; margin-bottom:3px;"required>
        <button class="btn" style="margin-top: 10px" type="submit" name="apply">Apply</button>
		</form>
    </div>
</div>

</body>
</html>
