<?php
$key = "z29mLrj0VSUAeezumCdcCqj8KksFRyUB";
$url_info = "https://api.shodan.io/api-info?key=$key";
$ch = curl_init();
curl_setopt($ch, CURLOPT_SSL_VERIFYPEER, false);
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
curl_setopt($ch, CURLOPT_URL, $url_info);
$result = curl_exec($ch);
curl_close($ch);

$obj = json_decode($result);
echo $result;
?>