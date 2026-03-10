import java.util.Scanner;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.time.LocalDate;

public class Comercio {

    //pra inclusao de novos produtos no vetor

    static final int MAX_NOVOS_PRODUTOS=10;

    //nome do arquivo de dados

    static String nomeArquivoDados;

    //scanner para a leitura do teclado

    static Scanner teclado;

    //vetor de produtos cadastrados. sempre tera espaço pra 10 novos produtso a cada
    //execuçao

    static Produto[] produtosCadastrados;

    //quantidade de produtos cadastrados atualmente no vetor

    static int quantosProdutos;

    //gera um efeito de pausa na CLI. Espera um enter pra continaur

    static void pausa(){
        System.out.println("Pressione 'enter' para continuar");
        teclado.nextLine();
    }

    //CABEÇALHO PRINCIPAL DA CLI DO SISTEMA
    static void cabecalho(){
        System.out.println("AEDII COMÉRCIO DE COISINHAS");
        System.out.println("===========================");
    }

    /** Imprime o menu principal, lê a opção do usuário e a retorna (int).
    * Perceba que poderia haver uma melhor modularização com a criação de uma classe Menu.
    * @return Um inteiro com a opção do usuário.
    */

    static int menu(){
        cabecalho();
        System.out.println("1 - Listar todos os produtos");
        System.out.println("2 - Procurar e listar um produto");
        System.out.println("3 - Cadastrar novo produto");
        System.out.println("0 - Sair");
        System.out.println("Digite sua opção: ");
        return Integer.parseInt(teclado.nextLine());
    }

    /**
    * Lê os dados de um arquivo texto e retorna um vetor de produtos. Arquivo no formato
    * N (quantiade de produtos) <br/>
    * tipo; descrição;preçoDeCusto;margemDeLucro;[dataDeValidade] <br/>
    * Deve haver uma linha para cada um dos produtos. Retorna um vetor vazio em caso de problemas com o
    arquivo.
    * @param nomeArquivoDados Nome do arquivo de dados a ser aberto.
    * @return Um vetor com os produtos carregados, ou vazio em caso de problemas de leitura.
    */
    static Produto[] lerProdutos(String nomeArquivoDados) {
    
    /*Ler a primeira linha do arquivoDados contendo a quantidade de produtos armazenados no arquivo.
    Instanciar o vetorProdutos com o tamanho necessário para acomodar todos os produtos do arquivo + o
    espaço reserva MAX_NOVOS_PRODUTOS. Após isso, ler uma após a outra o restante das linhas do
    arquivo, convertendo, a cada leitura de linha, seus dados em objetos do tipo Produto (utilizar o método
    criarDoTexto()). Cada objeto Produto instanciado será armazenado no vetorProdutos.*/
    
    Produto[] vetorProdutos;

    try{
        //cria arquivo
        Scanner arquivo = new Scanner(new File(nomeArquivoDados));
                
        //atribui quantidade de produtos dentro do arquivo
        int quantidade = Integer.parseInt(arquivo.nextLine());

        vetorProdutos = new Produto[quantidade+MAX_NOVOS_PRODUTOS];

        for(int i=0; i<quantidade; i++){
            String linha = arquivo.nextLine();
            vetorProdutos[i] = Produto.criarDoTexto(linha);
        }
    
    }catch(Exception e){
        vetorProdutos = new Produto[MAX_NOVOS_PRODUTOS];
    }


return vetorProdutos;


}




}