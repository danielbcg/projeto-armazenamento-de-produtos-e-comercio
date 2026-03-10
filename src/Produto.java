import java.text.NumberFormat;
import java.time.LocalDate;

/** 
 * MIT License
 *
 * Copyright(c) 2025 João Caram <caram@pucminas.br>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

public abstract class Produto {
    private static final double MARGEM_PADRAO = 0.2;
    private String descricao;
    private double precoCusto;
    private double margemLucro;
     
    //getters
    public String getDescricao(){
        return descricao;
    }
    public double getPrecoCusto(){
        return precoCusto;
    }
    public double getMargemLucro(){
        return margemLucro;
    }
    
        
    /**
     * Inicializador privado. Os valores default em caso de erro são:
     * "Produto sem descrição", R$0.01, 1 unidade, 0 unidades 
     * @param desc Descrição do produto (mínimo 3 caracteres)
     * @param precoCusto Preço do produto (mínimo 0.01)
     * @param quant Quantidade atual no estoque (mínimo 0)
     * @param estoqueMinimo Estoque mínimo (mínimo 0)
     * @param validade Data de validade passada como parâmetro
     */
    private void init(String desc, double precoCusto, double margemLucro){
               
        if(desc.length()<3 ||precoCusto<=0||margemLucro<=0)
            throw new IllegalArgumentException("Valores inválidos para o produto");
        descricao = desc;
        this.precoCusto = precoCusto;
        this.margemLucro = margemLucro;
    }

    /**
     * Construtor completo. Os valores default em caso de erro são:
     * "Produto sem descrição", R$0.01, 1 unidade, 0 unidades 
     * @param desc Descrição do produto (mínimo 3 caracteres)
     * @param preco Preço do produto (mínimo 0.01)
     * @param quant Quantidade atual no estoque (mínimo 0)
     * @param estoqueMinimo Estoque mínimo (mínimo 0)
     * @param validade Data de validade passada como parâmetro
     */
    public Produto(String desc, double precoCusto, double margemLucro){
        init(desc, precoCusto, margemLucro);
    }

    /**
     * Construtor sem estoque mínimo - fica considerado como 0. 
     * Os valores default em caso de erro são:
     * "Produto sem descrição", R$0.01, 1 unidade, 0 unidades 
     * @param desc Descrição do produto (mínimo 3 caracteres)
     * @param preco Preço do produto (mínimo 0.01)
     * @param quant Quantidade atual no estoque (mínimo 0)
     * @param validade Data de validade passada como parâmetro
     */
    public Produto(String desc, double precoCusto){
        init(desc, precoCusto, MARGEM_PADRAO);
    }

    /**
    * Igualdade de produtos: caso possuam o mesmo nome/descrição.
    * @param obj Outro produto a ser comparado
    * @return booleano true/false conforme o parâmetro possua a descrição igual ou não a este produto.
    */
    @Override
    public boolean equals(Object obj){
     Produto outro = (Produto)obj;
     return this.descricao.toLowerCase().equals(outro.descricao.toLowerCase());
    }

    /**
    * Gera uma linha de texto a partir dos dados do produto
    * @return Uma string no formato "tipo; descrição;preçoDeCusto;margemDeLucro;[dataDeValidade]"
    */
    public abstract String gerarDadosTexto();

    /**
     * Retorna o valor de venda do produto, considerando seu preço de custo e margem de lucro
     * @return Valor de venda do produto (double, positivo)
     */
    public double valorDeVenda(){
        return precoCusto * (1+margemLucro);
    }        
    
    /**
    * Cria um produto a partir de uma linha de dados em formato texto. A linha de dados deve estar de acordo
    com a formatação
    * "tipo; descrição;preçoDeCusto;margemDeLucro;[dataDeValidade]"
    * ou o funcionamento não será garantido. Os tipos são 1 para produto não perecível e 2 para perecível.
    * @param linha Linha com os dados do produto a ser criado.
    * @return Um produto com os dados recebidos
    */
    static Produto criarDoTexto(String linha){
       Produto novoProduto = null;
      /*Você deve implementar aqui a lógica que separa os dados existentes na String linha, verifica se o
      produto é do tipo 1 ou 2 e constrói o objeto adequado, com os dados fornecidos de acordo com seu tipo. O
      objeto construído é retornado pelo método*/
      
      String[] partes = linha.split(";");

      //"tipo; descrição;preçoDeCusto;margemDeLucro;[dataDeValidade]"
      int tipo = Integer.parseInt(partes[0]);
      String desc = partes[1];
      double preçoCusto = Double.parseDouble(partes[2]);
      double margemLucro = Double.parseDouble(partes[3]);
      if(tipo==1){

        novoProduto = new ProdutoNaoPerecivel(desc, preçoCusto,margemLucro);

      }
      else if(tipo==2){

        String dataString = partes[4];
        String[] partesData = dataString.split("/");
        int ano = Integer.parseInt(partesData[0]);
        int mes = Integer.parseInt(partesData[1]);
        int dia = Integer.parseInt(partesData[2]);

        LocalDate dataVal = LocalDate.of(ano,mes,dia);

        //cria produto perecivel com data de validade
        novoProduto = new ProdutoPerecivel(desc, preçoCusto, margemLucro, dataVal);
        
      }
      

       return novoProduto;
   }
 
    /**
     * Descrição em string do produto, contendo sua descrição e o valor de venda.
     *  @return String com o formato:
     * [NOME]: R$ [VALOR DE VENDA]
     */
    @Override
    public String toString(){
        NumberFormat moeda = NumberFormat.getCurrencyInstance();
        
        return String.format("NOME: %s: %s", descricao, moeda.format(valorDeVenda()));
    }
}
