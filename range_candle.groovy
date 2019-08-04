
int NUMERO_DIAS=12;


r = newLines();

def	diaAtualAux = 0;
def fechamentoDia = 0;
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
    def	fechamento = close.value( i );

	cal.setTime( dataBarraAtual );
    def diaAtual = cal.get(Calendar.DAY_OF_MONTH);

    if ( diaAtualAux == 0 ) {
        idxIniDia = i;
        idxFimDia = i;
        diaAtualAux = diaAtual;
        fechamentoDia = fechamento;
    } 
	
    boolean barraAtualEhMesmoDia = diaAtualAux == diaAtual;
    if ( barraAtualEhMesmoDia ) {
        idxIniDia = i;      
    } else { 
        def linhaMaisDois = newLineData();
        def linhaMaisUmMeio = newLineData();
        def linhaMaisUm = newLineData();
        def linhaMaisMeio = newLineData();
        def linhaMenosDois = newLineData();
        def linhaMenosUmMeio = newLineData();
        def linhaMenosUm = newLineData();
        def linhaMenosMeio = newLineData();

        linhaMaisDois.setColor(255, 0, 0);
        linhaMaisUmMeio.setColor(255, 0, 0);
        linhaMaisUm.setColor(255, 0, 0);
        linhaMaisMeio.setColor(255, 0, 0);

        linhaMenosDois.setColor(0, 255, 0);
        linhaMenosUmMeio.setColor(0, 255, 0);
        linhaMenosUm.setColor(0, 255, 0);
        linhaMenosMeio.setColor(0, 255, 0);

        for ( def j = idxIniDia; ( j <= idxFimDia ); j++ ) {
            def data = low.date(j);
            linhaMaisDois.addDated(data, ((fechamento*1.02)).toDouble());
            linhaMaisUmMeio.addDated(data, ((fechamento*1.015)).toDouble());
            linhaMaisUm.addDated(data, ((fechamento*1.01)).toDouble());
            linhaMaisMeio.addDated(data, ((fechamento*1.005)).toDouble());
            linhaMenosDois.addDated(data, (fechamento - (fechamento*0.02)).toDouble());
            linhaMenosUmMeio.addDated(data, (fechamento - (fechamento*0.015)).toDouble());
            linhaMenosUm.addDated(data, (fechamento - (fechamento*0.01)).toDouble());
            linhaMenosMeio.addDated(data, (fechamento - (fechamento*0.005)).toDouble());
        }
        numDias++;

        r.add(linhaMaisDois);
        r.add(linhaMaisUmMeio);
        r.add(linhaMaisUm);
        r.add(linhaMaisMeio);
        r.add(linhaMenosDois);
        r.add(linhaMenosUmMeio);
        r.add(linhaMenosUm);
        r.add(linhaMenosMeio);
        
         if(numDias > NUMERO_DIAS-1) {
            break;
        }

        idxIniDia = i;
        idxFimDia = i;
        diaAtualAux = diaAtual;
        fechamentoDia = fechamento;
    }
        
}

