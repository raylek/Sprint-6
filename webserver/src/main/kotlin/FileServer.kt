import ru.sber.filesystem.VFilesystem
import ru.sber.filesystem.VPath
import java.io.IOException
import java.io.PrintWriter
import java.net.ServerSocket
import java.net.Socket

class FileServer {

    @Throws(IOException::class)
    fun run(socket: ServerSocket, fs: VFilesystem) {

        while (true) {
            socket.accept().use {socket ->
                handle(socket, fs)
            }
        }
    }

    private fun handle(socket: Socket, fs: VFilesystem) {
        socket.getInputStream().use { inputStream ->
            inputStream.bufferedReader().use { reader ->
                val clientRequest = reader.readLine().trim().split(" ")
                val vPath = VPath(clientRequest[1])
                val readFile = fs.readFile(vPath)
                val serverResponse = if (readFile == null) {
                    "${clientRequest[2]} 404 Not Found\r\n" +
                            "Server: FileServer"
                } else {
                    "${clientRequest[2]} 200 OK\r\n" +
                            "Server: FileServer\r\n" +
                            "\r\n$readFile"
                }

                PrintWriter(socket.getOutputStream()).use { writer ->
                    writer.println(serverResponse)
                    writer.flush()
                }
            }
        }
    }
}