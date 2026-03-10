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

    @Override
    public String gerarDadosTexto() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'gerarDadosTexto'");
    }
    
}
