<?php

include '../connection.php';

if($_POST){

    //POST DATA
    $username = filter_input(INPUT_POST, 'username', FILTER_SANITIZE_STRING);
    $password = filter_input(INPUT_POST, 'password', FILTER_SANITIZE_STRING);
    $nama = filter_input(INPUT_POST, 'nama', FILTER_SANITIZE_STRING);
    $jenis_kelamin = filter_input(INPUT_POST, 'jenis_kelamin', FILTER_SANITIZE_STRING);
    $alamat = filter_input(INPUT_POST, 'alamat', FILTER_SANITIZE_STRING);
    $no_tlp = filter_input(INPUT_POST, 'no_tlp', FILTER_SANITIZE_STRING);

    $response = [];

    //Cek username didalam databse
    $userQuery = $connection->prepare("SELECT * FROM pembeli where username = ?");
    $userQuery->execute(array($username));

    // Cek username apakah ada tau tidak
    if($userQuery->rowCount() != 0){
        // Beri Response
        $response['status']= false;
        $response['message']='Akun sudah digunakan';
    } else {
        $insertAccount = 'INSERT INTO pembeli (nama,username,password,jenis_kelamin,alamat,no_tlp) values (:nama, :username, :password, :jenis_kelamin, :alamat, :no_tlp)';
        $statement = $connection->prepare($insertAccount);

        try{
            //Eksekusi statement db
            $statement->execute([
                ':nama' => $nama,
                ':username' => $username,
                ':password' => md5($password),
                ':jenis_kelamin' => $jenis_kelamin,
                ':alamat' => $alamat,
                ':no_tlp' => $no_tlp
            ]);

            //Beri response
            $response['status']= true;
            $response['message']='Akun berhasil didaftar';
            $response['data'] = [
                'nama' => $nama,
                'username' => $username,
                'jenis_kelamin' => $jenis_kelamin,
                'alamat' => $alamat,
                'no_tlp' => $no_tlp
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