<?php

include '../connection.php';

if($_POST){

    //POST DATA
    $nama = filter_input(INPUT_POST, 'nama', FILTER_SANITIZE_STRING);
    $username = filter_input(INPUT_POST, 'username', FILTER_SANITIZE_STRING);
    $password = filter_input(INPUT_POST, 'password', FILTER_SANITIZE_STRING);
    $jenis_kelamin = filter_input(INPUT_POST, 'jenis_kelamin', FILTER_SANITIZE_STRING);
    $no_telp = filter_input(INPUT_POST, 'no_telp', FILTER_SANITIZE_STRING);
    

    $response = [];

    //Cek username_admin didalam databse
    $userQuery = $connection->prepare("SELECT * FROM admin where username = ?");
    $userQuery->execute(array($username));

    // Cek username_admin apakah ada tau tidak
    if($userQuery->rowCount() != 0){
        // Beri Response
        $response['status']= false;
        $response['message']='Akun sudah digunakan';
    } else {
        $insertAccount = 'INSERT INTO admin (nama, username, password, jenis_kelamin, no_telp) values (:nama, :username, :password, :jenis_kelamin, :no_telp)';
        $statement = $connection->prepare($insertAccount);

        try{
            //Eksekusi statement db
            $statement->execute([
                ':nama' => $nama,
                ':username' => $username,
                ':password' => md5($password),
                ':jenis_kelamin' => $jenis_kelamin,
                ':no_telp' => $no_telp
            ]);

            //Beri response
            $response['status']= true;
            $response['message']='Akun berhasil didaftar';
            $response['data'] = [
                'nama' => $nama,
                'username' => $username,
                'jenis_kelamin' => $jenis_kelamin,
                'no_telp' => $no_telp
            ];
        } catch (Exception $e){
            die($e->getMessage());
        }

    }
    
    //Jadikan data JSON
    $json = json_encode($response, JSON_PRETTY_PRINT);

    //Print JSON
    echo $json;
}