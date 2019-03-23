
int NUMERO_DIAS=2;


r = newLines();

def	diaAtualAux = 0;
def maximaDia = 0;
def minimaDia = 0;
def idxIniDia = 0;
def idxFimDia = 0;

Calendar cal = Calendar.getInstance();

// Agora, navega nas listas dos valores
def size = high().size();
def high = high();
def low = low();
def close = close();
def numDias = 0;

for ( def i = size-1; i > 0; i-- ) {
    
	def	dataBarraAtual = close.date( i );

    def	maxima = high.value( i );
    def	minima = low.value( i );

	cal.setTime( dataBarraAtual );
    boolean isMaiorIgualNoveHora = cal.get(Calendar.HOUR_OF_DAY) >= 9;
    boolean isMenorDezHora = cal.get(Calendar.HOUR_OF_DAY) < 10;
    def diaAtual = cal.get(Calendar.DAY_OF_MONTH);

    if ( diaAtualAux == 0 ) {
        idxIniDia = i;
        idxFimDia = i;
        diaAtualAux = diaAtual;
        maximaDia = maxima;
        minimaDia = minima;
    } 
	
    boolean barraAtualEhMesmoDia = diaAtualAux == diaAtual;
    if ( barraAtualEhMesmoDia ) {
        idxIniDia = i;
        if( isMaiorIgualNoveHora && isMenorDezHora ) {
            if ( maxima > maximaDia) {
                maximaDia = maxima;
            }
            if ( minima < minimaDia ) {
                minimaDia = minima;
            }
        }
    } else { 
        def linhaMinimaH1 = newLineData();
        def linhaMeioH1 = newLineData();
        def linhaMaximaH1 = newLineData();
        
        for ( def j = idxIniDia; ( j <= idxFimDia ); j++ ) {
            def data = low.date(j);
            linhaMinimaH1.addDated(data, minimaDia);
            linhaMeioH1.addDated(data, (((maximaDia-minimaDia)*0.5)+ minimaDia).toDouble());
            linhaMaximaH1.addDated(data, maximaDia);
        }
        numDias++;

        linhaMinimaH1.setLabel("H1[F" + numDias +  "]");
        linhaMeioH1.setLabel("H1[M" + numDias +  "]");
        linhaMaximaH1.setLabel("H1[T" + numDias +  "]");
        
        r.add(linhaMinimaH1);
        r.add(linhaMeioH1);
        r.add(linhaMaximaH1);       
        
        
        if(numDias > NUMERO_DIAS-1) {
            break;
        }

        idxIniDia = i;
        idxFimDia = i;
        diaAtualAux = diaAtual;
        maximaDia = maxima;
        minimaDia = minima;
    }
}
