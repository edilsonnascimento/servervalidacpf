import java.io.*;
import java.net.*;
import java.sql.SQLOutput;


public class Servidor {

    static String cpf = "";

    public static void main(String[] args) {
        try {
            ServerSocket servidor = new ServerSocket(54333);
            System.out.println("A porta 54333 está aberta");

            Socket conexao = servidor.accept();
            System.out.println("Conexão iniciada...");

            DataInputStream entrada = new DataInputStream(conexao.getInputStream());
            DataOutputStream saida = new DataOutputStream(conexao.getOutputStream());
            System.out.println("Iniciado canais de comunicação servidor...");

            cpf = entrada.readUTF();

            String retorno = "";
            if(validaCpf(cpf)){
                retorno = "TRUE";
            }else{
                retorno = "FALSE";

            }

            saida.writeUTF(retorno);
            System.out.println("Enviado resposta oa cliente...");

            conexao.close();
            servidor.close();
            System.out.println("Conexão finalizada...");

        } catch (IOException e) {
            System.out.println("Não foi possível estabelecer conexão com o servidor...");
        }

    }

    private static boolean validaCpf(String cpfParametro) {

        if ( cpfParametro == null ){
            return false;
        }
        else{
            String cpfGerado = "";
            cpf = cpfParametro;

            removerCaracteres();
            if ( verificarSeTamanhoInvalido(cpf) )
                return false;

            if ( verificarSeDigIguais(cpf) )
                return false;
        }
        return true;
    }

    private static String calculoComCpf(String cpfGerado) {
        return  cpfGerado;
    }

    private static boolean verificarSeDigIguais(String cpf) {
        //char primDig = cpf.charAt(0);
        char primDig = '0';
        char [] charCpf = cpf.toCharArray();
        for( char c: charCpf  )
            if ( c != primDig )
                return false;
        return true;
    }

    private static boolean verificarSeTamanhoInvalido(String cpf) {
        if ( cpf.length() != 11 )
            return true;
        return false;
    }

    private static void removerCaracteres() {
        cpf = cpf.replace("-","");
        cpf = cpf.replace(".","");
    }
}
