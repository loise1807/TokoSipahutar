<?php

include 'koneksi.php';

$response = array();

if($_SERVER['REQUEST_METHOD'] == 'POST')
{
	$id_barang = (int)$_POST["id_barang"];
	$nama_barang = $_POST["nama_barang"];
	$stok_barang = (int)$_POST["stok"];
	$harga_barang = (int)$_POST["harga"];
	$jenis_barang = $_POST["jenis_barang"];

	$perintah = "UPDATE barang SET nama_barang = '$nama_barang', stok = '$stok_barang' ,harga = '$harga_barang', jenis_barang = '$jenis_barang' WHERE id_barang = '$id_barang'";

	$eksekusi = mysqli_query($konek,$perintah);
	$cek = mysqli_affected_rows($konek);

	if($cek>0){
		$response["kode"] = 1;
        $response["pesan"] = "Data Berhasil Di Ubah";
	}else{
		$response["kode"] = 0;
		$response["pesan"] = "Data Gagal Di Ubah";
	}
}
else{
	$response["kode"] = 0;
	$response["pesan"] = "Tidak ada POST data";	
}

echo json_encode($response);

mysqli_close($konek)

?>