<?php

include 'koneksi.php';

$response = array();

if($_SERVER['REQUEST_METHOD'] == 'POST'){

	$id_barang = $_POST["id_barang"];

	$perintah = "SELECT * FROM barang where id_barang = $id_barang";
	$eksekusi = mysqli_query($konek,$perintah);
	$cek = mysqli_affected_rows($konek);

	if($cek>0){
		$response["kode"] = 1;
        $response["pesan"] = "Data Tersedia";
        $response["data"] = array();

	while($ambil = mysqli_fetch_object($eksekusi)){
		$F["id_barang"] = $ambil->id_barang;
		$F["id_admin"] = $ambil->id_admin;
		$F["nama_barang"] = $ambil->nama_barang;
		$F["stok"] = $ambil->stok;
		$F["harga"] = $ambil->harga;
		$F["jenis_barang"] = $ambil->jenis_barang;
		$F["gambar"] = $ambil->gambar;

		array_push($response["data"],$F);
	}
	}else{
		$response["kode"] = 0;
		$response["pesan"] = "Data Tidak Tersedia xxxx";
	}

}else{
	$response["kode"] = 0;
	$response["pesan"] = "Tidak ada POST data";	
}

echo json_encode($response);

mysqli_close($konek)

?>