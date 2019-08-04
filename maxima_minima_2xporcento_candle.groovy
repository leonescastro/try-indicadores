//ENTRADA DE DADOS//
int TAMANHO_LINHA=1;
int NUMERO_CANDLE=15;
//FIM ENTRADA DE DADOS//

r = newLines();

def low = low();
def high = high();
def size = low.size()

if(size > TAMANHO_LINHA+1) {
    for (int i=size-1; i>size-NUMERO_CANDLE-1; i--) {
        // Variaveis de apoio
        def linha0 = newLineData();
        def linha100 = newLineData();
        def linhaMenos100 = newLineData();
        def linha200 = newLineData();
        def maxima = high.value(i);
        def minima = low.value(i);
        def menos100 = minima - (maxima-minima);
        def mais100 = maxima + (maxima-minima);
        for (int j=i-TAMANHO_LINHA; j<=i; j++) {
            def data = low.date(j);
            linha0.addDated(data, minima);
            linha100.addDated(data, maxima);
            linhaMenos100.addDated(data, menos100.toDouble());
            linha200.addDated(data, mais100.toDouble());
        }
        r.add(linha0);
        r.add(linha100);
        r.add(linhaMenos100);
        r.add(linha200);
    }
}      
