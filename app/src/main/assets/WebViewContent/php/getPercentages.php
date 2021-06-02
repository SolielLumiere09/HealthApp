<?php

//Recibe id
$id = $_POST['id'];

$sql = "SELECT dailyFat, dailyCalories, dailyCarbs, dailyProtein
        FROM usuario WHERE UserId = '$id'";

include ('conection.php');
$result = mysqli_query($conexion, $sql);

if($result->num_rows){
    $conexion->close();
    $json = Array();
    $row = $result->fetch_array();

    //en unidades
    $dailyFat = $row['dailyFat'];
    $dailyCalories = $row['dailyCalories'];
    $dailyCarbs = $row['dailyCarbs'];
    $dailyProtein = $row['dailyProtein'];
    ///

    $json = Array(
      'dailyCalories' => $dailyCalories,
      'dailyFat' => $dailyFat,
      'dailyProtein' => $dailyProtein,
      'dailyCarbs' => $dailyCarbs
    );

    echo json_encode($json);
}
else{
    $conexion->close();
    echo "0";
}



?>