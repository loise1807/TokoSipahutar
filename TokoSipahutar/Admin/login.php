<?php

include '../connection.php';

if($_POST){

    //Data
    $username = $_POST['username'] ?? '';
    $password = $_POST['password'] ?? '';

    $response = []; //Data Response

    //Cek username_admin didalam databse
    $userQuery = $connection->prepare("SELECT * FROM admin where username = ?");
    $userQuery->execute(array($username));
    $query = $userQuery->fetch();

    if($userQuery->rowCount() == 0){
        $response['status'] = false;
        $response['message'] = "Username Tidak Terdaftar";
    } else {
        // Ambil password_admin di db

        $passwordDB = $query['password'];

        if(strcmp(($password),$passwordDB) === 0){
            $response['status'] = true;
            $response['message'] = "Login Berhasil";
            $response['data'] = [
                'id_admin' => $query['id_admin'],
                'nama' => $query['nama'],
                'username' => $query['username'],
                'jenis_kelamin' => $query['jenis_kelamin'],
                'no_telp' => $query['no_telp']
            ];
        } else {
            $response['status'] = false;
            $response['message'] = "Password anda salah";
        }
    }

    //Jadikan data JSON
    $json = json_encode($response, JSON_PRETTY_PRINT);

    //Print
    echo $json;

}