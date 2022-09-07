package com.pradeep.bledemo

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.pradeep.bledemo.databinding.ActivityMainBinding

const val REQUEST_ENABLE_BT=109
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.toggleBluetooth.setOnClickListener {
            toggleBluetooth()
        }
    }

    private fun toggleBluetooth() {
        val bluetoothManager:BluetoothManager = getSystemService(BluetoothManager::class.java)
        val bluetoothAdapter:BluetoothAdapter? = bluetoothManager.adapter
        if (bluetoothAdapter==null){
            Toast.makeText(this, "Device dosen't support Bluetooth!", Toast.LENGTH_SHORT).show()
            return
        }

        if (!bluetoothAdapter.isEnabled){
            val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            getResult.launch(enableBtIntent)

        }

    }



    private val getResult=
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ){
            if (it.resultCode== RESULT_OK){
                    Snackbar.make(binding.root,"Bluetooth is enabled", Snackbar.LENGTH_SHORT).show()
            }else if (it.resultCode == RESULT_CANCELED){
                Snackbar.make(binding.root,"Bluetooth is not enabled", Snackbar.LENGTH_SHORT).show()
            }
        }








}