<?php

include 'koneksi.php';

$response = array();

if($_SERVER['REQUEST_METHOD'] == 'POST'){

	$id_barang = (int)$_POST["id_barang"];

	$perintah = "DELETE FROM barang where id_barang = $id_barang";

	$eksekusi = mysqli_query($konek,$perintah);
	$cek = mysqli_affected_rows($konek);

	if($cek>0){
		$response["kode"] = 1;
		$response["pesan"] = "Hapus Data Berhasil";
	}else{
		$response["kode"] = 0;
		$response["pesan"] = "Gagal Menghapus Data";
	}

}else{
	$response["kode"] = 0;
	$response["pesan"] = "Tidak ada POST data";	
}

echo json_encode($response);

mysqli_close($konek)

?>