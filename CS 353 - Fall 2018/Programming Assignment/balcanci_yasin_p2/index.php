<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title>Log in</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    
</head>

<body>

<div class="container">
    <nav class="navbar navbar-inverse bg-primary navbar-fixed-top">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="index.php">Log in</a>
            </div>
        </div>
    </nav>
    <form class="form-signin" action="login.php" method='post' accept-charset='UTF-8' style='width: 30%;margin-left: auto;margin-right: auto;margin-top: 100px;'>
        <h2 class="form-signin-heading">Please log in</h2>
        <label for="inputsname" class="sr-only">Name</label>
        <input type="text" id="inputsname" class="form-control" placeholder="sname" name="sname" required
               autofocus>
        <label for="inputsid" class="sr-only">SID</label>
        <input type="password" id="inputsid" class="form-control" placeholder="sid" name="sid" required>
        <button class="btn btn-lg btn-primary btn-block" style="margin-top: 10px" type="submit" name="logIn">Log in</button>
    </form>
</div>
</body>
</html>
