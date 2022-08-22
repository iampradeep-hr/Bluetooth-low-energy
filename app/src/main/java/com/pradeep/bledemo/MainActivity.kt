package com.pradeep.bledemo

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.pradeep.bledemo.databinding.ActivityMainBinding

const val REQUEST_ENABLE_BT=1001
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
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.BLUETOOTH_CONNECT
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT)
                return
            }

        }else{
            Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
        }

    }
}