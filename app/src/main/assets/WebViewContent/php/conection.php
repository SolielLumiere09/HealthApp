<?php

/*
$DB_HOST = 'localhost';
$DB_USER = 'conisoft_Health';
$DB_PASS = 'MIhFh&XH2tgf';
$DB_NAME = 'conisoft_HealthApp_V2';
*/


$DB_HOST = 'localhost';
$DB_USER = 'root';
$DB_PASS = 'harry6627';
$DB_NAME = 'healthappv2';


$conexion = mysqli_connect($DB_HOST, $DB_USER, $DB_PASS, $DB_NAME);


$conexion->set_charset('utf8')
?>