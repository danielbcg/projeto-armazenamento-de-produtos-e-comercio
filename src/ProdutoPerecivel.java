import java.time.LocalDate;
import java.time.LocalDateTime;
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
    public String gerarDadosTexto() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'gerarDadosTexto'");
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

    


}
