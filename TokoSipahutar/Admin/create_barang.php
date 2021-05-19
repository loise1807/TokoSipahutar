<?php

include 'koneksi.php';

$response = array();

if($_SERVER['REQUEST_METHOD'] == 'POST'){

	$id_admin = (int)$_POST["id_admin"];
	$nama_barang = $_POST["nama_barang"];
	$stok = (int)$_POST["stok"];
	$harga = (int)$_POST["harga"];
	$jenis_barang = $_POST["jenis_barang"];

	$perintah = "INSERT INTO barang (id_admin,nama_barang,stok,harga,jenis_barang) VALUES ($id_admin,'$nama_barang',$stok,$harga,'$jenis_barang')";

	$eksekusi = mysqli_query($konek,$perintah);
	$cek = mysqli_affected_rows($konek);

	if($cek>0){
		$response["kode"] = 1;
		$response["pesan"] = "Tambah Data Berhasil";
	}else{
		$response["kode"] = 0;
		$response["pesan"] = "Gagal Menambah Data";
	}

}else{
	$response["kode"] = 0;
	$response["pesan"] = "Tidak ada POST data";	
}

echo json_encode($response);

mysqli_close($konek)

?>