<?php

/* Recibe:
 *  groupId INT
 *
 *  Retorna
 * ALIMENTOS,
 * Cantidad_sugerida, 0
 * Unidad, I
 * Peso_bruto_redondeado_g,
 * Peso_neto_g,
 * Energia_Kcal, II
 * Proteina_g, V
 * Lípidos_g, IV
 * Hidratos_de_carbono_g, III
 * Fibra_g,
 * Acido_Fólico_µg,
 * Calcio_mg,
 * Hierro_mg,
 * sodio_mg,
 * Azúcar_por_equivalente_g, ***
 * indice_glicémuco,
 * Carga_glicémica
 */

include ("conection.php");

const cerealescongrasa = "0" ;
const cerealessingrasa = "1" ;
const frutas = "2";
const aoa_bajo_grasa = "3";
const aor_bajo_aporte_grasas = "4";


if(isset($_POST['groupId'])){
    $groupId = $_POST['groupId'];

    switch ($groupId){
        case cerealescongrasa :
                $sql = "SELECT * FROM cerealescongrasa";
                break;
        case cerealessingrasa:
            $sql = "SELECT * FROM cerealessingrasa";
            break;

        case frutas:
            $sql = "SELECT * FROM frutas";
            break;

        case aoa_bajo_grasa:
            $sql = "SELECT * FROM aoa_bajo_grasas" ;
            break;

        case aor_bajo_aporte_grasas;
            $sql = "SELECT * FROM aor_bajo_aporte_grasas";
            break;
    }

}

$res = mysqli_query($conexion, $sql);
$json = array();

while($row = mysqli_fetch_array($res)){
    $json [] = ([
        'Unidad' => $row['Unidad'],
        'Cantidad_sugerida' => $row['Cantidad_sugerida'],
        'ALIMENTOS' => $row['ALIMENTOS'],
        'Energia_Kcal' => $row['Energia_Kcal'],
        'Hidratos_de_carbono_g' => $row['Hidratos_de_carbono_g'],
        'Lipidos_g' => $row['Lipidos_g'],
        'Proteina_g' => $row['Proteina_g'],
        'Peso_neto_g' => $row['Peso_neto_g']
    ]);
}
echo json_encode($json);

$conexion->close();

?>