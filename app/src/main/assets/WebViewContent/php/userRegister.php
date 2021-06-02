<?php

/*
    Recibe las llaves:
    name
    lastname
    email
    pass

    Retorna:
    JSON
    id
    name
    lastname
    email
    pass
 */


$name = $_POST['name'];
$lastname = $_POST['lastname'];
$email = $_POST['email'];
$pass = $_POST['pass'];

$sql = "INSERT INTO usuario(name, lastname, email, pass) 
        VALUES ('$name', '$lastname', '$email', '$pass')";

include ('conection.php');
if($result = mysqli_query($conexion, $sql)){
    $id = mysqli_insert_id($conexion);

    $sql = "SELECT name, lastname, email, pass
            FROM usuario WHERE UserId = '$id'";

    $result = mysqli_query($conexion, $sql);
    $conexion->close();

    $json = Array();
    $row = $result->fetch_array();

    $json = Array(
      'id' => $id,
      'name' => $row['name'],
      'lastname'=> $row['lastname'],
      'email' => $row['email'],
      'pass' => $row['pass']
    );

    echo json_encode($json);

}else{
    $conexion->close();
    echo "0";
}

?>