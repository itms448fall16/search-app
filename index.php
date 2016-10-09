<!doctype html>

<html lang="en">
<head>
  <meta charset="utf-8" />
  <title>App</title>
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
</head>
<body>
	<div class="container">
		<form action="query.php">
			<div class="row">
				<div class="col-sm-12 text-center">
					<h1>Shodan/Census</h1>
					<input name="query"  placeholder="Search" />
				</div> 
			</div>
			<div class="row">
				<div class="col-sm-12 text-center">
				<button type="submit" class="btn btn-small">Submit</button> 
				</div> 
			</div>
		</form>	
		<div class="row">
			<div class="col-sm-6 text-center">
				<h3>Shodan Results</h3>
			</div>
			<div class="col-sm-6 text-center">
				<h3>Censys Results</h3>
			</div> 					
		</div>
	</div>
</body>
</html>