<?php
/*
 * Convert domain to ip
 *
 */

$input = $_GET['input'];//variable to get input from form
$service_url = "http://ip-api.com/json/$input";//ip-api.com api url
$ch = curl_init();
curl_setopt($ch, CURLOPT_SSL_VERIFYPEER, false);
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
curl_setopt($ch, CURLOPT_URL, $service_url);
$result = curl_exec($ch);
//After option set and variable has url this goes to that url and stores returned info in variable
$curl_response = curl_exec($ch);
//error catch if something went wrong
if ($curl_response === false) {
    $info = curl_getinfo($ch);
    curl_close($ch);
    die('error occured during curl exec. Additioanl info: ' . var_export($info));
}
//Closes a cURL session and frees all resources
curl_close($ch);
//decodes all info returned from execution
$decoded = json_decode($curl_response, true);
//error catch
if (isset($decoded->response->status) && $decoded->response->status == 'ERROR') {
    die('error occured: ' . $decoded->response->errormessage);
}
//variable to get ip address from returned info
$ip_add = $decoded['query'];
print($ip_add);
//pass ip address to functions
shodan($ip_add);
//censys($ip_add);
//Ends geting ip from domain



/*
 * Start of the API for Shodan
 *
 */
function shodan($i)
{
    $ip = $i;
    $key = "z29mLrj0VSUAeezumCdcCqj8KksFRyUB";
    $url_info = "https://api.shodan.io/shodan/host/$ip?key=$key";
    echo $url_info;//remove after testing
    $ch = curl_init();
    curl_setopt($ch, CURLOPT_SSL_VERIFYPEER, false);
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
    curl_setopt($ch, CURLOPT_URL, $url_info);
    $result = curl_exec($ch);
    $err = curl_error($ch);
    curl_close($ch);
    if(empty($err)) {
        $obj = json_decode($result, true);
        /*
         * using print_r() takes browser to the site
         */
        print_r($obj);
    }
    else{
        var_dump($err);
    }
}
/*
 * Orgional work before I(chris) Started working on it
function orgShodan(){
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
} */
//END of API for Shodan*/


/*
 * Start of API for census
 *
 */
/*
function censys($i)
{
    echo "<br /><br /><br /><hr>";//ip address passed in from ip-api.com
    $ip = $i;
    /*
     * url to censys api
     * unsure how to authenticate
     * error: {"status": "error", "error_type": "unathorized", "error": "Unauthorized. You must authenticate with a valid API ID and secret."}
     */
/*
    $API_ID="7ac0c4fa-97d3-4d8d-801d-085f7a279928";
    $Secret = "X0TjXpDMzWTgV1F1QVFukOSyMImL7Efm";
    $censys_url = "https://www.censys.io/api/v1/view/ipv4/$ip";
    $ch = curl_init();
    curl_setopt($ch, CURLOPT_SSL_VERIFYPEER, false);
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
    curl_setopt($ch, CURLOPT_URL, $censys_url);
    $censys_curl_response = curl_exec($ch);
    $err = curl_error($ch);
    curl_close($ch);
    if(empty($err)){
        print_r($censys_curl_response);
    }
    else {
        var_dump($err);
    }
    //$decoded = json_decode($curl_response, true);
}*/
?>

