public class ProdutoNaoPerecivel extends Produto{

    public ProdutoNaoPerecivel(String desc, double precoCusto, double margemLucro){
        super(desc, precoCusto, margemLucro);
    }

    public ProdutoNaoPerecivel(String desc, double precoCusto){
        super(desc, precoCusto);
    }

    @Override
    public double valorDeVenda(){
        return super.valorDeVenda();
    }
 
    /**
    * Gera uma linha de texto a partir dos dados do produto. Preço e margem de lucro vão formatados com 2
    casas decimais.
    * Data de validade vai no formato dd/mm/aaaa
    * @return Uma string no formato "1; descrição;preçoDeCusto;margemDeLucro"
    */
    @Override
    public String gerarDadosTexto() {
    /*Você deve implementar aqui a lógica que monta a String com os atributos do objeto ProdutoPerecivel,
    respeitando o formato do arquivo de dados. */

    return String.format("1;%s;%.2f;%.2f", getDescricao(), getPrecoCusto(),
                                                   getMargemLucro());
  }
    
}
