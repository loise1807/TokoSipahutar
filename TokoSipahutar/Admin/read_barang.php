<?php

include 'koneksi.php';

$perintah = "Select * from barang";
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
	$response["pesan"] = "Data Tidak Tersedia";
}

echo json_encode($response);
mysqli_close($konek);

?>