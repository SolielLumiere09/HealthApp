<?php

    $id = $_POST['id'];
    $dailyFat = $_POST['dailyFat'];
    $dailyCarbs = $_POST['dailyCarbs'];
    $dailyProtein = $_POST['dailyProtein'];
    $dailyCalories = $_POST['dailyCalories'];

    $sql = "UPDATE usuario
            SET dailyFat = '$dailyFat',
                dailyProtein = '$dailyProtein',
                dailyCarbs = '$dailyCarbs',
                dailyCalories = '$dailyCalories'
            WHERE UserId = '$id'";
    include ('conection.php');

    if(mysqli_query($conexion, $sql)){
        $conexion->close();
        echo "1";
    }else{
        $conexion->close();
        echo "0";
    }

?>