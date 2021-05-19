<?php

include '../config.php';


    //Config
    $host = host;
    $username = username;
    $password = password;
    $dbname = dbname;

    $konek = mysqli_connect($host,$username,$password,$dbname) or die("Database tidak terhubung");

?>