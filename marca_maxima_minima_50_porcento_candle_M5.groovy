
int NUMERO_CANDLES=2;
int PONTOS_PAVIO=80;


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
        def linhaMinimaM5 = newLineData();
        def linhaMeioM5 = newLineData();
        def linhaMaximaM5 = newLineData();
        
        for ( def j = idxIniM5; ( j <= idxFimM5 ); j++ ) {
            def data = low.date(j);
            linhaMinimaM5.addDated(data, minimaM5);
            linhaMeioM5.addDated(data, (((maximaM5-minimaM5)*0.5)+ minimaM5).toDouble());
            linhaMaximaM5.addDated(data, maximaM5);
        }
        numCandles++;

        linhaMinimaM5.setLabel("M5[F" + numCandles +  "]");
        linhaMeioM5.setLabel("M5[M" + numCandles +  "]");
        linhaMaximaM5.setLabel("M5[T" + numCandles +  "]");
        
        r.add(linhaMinimaM5);
        r.add(linhaMeioM5);
        r.add(linhaMaximaM5);       
        
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
