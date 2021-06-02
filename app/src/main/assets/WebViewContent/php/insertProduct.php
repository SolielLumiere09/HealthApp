<?php
/*
 * Recibe
 * id
 * barcode
 *
 */

$id = $_POST['id'];
$barcode = $_POST['barcode'];
$nombre = $_POST['Nombre'];

$sql = "INSERT INTO producto(UserId, barcode, Nombre)
        VALUES ('$id', '$barcode', '$nombre')";

include('conection.php');

if(mysqli_query($conexion, $sql)){
    $conexion->close();
    echo "1";
}
else{
    $conexion->close();
    echo "0";
}


?>
