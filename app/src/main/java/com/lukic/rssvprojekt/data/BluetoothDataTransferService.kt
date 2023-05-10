package com.lukic.rssvprojekt.data

import android.bluetooth.BluetoothSocket
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import java.io.IOException

class BluetoothDataTransferService(
    private val socket: BluetoothSocket
) {

    fun listenForIncomingMessages(): Flow<String> = flow {
        if (socket.isConnected.not()) {
            return@flow
        }

        val buffer = ByteArray(1024)
        while (true) {
            try {
                socket.inputStream.read(buffer)
            } catch (e: IOException) {
                e.printStackTrace()
                socket.close()
                return@flow
            }

            emit(buffer.decodeToString())
        }
    }.flowOn(Dispatchers.IO)

    suspend fun sendMessage(bytes: ByteArray): Boolean = withContext(Dispatchers.IO) {
        return@withContext try {
            socket.outputStream.write(bytes)
            true
        } catch (e: IOException) {
            e.printStackTrace()
            false
        }
    }
}
