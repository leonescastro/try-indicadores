
int NUMERO_CANDLES=2;


r = newLines();

def	minutoAtualAux = 0;
def maximaM5 = 0;
def minimaM5 = 0;
def idxIniM5 = 0;
def idxFimM5 = 0;

Calendar cal = Calendar.getInstance();

// Agora, navega nas listas dos valores
def size = high().size();
def high = high();
def low = low();
def close = close();
def numCandles = 0;

for ( def i = size-1; i > 0; i-- ) {
    
	def	dataBarraAtual = close.date( i );

    def	maxima = high.value( i );
    def	minima = low.value( i );

	cal.setTime( dataBarraAtual );
    def minutoAtual = cal.get(Calendar.MINUTE);
    minutoAtual = minutoAtual == 0 ? 60 :  minutoAtual;

    if ( minutoAtualAux == 0 ) {
        idxIniM5 = i;
        idxFimM5 = i;
        minutoAtualAux = minutoAtual;
        maximaM5 = maxima;
        minimaM5 = minima;
    } 
	
    if ( minutoAtual % 5 != 0 ) {
        idxIniM5 = i;
        if ( maxima > maximaM5) {
            maximaM5 = maxima;
        }
        if ( minima < minimaM5 ) {
            minimaM5 = minima;
        }
        
    } else { 
        def linhaMinimaH1 = newLineData();
        def linhaMeioH1 = newLineData();
        def linhaMaximaH1 = newLineData();
        
        for ( def j = idxIniM5; ( j <= idxFimM5 ); j++ ) {
            def data = low.date(j);
            linhaMinimaH1.addDated(data, minimaM5);
            linhaMeioH1.addDated(data, (((maximaM5-minimaM5)*0.5)+ minimaM5).toDouble());
            linhaMaximaH1.addDated(data, maximaM5);
        }
        numCandles++;

        linhaMinimaH1.setLabel("M5[F" + numCandles +  "]");
        linhaMeioH1.setLabel("M5[M" + numCandles +  "]");
        linhaMaximaH1.setLabel("M5[T" + numCandles +  "]");
        
        r.add(linhaMinimaH1);
        r.add(linhaMeioH1);
        r.add(linhaMaximaH1);       
        
        if(numCandles > NUMERO_CANDLES-1) {
            break;
        }

        idxIniM5 = i;
        idxFimM5 = i;
        minutoAtualAux = minutoAtual;
        maximaM5 = maxima;
        minimaM5 = minima;
    }
}
