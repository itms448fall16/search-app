<?php
//Convert domain to ip
$input = $_GET['input'];//variable to get input from form
$service_url = "http://ip-api.com/json/$input";//ip-api.com api url
$curl = curl_init($service_url); //Initialize a cURL session @url of api
//set cURL option to return string
curl_setopt($curl, CURLOPT_RETURNTRANSFER, true);
//After option set and variable has url this goes to that url and stores returned info in variable
$curl_response = curl_exec($curl);
//error catch if something went wrong
if ($curl_response === false) {
    $info = curl_getinfo($curl);
    curl_close($curl);
    die('error occured during curl exec. Additioanl info: ' . var_export($info));
}
//Closes a cURL session and frees all resources
curl_close($curl);
//decodes all info returned from execution
$decoded = json_decode($curl_response, true);
//error catch
if (isset($decoded->response->status) && $decoded->response->status == 'ERROR') {
    die('error occured: ' . $decoded->response->errormessage);
}
//variable to get ip address from returned info
$ip_add = $decoded['query'];
//Ends geting ip from domain

/*
 * Start of the API for querrying Shodan
 *
 */
$key = "z29mLrj0VSUAeezumCdcCqj8KksFRyUB";
$url_info = "https://api.shodan.io/api-info?key=$key";
$ch = curl_init();
curl_setopt($ch, CURLOPT_SSL_VERIFYPEER, false);
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
curl_setopt($ch, CURLOPT_URL, $url_info);
$result = curl_exec($ch);
curl_close($ch);

$obj = json_decode($result);
print_r($obj);
//END of API for Shodan

/*
 * Start of API for census
 *
 */

?>

<?php

?>

