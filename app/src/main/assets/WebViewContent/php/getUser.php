<?php
/*
 * Recibe el ID de usuario:
 * userId
 *
 * Retorna un JSON:
 *  name
 *  lastname
 *  email
 */

$userId = 1;//$_POST['userId'];
$sql = "SELECT name, lastname, email
        FROM usuario WHERE UserId = '$userId'";


include('conection.php');

$result = mysqli_query($conexion, $sql);
$conexion->close();


$json = array();
while($row = $result->fetch_array()){
    $json[] = array(
        'name' => $row['name'],
        'lastname' => $row['lastname'],
        'email' => $row['email']
    );
}
$jsonString = json_encode($json, JSON_UNESCAPED_UNICODE);


echo $jsonString;



?>