//ENTRADA DE DADOS//
int PERCENT_CANDLE = 50;
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
        def linha0 = newLineData();
        def linha50 = newLineData();
        def linha100 = newLineData();
        def maxima = high.value(i);
        def minima = low.value(i);
        def metadePreco = ((maxima-minima)*(PERCENT_CANDLE/100))+ minima;
        for (int j=i-TAMANHO_LINHA; j<=i; j++) {
            def data = low.date(j);
            linha0.addDated(data, minima);
            linha50.addDated(data, metadePreco.toDouble());
            linha100.addDated(data, maxima);
        }
        r.add(linha0);
        r.add(linha50);
        r.add(linha100);
    }
}      
