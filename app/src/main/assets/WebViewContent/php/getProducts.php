<?php

$id = $_POST['id'];

$sql = "SELECT  Nombre, 
		barcode 
		FROM producto
        WHERE UserId = '$id'";

include('conection.php');

$result = mysqli_query($conexion, $sql);
$conexion->close();


if($result->num_rows){
    $json = Array();

    while($row = $result->fetch_array()){

        $json [] = Array(
            'Nombre' => $row['Nombre'],
            'Barcode' => $row['barcode']
        );
    }

    echo json_encode($json);

}else{
    exit('0');
}

?>