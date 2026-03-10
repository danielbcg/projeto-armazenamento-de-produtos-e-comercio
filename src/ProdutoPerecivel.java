import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class ProdutoPerecivel extends Produto{
    private static double DESCONTO = 0.25;
    private static int PRAZO_DESCONTO = 7;
    private LocalDate dataDeValidade; 

    public ProdutoPerecivel(String desc, double precoCusto, double margemLucro,
        LocalDate validade){
            super(desc, precoCusto, margemLucro);
            
            //data de validade anterior ao dia atual
            if(validade.isBefore(LocalDate.now())){
                throw new IllegalArgumentException("Data de validade anterior ao dia atual");
            }else{
                dataDeValidade = validade;
            }
    }

    

    @Override
    public double valorDeVenda(){
        LocalDate hoje = LocalDate.now();

        //calcula quantos dias falta pra vencer
        long diasAteVencer = ChronoUnit.DAYS.between(hoje,dataDeValidade);
        
        if(diasAteVencer<=PRAZO_DESCONTO){
            return super.valorDeVenda()*(1-DESCONTO); //desconto é de 25%
        }
        else{
            return super.valorDeVenda();
        }

    }

    
    /**
    * Gera uma linha de texto a partir dos dados do produto. Preço e margem de lucro vão formatados com 2
    casas decimais.
    * Data de validade vai no formato dd/mm/aaaa 
    * @return Uma string no formato "2; descrição;preçoDeCusto;margemDeLucro;dataDeValidade"
    */
    @Override
    public String gerarDadosTexto() {
    //*Você deve implementar aqui a lógica que monta a String com os atributos do objeto ProdutoPerecivel,
    //respeitando o formato do arquivo de dados. */
    
    return String.format("2;%s;%.2f;%.2f;%02d/%02d/%02d",
        getDescricao(),
        getPrecoCusto(),
        getMargemLucro(),
        dataDeValidade.getDayOfMonth(),
        dataDeValidade.getMonthValue(),
        dataDeValidade.getYear()
    );

}

}
