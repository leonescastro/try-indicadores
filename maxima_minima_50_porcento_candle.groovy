//ENTRADA DE DADOS//
int PERCENT_CANDLE = 50; //alterar esse numero para modificar o numero de canlde marcados
int TAMANHO_LINHA=1;
int NUMERO_CANDLE=5;
//FIM ENTRADA DE DADOS//

r = newLines();

def low = low();
def high = high();
def size = low.size()

if(size > TAMANHO_LINHA+1) {
    for (int i=size-1; i>size-NUMERO_CANDLE-1; i--) {
        // Variaveis de apoio
        def linha0 = newLineData();//excluir essa linha para nao marcar minima
        def linha50 = newLineData();
        def linha100 = newLineData();//excluir essa linha para nao marcar a maxima
        def maxima = high.value(i);
        def minima = low.value(i);
        def pavio25H = ((maxima-minima)*(PERCENT_CANDLE/100))+ minima;
        def pavio25H = ((maxima-minima)*(PERCENT_CANDLE/100))+ minima;
        def metadePavioH = ((maxima-minima)*(PERCENT_CANDLE/100))+ minima;
        def metadeCorpo = ((maxima-minima)*(PERCENT_CANDLE/100))+ minima;
        for (int j=i-TAMANHO_LINHA; j<=i; j++) {
            def data = low.date(j);
            linha0.addDated(data, minima); //excluir essa linha para nao marcar minima
            linha50.addDated(data, metadeCorpo.toDouble());
            linha100.addDated(data, maxima); //excluir essa linha para nao marcar a maxima
        }
        r.add(linha0);//excluir essa linha para nao marcar minima
        r.add(linha50);
        r.add(linha100);//excluir essa linha para nao marcar a maxima
    }
}      
