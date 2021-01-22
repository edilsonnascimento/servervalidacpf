import java.io.*;
import java.net.Socket;

public class Cliente {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 54333);
            System.out.println("Conexão com o servidor na porta 54333 iniciada...");

            DataInputStream entrada = new DataInputStream(socket.getInputStream());
            DataOutputStream saida = new DataOutputStream(socket.getOutputStream());
            System.out.println("Iniciado canais de comunicação cliente...");

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Digite um número: ");
            String cpf = br.readLine();

            saida.writeUTF(cpf);
            System.out.println("Enviado valor ao servidor...");

            System.out.println("Retorno do servidor: " + entrada.readUTF());

            socket.close();

        } catch (IOException e) {
            System.out.println("Não foi possível estabelecer a conexão");
        }

    }
}
