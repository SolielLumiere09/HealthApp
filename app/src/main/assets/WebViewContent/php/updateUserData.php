<?php

$genero = $_POST['genero'];
$estatura = $_POST['estatura'];
$edad = $_POST['edad'];
$peso = $_POST['peso'];
$actividad = $_POST['actividad'];
$id = $_POST['id'];
$calorias = $_POST['calories'];
$dailyFat = $_POST['dailyFat'];
$dailyCarbs = $_POST['dailyCarbs'];
$dailyProtein = $_POST['dailyProtein'];


$sql = "UPDATE usuario SET 
                   gen = '$genero', 
                   age = '$edad', 
                   height = '$estatura', 
                   weight = '$peso', 
                   activity = '$actividad',
                   dailyCarbs = '$dailyCarbs',
                   dailyCalories = '$calorias',
                   dailyProtein = '$dailyProtein',
                   dailyFat = '$dailyFat'
        WHERE UserId = '$id'";

include ('conection.php');

if($result = mysqli_query($conexion, $sql)){
    $conexion->close();
    echo "1";
}
else{
    $conexion->close();
    echo "0";

}



?>