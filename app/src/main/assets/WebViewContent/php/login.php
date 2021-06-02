<?php
/*
 * Recibe:
 * email
 * pass
 *
 * retorna
 * JSON (TRUE)
    id
    name
    lastname
    email
    pass

    0: FALSE
 */

$email = $_POST['email'];
$pass = $_POST['pass'];


$sql = "SELECT UserId, name, lastname, email, pass
        FROM usuario WHERE email = '$email' AND pass = '$pass'";


include ('conection.php');
$result = mysqli_query($conexion, $sql);
$conexion->close();
if($result->num_rows){

    $json = Array();
    $row = $result->fetch_array();

    $json = Array(
        'id' => $row['UserId'],
        'name' => $row['name'],
        'lastname'=> $row['lastname'],
        'email' => $row['email'],
        'pass' => $row['pass']
    );


    echo json_encode($json);

}else{
    exit('0');
}

?>